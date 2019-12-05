package com.solar.uc.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.solar.common.bean.KeyValue;

/**
 * 状态枚举
 * 
 * 
 * @author tllen
 *
 */
public class StatusEnum {
	
	
	/**
	 * 用户状态
	 */
	public enum UserStatus {
		UNACTIVE("UNACTIVE","未激活"),
		
		NORMAL("NORMAL","正常"),
		
		LOGOUT("LOGOUT","注销"),
		
		LOCK("LOCK","黑名单");

		private final String code;

		private final String text;

		private UserStatus(String code, String name) {
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
			for (UserStatus item : values()) {
				list.add(convertKeyValue(item));
			}
			return list;
		}

		public static KeyValue convertKeyValue(UserStatus item) {
			return new KeyValue(item.getCode(), item.getText());
		}
		
	 
		@JsonCreator
		public static UserStatus get(String value) {
			if (StringUtils.isEmpty(value)) {
				return null;
			}

			for (UserStatus status : values()) {
				if (status.getCode().equals(value)) {
					return status;
				}
			}
			return null;
		}
		
		/**
		 * @declaration:判断客户是否封号
		 * @author:fbc
		 * @date:2019年1月10日下午6:11:49
		 * @param:
		 * @return:
		 * @exception:
		 * @ModifyInformation01:
		 */
		public static Boolean getUserIsLock(String value) {
			if (StringUtils.isNotEmpty(value) && LOCK.getCode().equals(value)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 商家信息审核状态
	 * @author fbc
	 *
	 * 2019年1月7日
	 */
	public enum AuditStatus{

		APPLY("申请", "APPLY"), //首次申请后状态
		
		REFUSE("拒绝(审核拒绝)", "REFUSE"), //首次审核拒绝
		
//		PROCESSING("处理中(审核通过)", "PROCESSING"),
		
		UPDATE("二次修改", "UPDATE"), //二次修改后状态
		
		UPDATE_REFUSE("二次修改拒绝", "UPDATE_REFUSE"), //二次修改被拒绝
		          
		PASS("通过", "PASS"); //审核通过

		private final String code;

		private final String text;

		private AuditStatus(String name, String code) {
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
			for (AuditStatus item : values()) {
				list.add(convertKeyValue(item));
			}
			return list;
		}

		public static KeyValue convertKeyValue(AuditStatus item) {
			return new KeyValue(item.getCode(), item.getText());
		}
		
		@JsonCreator
		public static AuditStatus get(String value) {
			if (StringUtils.isEmpty(value)) {
				return null;
			}

			for (AuditStatus status : values()) {
				if (status.getCode().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}
	
	public enum CategoryStatus{

		ENABLED("ENABLED","正常"),
		
		DISABLED("DISABLED","无效");

		private final String code;

		private final String text;

		private CategoryStatus(String code, String name) {
			this.code = code;
			this.text = name;
		}

		public String getCode() {
			return code;
		}

		public String getText() {
			return text;
		}
	 
		@JsonCreator
		public static CategoryStatus get(String value) {
			if (StringUtils.isEmpty(value)) {
				return null;
			}

			for (CategoryStatus status : values()) {
				if (status.getCode().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}
	
	public enum YesOrNoStatus{

		YES("Y","是"),
		
		NO("N","否");

		private final String code;

		private final String text;

		private YesOrNoStatus(String code, String name) {
			this.code = code;
			this.text = name;
		}

		public String getCode() {
			return code;
		}

		public String getText() {
			return text;
		}
	 
		@JsonCreator
		public static YesOrNoStatus get(String value) {
			if (StringUtils.isEmpty(value)) {
				return null;
			}

			for (YesOrNoStatus status : values()) {
				if (status.getCode().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}
	
}
