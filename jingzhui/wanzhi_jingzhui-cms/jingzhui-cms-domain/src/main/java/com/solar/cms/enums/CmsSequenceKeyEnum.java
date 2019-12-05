package com.solar.cms.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * CMS 序列KEY
 *
 * 
 * @author tllen
 *
 * @date SequenceKeyEnum.java 2016年5月8日 下午2:13:57 
 *
 */
public enum CmsSequenceKeyEnum {

	CMS_CATEGORY_ID("CMS_CATEGORY_ID", "内容类型"),
	
	CMS_CONTENT_ID("CMS_CONTENT_ID", "内容"),
		
	CMS_TAG_TYPE_ID("CMS_TAG_TYPE_ID", "标签类型"),
	
	CMS_EXPERT_ID("CMS_EXPERT_ID", "专家"),
	
	CMS_AD_ID("CMS_AD_ID", "广告"),
	
	CMS_FEEDBACK_ID("CMS_FEEDBACK_ID", "意见反馈"),
	
	BASE_QA_CATEGROY_ID("BASE_QA_CATEGROY_ID", "问题分类"),
	
	BASE_QA_ITEM_ID("BASE_QA_ITEM_ID", "问题答案"),
	
	;

	private final String key;

	private final String text;

	private CmsSequenceKeyEnum(String key, String text) {
		this.key = key;
		this.text = text;
	}

	public String getKey() {
		return key;
	}
	
	public String getText() {
		return text;
	}

	public static CmsSequenceKeyEnum get(String key) {
		
		for (CmsSequenceKeyEnum status : values()) {
			if (StringUtils.equalsIgnoreCase(status.getKey(), key)) {
				return status;
			}
		}
		
		return null;
	}
	
	
	
}