package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标签类型 cms_tag_type 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsTagType extends ModelCreated {


	/** 站点ID */
    private String siteId;

	/** 名称 */
    private String name;

	/** 标签数 */
    private Integer count;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("siteId", getSiteId())
			.append("name", getName())
			.append("count", getCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTagType == false) return false;
		if(this == obj) return true;
		CmsTagType other = (CmsTagType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
