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
 * 会员日志 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191205
 * 
 */
@Getter 
@Setter
@ApiModel(value = "会员日志")
public class MemberLogDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "主键", required = true)
    private String id;

	@ApiModelProperty(name = "userId", value = "用户id", required = false)
    private String userId;

	@ApiModelProperty(name = "optType", value = "操作类型 10 登陆", required = false)
    private Integer optType;

	@ApiModelProperty(name = "optTime", value = "登录时间", required = false)
    private Long optTime;

	@ApiModelProperty(name = "ip", value = "操作IP", required = false)
    private String ip;

	@ApiModelProperty(name = "ipArea", value = "ip归属地", required = false)
    private String ipArea;

	@ApiModelProperty(name = "optRemark", value = "操作结果", required = false)
    private String optRemark;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("userId", getUserId())
			.append("optType", getOptType())
			.append("optTime", getOptTime())
			.append("ip", getIp())
			.append("ipArea", getIpArea())
			.append("optRemark", getOptRemark())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberLogDto == false) return false;
		if(this == obj) return true;
		MemberLogDto other = (MemberLogDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}