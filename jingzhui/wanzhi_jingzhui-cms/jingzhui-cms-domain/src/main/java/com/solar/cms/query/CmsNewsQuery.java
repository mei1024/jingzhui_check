package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "咨讯查询参数")
public class CmsNewsQuery extends BaseQuery {
	
	@ApiModelProperty(name = "categoryId", value = "分类ID", required = true)
    private String categoryId;

	@ApiModelProperty(name = "provinceId", value = "省份ID", required = false)
    private String provinceId;

	@ApiModelProperty(name = "cityId", value = "城市ID", required = false)
    private String cityId;
 
	@ApiModelProperty(name = "districtId", value = "地区ID", required = false)
    private String districtId;
	
	@ApiModelProperty(name = "villagesId", value = "乡镇ID", required = false)
    private String villagesId;

	@ApiModelProperty(name = "hamletId", value = "村ID", required = false)
    private String hamletId;

	@ApiModelProperty(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false)
    private String minDate;
	
	@ApiModelProperty(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false)
    private String maxDate;
	
	@ApiModelProperty(name = "status", value = "状态：0、草稿 1、已发布 2、待审核", required = true)
    private String status;

	@ApiModelProperty(name = "tagId", value = "标签ID", required = false)
    private String tagId;

	
}