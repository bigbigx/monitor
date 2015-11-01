package com.lehecai.dao.impl;

import org.springframework.stereotype.Repository;
import com.lehecai.dao.UserDaoI;
import com.lehecai.model.Tuser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Tuser> implements UserDaoI {


}
