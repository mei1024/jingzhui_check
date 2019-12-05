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
 * 广告 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Getter 
@Setter
@ApiModel(value = "广告")
public class CmsAdDto implements java.io.Serializable {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;

	@ApiModelProperty(name = "type", value = "类型: CONTENT_DETAILS_BANNER=咨讯,视频详情轮播", required = true)
    private String type;

	@ApiModelProperty(name = "contentId", value = "内容ID,指定type类型内容ID", required = false)
    private String contentId;

	@ApiModelProperty(name = "imgUrl", value = "图片地址", required = true)
    private String imgUrl;
	
	@ApiModelProperty(name = "url", value = "图片地址", required = true)
    private String url;

	@ApiModelProperty(name = "linkUrl", value = "外链地址", required = false)
    private String linkUrl;

	@ApiModelProperty(name = "status", value = "状态 ENABLED=正常，DISABLED=下线", required = true)
    private String status;

	@ApiModelProperty(name = "sortno", value = "顺序", required = true)
    private Integer sortno;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("name", getName())
			.append("type", getType())
			.append("contentId", getContentId())
			.append("imgUrl", getImgUrl())
			.append("linkUrl", getLinkUrl())
			.append("status", getStatus())
			.append("sortno", getSortno())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsAdDto == false) return false;
		if(this == obj) return true;
		CmsAdDto other = (CmsAdDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}