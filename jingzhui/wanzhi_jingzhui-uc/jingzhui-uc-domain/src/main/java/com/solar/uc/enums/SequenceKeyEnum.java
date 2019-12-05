package com.solar.uc.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @declaration UC id枚举
 * @author mw
 * @time 2019-12-05 10:39
 */
public enum SequenceKeyEnum {
	
	UC_MEMBER_ID("UC_MEMBER_ID", "会员ID"),
	
	UC_MEMBER_LOG_ID("UC_MEMBER_LOG_ID", "会员日志ID"),
	
	AUTH_THIRD_USER_ID("AUTH_THIRD_USER_ID", "第三方用户ID"),
	
	;

	private final String key;

	private final String text;

	private SequenceKeyEnum(String key, String text) {
		this.key = key;
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public String getText() {
		return text;
	}

	public static SequenceKeyEnum get(String key) {

		for (SequenceKeyEnum status : values()) {
			if (StringUtils.equalsIgnoreCase(status.getKey(), key)) {
				return status;
			}
		}

		return null;
	}

}