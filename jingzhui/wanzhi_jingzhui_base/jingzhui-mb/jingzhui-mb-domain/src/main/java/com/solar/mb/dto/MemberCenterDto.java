package com.solar.mb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "会员-个人中心")
public class MemberCenterDto implements java.io.Serializable {

	@ApiModelProperty(name = "avatarUrl", value = "头像地址", required = true)
	private String avatarUrl;

	@ApiModelProperty(name = "userName", value = "用户名", required = true)
	private String userName;
	
	@ApiModelProperty(name = "amount", value = "余额", required = true)
    private java.math.BigDecimal amount;

	@ApiModelProperty(name = "accumulativeIncomeAmount", value = "已收入", required = true)
    private java.math.BigDecimal accumulativeIncomeAmount;

	@ApiModelProperty(name = "unsettledAmount", value = "待结算", required = true)
    private java.math.BigDecimal unsettledAmount;

	@ApiModelProperty(name = "orderWaitConfirmCount", value = "订单-待确认个数", required = true)
    private int orderWaitConfirmCount;
	
	@ApiModelProperty(name = "orderUnsettledCount", value = "订单-待结算个数", required = true)
    private int orderUnsettledCount;

}