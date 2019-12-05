package com.solar.cms.dto;


import java.util.List;

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
 * 内容 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "内容")
public class CmsContentDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "主键ID", required = true)
    private String id;

	@ApiModelProperty(name = "siteId", value = "站点ID", required = false)
    private String siteId;

	@ApiModelProperty(name = "title", value = "标题", required = true)
    private String title;

	@ApiModelProperty(name = "userId", value = "发表用户", required = true)
    private String userId;

	@ApiModelProperty(name = "checkUserId", value = "审核用户", required = false)
    private String checkUserId;

	@ApiModelProperty(name = "categoryId", value = "分类", required = true)
    private String categoryId;

	@ApiModelProperty(name = "modelId", value = "模型", required = false)
    private String modelId;

	@ApiModelProperty(name = "parentId", value = "父内容ID", required = false)
    private String parentId;

	@ApiModelProperty(name = "copied", value = "是否转载 是=Y,否=N", required = true)
    private String copied;

	@ApiModelProperty(name = "author", value = "作者", required = false)
    private String author;

	@ApiModelProperty(name = "editor", value = "编辑", required = false)
    private String editor;

	@ApiModelProperty(name = "onlyUrl", value = "外链 是=Y,否=N", required = true)
    private String onlyUrl;

	@ApiModelProperty(name = "hasImages", value = "拥有图片列表 是=Y,否=N", required = true)
    private String hasImages;

	@ApiModelProperty(name = "hasFiles", value = "拥有附件列表  是=Y,否=N", required = true)
    private String hasFiles;

	@ApiModelProperty(name = "hasStatic", value = "已经静态化  是=Y,否=N", required = true)
    private String hasStatic;

	@ApiModelProperty(name = "url", value = "地址", required = false)
    private String url;

	@ApiModelProperty(name = "description", value = "简介", required = false)
    private String description;

	@ApiModelProperty(name = "tagIds", value = "标签", required = false)
    private String tagIds;
	
	@ApiModelProperty(name = "tagNames", value = "标签对应的名称", required = false)
    private List<String> tagNames;

	@ApiModelProperty(name = "cover", value = "封面", required = false)
    private String cover;

	@ApiModelProperty(name = "comments", value = "评论数", required = true)
    private Integer comments;

	@ApiModelProperty(name = "clicks", value = "点击数", required = true)
    private Integer clicks;

	@ApiModelProperty(name = "publishDate", value = "发布日期", required = true)
    private java.util.Date publishDate;

	@ApiModelProperty(name = "checkDate", value = "审核日期", required = false)
    private java.util.Date checkDate;

	@ApiModelProperty(name = "sortno", value = "顺序", required = true)
    private Integer sortno;

	@ApiModelProperty(name = "status", value = "状态：0、草稿 1、已发布 2、待审核", required = true)
    private String status;
	
	@ApiModelProperty(name = "source", value = "内容来源", required = false)
    private String source;

	@ApiModelProperty(name = "sourceUrl", value = "来源地址", required = false)
    private String sourceUrl;

	@ApiModelProperty(name = "text", value = "内容", required = false)
    private String text;

	@ApiModelProperty(name = "wordCount", value = "字数", required = true)
    private Integer wordCount;
	
	@ApiModelProperty(name = "attachmentIds", value = "附件ID集合", required = true)
    private List<String> attachmentIds;

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
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContentDto == false) return false;
		if(this == obj) return true;
		CmsContentDto other = (CmsContentDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}