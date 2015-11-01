package com.lehecai.pagemodel;

import java.util.Date;

import com.lehecai.util.DateUtil;

public class OperateLogModel implements java.io.Serializable{
	private String id;
	private String logType;//操作类型,split,allot,send,check
	private String lotteryTypePara;//彩票类型单元参数
	private Integer allPlanTotal;//总票数
	private Integer allPlanSucessCount;//成功的总票数
	private Integer allTicketTotalCount;//成功的总票数
	private Integer allCostTime;//所用总时间
	private Integer timeType;//统计分析单位，时间类型。
	private Integer recordNum;//记录条数 
	private String phasePara;//阶段单元参数
	private String ticketBatchIdPara;//批次号的单元参数
	private String terminalIdPara;//终端号的单元参数
	private Integer valid ;//是否有效，1-有效，0-无效
	private Date createdatetime;
	private String filePath;//文件路径
	private Date logTime;//日志文件所记录的时间
	private int averageUseTime;//平均每个方案用时
	private int page;
	private String queryName;//请求字段名
	//===针对详细分析
	private String analyName;//分析字段名
	private Integer analyNum;//分析字段总数量
	private Integer analyAllTime;//分析字段总耗时
	private Integer analyAveTime;//分析字段平均耗时
	private Integer analyTicketCount;//分析字段-票数
	public Integer getAnalyTicketCount() {
		return analyTicketCount;
	}
	public void setAnalyTicketCount(Integer analyTicketCount) {
		this.analyTicketCount = analyTicketCount;
	}
	private String getNameById;//通过id转换成中文名
	public Integer getAllTicketTotalCount() {
		return allTicketTotalCount;
	}
	public void setAllTicketTotalCount(Integer allTicketTotalCount) {
		this.allTicketTotalCount = allTicketTotalCount;
	}
	public String getGetNameById() {
		return getNameById;
	}
	public void setGetNameById(String getNameById) {
		this.getNameById = getNameById;
	}
	private int rows;
	private String order;//desc,asc
	private String sort;
	
	public String getAnalyName() {
		return analyName;
	}
	public void setAnalyName(String analyName) {
		this.analyName = analyName;
	}
	public Integer getAnalyNum() {
		return analyNum;
	}
	public void setAnalyNum(Integer analyNum) {
		this.analyNum = analyNum;
	}
	public Integer getAnalyAllTime() {
		return analyAllTime;
	}
	public void setAnalyAllTime(Integer analyAllTime) {
		this.analyAllTime = analyAllTime;
	}
	public Integer getAnalyAveTime() {
		return analyAveTime;
	}
	public void setAnalyAveTime(Integer analyAveTime) {
		this.analyAveTime = analyAveTime;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public int getAverageUseTime() {
		return averageUseTime;
	}
	public void setAverageUseTime(int averageUseTime) {
		this.averageUseTime = averageUseTime;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLotteryTypePara() {
		return lotteryTypePara;
	}
	public void setLotteryTypePara(String lotteryTypePara) {
		this.lotteryTypePara = lotteryTypePara;
	}
	public Integer getAllPlanTotal() {
		return allPlanTotal;
	}
	public void setAllPlanTotal(Integer allPlanTotal) {
		this.allPlanTotal = allPlanTotal;
	}
	public Integer getAllPlanSucessCount() {
		return allPlanSucessCount;
	}
	public void setAllPlanSucessCount(Integer allPlanSucessCount) {
		this.allPlanSucessCount = allPlanSucessCount;
	}
	public Integer getAllCostTime() {
		return allCostTime;
	}
	public void setAllCostTime(Integer allCostTime) {
		this.allCostTime = allCostTime;
	}
	public Integer getTimeType() {
		return timeType;
	}
	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}
	public Integer getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}
	public String getPhasePara() {
		return phasePara;
	}
	public void setPhasePara(String phasePara) {
		this.phasePara = phasePara;
	}
	public String getTicketBatchIdPara() {
		return ticketBatchIdPara;
	}
	public void setTicketBatchIdPara(String ticketBatchIdPara) {
		this.ticketBatchIdPara = ticketBatchIdPara;
	}
	public String getTerminalIdPara() {
		return terminalIdPara;
	}
	public void setTerminalIdPara(String terminalIdPara) {
		this.terminalIdPara = terminalIdPara;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
