package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "PV记录查询参数")
public class CmsPvLogQuery extends BaseQuery {

}