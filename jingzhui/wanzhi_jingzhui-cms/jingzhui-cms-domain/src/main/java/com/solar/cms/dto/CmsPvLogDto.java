package com.solar.cms.dto;


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
 * PV记录 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
@ApiModel(value = "PV记录")
public class CmsPvLogDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "访问ID", required = true)
    private String id;

	@ApiModelProperty(name = "objectType", value = "类型ID", required = false)
    private String objectType;

	@ApiModelProperty(name = "objectId", value = "类型对象ID", required = true)
    private String objectId;

	@ApiModelProperty(name = "userId", value = "访问用户ID 匿名用户=0", required = false)
    private String userId;

	@ApiModelProperty(name = "pvtime", value = "访问时间", required = true)
    private java.util.Date pvtime;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("objectType", getObjectType())
			.append("objectId", getObjectId())
			.append("userId", getUserId())
			.append("pvtime", getPvtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsPvLogDto == false) return false;
		if(this == obj) return true;
		CmsPvLogDto other = (CmsPvLogDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}