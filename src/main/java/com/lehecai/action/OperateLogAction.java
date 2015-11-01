package com.lehecai.action;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.lehecai.conf.TitleType;
import com.lehecai.pagemodel.DataGrid;
import com.lehecai.pagemodel.Json;
import com.lehecai.pagemodel.OperateLogModel;
import com.lehecai.pagemodel.Pie;
import com.lehecai.service.OperateLogServiceI;
import com.lehecai.util.DateUtil;
import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "operateLogAction")
public class OperateLogAction extends BaseAction implements ModelDriven<OperateLogModel> {

	private OperateLogModel logModel = new OperateLogModel();
	OperateLogModel log = new OperateLogModel();
	private OperateLogServiceI operateLogService;

	public OperateLogServiceI getOperateLogService() {
		return operateLogService;
	}
	@Autowired
	public void setOperateLogService(OperateLogServiceI operateLogService) {
		this.operateLogService = operateLogService;
	}

	@Override
	public OperateLogModel getModel() {
		return logModel;
	}
	
	public void addLog(){
		Json j = new Json();
		String result = operateLogService.save(logModel.getFilePath(),logModel.getLogType(),logModel.getTimeType());
		if(result.equals("SUCESS")){
			j.setSuccess(true);
			j.setMsg("成功");
		}else
		j.setMsg("失败");
		super.writeJson(j);
	}
	public void datagrid(){
		DataGrid dg = operateLogService.datagrid(logModel);
		super.writeJson(dg);
	}
	
	public void datagridWithDetailAnalysis(){
		DataGrid dg = operateLogService.datagridWithDetailAnalysis(logModel);
		super.writeJson(dg);
	}
	
	public void datagrid4Pic(){
		Map<String, Object> m = new HashMap<String, Object>();
		Map data = operateLogService.getOperateLog(logModel);
		String[] logTime = (String[]) data.get("logTime");  
		Integer[] useTime = (Integer[]) data.get("useTime");  
		m.put("logTime", logTime);  
		m.put("useTime", useTime);
		m.put("title",TitleType.PicTitleType_everyPlan);
		super.writeJson(m); 
	}
	public void testDate(){
		System.out.println(DateUtil.formatDate(logModel.getLogTime()));
	}
	public void datagrid4Pie(){
		Map<String,Object> m = operateLogService.getOperateLogByDate(logModel);
		super.writeJson(m);
		/*List<Json> lst = new ArrayList<Json>();
		Json p1 = new Json();
		p1.setName("2024");
		p1.setValue(323);
		Json p2 = new Json();
		p2.setName("2003");
		p2.setValue(341);
		Json p3 = new Json();
		p3.setName("2021");
		p3.setValue(121);
		Json p4 = new Json();
		p4.setName("2011");
		p4.setValue(432);
		Json p5 = new Json();
		p5.setName("2032");
		p5.setValue(775);
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);
		lst.add(p4);
		lst.add(p5);
		Map<String, Object> m = new HashMap<String, Object>();
		String[] logTime = {"2011","2032","2024","2003","2021"};
		m.put("lotteryTypesNames", logTime);
		m.put("lotteryTypePara", lst);
		super.writeJson(m);*/
	}
	public void datagrid4Pie1(){
		List<Pie> lst = new ArrayList<Pie>();
		Pie p1 = new Pie();
		p1.setName("2001");
		p1.setValue(323);
		Pie p2 = new Pie();
		p2.setName("2003");
		p2.setValue(341);
		Pie p3 = new Pie();
		p3.setName("2021");
		p3.setValue(121);
		Pie p4 = new Pie();
		p4.setName("2011");
		p4.setValue(432);
		Pie p5 = new Pie();
		p5.setName("2032");
		p5.setValue(775);
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);
		lst.add(p4);
		lst.add(p5);
		Map<String, Object> m = new HashMap<String, Object>();
		String[] logTime = {"2021","2011","2032","2001","2003"};
		m.put("logTime", logTime);
		m.put("useTime", lst);
		super.writeJson(m);
		/*Map<String, Object> m = new HashMap<String, Object>();
		Map data = operateLogService.getOperateLog(logModel);
		String[] logTime = (String[]) data.get("logTime");  
	    Integer[] useTime = (Integer[]) data.get("useTime");
	    Map<String,String> retMap = new HashMap<String,String>();
	    Map<String,Integer> valueMap = new HashMap<String,Integer>();
	    Map<String,String> nameMap = new HashMap<String,String>();
	    	valueMap.put("value", 335);
	    	valueMap.put("value", 423);
	    	valueMap.put("value", 445);
	    	valueMap.put("value", 535);
	    	valueMap.put("value", 621);
	    	nameMap.put("name", "2001");
	    	nameMap.put("name", "2003");
	    	nameMap.put("name", "2004");
	    	nameMap.put("name", "2002");
	    	nameMap.put("name", "2012");
	    	retMap.put(key, value)
	    m.put("newValue", retMap);
	    m.put("logTime", logTime);  
	    m.put("useTime", useTime);
	    m.put("title",TitleType.PicTitleType_everyPlan);
	    super.writeJson(m); */
	}
	
	public void getDateList(){
		List<OperateLogModel> lst = operateLogService.getDateList(logModel);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("dateValue", lst);
		super.writeJson(m);
	}
	
}
