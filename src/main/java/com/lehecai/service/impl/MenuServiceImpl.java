package com.lehecai.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lehecai.dao.MenuDaoI;
import com.lehecai.model.Tmenu;
import com.lehecai.pagemodel.Menu;
import com.lehecai.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {

	private MenuDaoI menuDao = null;

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> getTreeNode(String id) {
		List<Menu> lm = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (id != null) {
			// 查询所有根节点
			hql = "from Tmenu t where t.tmenu.id = :id";
			params.put("id", id);
		} else {
			// 异步加载当前id的子节点
			hql = "from Tmenu t where t.tmenu is null";
		}

		List<Tmenu> lt = menuDao.find(hql, params);

		if (lt != null && lt.size() > 0) {
			for (Tmenu t : lt) {
				// 进行了一个模型转换将tmenu转成menu，原因是tmenu有一个Set<Tmenu> tmenus，在super.writeJson返回前台时，会一直做完数据库相关查询
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				if (t.getTmenus() != null && !t.getTmenus().isEmpty()) {
					m.setState("closed");// 节点以文件夹方式打开
				} else {
					m.setState("open");// 节点以文件方式打开
				}
				lm.add(m);
			}
		}
		return lm;
	}

	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> lm = new ArrayList<Menu>();
		String hql = "from Tmenu t";
		List<Tmenu> lt = menuDao.find(hql);

		if (lt != null && lt.size() > 0) {
			for (Tmenu t : lt) {
				// 进行了一个模型转换将tmenu转成menu，原因是tmenu有一个Set<Tmenu> tmenus，在super.writeJson返回前台时，会一直做完数据库相关查询
				Menu m = new Menu();
				Map<String,Object> attributes = new HashMap<String,Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				BeanUtils.copyProperties(t, m);
				Tmenu tm = t.getTmenu();
				if (tm!=null) {
					m.setPid(tm.getId());
				}
				lm.add(m);
			}
		}
		return lm;
	}
}
