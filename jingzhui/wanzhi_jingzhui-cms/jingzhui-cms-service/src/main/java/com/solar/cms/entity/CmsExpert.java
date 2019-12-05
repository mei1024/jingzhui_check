package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家 cms_expert 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsExpert extends ModelCreated {


	/** 专家名称 */
    private String realName;

	/** 专家图片 */
    private String avatarUrl;

	/** 专家职务 */
    private String duty;

	/** 所在机构 */
    private String organization;

	/** 专家简介1000字以内 */
    private String introduction;

	/** 擅长领域标签ID多个“,”号分割 */
    private String geniusTagIds;

	/** 擅长领域200字以内 */
    private String genius;

	/** 顺序 */
    private Integer sortno;

	/** 隐藏 Y=是,N=否 */
    private String hidden;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("realName", getRealName())
			.append("avatarUrl", getAvatarUrl())
			.append("duty", getDuty())
			.append("organization", getOrganization())
			.append("introduction", getIntroduction())
			.append("geniusTagIds", getGeniusTagIds())
			.append("genius", getGenius())
			.append("sortno", getSortno())
			.append("hidden", getHidden())
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
		if(obj instanceof CmsExpert == false) return false;
		if(this == obj) return true;
		CmsExpert other = (CmsExpert)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
