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
 * 意见反馈 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181218
 * 
 */
@Getter 
@Setter
@ApiModel(value = "意见反馈")
public class CmsFeedbackDto extends BaseDto {

	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "categoryId", value = "反馈类型ID", required = true)
    private String categoryId;
    
	@ApiModelProperty(name = "categoryName", value = "类型名称", required = true)
    private String categoryName;

	@ApiModelProperty(name = "userId", value = "0=匿名,其它=用户ID", required = true, hidden=true)
    private String userId;

	@ApiModelProperty(name = "content", value = "反馈内容", required = true)
    private String content;

	@ApiModelProperty(name = "source", value = "反馈来源 Android iOS", required = false)
    private String source;

	@ApiModelProperty(name = "imgUrls", value = "图片地址,多个,号分割", required = false)
    private String imgUrls;

	@ApiModelProperty(name = "status", value = "状态 READY=未处理 PROCESSED=已处理", required = true, hidden=true)
    private String status;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("categoryId", getCategoryId())
			.append("userId", getUserId())
			.append("content", getContent())
			.append("source", getSource())
			.append("imgUrls", getImgUrls())
			.append("status", getStatus())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsFeedbackDto == false) return false;
		if(this == obj) return true;
		CmsFeedbackDto other = (CmsFeedbackDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}