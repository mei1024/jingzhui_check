package com.solar.cms.dto;


import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 专家 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "专家")
public class CmsExpertDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "专家ID", required = true)
    private String id;

	@ApiModelProperty(name = "realName", value = "专家名称", required = false)
    private String realName;

	@ApiModelProperty(name = "avatarUrl", value = "专家图片", required = false)
    private String avatarUrl;

	@ApiModelProperty(name = "duty", value = "专家职务", required = false)
    private String duty;
	
	@ApiModelProperty(name = "duty", value = "专家职务", required = false)
    private String dutyName;

	@ApiModelProperty(name = "organization", value = "所在机构", required = false)
    private String organization;

	@ApiModelProperty(name = "introduction", value = "专家简介1000字以内", required = false)
    private String introduction;

	@ApiModelProperty(name = "geniusTagIds", value = "擅长领域标签ID多个“,”号分割", required = false)
    private String geniusTagIds;
	
	@ApiModelProperty(name = "geniusTagNames", value = "擅长领域对应的名称", required = false)
    private List<String> geniusTagNames;

	@ApiModelProperty(name = "genius", value = "擅长领域200字以内", required = false)
    private String genius;

	@ApiModelProperty(name = "sortno", value = "顺序", required = false)
    private Integer sortno;

	@ApiModelProperty(name = "hidden", value = "隐藏 Y=是,N=否", required = false)
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
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsExpertDto == false) return false;
		if(this == obj) return true;
		CmsExpertDto other = (CmsExpertDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}