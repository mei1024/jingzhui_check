package com.solar.uc.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.solar.common.bean.KeyValue;

/**
 * 
 * 用户身份
 * 
 * RolesEnum   
 *  
 * @author tllen  
 * @date   Dec 20, 2018 10:59:07 AM   
 *
 */
public enum RoleEnum {

	ROLE_PLATFORM("ROLE_PLATFORM", "运营"),

	ROLE_USER("ROLE_USER", "用户");

	private final String code;

	private final String text;

	private RoleEnum(String code, String name) {
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
		for (RoleEnum item : values()) {
			list.add(convertKeyValue(item));
		}
		return list;
	}

	public static KeyValue convertKeyValue(RoleEnum item) {
		return new KeyValue(item.getCode(),item.getText());
	}

	public static RoleEnum get(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		for (RoleEnum status : values()) {
			if (status.getCode().equals(value)) {
				return status;
			}
		}
		return null;
	}
}