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
 * 分类扩展 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "分类扩展")
public class CmsCategoryAttributeDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "分类ID category_id", required = true)
    private String id;

	@ApiModelProperty(name = "title", value = "标题", required = false)
    private String title;

	@ApiModelProperty(name = "keywords", value = "关键词", required = false)
    private String keywords;

	@ApiModelProperty(name = "description", value = "描述", required = false)
    private String description;

	@ApiModelProperty(name = "data", value = "数据JSON", required = false)
    private String data;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("title", getTitle())
			.append("keywords", getKeywords())
			.append("description", getDescription())
			.append("data", getData())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsCategoryAttributeDto == false) return false;
		if(this == obj) return true;
		CmsCategoryAttributeDto other = (CmsCategoryAttributeDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}