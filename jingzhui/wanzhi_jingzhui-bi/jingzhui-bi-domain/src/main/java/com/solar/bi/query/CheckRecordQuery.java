package com.solar.bi.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "检查记录查询参数")
public class CheckRecordQuery extends BaseQuery {

	@ApiModelProperty(name = "userId", value = "前台用户id", required = false)
    private String userId;

	@ApiModelProperty(name = "checkType", value = "检查类型 JZ:颈椎 YZ:腰椎", required = false)
    private String checkType;

}