package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频 cms_video 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Getter 
@Setter
public class CmsVideo extends ModelCreated {


	/** 封面URL */
    private String coverUrl;

	/** 原视频上传URL */
    private String originUrl;

	/** 视频转码后目标240pURL,即播放地址 */
    private String url240;

	/** 视频转码后目标480pURL,即播放地址 */
    private String url480;

	/** 视频转码后目标720pURL,即播放地址 */
    private String url780;

	/** 视频转码后目标1080pURL,即播放地址 */
    private String url1080;

	/** 私有240视频访问token */
    private String token240;

	/** 私有480视频访问token */
    private String token480;

	/** 私有780视频访问token */
    private String token780;

	/** 私有1080视频访问token */
    private String token1080;

	/** 240视频存储KEY */
    private String key240;

	/** 480视频存储KEY */
    private String key480;

	/** 780视频存储KEY */
    private String key780;

	/** 1080视频存储KEY */
    private String key1080;

	/** 0=处理完成,1=解码处理中 */
    private String vstatus;

	/** 原视频存储KEY */
    private String vkey;

	/** 视频转码任务id */
    private String vavthumbTaskId;

	/** 转码完成: Y=是, N=否 */
    private String vavthumbStatus;

	/** 视频缩略图采样任务id */
    private String vsampleTaskId;

	/** 视频缩略图采样完成: Y=是, N=否 */
    private String vsampleStatus;

	/** 上传IP */
    private String uploadIp;

	/** 上传时间 */
    private Long uploadTime;

	/** 视频大小,单位/字节 */
    private Long size;

	/** 视频长度,单位秒 */
    private Long length;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("coverUrl", getCoverUrl())
			.append("originUrl", getOriginUrl())
			.append("url240", getUrl240())
			.append("url480", getUrl480())
			.append("url780", getUrl780())
			.append("url1080", getUrl1080())
			.append("token240", getToken240())
			.append("token480", getToken480())
			.append("token780", getToken780())
			.append("token1080", getToken1080())
			.append("key240", getKey240())
			.append("key480", getKey480())
			.append("key780", getKey780())
			.append("key1080", getKey1080())
			.append("vstatus", getVstatus())
			.append("vkey", getVkey())
			.append("vavthumbTaskId", getVavthumbTaskId())
			.append("vavthumbStatus", getVavthumbStatus())
			.append("vsampleTaskId", getVsampleTaskId())
			.append("vsampleStatus", getVsampleStatus())
			.append("uploadIp", getUploadIp())
			.append("uploadTime", getUploadTime())
			.append("size", getSize())
			.append("length", getLength())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsVideo == false) return false;
		if(this == obj) return true;
		CmsVideo other = (CmsVideo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
