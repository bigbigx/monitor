package com.lehecai.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.lehecai.conf.LogContent;
import com.lehecai.conf.LotteryType;
import com.lehecai.pagemodel.Menu;

import com.alibaba.fastjson.JSON;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction {
	public void writeJson(Object object){
		try {
			String json = JSON.toJSONStringWithDateFormat(object,"yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	* @Title: getNameById 
	* @Description: TODO 数据字典转换 
	* @param id
	* @param type
	* @return 
	* String
	* @author sunqp
	 */
	public String getNameById(int id,String type){
		String retStr = "";
		if(type.equals(LogContent.lotteryTypePara)){
			retStr = LotteryType.getItem(id).getName();
		}
		return retStr;
	}
}

