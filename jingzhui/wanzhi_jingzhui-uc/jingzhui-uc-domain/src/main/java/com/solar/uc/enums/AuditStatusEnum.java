package com.solar.uc.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.solar.common.bean.KeyValue;

/**
 * @declaration 申请状态
 * @author mw
 * @time 2019-09-05 11:03
 */
public enum AuditStatusEnum {
	
	APPLY("APPLY", "待审核"),

	PASS("PASS", "审核通过"),

	REFUSE("REFUSE", "审核拒绝"),;

	private final String code;

	private final String text;

	private AuditStatusEnum(String code, String name) {
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
		for (AuditStatusEnum item : values()) {
			list.add(convertKeyValue(item));
		}
		return list;
	}

	public static KeyValue convertKeyValue(AuditStatusEnum item) {
		return new KeyValue(item.getCode(), item.getText());
	}

	public static AuditStatusEnum get(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		for (AuditStatusEnum status : values()) {
			if (status.getCode().toString().equals(value)) {
				return status;
			}
		}
		return null;
	}
}
