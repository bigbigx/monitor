package com.lehecai.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.Json;
import com.lehecai.pagemodel.User;
import com.lehecai.service.UserServiceI;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<User> {
	// modelDriven模型驱动来接受前台传过来的相同字段名的字段，虽然比较难理解，但是比较省事。也可用注释的参考视频8那段来获取前台传过来的内容
	// 但是模型驱动必须要求前台传过来的name字段和实例User中的字段内容匹配
	User user = new User();

	@Override
	public User getModel() {
		// action必须的
		return user;
	}

	private static final Logger logger = Logger.getLogger(UserAction.class);
	public UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	/*
	 * public void test() { logger.info("进入action"); // ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext()); // UserServiceI userService = (UserServiceI) ac.getBean("userService"); // userService.test(); // userService.test(); } public void addUser(){ Tuser t = new Tuser(); t.setId(UUID.randomUUID().toString()); t.setName("张三"); t.setPwd("12121"); t.setCreatedatetime(new Date()); userService.save(t); }
	 */
	/**
	 * 老式的获取参数
	 */
	public void reg_test() {
		String name = ServletActionContext.getRequest().getParameter("name");
		String pwd = ServletActionContext.getRequest().getParameter("pwd");
		logger.info("name:" + name + ";pwd" + pwd);
		String json = null;
		try {
			userService.save(name, pwd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(json);// 将数据传输出到前台jsp
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: TODO::
	 * @return void:
	 * @author sunqp
	 */
	public void reg2() {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			userService.save(user.getName(), user.getPwd());
			m.put("success", true);
			m.put("msg", "成功");
		} catch (Exception e1) {
			e1.printStackTrace();
			m.put("success", false);
			m.put("msg", "失败");
		}
		super.writeJson(m);
	}

	public void reg() {
		Json j = new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("成功");
		} catch (Exception e1) {
			j.setMsg("失败");
		}
		super.writeJson(j);
	}

	public void login() {
		User u = userService.login(user);
		Json j = new Json();
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("登录成功");
		} else
			j.setMsg("登录失败,用户名或者密码错误!");
		super.writeJson(j);
	}

	public void datagrid() {
		DataGrid dg = userService.datagrid(user);
		super.writeJson(dg);
	}

	public void addUser() {
		Json j = new Json();
		try {
			User u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("成功");
			j.setObj(u);// 向前台输出user对象
		} catch (Exception e1) {
			j.setMsg("失败");
		}
		super.writeJson(j);
	}

	public void remove() {
		Json j = new Json();
		try {
			userService.remove(user.getIds());
			j.setMsg("成功");
			j.setSuccess(true);
		} catch (Exception e1) {
			j.setMsg("失败");
		}
		super.writeJson(j);
	}
	
	public void edit(){
		Json j = new Json();
		try{
			User u = userService.edit(user);
			j.setSuccess(true);
			j.setMsg("成功");
			j.setObj(u);
		}catch(Exception e){
			j.setMsg("失败");
		}
		super.writeJson(j);
	}
}
