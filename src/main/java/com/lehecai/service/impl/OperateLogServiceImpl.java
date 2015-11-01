package com.lehecai.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lehecai.conf.LogContent;
import com.lehecai.conf.LotteryType;
import com.lehecai.conf.ProcessType;
import com.lehecai.conf.TerminalType;
import com.lehecai.dao.OperateLogDaoI;
import com.lehecai.model.Toperatelog;
import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.Json;
import com.lehecai.pagemodel.OperateLogModel;
import com.lehecai.service.OperateLogServiceI;
import com.lehecai.util.DateUtil;
import com.lehecai.util.JsonUtil;
@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogServiceI {
	private static final Logger logger = Logger.getLogger(OperateLogServiceImpl.class);

	private OperateLogDaoI operateLogDao;
	
	public OperateLogDaoI getOperateLogDao() {
		return operateLogDao;
	}
	@Autowired
	public void setOperateLogDao(OperateLogDaoI operateLogDao) {
		this.operateLogDao = operateLogDao;
	}
	@Override
	public String save(String filePath, String logType,int timeType) {
		Toperatelog operatelog = readFile(filePath,logType,timeType);
		if(operatelog!=null && operatelog.getAllCostTime()!=null){
			operatelog.setId(UUID.randomUUID().toString());
			operatelog.setCreatedatetime(new Date());
			operateLogDao.save(operatelog);
			return "SUCESS";
		}
		return "FALSE";
	}
	
	public Toperatelog readFile(String filePath, String logType,int timeType){
		Toperatelog operatelog = new Toperatelog();
//		OperateLogModel logModel = new OperateLogModel();
		FileInputStream fis = null;
		BufferedReader reader = null;
		try {
			File f = new File(filePath);
			if(!f.exists()){
				return operatelog;
			}
			fis = new FileInputStream(new File(filePath));
			reader = new BufferedReader(new InputStreamReader(fis,"UTF-8"),5*1024*1024);
			if(ProcessType.operate_split.equals(logType)){
				operatelog = dealSplitLog(logType,operatelog, reader, timeType);
			}else if(ProcessType.operate_allot.equals(logType)){
				operatelog = dealAllotLog(logType,operatelog, reader, timeType);
			}else if(ProcessType.operate_send.equals(logType)){
				operatelog = dealSendLog(logType,operatelog, reader, timeType);
			}else if(ProcessType.operate_check.equals(logType)){
				operatelog = dealCheckLog(logType,operatelog, reader, timeType);
			}
			reader.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} /*catch (JSONException e) {
			logger.error("JSON转换异常，文件名"+filePath+"，异常为"+e);
			return null;
		}*/
		return operatelog;
	}
	
	private Toperatelog dealAllotLog(String logType, Toperatelog operatelog, BufferedReader reader, int timeType) throws IOException {
		String temp="";
		String lotteryType = "";
		int tiketCount = 0;
//		Map<Integer,Integer> lotMap = new HashMap<Integer,Integer>();
		JSONObject lotteryTypeJson = new JSONObject();
		boolean flag = true;
		int recordNum = 0;
		int _costTime = 0;
		while((temp = reader.readLine()) != null){
			recordNum++;
			if(flag){
				String dateTemp = temp.substring(0,10);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					operatelog.setLogTime(sdf.parse(dateTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag = false;
			}
			temp = temp.substring(temp.indexOf("{"), temp.lastIndexOf("}")+1);
			JSONObject myJsonObject = JSONObject.fromObject(temp);
			if(!temp.contains(LogContent.logType_allot)){
				return null;
			}
			if(temp.contains(LogContent.lotteryType)){
				lotteryType = myJsonObject.getInt(LogContent.lotteryType)+"";
				if(lotteryTypeJson.has(lotteryType)){
					String _value = lotteryTypeJson.getString(lotteryType);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					lotteryTypeJson.put(lotteryType, "{"+(++aNum)+","+_constTimeValue+"}");
				}else
					lotteryTypeJson.put(lotteryType, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
				/*lotteryType = myJsonObject.getInt(LogContent.lotteryType)+"";
				if(lotteryTypeJson.has(lotteryType)){
					int aNum = lotteryTypeJson.getInt(lotteryType);
					lotteryTypeJson.put(lotteryType, ++aNum);
				}else{
					lotteryTypeJson.put(lotteryType, 1);
				}*/
			}
			if(temp.contains(LogContent.tiketCount)){
				tiketCount += myJsonObject.getInt(LogContent.tiketCount);
			}
			if(temp.contains(LogContent.costTime)){
				_costTime += myJsonObject.getInt(LogContent.costTime);
			}
		}
		operatelog.setRecordNum(recordNum);
		operatelog.setLotteryTypePara(lotteryTypeJson.toString());
		operatelog.setAllPlanTotal(tiketCount);
//		operatelog.setAllPlanSucessCount(planSuccessCount);
		operatelog.setAllCostTime(_costTime);
		operatelog.setLogType(LogContent.logType_allot);
		operatelog.setValid(1);
		operatelog.setTimeType(timeType);
		return operatelog;
	}
	private Toperatelog dealCheckLog(String logType, Toperatelog operatelog, BufferedReader reader,int timeType) throws IOException {
		String temp="";
		String _lotteryType = "";
		String _terminalId = "";
		int _tiketTotalCount = 0;
//		Map<Integer,Integer> lotMap = new HashMap<Integer,Integer>();
//		Map<Integer,Integer> terminalIdMap = new HashMap<Integer,Integer>();
		JSONObject lotteryTypeJson = new JSONObject();
		JSONObject terminalIdJson = new JSONObject();
		
		boolean flag = true;
		int _costTime = 0;
		int recordNum = 0;
		while((temp = reader.readLine()) != null){
			recordNum++;
			if(flag){
				String dateTemp = temp.substring(0,10);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					operatelog.setLogTime(sdf.parse(dateTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag = false;
			}
			temp = temp.substring(temp.indexOf("{"), temp.lastIndexOf("}")+1);
			JSONObject myJsonObject = JSONObject.fromObject(temp);
			if(!temp.contains(LogContent.logType_check)){
				return null;
			}
			if(temp.contains(LogContent.terminalId)){
				_terminalId = myJsonObject.getInt(LogContent.terminalId)+"";
				if(terminalIdJson.has(_terminalId)){
					String _value = terminalIdJson.getString(_terminalId);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					terminalIdJson.put(_terminalId, "{"+(++aNum)+","+_constTimeValue+"}");
				}else
					terminalIdJson.put(_terminalId, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
			}
			/*if(temp.contains(LogContent.terminalId)){
				_terminalId = myJsonObject.getInt(LogContent.terminalId)+"";
				if(terminalIdJson.has(_terminalId)){
					int aNum = terminalIdJson.getInt(_terminalId);
					terminalIdJson.put(_terminalId,++aNum);
				}else
					terminalIdJson.put(_terminalId, 1);
			}*/
			if(temp.contains(LogContent.lotteryType)){
				_lotteryType = myJsonObject.getInt(LogContent.lotteryType)+"";
				if(lotteryTypeJson.has(_lotteryType)){
					String _value = lotteryTypeJson.getString(_lotteryType);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					lotteryTypeJson.put(_lotteryType, "{"+(++aNum)+","+_constTimeValue+"}");
				}else{
					lotteryTypeJson.put(_lotteryType, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
				}
			}
			if(temp.contains(LogContent.tiketTotalCount)){
				_tiketTotalCount += myJsonObject.getInt(LogContent.tiketTotalCount);
			}
			if(temp.contains(LogContent.costTime)){
				_costTime += myJsonObject.getInt(LogContent.costTime);
			}
		}
		operatelog.setRecordNum(recordNum);
		operatelog.setLogType(LogContent.logType_check);
		operatelog.setTerminalIdPara(terminalIdJson.toString());
		operatelog.setLotteryTypePara(lotteryTypeJson.toString());
		operatelog.setAllPlanTotal(_tiketTotalCount);
		operatelog.setAllCostTime(_costTime);
		operatelog.setValid(1);
		operatelog.setTimeType(timeType);
		return operatelog;
	}
	private Toperatelog dealSendLog(String logType, Toperatelog operatelog, BufferedReader reader, int timeType) throws IOException{
		String temp="";
		String _lotteryType = "";
		String _terminalId = "" ;
		String _phase = "";
		String _ticketBatchId = "";
	/*	Map<Integer,Integer> lotMap = new HashMap<Integer,Integer>();
		Map<Integer,Integer> terminalIdMap = new HashMap<Integer,Integer>();
		Map<Integer,Integer> phaseMap = new HashMap<Integer,Integer>();
		Map<Long,Integer> ticketBatchIdMap = new HashMap<Long,Integer>();*/
		JSONObject lotteryTypeJson = new JSONObject();
		JSONObject terminalIdJson = new JSONObject();
		JSONObject phaseJson = new JSONObject();
		JSONObject ticketBatchIdJson = new JSONObject();
		boolean flag = true;
		int recordNum = 0;
		int _costTime = 0;
		while((temp = reader.readLine()) != null){
			recordNum++;
			if(flag){
				String dateTemp = temp.substring(0,10);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					operatelog.setLogTime(sdf.parse(dateTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag = false;
			}
			temp = temp.substring(temp.indexOf("{"), temp.lastIndexOf("}")+1);
			JSONObject myJsonObject = JSONObject.fromObject(temp);
			if(!temp.contains(LogContent.logType_send)){
				return null;
			}
			/*if(temp.contains(LogContent.terminalId)){
				_terminalId = myJsonObject.getInt(LogContent.terminalId)+"";
				if(terminalIdJson.has(_terminalId)){
					int aNum = terminalIdJson.getInt(_terminalId);
					terminalIdJson.put(_terminalId,++aNum);
				}else
					terminalIdJson.put(_terminalId, 1);
			}*/
			if(temp.contains(LogContent.terminalId)){
				_terminalId = myJsonObject.getInt(LogContent.terminalId)+"";
				if(terminalIdJson.has(_terminalId)){
					String _value = terminalIdJson.getString(_terminalId);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					terminalIdJson.put(_terminalId,"{"+(++aNum)+","+_constTimeValue+"}");
				}else
					terminalIdJson.put(_terminalId, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
			}
			if(temp.contains(LogContent.lotteryType)){
				_lotteryType = myJsonObject.getInt(LogContent.lotteryType)+"";
				if(lotteryTypeJson.has(_lotteryType)){
					String _value = lotteryTypeJson.getString(_lotteryType);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					lotteryTypeJson.put(_lotteryType, "{"+(++aNum)+","+_constTimeValue+"}");
				}else{
					lotteryTypeJson.put(_lotteryType, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
				}
			}
			if(temp.contains(LogContent.phase)){
				_phase = myJsonObject.getInt(LogContent.phase)+"";
				if(phaseJson.has(_phase)){
					String _value = phaseJson.getString(_phase);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					phaseJson.put(_phase, "{"+(++aNum)+","+_constTimeValue+"}");
				}else
					phaseJson.put(_phase, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
			}
			if(temp.contains(LogContent.ticketBatchId)){
				_ticketBatchId = myJsonObject.getString(LogContent.ticketBatchId)+"";
				if(ticketBatchIdJson.has(_ticketBatchId)){
					String _value = ticketBatchIdJson.getString(_ticketBatchId);
					int aNum = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
					long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);
					ticketBatchIdJson.put(_ticketBatchId, "{"+(++aNum)+","+_constTimeValue+"}");
				}else
					ticketBatchIdJson.put(_ticketBatchId, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
			}
			if(temp.contains(LogContent.costTime)){
				_costTime += myJsonObject.getInt(LogContent.costTime);
			}
		}
		operatelog.setRecordNum(recordNum);
		operatelog.setLogType(LogContent.logType_send);
		operatelog.setTerminalIdPara(terminalIdJson.toString());
		operatelog.setLotteryTypePara(lotteryTypeJson.toString());
		operatelog.setPhasePara(phaseJson.toString());
		operatelog.setTicketBatchIdPara(ticketBatchIdJson.toString());
		operatelog.setAllCostTime(_costTime);
		operatelog.setValid(1);
		operatelog.setTimeType(timeType);
		return operatelog;
	}
	private Toperatelog dealSplitLog(String logType, Toperatelog operatelog, BufferedReader reader,int timeType) throws IOException{
		String temp="";
		String lotteryType = "";
//		Map<Integer,Integer> lotMap = new HashMap<Integer,Integer>();
		boolean flag = true;
		int recordNum = 0;
		int _planTotal = 0;
		int _planSuccessCount = 0;
		int _costTime = 0;
		int _lotCostTime = 0;//记录各彩种所用时间
		JSONObject lotteryTypeJson = new JSONObject();
		while((temp = reader.readLine()) != null){
			int thisPlanTotale = 0;//本行记录方案数
			int thisTicketTotalCount = 0;//本行记录票数
			int thisPlanSuccessCount = 0;//本行记录成功方案数
			int thisTime = 0;//本行记录时间
			recordNum++;
			if(flag){
				String dateTemp = temp.substring(0,10);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					operatelog.setLogTime(sdf.parse(dateTemp));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag = false;
			}
			temp = temp.substring(temp.indexOf("{"), temp.lastIndexOf("}")+1);
			JSONObject myJsonObject = JSONObject.fromObject(temp);
			if(!temp.contains(LogContent.logType_split)){
				return null;
			}
			if(temp.contains(LogContent.costTime)){
				thisTime = myJsonObject.getInt(LogContent.costTime);
				_costTime += thisTime;
			}
			
			if(temp.contains(LogContent.planTotal)){
				thisPlanTotale = myJsonObject.getInt(LogContent.planTotal);
				_planTotal += thisPlanTotale;
			}
			if(temp.contains(LogContent.planSuccessCount)){
				thisPlanSuccessCount = myJsonObject.getInt(LogContent.planSuccessCount);
				_planSuccessCount += thisPlanSuccessCount;
			}
			if(temp.contains(LogContent.ticketTotalCount)){
				thisTicketTotalCount = myJsonObject.getInt(LogContent.ticketTotalCount);
				_planSuccessCount += thisTicketTotalCount;
			}
			if(temp.contains(LogContent.lotteryType)){
				lotteryType = myJsonObject.getInt(LogContent.lotteryType)+"";
				StringBuilder num = new StringBuilder(LogContent.lotteryTypePara_num+":");
				StringBuilder pc = new StringBuilder(LogContent.lotteryTypePara_planTotal+":");
				StringBuilder psc = new StringBuilder(LogContent.lotteryTypePara_planSuccessTotal+":");
				StringBuilder tc = new StringBuilder(LogContent.lotteryTypePara_ticketTotalCount+":");
				StringBuilder time = new StringBuilder(LogContent.lotteryTypePara_time+":");
				if(lotteryTypeJson.has(lotteryType)){
					String _value = lotteryTypeJson.getString(lotteryType);
					JSONObject _lotteryJson = JSONObject.fromObject(_value);
					num.append(_lotteryJson.getInt(LogContent.lotteryTypePara_num)+1);
					pc.append(_lotteryJson.getInt(LogContent.lotteryTypePara_planTotal)+thisPlanTotale);
					psc.append(_lotteryJson.getInt(LogContent.lotteryTypePara_planSuccessTotal)+thisPlanSuccessCount);
					tc.append(_lotteryJson.getInt(LogContent.lotteryTypePara_ticketTotalCount)+thisTicketTotalCount);
					time.append(_lotteryJson.getLong(LogContent.lotteryTypePara_time)+thisTime);
					
					/*long _constTimeValue = Long.parseLong(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
					_constTimeValue += myJsonObject.getInt(LogContent.costTime);*/
					lotteryTypeJson.put(lotteryType, "{"+num+","+pc+","+psc+","+tc+","+time+"}");
				}else{
					num.append(1);//记录行数
					pc.append(thisPlanTotale);
					psc.append(thisPlanSuccessCount);
					tc.append(thisTicketTotalCount);
					time.append(thisTime);
					lotteryTypeJson.put(lotteryType, "{"+num+","+pc+","+psc+","+tc+","+time+"}");
//					lotteryTypeJson.put(lotteryType, "{1,"+myJsonObject.getInt(LogContent.costTime)+"}");
				}
			}
		}
		operatelog.setRecordNum(recordNum);
		operatelog.setLotteryTypePara(lotteryTypeJson.toString());
		operatelog.setAllPlanTotal(_planTotal);
		operatelog.setAllPlanSucessCount(_planSuccessCount);
		operatelog.setAllCostTime(_costTime);
		operatelog.setLogType(LogContent.logType_split);
		operatelog.setValid(1);
		operatelog.setTimeType(timeType);
		return operatelog;
	}
	
	@Override
	public OperateLogModel save(OperateLogModel logModel) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DataGrid datagrid(OperateLogModel logModel) {
		DataGrid dg = new DataGrid();
		String hql = "from Toperatelog t";
		Map<String,Object> params = new HashMap<String,Object>();
		hql = addWhereHql(logModel, hql, params);
		String totalHql = "select count(*) "+hql;
		hql = addOrderHql(logModel, hql);
		List<Toperatelog> lt = operateLogDao.find(hql, params, logModel.getPage(), logModel.getRows());
		List<OperateLogModel> lu = new ArrayList<OperateLogModel>();
		changeModel(lt,lu);
		sortByBeanComparator(lu,"logTime");
		dg.setRows(lu);
		dg.setTotal(operateLogDao.count(totalHql, params));
		return dg;
	}
	private String addWhereHql(OperateLogModel logModel, String hql, Map<String, Object> params) {
		if(logModel.getLogType()!=null && !logModel.getLogType().trim().equals("")){
			params.put("logType", "%%"+logModel.getLogType().trim()+"%%");
			hql += " where t.logType like :logType";
		}
		/*if(logModel.getLogTime()!=null && !logModel.getLogType().trim().equals("")){
			params.put("logTime", "%%"+logModel.getLogTime()+"%%");
			hql += " where t.logType like :logTime";
		}*/
		return hql;
	}
	private String addWhereWithEqualHql(OperateLogModel logModel, String hql) {
		if(logModel.getLogTime()!=null && !logModel.getLogType().trim().equals("")){
			hql += " and t.logTime = '"+DateUtil.formatDate(logModel.getLogTime())+"'";
		}
		if(logModel.getTimeType()!=null){
			hql += " and t.timeType = "+logModel.getTimeType();
		}
		if(logModel.getLogType()!=null){
			hql += " and t.logType = '"+logModel.getLogType()+"'";
		}
		return hql;
	}
	private String addOrderHql(OperateLogModel logModel, String hql) {
		if(logModel.getOrder()!=null){
			hql += " order by "+logModel.getSort()+" "+ logModel.getOrder();
		}
		return hql;
	}
	
	private void changeModel(List<Toperatelog> lt, List<OperateLogModel> lu) {
		if (lt != null && lt.size() > 0) {
			for (Toperatelog t : lt) {
				OperateLogModel u = new OperateLogModel();
				BeanUtils.copyProperties(t, u);
				u.setAverageUseTime(t.getAllCostTime()/t.getRecordNum());
				lu.add(u);
			}
		}
	}
	@Override
	public Map getOperateLog(OperateLogModel logModel) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Toperatelog t where t.logType = '"+logModel.getLogType()+"' and t.timeType = "+logModel.getTimeType()+" and valid = "+logModel.getValid();
		/*Map<String,Object> params = new HashMap<String,Object>();
		hql = addWhereHql(logModel, hql, params);*/
		List<Toperatelog> lt = operateLogDao.find(hql);
		if(lt != null && lt.size()>0){
			Integer[] useTime = new Integer[lt.size()];//每条记录所用时间
			String[] logTime = new String[lt.size()];
			for(int i = 0;i<lt.size();i++){
				OperateLogModel o = new OperateLogModel();
				Toperatelog t = lt.get(i);
//				BeanUtils.copyProperties(t, o);//将t的值赋给o
				useTime[i] = t.getAllCostTime()/t.getRecordNum();
				logTime[i] = t.getLogTime().toString();
			}
			map.put("useTime", useTime);
			map.put("logTime", logTime);
		}
		return map;
	}
	
	@Override
	public List<OperateLogModel> getDateList(OperateLogModel logModel) {
		String hql = "";
		if(logModel.getValid()!=null)
			hql = " from Toperatelog t where t.valid = "+logModel.getValid();
		else
			hql = " from Toperatelog t where t.valid = 1";
		hql = addWhereWithEqualHql(logModel, hql);
		hql = addOrderHql(logModel, hql);
		List<OperateLogModel> lm = new ArrayList<OperateLogModel>();
		List<Toperatelog> lt = operateLogDao.find(hql);
		if (lt != null && lt.size() > 0) {
			for (Toperatelog t : lt) {
				OperateLogModel m = new OperateLogModel();
//				BeanUtils.copyProperties(t, m);
				m.setLogTime(t.getLogTime());
				lm.add(m);
			}
		}
		return lm;
	}
	@Override
	public Map<String,Object> getOperateLogByDate(OperateLogModel logModel) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Toperatelog t where t.logType = '"+logModel.getLogType()+"' and t.timeType = "+logModel.getTimeType()+" and valid = "+logModel.getValid();
		hql = addWhereWithEqualHql(logModel, hql);
		List<Toperatelog> lt = operateLogDao.find(hql);
		if(lt != null && lt.size()>0){
			Toperatelog t = lt.get(0);
			OperateLogModel o = new OperateLogModel();
			BeanUtils.copyProperties(t, o);//将t的值赋给o
			
			map.put("lotteryTypePara", getPoJoFromPara(o.getLotteryTypePara()));
			JSONArray lotteryType = null;
			lotteryType = JSONObject.fromObject(o.getLotteryTypePara()).names();
			Object[] lotteryTypesNames = lotteryType.toArray();
			int length = lotteryTypesNames.length;
			for(int i = 0;i<length;i++){
				lotteryTypesNames[i]=LotteryType.getItem(Integer.parseInt(lotteryTypesNames[i].toString())).getName();
			}
			map.put("lotteryTypesNames", lotteryTypesNames);
		}
		return map;
	}
	//从json格式的单元参数里获取list对象
	public List getPoJoFromPara(String jsonString){
		List<Json> lst = new ArrayList<Json>();
		try {
			Map map = JsonUtil.getMap4Json(jsonString);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				Json j = new Json();
				String key = it.next().toString();
				j.setName(LotteryType.getItem(Integer.parseInt(key)).getName());
				String _value = map.get(key).toString();
				j.setValue(Integer.parseInt(_value.substring(1, _value.indexOf(","))));
				lst.add(j);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
	
	@Override
	public DataGrid datagridWithDetailAnalysis(OperateLogModel logModel) {
		DataGrid dg = new DataGrid();
		String hql = "";
		String frontHql = "";
		if(logModel.getQueryName()!=null){
			frontHql = "select "+logModel.getQueryName();
		}
		String totalHql = "";
		List<Toperatelog> lt = null;
		
		if(logModel.getValid()!=null)
			hql += " from Toperatelog t where t.valid = "+logModel.getValid();
		else
			hql += " from Toperatelog t where t.valid = 1";
		
		hql = addWhereWithEqualHql(logModel, hql);
		totalHql = "select count(*) "+hql;
		hql = addOrderHql(logModel, hql);
		lt = operateLogDao.find(frontHql+hql, logModel.getPage(), logModel.getRows());
		List<OperateLogModel> lu = new ArrayList<OperateLogModel>();
		if (lt != null && lt.size() > 0) {
//			for (Toperatelog t : lt) {
				String _Para = lt.get(0)+"";
				if(logModel.getLogType().equals("split")){
					lu = getSplitLotteryTypePara(_Para,logModel,lu);
				}else{
					try {
						Map map = JsonUtil.getMap4Json(_Para);
						Iterator it = map.keySet().iterator();
						while(it.hasNext()){
							OperateLogModel u = new OperateLogModel();
							String key = it.next().toString();
							String _value = map.get(key).toString();
							int _Num = Integer.parseInt(_value.substring(1, _value.indexOf(",")));
							int _allTime = Integer.parseInt(_value.substring(_value.indexOf(",")+1,_value.indexOf("}")));
							if(logModel.getQueryName().equals(LogContent.lotteryTypePara)){
								u.setAnalyName(LotteryType.getItem(Integer.parseInt(key)).getName());
							}else if(logModel.getQueryName().equals(LogContent.terminalIdPara)){
								u.setAnalyName(TerminalType.getItem(Integer.parseInt(key)).getName());
							}else
								u.setAnalyName(key);
							u.setAnalyNum(_Num);//彩种相关操作数
							u.setAnalyAllTime(_allTime);
							u.setAnalyAveTime(_allTime/_Num);
							lu.add(u);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
//			}
		}
		sortByBeanComparator(lu,"analyAveTime");
		dg.setRows(lu);
		dg.setTotal(operateLogDao.count(totalHql));
		return dg;
	}
	public List<OperateLogModel> getSplitLotteryTypePara(String _Para,OperateLogModel logModel,List<OperateLogModel> lu){
		JSONObject json = JSONObject.fromObject(_Para);
		Iterator<String> keys=json.keys();
		while(keys.hasNext()){
			String key = keys.next().toString();
			String _value = json.get(key).toString();
			JSONObject _valueJson = JSONObject.fromObject(_value);
			OperateLogModel u = new OperateLogModel();
			int _Num = _valueJson.getInt(LogContent.lotteryTypePara_num);
			int _planTotal = _valueJson.getInt(LogContent.lotteryTypePara_planTotal);
			int _planSuccessTotal = _valueJson.getInt(LogContent.lotteryTypePara_planSuccessTotal);
			int _ticketTotalCount = _valueJson.getInt(LogContent.lotteryTypePara_ticketTotalCount);
			int _time = _valueJson.getInt(LogContent.lotteryTypePara_time);
			if(logModel.getQueryName().equals(LogContent.lotteryTypePara)){
				u.setAnalyName(LotteryType.getItem(Integer.parseInt(key)).getName());
			}else if(logModel.getQueryName().equals(LogContent.terminalIdPara)){
				u.setAnalyName(TerminalType.getItem(Integer.parseInt(key)).getName());
			}else
				u.setAnalyName(key);
			u.setAnalyNum(_Num);
			u.setAnalyAllTime(_time);
			u.setAnalyAveTime(_time/_Num);
			u.setAnalyTicketCount(_ticketTotalCount);//彩种票数
			lu.add(u);
		}
		return lu;
	}
	public static List<OperateLogModel> sortByBeanComparator(List<OperateLogModel> list,String sortName){
		Comparator comparator = ComparableComparator.getInstance();
	    comparator = ComparatorUtils.nullLowComparator(comparator);   
	    comparator = ComparatorUtils.reversedComparator(comparator);
	    List<BeanComparator> comparas = new ArrayList<BeanComparator>();
	    comparas.add(new BeanComparator(sortName, comparator));
	    ComparatorChain comparatorChain = new ComparatorChain(comparas);
	    Collections.sort(list,comparatorChain);
	    return list;
	}
}
