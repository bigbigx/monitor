package com.lehecai.service;

import java.io.Serializable;

import com.lehecai.model.Tuser;
import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.User;

public interface UserServiceI {
	
	public void test();
	
	public Serializable save(Tuser t);
	
	public void save(String name,String pwd);
	
	public User save(User user);
	
	public User login(User user);
	
	public DataGrid datagrid(User user);
	
	public void remove(String ids);

	public User edit(User user);
	
}
