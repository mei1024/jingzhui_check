package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 意见反馈 cms_feedback 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181218
 * 
 */
@Getter 
@Setter
public class CmsFeedback extends ModelCreated {


	/** 反馈类型ID,cms_category ID */
    private String categoryId;

	/** 0=匿名,其它=用户ID */
    private String userId;

	/** 反馈内容 */
    private String content;

	/** 反馈来源 */
    private String source;

	/** 图片地址,多个,号分割 */
    private String imgUrls;

	/** 状态 READY=未处理 PROCESSED=已处理 */
    private String status;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("categoryId", getCategoryId())
			.append("userId", getUserId())
			.append("content", getContent())
			.append("source", getSource())
			.append("imgUrls", getImgUrls())
			.append("status", getStatus())
			.append("memo", getMemo())
			.append("dstatus", getDstatus())
			.append("creator", getCreator())
			.append("createDate", getCreateDate())
			.append("lastModifier", getLastModifier())
			.append("lastModDate", getLastModDate())
			.append("version", getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsFeedback == false) return false;
		if(this == obj) return true;
		CmsFeedback other = (CmsFeedback)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
