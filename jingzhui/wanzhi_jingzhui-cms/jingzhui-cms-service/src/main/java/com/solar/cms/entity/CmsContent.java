package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 内容 cms_content 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsContent extends ModelCreated {


	/** 站点ID */
    private String siteId;

	/** 标题 */
    private String title;

	/** 发表用户 */
    private String userId;

	/** 审核用户 */
    private String checkUserId;

	/** 分类 */
    private String categoryId;

	/** 模型 */
    private String modelId;

	/** 父内容ID */
    private String parentId;

	/** 是否转载 是=Y,否=N */
    private String copied;

	/** 作者 */
    private String author;

	/** 编辑 */
    private String editor;

	/** 外链 是=Y,否=N */
    private String onlyUrl;

	/** 拥有图片列表 是=Y,否=N */
    private String hasImages;

	/** 拥有附件列表  是=Y,否=N */
    private String hasFiles;

	/** 已经静态化  是=Y,否=N */
    private String hasStatic;

	/** 地址 */
    private String url;

	/** 简介 */
    private String description;

	/** 标签 */
    private String tagIds;

	/** 封面 */
    private String cover;

	/** 评论数 */
    private Integer comments;

	/** 点击数 */
    private Integer clicks;

	/** 发布日期 */
    private java.util.Date publishDate;

	/** 审核日期 */
    private java.util.Date checkDate;

	/** 顺序 */
    private Integer sortno;

	/** 状态：0、草稿 1、已发布 2、待审核 */
    private String status;
	
    /** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("siteId", getSiteId())
			.append("title", getTitle())
			.append("userId", getUserId())
			.append("checkUserId", getCheckUserId())
			.append("categoryId", getCategoryId())
			.append("modelId", getModelId())
			.append("parentId", getParentId())
			.append("copied", getCopied())
			.append("author", getAuthor())
			.append("editor", getEditor())
			.append("onlyUrl", getOnlyUrl())
			.append("hasImages", getHasImages())
			.append("hasFiles", getHasFiles())
			.append("hasStatic", getHasStatic())
			.append("url", getUrl())
			.append("description", getDescription())
			.append("tagIds", getTagIds())
			.append("cover", getCover())
			.append("comments", getComments())
			.append("clicks", getClicks())
			.append("publishDate", getPublishDate())
			.append("checkDate", getCheckDate())
			.append("sortno", getSortno())
			.append("status", getStatus())
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
		if(obj instanceof CmsContent == false) return false;
		if(this == obj) return true;
		CmsContent other = (CmsContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
