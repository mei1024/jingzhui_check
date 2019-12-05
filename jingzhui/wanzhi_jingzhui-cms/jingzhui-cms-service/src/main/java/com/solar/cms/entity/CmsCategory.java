package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分类 cms_category 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsCategory extends ModelCreated {


	/** 站点ID */
    private String siteId;

	/** 名称 */
    private String name;

	/** 父分类ID */
    private String parentId;

	/** 分类类型 */
    private String typeId;

	/** 所有子分类ID */
    private String childIds;

	/** 标签分类 */
    private String tagTypeIds;

	/** 编码 */
    private String code;

	/** 模板路径 */
    private String templatePath;

	/** 首页路径 */
    private String path;

	/** 外链 */
    private String onlyUrl;

	/** 已经静态化 */
    private String hasStatic;

	/** 首页地址 */
    private String url;

	/** 内容路径 */
    private String contentPath;

	/** 包含子分类内容 */
    private String containChild;

	/** 每页数据条数 */
    private Integer pageSize;

	/** 允许投稿 */
    private String allowContribute;

	/** 顺序 */
    private Integer sortno;

	/** 隐藏 */
    private String hidden;

	/** 内容数 */
    private Integer contents;

	/** 扩展ID */
    private String extendId;

	/** 备注 */
    private String memo;

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
		if(obj instanceof CmsCategory == false) return false;
		if(this == obj) return true;
		CmsCategory other = (CmsCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
