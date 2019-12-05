package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 问题答案 base_qa_item 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
public class QaItem extends ModelCreated {


	/** 问题分类ID */
    private Long categroyId;

	/** 是否展示  1 展示 0不展示 */
    private Integer isShowFlag;

	/** 问题 */
    private String question;

	/** 答案 */
    private String answer;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("categroyId", getCategroyId())
			.append("isShowFlag", getIsShowFlag())
			.append("question", getQuestion())
			.append("answer", getAnswer())
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
		if(obj instanceof QaItem == false) return false;
		if(this == obj) return true;
		QaItem other = (QaItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
