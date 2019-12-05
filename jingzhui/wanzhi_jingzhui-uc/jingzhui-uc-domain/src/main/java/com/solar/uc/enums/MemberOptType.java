package com.solar.uc.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.solar.common.bean.KeyValue;

public enum MemberOptType {
	
	REGISTER(10, "注册"),
	
	LOGIN(20, "登录"),
	
	;
	
	private final Integer code;

	private final String text;

	private MemberOptType(Integer code, String name) {
		this.code = code;
		this.text = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
	
	public static List<KeyValue> list() {
		List<KeyValue> list = new ArrayList<KeyValue>();
		for (MemberOptType item : values()) {
			list.add(convertKeyValue(item));
		}
		return list;
	}

	public static KeyValue convertKeyValue(MemberOptType item) {
		return new KeyValue(item.getCode()+"",item.getText());
	}

	public static MemberOptType get(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		for (MemberOptType status : values()) {
			if (status.getCode().toString().equals(value)) {
				return status;
			}
		}
		return null;
	}
}
