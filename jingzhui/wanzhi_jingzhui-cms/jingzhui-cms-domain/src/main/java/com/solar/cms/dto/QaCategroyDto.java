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
 * 问题分类 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
@ApiModel(value = "问题分类")
public class QaCategroyDto {


	@ApiModelProperty(name = "id", value = "主键", required = true)
    private String id;

	@ApiModelProperty(name = "parentId", value = "上级分类ID", required = false)
    private String parentId;

	@ApiModelProperty(name = "typeName", value = "分类名称", required = false)
    private String typeName;

	@ApiModelProperty(name = "sortNo", value = "排序号", required = false)
    private Integer sortNo;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("parentId", getParentId())
			.append("typeName", getTypeName())
			.append("sortNo", getSortNo())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QaCategroyDto == false) return false;
		if(this == obj) return true;
		QaCategroyDto other = (QaCategroyDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}