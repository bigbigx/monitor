package com.lehecai.service;

import java.util.List;
import com.lehecai.pagemodel.Menu;

public interface MenuServiceI {
	public List<Menu> getTreeNode(String id);
	public List<Menu> getAllTreeNode();
}
