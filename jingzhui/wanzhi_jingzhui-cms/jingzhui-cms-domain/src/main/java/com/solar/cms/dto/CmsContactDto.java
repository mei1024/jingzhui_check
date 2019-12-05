package com.solar.cms.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.solar.common.core.base.BaseCreatedDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 联系我们 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181113
 * 
 */
@Getter 
@Setter
@ApiModel(value = "联系我们")
public class CmsContactDto extends BaseCreatedDto {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "fullname", value = "姓名", required = true)
    private String fullname;

	@ApiModelProperty(name = "firstName", value = "名", required = false)
    private String firstName;

	@ApiModelProperty(name = "lastName", value = "姓", required = false)
    private String lastName;

	@ApiModelProperty(name = "email", value = "邮箱", required = false)
    private String email;

	@ApiModelProperty(name = "phone", value = "电话号码", required = true)
    private String phone;

	@ApiModelProperty(name = "company", value = "公司名字（30字以内）", required = false)
    private String company;

	@ApiModelProperty(name = "text", value = "描述（200字以内）", required = false)
    private String text;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
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
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContactDto == false) return false;
		if(this == obj) return true;
		CmsContactDto other = (CmsContactDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}