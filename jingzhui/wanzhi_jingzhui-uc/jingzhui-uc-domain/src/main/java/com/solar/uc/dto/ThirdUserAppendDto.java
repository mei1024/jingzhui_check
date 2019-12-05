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
 * 第三方用户关系 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20190424
 * 
 */
@Getter 
@Setter
@ApiModel(value = "第三方用户关系")
public class ThirdUserAppendDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "主键", required = true)
    private String id;

	@ApiModelProperty(name = "userId", value = "用户ID", required = false)
    private String userId;

	@ApiModelProperty(name = "openId", value = "第三方OPENID", required = false)
    private String openId;

	@ApiModelProperty(name = "openType", value = "三方类别 微信=WEIXIN,支付宝=ALIPAY", required = false)
    private String openType;

	@ApiModelProperty(name = "requestKey", value = "请求key 小程序的formId", required = false)
    private String requestKey;

	@ApiModelProperty(name = "requestKeyExceed", value = "请求key有效期(秒)", required = false)
    private Integer requestKeyExceed;

	@ApiModelProperty(name = "requestKeyGetTime", value = "请求key获取时间", required = false)
    private Long requestKeyGetTime;

	@ApiModelProperty(name = "useFlag", value = "是否可用 Y:可用 N:不可用", required = false)
    private String useFlag;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("userId", getUserId())
			.append("openId", getOpenId())
			.append("openType", getOpenType())
			.append("requestKey", getRequestKey())
			.append("requestKeyExceed", getRequestKeyExceed())
			.append("requestKeyGetTime", getRequestKeyGetTime())
			.append("useFlag", getUseFlag())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ThirdUserAppendDto == false) return false;
		if(this == obj) return true;
		ThirdUserAppendDto other = (ThirdUserAppendDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}