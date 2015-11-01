package com.lehecai.enums;

public enum CodeEnum {
	success( 0, "成功"),
	error( 1, "系统异常"),
	sign_error( 2, "非法数据"),
	parameter_error(3, "参数校验失败"),
	db_error(4, "数据库异常"),
	state_error(5, "业务状态异常"),	
	login_error(6, "登陆异常");
	
    private int code;
    private String desc;
    
    private CodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public static String getDescByCode(Short code){
        for(CodeEnum refer : CodeEnum.values())
            if(code==refer.getCode())
                return refer.getDesc();
        return null;
    }

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
