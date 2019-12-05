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
 * 内容附件 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "内容附件")
public class CmsContentFileDto implements java.io.Serializable {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "contentId", value = "内容ID", required = true)
    private String contentId;

	@ApiModelProperty(name = "attachmentId", value = "关联内部上传文件ID", required = true)
    private String attachmentId;

	@ApiModelProperty(name = "userId", value = "上传者ID", required = false)
    private String userId;

	@ApiModelProperty(name = "name", value = "文件名称", required = false)
    private String name;

	@ApiModelProperty(name = "url", value = "URL访问地址", required = false)
    private String url;

	@ApiModelProperty(name = "path", value = "文件存储路径", required = false)
    private String path;

	@ApiModelProperty(name = "size", value = "文件大小", required = false)
    private Long size;

	@ApiModelProperty(name = "image", value = "是否图片", required = false)
    private int image;

	@ApiModelProperty(name = "clicks", value = "点击数", required = false)
    private Integer clicks;

	@ApiModelProperty(name = "sortno", value = "排序", required = false)
    private Integer sortno;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("contentId", getContentId())
			.append("attachmentId", getAttachmentId())
			.append("userId", getUserId())
			.append("name", getName())
			.append("url", getUrl())
			.append("path", getPath())
			.append("size", getSize())
			.append("image", getImage())
			.append("clicks", getClicks())
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
		if(obj instanceof CmsContentFileDto == false) return false;
		if(this == obj) return true;
		CmsContentFileDto other = (CmsContentFileDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}