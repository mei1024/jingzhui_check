package com.solar.cms.enums;

/**
 * 
 * CMS消息码
 * 
 * 业务错误定义: 以 “0”开头 (业务系统2位) + (消息码5位)
 * 
 * 系统错误定义： 以 “1”开头 (业务系统2位) + (消息码5位)
 * 
 * 
 * @author tllen
 * 
 * @version CmsResultCodeEnum.java 2015年10月4日-下午2:16:01
 */
public enum CmsResultCodeEnum {

	EXPERT_NAME_EXISTS("00600001", "专家名称已存在"),
	
	ORIGINAL_PASSWORD_ERROR("00200003", "原始密码输入有误"),
	;

	/** 枚举编号 */
	private String code;

	/** 枚举详情 */
	private String message;

	private CmsResultCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
