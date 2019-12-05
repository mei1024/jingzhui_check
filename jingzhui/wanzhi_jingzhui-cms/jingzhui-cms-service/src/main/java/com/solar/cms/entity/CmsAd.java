package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 广告 cms_ad 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Getter 
@Setter
public class CmsAd extends ModelCreated {


	/** 名称 */
    private String name;

	/** 类型: CONTENT_DETAILS_BANNER=咨讯,视频详情轮播 */
    private String type;

	/** 内容ID,指定type类型内容ID */
    private String contentId;

	/** 图片地址 */
    private String imgUrl;

	/** 外链地址 */
    private String linkUrl;

	/** 状态 ENABLED=正常，DISABLED=下线 */
    private String status;

	/** 顺序 */
    private Integer sortno;

	/** 备注 */
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
		if(obj instanceof CmsAd == false) return false;
		if(this == obj) return true;
		CmsAd other = (CmsAd)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
