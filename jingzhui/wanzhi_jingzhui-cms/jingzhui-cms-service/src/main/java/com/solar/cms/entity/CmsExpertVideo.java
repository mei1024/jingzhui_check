package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家视频 cms_expert_video 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsExpertVideo extends ModelCreated {


	/** 专家ID */
    private String expertId;

	/** 视频ID */
    private String videoId;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("expertId", getExpertId())
			.append("videoId", getVideoId())
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
		if(obj instanceof CmsExpertVideo == false) return false;
		if(this == obj) return true;
		CmsExpertVideo other = (CmsExpertVideo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
