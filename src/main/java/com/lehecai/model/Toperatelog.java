package com.lehecai.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Toperatelog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "toperatelog", catalog = "monitor")
public class Toperatelog implements java.io.Serializable {

	// Fields

	private String id;
	private String logType;
	private String lotteryTypePara;
	private Integer allPlanTotal;
	private Integer allPlanSucessCount;
	private Integer allCostTime;
	private Integer allTicketTotalCount;
	private Integer timeType;
	private Integer recordNum;
	private String phasePara;
	private String ticketBatchIdPara;
	private String terminalIdPara;
	private Date logTime;
	private Integer valid;
	private Date createdatetime;

	// Constructors

	/** default constructor */
	public Toperatelog() {
	}

	/** minimal constructor */
	public Toperatelog(String id) {
		this.id = id;
	}

	/** full constructor */
	public Toperatelog(String id, String logType, String lotteryTypePara, Integer allPlanTotal, Integer allPlanSucessCount, Integer allCostTime, Integer allTicketTotalCount, Integer timeType, Integer recordNum, String phasePara, String ticketBatchIdPara, String terminalIdPara, Date logTime, Integer valid, Date createdatetime) {
		this.id = id;
		this.logType = logType;
		this.lotteryTypePara = lotteryTypePara;
		this.allPlanTotal = allPlanTotal;
		this.allPlanSucessCount = allPlanSucessCount;
		this.allCostTime = allCostTime;
		this.allTicketTotalCount = allTicketTotalCount;
		this.timeType = timeType;
		this.recordNum = recordNum;
		this.phasePara = phasePara;
		this.ticketBatchIdPara = ticketBatchIdPara;
		this.terminalIdPara = terminalIdPara;
		this.logTime = logTime;
		this.valid = valid;
		this.createdatetime = createdatetime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "logType", length = 10)
	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Column(name = "lotteryTypePara", length = 65535)
	public String getLotteryTypePara() {
		return this.lotteryTypePara;
	}

	public void setLotteryTypePara(String lotteryTypePara) {
		this.lotteryTypePara = lotteryTypePara;
	}

	@Column(name = "allPlanTotal")
	public Integer getAllPlanTotal() {
		return this.allPlanTotal;
	}

	public void setAllPlanTotal(Integer allPlanTotal) {
		this.allPlanTotal = allPlanTotal;
	}

	@Column(name = "allPlanSucessCount")
	public Integer getAllPlanSucessCount() {
		return this.allPlanSucessCount;
	}

	public void setAllPlanSucessCount(Integer allPlanSucessCount) {
		this.allPlanSucessCount = allPlanSucessCount;
	}

	@Column(name = "allCostTime")
	public Integer getAllCostTime() {
		return this.allCostTime;
	}

	public void setAllCostTime(Integer allCostTime) {
		this.allCostTime = allCostTime;
	}

	@Column(name = "allTicketTotalCount")
	public Integer getAllTicketTotalCount() {
		return this.allTicketTotalCount;
	}

	public void setAllTicketTotalCount(Integer allTicketTotalCount) {
		this.allTicketTotalCount = allTicketTotalCount;
	}

	@Column(name = "timeType")
	public Integer getTimeType() {
		return this.timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	@Column(name = "recordNum")
	public Integer getRecordNum() {
		return this.recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	@Column(name = "phasePara", length = 65535)
	public String getPhasePara() {
		return this.phasePara;
	}

	public void setPhasePara(String phasePara) {
		this.phasePara = phasePara;
	}

	@Column(name = "ticketBatchIdPara", length = 65535)
	public String getTicketBatchIdPara() {
		return this.ticketBatchIdPara;
	}

	public void setTicketBatchIdPara(String ticketBatchIdPara) {
		this.ticketBatchIdPara = ticketBatchIdPara;
	}

	@Column(name = "terminalIdPara", length = 65535)
	public String getTerminalIdPara() {
		return this.terminalIdPara;
	}

	public void setTerminalIdPara(String terminalIdPara) {
		this.terminalIdPara = terminalIdPara;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "logTime", length = 10)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Column(name = "valid")
	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createdatetime", length = 10)
	public Date getCreatedatetime() {
		return this.createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

}