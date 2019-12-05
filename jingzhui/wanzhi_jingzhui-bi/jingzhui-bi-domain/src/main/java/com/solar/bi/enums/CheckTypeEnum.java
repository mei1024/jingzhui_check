package com.solar.bi.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.solar.common.bean.KeyValue;

/**
 * @declaration 检查类型枚举
 * @author mw
 * @time 2019-12-05 10:48
 */
public enum CheckTypeEnum {
	
	JZ("JZ", "颈椎"),

	YZ("YZ", "腰椎"),
	
	;

	private final String code;

	private final String text;

	private CheckTypeEnum(String code, String name) {
		this.code = code;
		this.text = name;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public static List<KeyValue> list() {
		List<KeyValue> list = new ArrayList<KeyValue>();
		for (CheckTypeEnum item : values()) {
			list.add(convertKeyValue(item));
		}
		return list;
	}

	public static KeyValue convertKeyValue(CheckTypeEnum item) {
		return new KeyValue(item.getCode(), item.getText());
	}

	public static CheckTypeEnum get(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		for (CheckTypeEnum status : values()) {
			if (status.getCode().toString().equals(value)) {
				return status;
			}
		}
		return null;
	}
}
