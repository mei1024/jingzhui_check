package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "问题答案查询参数")
public class QaItemQuery extends BaseQuery {

	@ApiModelProperty(name = "categroyId", value = "问题分类ID", required = false)
    private String categroyId;

}