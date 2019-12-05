package com.solar.uc.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.solar.common.bean.KeyValue;

public enum RegSourceEnum {
	
	WECHAT_MINI("wechat_mini", "小程序"),

	PC("pc", "PC站");

	private final String code;

	private final String text;

	private RegSourceEnum(String code, String name) {
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
		for (RegSourceEnum item : values()) {
			list.add(convertKeyValue(item));
		}
		return list;
	}

	public static KeyValue convertKeyValue(RegSourceEnum item) {
		return new KeyValue(item.getCode(),item.getText());
	}

	public static RegSourceEnum get(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		for (RegSourceEnum status : values()) {
			if (status.getCode().equals(value)) {
				return status;
			}
		}
		return null;
	}
}
