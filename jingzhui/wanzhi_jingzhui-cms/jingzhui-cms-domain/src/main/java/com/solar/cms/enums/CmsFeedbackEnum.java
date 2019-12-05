package com.solar.cms.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 意见反馈枚举
 * 
 * 
 * @author tllen
 *
 */
public class CmsFeedbackEnum {
	
	
	/**
	 * 
	 * 内容状态
	 * 
	 * 状态 READY=未处理 PROCESSED=已处理
	 * 
	 * @author tllen
	 *
	 */
	public enum Status {
		
		READY("READY", "未处理"),
		
		PROCESSED("PROCESSED", "已处理")

		;
		
	
		private String code;

		private String text;

		public String getCode() {
			return code;
		}

		public String getText() {
			return text;
		}

		private Status(String code, String text) {
			this.code = code;
			this.text = text;
		}

		public static Status get(String code) {
			if (StringUtils.isEmpty(code)) {
				return null;
			}
			for (Status status : values()) {
				if (status.getCode().equalsIgnoreCase(code)) {
					return status;
				}
			}
			return null;
		}
	}
	 
	
}
