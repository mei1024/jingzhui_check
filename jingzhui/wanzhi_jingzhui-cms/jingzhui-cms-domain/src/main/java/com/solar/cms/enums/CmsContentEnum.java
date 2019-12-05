package com.solar.cms.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 内容枚举
 * 
 * 
 * @author tllen
 *
 */
public class CmsContentEnum {
	
	
	/**
	 * 
	 * 内容状态
	 * 
	 * 状态：0、草稿 1、已发布 2、待审核
	 * 
	 * @author tllen
	 *
	 */
	public enum Status {
		
		DRAFT("0", "草稿"),
		
		PUBLISHED("1", "已发布"),

		WAIT_AUDIT("2", "待发布");
		
	
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
