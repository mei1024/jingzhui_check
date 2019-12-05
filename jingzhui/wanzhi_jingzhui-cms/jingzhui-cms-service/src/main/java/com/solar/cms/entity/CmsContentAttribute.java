package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 内容扩展 cms_content_attribute 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsContentAttribute extends ModelCreated {


	/** 内容来源 */
    private String source;

	/** 来源地址 */
    private String sourceUrl;

	/** 内容 */
    private String text;

	/** 字数 */
    private Integer wordCount;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("source", getSource())
			.append("sourceUrl", getSourceUrl())
			.append("text", getText())
			.append("wordCount", getWordCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContentAttribute == false) return false;
		if(this == obj) return true;
		CmsContentAttribute other = (CmsContentAttribute)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
