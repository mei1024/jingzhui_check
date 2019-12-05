package com.solar.job.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "一次性定时器查询参数")
public class JobOnceTimerQuery extends BaseQuery {

}