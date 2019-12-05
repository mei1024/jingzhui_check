package com.solar.uc.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 会员扩展信息 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
@ApiModel(value = "会员扩展信息")
public class MemberAttributeDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "用户ID", required = true)
    private String id;

	@ApiModelProperty(name = "age", value = "年龄", required = false)
    private String age;

	@ApiModelProperty(name = "height", value = "身高", required = false)
    private String height;

	@ApiModelProperty(name = "weight", value = "体重", required = false)
    private String weight;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("age", getAge())
			.append("height", getHeight())
			.append("weight", getWeight())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberAttributeDto == false) return false;
		if(this == obj) return true;
		MemberAttributeDto other = (MemberAttributeDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}