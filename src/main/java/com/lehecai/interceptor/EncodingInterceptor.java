package com.lehecai.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 此方法用于拦截乱码字符串的，但是目前用不着了。
 * @Description: TODO:
 * ClassName: EncodingInterceptor 
 * @author sunqp
 * @date 2015-1-29
 */
public class EncodingInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		return actionInvocation.invoke();
	}

}
