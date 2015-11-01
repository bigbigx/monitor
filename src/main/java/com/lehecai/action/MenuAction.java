package com.lehecai.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.lehecai.pagemodel.Menu;
import com.lehecai.service.MenuServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu>{

	private Menu menu = new Menu();

	private MenuServiceI menuService;
	public MenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * 异步加载获取节点
	 * @Description: TODO::
	 * @return void:  
	 * @author sunqp
	 */
	public void getTreeNode(){
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}
	
	/**
	 * 一次性获取节点
	 * @Description: TODO::
	 * @return void:  
	 * @author sunqp
	 */
	public void getAllTreeNode(){
		super.writeJson(menuService.getAllTreeNode());
	}
	@Override
	public Menu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
}
