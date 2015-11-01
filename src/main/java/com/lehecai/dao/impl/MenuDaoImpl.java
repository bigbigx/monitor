package com.lehecai.dao.impl;

import org.springframework.stereotype.Repository;

import com.lehecai.dao.MenuDaoI;
import com.lehecai.model.Tmenu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Tmenu> implements MenuDaoI {
	
}
