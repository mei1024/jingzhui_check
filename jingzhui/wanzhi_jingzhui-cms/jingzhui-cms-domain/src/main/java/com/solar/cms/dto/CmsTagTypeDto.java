package com.solar.cms.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签类型 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "标签类型")
public class CmsTagTypeDto implements java.io.Serializable {


	@ApiModelProperty(name = "id", value = "分类ID", required = true)
    private String id;

	@ApiModelProperty(name = "siteId", value = "站点ID", required = false)
    private String siteId;

	@ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;

	@ApiModelProperty(name = "count", value = "标签数", required = true)
    private Integer count;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("siteId", getSiteId())
			.append("name", getName())
			.append("count", getCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTagTypeDto == false) return false;
		if(this == obj) return true;
		CmsTagTypeDto other = (CmsTagTypeDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}