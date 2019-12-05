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
 * 内容扩展 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "内容扩展")
public class CmsContentAttributeDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "内容ID", required = true)
    private String id;

	@ApiModelProperty(name = "source", value = "内容来源", required = false)
    private String source;

	@ApiModelProperty(name = "sourceUrl", value = "来源地址", required = false)
    private String sourceUrl;

	@ApiModelProperty(name = "text", value = "内容", required = false)
    private String text;

	@ApiModelProperty(name = "wordCount", value = "字数", required = true)
    private Integer wordCount;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("source", getSource())
			.append("sourceUrl", getSourceUrl())
			.append("text", getText())
			.append("wordCount", getWordCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContentAttributeDto == false) return false;
		if(this == obj) return true;
		CmsContentAttributeDto other = (CmsContentAttributeDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}