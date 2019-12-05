package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分类模型 cms_category_model 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsCategoryModel extends ModelCreated {


	/** 模型编码 */
    private String modelId;

	/** 内容模板路径 */
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
		if(obj instanceof CmsCategoryModel == false) return false;
		if(this == obj) return true;
		CmsCategoryModel other = (CmsCategoryModel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelId(),other.getModelId())
			.isEquals();
	}
		
}
