package com.solar.cms.dto;


import java.util.List;

import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 视频 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Getter 
@Setter
@ApiModel(value = "视频")
public class CmsVideoDto extends BaseDto {

	@ApiModelProperty(name = "id", value = "内容ID", required = true)
    private String id;

	@ApiModelProperty(name = "expertId", value = "专家ID", required = true)
    private String expertId;
	
	@ApiModelProperty(name = "expert", value = "专家信息", required = false)
    private CmsExpertDto expert;

	
	@ApiModelProperty(name = "originUrl", value = "原视频上传URL", required = true)
    private String originUrl;

	@ApiModelProperty(name = "url240", value = "视频转码后目标240pURL,即播放地址", required = false)
    private String url240;

	@ApiModelProperty(name = "url480", value = "视频转码后目标480pURL,即播放地址", required = false)
    private String url480;

	@ApiModelProperty(name = "url780", value = "视频转码后目标720pURL,即播放地址", required = false)
    private String url780;

	@ApiModelProperty(name = "url1080", value = "视频转码后目标1080pURL,即播放地址", required = false)
    private String url1080;

	@ApiModelProperty(name = "token240", value = "私有240视频访问token", required = false)
    private String token240;

	@ApiModelProperty(name = "token480", value = "私有480视频访问token", required = false)
    private String token480;

	@ApiModelProperty(name = "token780", value = "私有780视频访问token", required = false)
    private String token780;

	@ApiModelProperty(name = "token1080", value = "私有1080视频访问token", required = false)
    private String token1080;

	@ApiModelProperty(name = "key240", value = "240视频存储KEY", required = false)
    private String key240;

	@ApiModelProperty(name = "key480", value = "480视频存储KEY", required = false)
    private String key480;

	@ApiModelProperty(name = "key780", value = "780视频存储KEY", required = false)
    private String key780;

	@ApiModelProperty(name = "key1080", value = "1080视频存储KEY", required = false)
    private String key1080;

	@ApiModelProperty(name = "vstatus", value = "0=处理完成,1=解码处理中", required = false)
    private String vstatus;

	@ApiModelProperty(name = "vkey", value = "原视频存储KEY", required = true)
    private String vkey;

	@ApiModelProperty(name = "vavthumbTaskId", value = "视频转码任务id", required = false)
    private String vavthumbTaskId;

	@ApiModelProperty(name = "vavthumbStatus", value = "转码完成: Y=是, N=否", required = true)
    private String vavthumbStatus;

	@ApiModelProperty(name = "vsampleTaskId", value = "视频缩略图采样任务id", required = false)
    private String vsampleTaskId;

	@ApiModelProperty(name = "vsampleStatus", value = "视频缩略图采样完成: Y=是, N=否", required = true)
    private String vsampleStatus;

	@ApiModelProperty(name = "uploadIp", value = "上传IP", required = false)
    private String uploadIp;

	@ApiModelProperty(name = "uploadTime", value = "上传时间", required = true)
    private Long uploadTime;

	@ApiModelProperty(name = "size", value = "视频大小,单位/字节", required = false)
    private Long size;

	@ApiModelProperty(name = "length", value = "视频长度,单位秒", required = false)
    private Long length;

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

	@ApiModelProperty(name = "text", value = "内容", required = false)
    private String text;
	
}