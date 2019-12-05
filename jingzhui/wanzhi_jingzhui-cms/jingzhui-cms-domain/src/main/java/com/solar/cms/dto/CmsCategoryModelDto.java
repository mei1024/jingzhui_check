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
 * 分类模型 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "分类模型")
public class CmsCategoryModelDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "分类ID category_id", required = true)
    private String id;

	@ApiModelProperty(name = "modelId", value = "模型编码", required = true)
    private String modelId;

	@ApiModelProperty(name = "templatePath", value = "内容模板路径", required = false)
    private String templatePath;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("modelId", getModelId())
			.append("templatePath", getTemplatePath())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getModelId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsCategoryModelDto == false) return false;
		if(this == obj) return true;
		CmsCategoryModelDto other = (CmsCategoryModelDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelId(),other.getModelId())
			.isEquals();
	}
		
}