package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 联系我们 cms_contact 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181113
 * 
 */
@Getter 
@Setter
public class CmsContact extends ModelCreated {


	/** 姓名 */
    private String fullname;

	/** 名 */
    private String firstName;

	/** 姓 */
    private String lastName;

	/** 邮箱 */
    private String email;

	/** 电话号码 */
    private String phone;

	/** 公司名字（30字以内） */
    private String company;

	/** 描述（200字以内） */
    private String text;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("fullname", getFullname())
			.append("firstName", getFirstName())
			.append("lastName", getLastName())
			.append("email", getEmail())
			.append("phone", getPhone())
			.append("company", getCompany())
			.append("text", getText())
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
		if(obj instanceof CmsContact == false) return false;
		if(this == obj) return true;
		CmsContact other = (CmsContact)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
