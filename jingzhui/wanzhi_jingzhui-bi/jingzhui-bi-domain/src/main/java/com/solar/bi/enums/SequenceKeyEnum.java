package com.solar.bi.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @declaration BI id枚举
 * @author mw
 * @time 2019-12-05 10:42
 */
public enum SequenceKeyEnum {
	
	BI_CHECK_RECORD_ID("BI_CHECK_RECORD_ID", "检查记录ID"),
	
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