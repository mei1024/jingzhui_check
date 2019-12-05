package com.solar.uc.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "会员日志查询参数")
public class MemberLogQuery extends BaseQuery {

}