package com.solar.cms.dto;


import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 咨讯 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "咨讯")
public class CmsNewsDto extends BaseDto {

	@ApiModelProperty(name = "id", value = "内容ID", required = true)
    private String id;

	@ApiModelProperty(name = "provinceId", value = "省份ID", required = false)
    private String provinceId;

	@ApiModelProperty(name = "provinceName", value = "省份名称", required = false)
    private String provinceName;

	@ApiModelProperty(name = "cityId", value = "城市ID", required = false)
    private String cityId;

	@ApiModelProperty(name = "cityName", value = "城市名称", required = false)
    private String cityName;

	@ApiModelProperty(name = "districtId", value = "地区ID", required = false)
    private String districtId;

	@ApiModelProperty(name = "districtName", value = "地区名称", required = false)
    private String districtName;

	@ApiModelProperty(name = "villagesId", value = "乡镇ID", required = false)
    private String villagesId;
	
	@ApiModelProperty(name = "villagesName", value = "乡镇名称", required = false)
    private String villagesName;
	
	@ApiModelProperty(name = "hamletId", value = "村ID", required = false)
    private String hamletId;
	
	@ApiModelProperty(name = "hamletName", value = "村名称", required = false)
    private String hamletName;

	@ApiModelProperty(name = "title", value = "标题", required = true)
    private String title;

	@ApiModelProperty(name = "userId", value = "发表用户", required = true)
    private String userId;

	@ApiModelProperty(name = "checkUserId", value = "审核用户", required = false)
    private String checkUserId;

	@ApiModelProperty(name = "categoryId", value = "分类", required = true)
    private String categoryId;

	@ApiModelProperty(name = "author", value = "作者", required = false)
    private String author;

	@ApiModelProperty(name = "editor", value = "编辑", required = false)
    private String editor;
	
	@ApiModelProperty(name = "description", value = "简介", required = false)
    private String description;

	@ApiModelProperty(name = "tagIds", value = "标签,多个','号分割", required = false)
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

	@ApiModelProperty(name = "wordCount", value = "字数", required = false)
    private Integer wordCount;
	
	@ApiModelProperty(name = "attachmentIds", value = "附件ID集合", required = false)
    private List<String> attachmentIds;
	
	@ApiModelProperty(name = "slideshows", value = "轮播图信息", required = false)
    private List<CmsAdDto> slideshows;

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsNewsDto == false) return false;
		if(this == obj) return true;
		CmsNewsDto other = (CmsNewsDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}