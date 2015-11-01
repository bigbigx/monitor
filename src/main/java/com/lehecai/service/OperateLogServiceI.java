package com.lehecai.service;

import java.util.List;
import java.util.Map;

import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.OperateLogModel;

public interface OperateLogServiceI {
	/**
	 * 
	* @Title: save 
	* @Description: TODO
	* @param filePath 文件路径
	* @param logType 日志类型，split，allot，send，check
	* @param timeType 监控日志时间类型，如24h
	* @return String
	* @author sunqp
	 */
	public String save(String filePath,String logType,int timeType);
	
	/**
	 * 
	 * @Title: save 
	 * @Description: TODO
	 * @param logModel
	 * @return 
	 * String
	 * @author sunqp
	 */
	public OperateLogModel save(OperateLogModel logModel);
	
	/**
	 * 
	* @Title: datagrid 
	* @Description: TODO查询数据表单
	* @param logModel
	* @return 
	* DataGrid
	* @author sunqp
	 */
	public DataGrid datagrid(OperateLogModel logModel);
	
	/**
	 * 
	* @Title: getOperateLog 
	* @Description: TODO 展示日志情况
	* @param logModel
	* @return 
	* OperateLogModel
	* @author sunqp
	 */
	public Map<String,Object> getOperateLog(OperateLogModel logModel);

	/**
	 * 
	* @Title: getList 
	* @Description: TODO 查询列数据
	* @param logModel 
	* void
	* @author sunqp
	 */
	public List<OperateLogModel> getDateList(OperateLogModel logModel);
	
	/**
	 * 
	* @Title: getOperateLogByDate 
	* @Description: TODO 通过日期来获取数据
	* @param logModel
	* @return 
	* Map
	* @author sunqp
	 */
	public Map<String,Object> getOperateLogByDate(OperateLogModel logModel);
	
	/**
	 * 
	* @Title: datagridWithLotType 
	* @Description: TODO 按照具体类型查询信息输出
	* @param logModel
	* @return 
	* DataGrid
	* @author sunqp
	 */
	public DataGrid datagridWithDetailAnalysis(OperateLogModel logModel);
}
