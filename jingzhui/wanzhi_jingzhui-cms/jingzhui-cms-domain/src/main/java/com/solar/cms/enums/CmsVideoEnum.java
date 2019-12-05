package com.solar.cms.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 视频枚举
 * 
 * 
 * @author tllen
 *
 */
public class CmsVideoEnum {
	
	
	/**
	 * 
	 * 视频状态
	 * 
	 * 状态：0=处理完成,1=解码处理中
	 * 
	 * @author tllen
	 *
	 */
	public enum VStatus {
		
		NORMAL("0", "正常"),
		
		DECODE("1", "解码处理中"),
		
		;
		
		private String code;

		private String text;

		public String getCode() {
			return code;
		}

		public String getText() {
			return text;
		}

		private VStatus(String code, String text) {
			this.code = code;
			this.text = text;
		}

		public static VStatus get(String code) {
			if (StringUtils.isEmpty(code)) {
				return null;
			}
			for (VStatus status : values()) {
				if (status.getCode().equalsIgnoreCase(code)) {
					return status;
				}
			}
			return null;
		} 
	}
	 
	
}
