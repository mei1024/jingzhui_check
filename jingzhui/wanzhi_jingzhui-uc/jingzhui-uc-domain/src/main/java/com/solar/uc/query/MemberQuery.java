package com.solar.uc.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "会员查询参数")
public class MemberQuery extends BaseQuery {

	@ApiModelProperty(name = "regBeginDate", value = "注册时间开始日期 YYYY-MM-DD", required = false)
    private String regBeginDate;
	
	@ApiModelProperty(name = "regEndDate", value = "注册时间结束日期 YYYY-MM-DD", required = false)
    private String regEndDate;

	@ApiModelProperty(name = "lastLoginBeginDate", value = "最后登录开始日期 YYYY-MM-DD", required = false)
    private String lastLoginBeginDate;
	
	@ApiModelProperty(name = "lastLoginEndDate", value = "最后登录结束日期 YYYY-MM-DD", required = false)
    private String lastLoginEndDate;
}