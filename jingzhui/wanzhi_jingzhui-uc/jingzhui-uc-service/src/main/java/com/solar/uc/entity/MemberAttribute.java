package com.solar.uc.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员扩展信息 uc_member_attribute 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
public class MemberAttribute extends ModelCreated {


	/** 年龄 */
    private String age;

	/** 身高 */
    private String height;

	/** 体重 */
    private String weight;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("age", getAge())
			.append("height", getHeight())
			.append("weight", getWeight())
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
		if(obj instanceof MemberAttribute == false) return false;
		if(this == obj) return true;
		MemberAttribute other = (MemberAttribute)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
