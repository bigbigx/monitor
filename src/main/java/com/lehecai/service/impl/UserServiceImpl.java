package com.lehecai.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Util;

import com.lehecai.dao.UserDaoI;
import com.lehecai.model.Tuser;
import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.User;
import com.lehecai.service.UserServiceI;
import com.lehecai.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserServiceI {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserDaoI userDao;

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	@Override
	public void test() {
		logger.info("开始测试");
	}

	@Override
	public Serializable save(Tuser t) {
		return userDao.save(t);
	}

	@Override
	public void save(String name, String pwd) {
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setName(name);
		t.setPwd(pwd);
		t.setCreatedatetime(new Date());
		t.setModifydatetime(new Date());
		userDao.save(t);
	}

	@Override
	public User save(User user) {
		Tuser t = new Tuser();
		BeanUtils.copyProperties(user, t, new String[] { "pwd" });// spring mvc提供的导入字段值，用于省去如果有大批量的set属性操作,本初抛出pwd字段
		t.setId(UUID.randomUUID().toString());
		t.setCreatedatetime(new Date());
		t.setPwd(Encrypt.e(user.getPwd()));
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public User login(User user) {
		// ===方式1-start====缺点自己ping串，
		// String hql = "from Tuser t where t.name='"+user.getName()+"' and t.pwd='"+Encrypt.e(user.getPwd())+"'";
		// Tuser t = userDao.get(hql);
		// ===方式1-end====
		// ===方式2-start====
		// Tuser t = userDao.get("from Tuser t where t.name = ? and t.pwd = ?", new Object[] { user.getName(), Encrypt.e(user.getPwd()) });
		// ===方式2-end====
		// ===方式3-start===缺点是需要map进行set下
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getName());
		map.put("pwd", Encrypt.e(user.getPwd()));
		Tuser t = userDao.get("from Tuser t where t.name = :name and t.pwd = :pwd", map);
		if (t != null)
			return user;
		else
			return null;
	}

	@Override
	public DataGrid datagrid(User user) {
		DataGrid dg = new DataGrid();
		String hql = "from Tuser t";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhereHql(user, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrderHql(user, hql);
		List<Tuser> lt = userDao.find(hql, params, user.getPage(), user.getRows());
		List<User> lu = new ArrayList<User>();
		changeModel(lt, lu);
		dg.setRows(lu);
		dg.setTotal(userDao.count(totalHql, params));
		return dg;
	}

	private void changeModel(List<Tuser> lt, List<User> lu) {
		if (lt != null && lt.size() > 0) {
			for (Tuser t : lt) {
				User u = new User();
				BeanUtils.copyProperties(t, u);
				lu.add(u);
			}
		}
	}

	private String addOrderHql(User user, String hql) {
		if (user.getOrder() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return hql;
	}

	private String addWhereHql(User user, String hql, Map<String, Object> params) {
		if (user.getName() != null && !user.getName().trim().equals("")) {
			params.put("name", "%%" + user.getName().trim() + "%%");
			hql += " where t.name like :name";
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		/*该方法缺点是，如果要删除多条，可能先去查询多条的对象，性能就降低了
		 * for (String id : ids.split(",")) {
			Tuser u = userDao.get(Tuser.class,id);
			if(u!=null){
				userDao.delete(u);
			}
		}*/
		String []nids = ids.split(",");
		String hql = "delete Tuser t where t.id in (";
		for(int i=0;i<nids.length;i++){
			if(i>0){
				hql += ",";
			}
			hql += "'"+nids[i]+"'";
		}
		hql += ")";
		userDao.executeHql(hql);
	}

	@Override
	public User edit(User user) {
		Tuser t = userDao.get(Tuser.class, user.getId());
		BeanUtils.copyProperties(user, t, new String[]{"id","pwd"});
		return user;
	}
}
