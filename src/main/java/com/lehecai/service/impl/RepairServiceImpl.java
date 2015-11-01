package com.lehecai.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lehecai.dao.BaseDaoI;
import com.lehecai.dao.MenuDaoI;
import com.lehecai.dao.UserDaoI;
import com.lehecai.model.Tmenu;
import com.lehecai.model.Tuser;
import com.lehecai.service.RepairServiceI;
import com.lehecai.util.Encrypt;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {

	private BaseDaoI<Tmenu> menuDao;
	private BaseDaoI<Tuser> userDao;

	
	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Override
	public void repair() {
		repairMenu();
		repairUser();
	}

	public void repairUser() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		Tuser t = userDao.get("from Tuser t where t.name=:name and t.id!='0'", m);
		if (t != null) {
			// 代表违反了一个表中name只能是唯一的原则
			t.setName(UUID.randomUUID().toString());
		}
		Tuser admin = new Tuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(Encrypt.e("admin"));
		admin.setModifydatetime(new Date());
		userDao.saveOrUpdate(admin);
	}

	public void repairMenu() {
		Tmenu root = new Tmenu();
		root.setId("0");
		root.setText("首页");
		root.setUrl("");
		menuDao.saveOrUpdate(root);
		
		Tmenu xtgl = new Tmenu();
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setTmenu(root);
		xtgl.setUrl("/admin/xtgl.jsp");
		menuDao.saveOrUpdate(xtgl);
		
		Tmenu yhgl = new Tmenu();
		yhgl.setId("yhgl");
		yhgl.setText("用户管理");
		yhgl.setTmenu(xtgl);
		yhgl.setUrl("/admin/yhgl.jsp");
		menuDao.saveOrUpdate(yhgl);

		Tmenu rzgl = new Tmenu();
		rzgl.setId("rzgl");
		rzgl.setText("日志管理");
		rzgl.setTmenu(root);
		rzgl.setUrl("/log/rzgl.jsp");
		menuDao.saveOrUpdate(rzgl);
		
		Tmenu splitrzgl = new Tmenu();
		splitrzgl.setId("splitrzgl");
		splitrzgl.setText("拆票日志管理");
		splitrzgl.setTmenu(rzgl);
		splitrzgl.setUrl("/log/splitrzgl.jsp");
		menuDao.saveOrUpdate(splitrzgl);
		
		Tmenu allotrzgl = new Tmenu();
		allotrzgl.setId("allotrzgl");
		allotrzgl.setText("分票日志管理");
		allotrzgl.setTmenu(rzgl);
		allotrzgl.setUrl("/log/allotrzgl.jsp");
		menuDao.saveOrUpdate(allotrzgl);
		
		Tmenu sendrzgl = new Tmenu();
		sendrzgl.setId("sendrzgl");
		sendrzgl.setText("送票日志管理");
		sendrzgl.setTmenu(rzgl);
		sendrzgl.setUrl("/log/sendrzgl.jsp");
		menuDao.saveOrUpdate(sendrzgl);
		
		Tmenu checkrzgl = new Tmenu();
		checkrzgl.setId("checkrzgl");
		checkrzgl.setText("检票日志管理");
		checkrzgl.setTmenu(rzgl);
		checkrzgl.setUrl("/log/checkrzgl.jsp");
		menuDao.saveOrUpdate(checkrzgl);
		
		Tmenu splitloglist = new Tmenu();
		splitloglist.setId("splitloglist");
		splitloglist.setText("拆票日志总列表");
		splitloglist.setTmenu(splitrzgl);
		splitloglist.setUrl("/log/splitloglist.jsp");
		menuDao.saveOrUpdate(splitloglist);
		
		Tmenu allotloglist = new Tmenu();
		allotloglist.setId("allotloglist");
		allotloglist.setText("分票日志总列表");
		allotloglist.setTmenu(allotrzgl);
		allotloglist.setUrl("/log/allotloglist.jsp");
		menuDao.saveOrUpdate(allotloglist);
		
		Tmenu sendloglist = new Tmenu();
		sendloglist.setId("sendloglist");
		sendloglist.setText("送票日志总列表");
		sendloglist.setTmenu(sendrzgl);
		sendloglist.setUrl("/log/sendloglist.jsp");
		menuDao.saveOrUpdate(sendloglist);
		
		Tmenu checkloglist = new Tmenu();
		checkloglist.setId("checkloglist");
		checkloglist.setText("检票日志总列表");
		checkloglist.setTmenu(checkrzgl);
		checkloglist.setUrl("/log/checkloglist.jsp");
		menuDao.saveOrUpdate(checkloglist);
		
		Tmenu splitlogpic = new Tmenu();
		splitlogpic.setId("splitlogpic");
		splitlogpic.setText("拆票日志总监控图");
		splitlogpic.setTmenu(splitrzgl);
		splitlogpic.setUrl("/log/splitlogpic.jsp");
		menuDao.saveOrUpdate(splitlogpic);
		
		Tmenu allotlogpic = new Tmenu();
		allotlogpic.setId("allotlogpic");
		allotlogpic.setText("分票日志总监控图");
		allotlogpic.setTmenu(allotrzgl);
		allotlogpic.setUrl("/log/allotlogpic.jsp");
		menuDao.saveOrUpdate(allotlogpic);
		
		Tmenu sendlogpic = new Tmenu();
		sendlogpic.setId("sendlogpic");
		sendlogpic.setText("送票日志总监控图");
		sendlogpic.setTmenu(sendrzgl);
		sendlogpic.setUrl("/log/sendlogpic.jsp");
		menuDao.saveOrUpdate(sendlogpic);
		
		Tmenu checklogpic = new Tmenu();
		checklogpic.setId("checklogpic");
		checklogpic.setText("检票日志总监控图");
		checklogpic.setTmenu(checkrzgl);
		checklogpic.setUrl("/log/checklogpic.jsp");
		menuDao.saveOrUpdate(checklogpic);
		
		Tmenu splitLogTypePic = new Tmenu();
		splitLogTypePic.setId("splitLogTypePic");
		splitLogTypePic.setText("拆票日志各类型分析图");
		splitLogTypePic.setTmenu(splitrzgl);
		splitLogTypePic.setUrl("/log/splitLogTypePic.jsp");
		menuDao.saveOrUpdate(splitLogTypePic);
		
		Tmenu allotLogTypePic = new Tmenu();
		allotLogTypePic.setId("allotLogTypePic");
		allotLogTypePic.setText("分票日志各类型分析图");
		allotLogTypePic.setTmenu(allotrzgl);
		allotLogTypePic.setUrl("/log/allotLogTypePic.jsp");
		menuDao.saveOrUpdate(allotLogTypePic);
		
		Tmenu sendLogTypePic = new Tmenu();
		sendLogTypePic.setId("sendLogTypePic");
		sendLogTypePic.setText("送票日志各类型分析图");
		sendLogTypePic.setTmenu(sendrzgl);
		sendLogTypePic.setUrl("/log/sendLogTypePic.jsp");
		menuDao.saveOrUpdate(sendLogTypePic);
		
		Tmenu checkLogTypePic = new Tmenu();
		checkLogTypePic.setId("checkLogTypePic");
		checkLogTypePic.setText("检票日志各类型分析图");
		checkLogTypePic.setTmenu(checkrzgl);
		checkLogTypePic.setUrl("/log/checkLogTypePic.jsp");
		menuDao.saveOrUpdate(checkLogTypePic);
		
		Tmenu splitLogDetailList = new Tmenu();
		splitLogDetailList.setId("splitLogDetailList");
		splitLogDetailList.setText("拆票日志各类型详细列表");
		splitLogDetailList.setTmenu(splitrzgl);
		splitLogDetailList.setUrl("/log/splitLogDetailList.jsp");
		menuDao.saveOrUpdate(splitLogDetailList);
		
		Tmenu allotLogDetailList = new Tmenu();
		allotLogDetailList.setId("allotLogDetailList");
		allotLogDetailList.setText("分票日志各类型详细列表");
		allotLogDetailList.setTmenu(allotrzgl);
		allotLogDetailList.setUrl("/log/allotLogDetailList.jsp");
		menuDao.saveOrUpdate(allotLogDetailList);
		
		Tmenu sendLogDetailList = new Tmenu();
		sendLogDetailList.setId("sendLogDetailList");
		sendLogDetailList.setText("送票日志各类型详细列表");
		sendLogDetailList.setTmenu(sendrzgl);
		sendLogDetailList.setUrl("/log/sendLogDetailList.jsp");
		menuDao.saveOrUpdate(sendLogDetailList);
		
		Tmenu checkLogDetailList = new Tmenu();
		checkLogDetailList.setId("checkLogDetailList");
		checkLogDetailList.setText("检票日志各类型详细列表");
		checkLogDetailList.setTmenu(checkrzgl);
		checkLogDetailList.setUrl("/log/checkLogDetailList.jsp");
		menuDao.saveOrUpdate(checkLogDetailList);
		
		Tmenu wjrk = new Tmenu();
		wjrk.setId("wjrk");
		wjrk.setText("文件入库管理");
		wjrk.setTmenu(root);
		wjrk.setUrl("/wjrk/wjrk.jsp");
		menuDao.saveOrUpdate(wjrk);
		
		Tmenu splitlogindb = new Tmenu();
		splitlogindb.setId("splitlogindb");
		splitlogindb.setText("拆票日志入库");
		splitlogindb.setTmenu(wjrk);
		splitlogindb.setUrl("/wjrk/splitlogindb.jsp");
		menuDao.saveOrUpdate(splitlogindb);
		
		Tmenu allotlogindb = new Tmenu();
		allotlogindb.setId("allotlogindb");
		allotlogindb.setText("分票日志入库");
		allotlogindb.setTmenu(wjrk);
		allotlogindb.setUrl("/wjrk/allotlogindb.jsp");
		menuDao.saveOrUpdate(allotlogindb);
		
		Tmenu sendlogindb = new Tmenu();
		sendlogindb.setId("sendlogindb");
		sendlogindb.setText("送票日志入库");
		sendlogindb.setTmenu(wjrk);
		sendlogindb.setUrl("/wjrk/sendlogindb.jsp");
		menuDao.saveOrUpdate(sendlogindb);
		
		Tmenu checklogindb = new Tmenu();
		checklogindb.setId("checklogindb");
		checklogindb.setText("检票日志入库");
		checklogindb.setTmenu(wjrk);
		checklogindb.setUrl("/wjrk/checklogindb.jsp");
		menuDao.saveOrUpdate(checklogindb);
	}

}
