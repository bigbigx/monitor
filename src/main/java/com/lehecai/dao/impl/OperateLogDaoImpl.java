package com.lehecai.dao.impl;
import org.springframework.stereotype.Repository;

import com.lehecai.dao.OperateLogDaoI;
import com.lehecai.model.Toperatelog;
@Repository("operateLogDao")
public class OperateLogDaoImpl extends BaseDaoImpl<Toperatelog> implements OperateLogDaoI {

}
