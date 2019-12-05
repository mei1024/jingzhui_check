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
 * 分类 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "分类")
public class CmsCategoryDto implements java.io.Serializable {


	@ApiModelProperty(name = "id", value = "分类ID", required = true)
    private String id;

	@ApiModelProperty(name = "siteId", value = "站点ID", required = false)
    private String siteId;

	@ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;

	@ApiModelProperty(name = "parentId", value = "父分类ID", required = false)
    private String parentId;

	@ApiModelProperty(name = "typeId", value = "分类类型", required = false)
    private String typeId;

	@ApiModelProperty(name = "childIds", value = "所有子分类ID", required = false)
    private String childIds;

	@ApiModelProperty(name = "tagTypeIds", value = "标签分类", required = false)
    private String tagTypeIds;

	@ApiModelProperty(name = "code", value = "编码", required = false)
    private String code;

	@ApiModelProperty(name = "templatePath", value = "模板路径", required = false)
    private String templatePath;

	@ApiModelProperty(name = "path", value = "首页路径", required = false)
    private String path;

	@ApiModelProperty(name = "onlyUrl", value = "外链", required = false)
    private String onlyUrl;

	@ApiModelProperty(name = "hasStatic", value = "已经静态化", required = false)
    private String hasStatic;

	@ApiModelProperty(name = "url", value = "首页地址", required = false)
    private String url;

	@ApiModelProperty(name = "contentPath", value = "内容路径", required = false)
    private String contentPath;

	@ApiModelProperty(name = "containChild", value = "包含子分类内容", required = false)
    private String containChild;

	@ApiModelProperty(name = "pageSize", value = "每页数据条数", required = false)
    private Integer pageSize;

	@ApiModelProperty(name = "allowContribute", value = "允许投稿", required = false)
    private String allowContribute;

	@ApiModelProperty(name = "sortno", value = "顺序", required = false)
    private Integer sortno;

	@ApiModelProperty(name = "hidden", value = "隐藏", required = false)
    private String hidden;

	@ApiModelProperty(name = "contents", value = "内容数", required = true)
    private Integer contents;

	@ApiModelProperty(name = "extendId", value = "扩展ID", required = false)
    private String extendId;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("siteId", getSiteId())
			.append("name", getName())
			.append("parentId", getParentId())
			.append("typeId", getTypeId())
			.append("childIds", getChildIds())
			.append("tagTypeIds", getTagTypeIds())
			.append("code", getCode())
			.append("templatePath", getTemplatePath())
			.append("path", getPath())
			.append("onlyUrl", getOnlyUrl())
			.append("hasStatic", getHasStatic())
			.append("url", getUrl())
			.append("contentPath", getContentPath())
			.append("containChild", getContainChild())
			.append("pageSize", getPageSize())
			.append("allowContribute", getAllowContribute())
			.append("sortno", getSortno())
			.append("hidden", getHidden())
			.append("contents", getContents())
			.append("extendId", getExtendId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsCategoryDto == false) return false;
		if(this == obj) return true;
		CmsCategoryDto other = (CmsCategoryDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}