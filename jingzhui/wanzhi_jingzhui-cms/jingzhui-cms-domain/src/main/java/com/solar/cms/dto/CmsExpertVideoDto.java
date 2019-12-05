package com.solar.cms.dto;


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
 * 专家视频 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "专家视频")
public class CmsExpertVideoDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "expertId", value = "专家ID", required = false)
    private String expertId;

	@ApiModelProperty(name = "videoId", value = "视频ID", required = false)
    private String videoId;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("expertId", getExpertId())
			.append("videoId", getVideoId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsExpertVideoDto == false) return false;
		if(this == obj) return true;
		CmsExpertVideoDto other = (CmsExpertVideoDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}