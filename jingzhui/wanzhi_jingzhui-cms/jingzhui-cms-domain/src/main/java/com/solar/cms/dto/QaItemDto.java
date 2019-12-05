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
 * 问题答案 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
@ApiModel(value = "问题答案")
public class QaItemDto {


	@ApiModelProperty(name = "id", value = "主键", required = true)
    private String id;

	@ApiModelProperty(name = "categroyId", value = "问题分类ID", required = false)
    private Long categroyId;

	@ApiModelProperty(name = "isShowFlag", value = "是否展示  1 展示 0不展示", required = false)
    private Integer isShowFlag;

	@ApiModelProperty(name = "question", value = "问题", required = false)
    private String question;

	@ApiModelProperty(name = "answer", value = "答案", required = false)
    private String answer;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("categroyId", getCategroyId())
			.append("isShowFlag", getIsShowFlag())
			.append("question", getQuestion())
			.append("answer", getAnswer())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QaItemDto == false) return false;
		if(this == obj) return true;
		QaItemDto other = (QaItemDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}