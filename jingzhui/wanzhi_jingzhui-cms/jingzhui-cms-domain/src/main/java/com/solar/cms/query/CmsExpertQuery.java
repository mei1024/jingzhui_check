package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "专家查询参数")
public class CmsExpertQuery extends BaseQuery {
	
	@ApiModelProperty(name = "duty", value = "专家职务", required = false)
    private String duty;

	@ApiModelProperty(name = "organization", value = "所在机构", required = false)
    private String organization;

	@ApiModelProperty(name = "geniusTagId", value = "擅长领域标签ID", required = false)
    private String geniusTagId;

	@ApiModelProperty(name = "hidden", value = "隐藏 Y=是,N=否", required = false)
    private String hidden;
	
	@ApiModelProperty(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false)
    private String minDate;
	
	@ApiModelProperty(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false)
    private String maxDate;

}