package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 内容附件 cms_content_file 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsContentFile extends ModelCreated {


	/** 内容ID */
    private String contentId;

	/** 关联内部上传文件ID */
    private String attachmentId;

	/** 上传者ID */
    private String userId;

	/** 文件名称 */
    private String name;

	/** URL访问地址 */
    private String url;

	/** 文件存储路径 */
    private String path;

	/** 文件大小 */
    private Long size;

	/** 是否图片 */
    private int image;

	/** 点击数 */
    private Integer clicks;

	/** 排序 */
    private Integer sortno;

	/** 备注 */
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
			.append("dstatus", getDstatus())
			.append("creator", getCreator())
			.append("createDate", getCreateDate())
			.append("lastModifier", getLastModifier())
			.append("lastModDate", getLastModDate())
			.append("version", getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContentFile == false) return false;
		if(this == obj) return true;
		CmsContentFile other = (CmsContentFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
