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
 * 标签 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "标签")
public class CmsTagDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "", required = true)
    private String id;

	@ApiModelProperty(name = "siteId", value = "站点ID", required = false)
    private String siteId;

	@ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;

	@ApiModelProperty(name = "typeId", value = "分类ID", required = false)
    private String typeId;

	@ApiModelProperty(name = "searchCount", value = "搜索次数", required = false)
    private Integer searchCount;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("siteId", getSiteId())
			.append("name", getName())
			.append("typeId", getTypeId())
			.append("searchCount", getSearchCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTagDto == false) return false;
		if(this == obj) return true;
		CmsTagDto other = (CmsTagDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}