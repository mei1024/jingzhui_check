package com.solar.job.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "任务定时器查询参数")
public class JobCronTimerQuery extends BaseQuery {

}