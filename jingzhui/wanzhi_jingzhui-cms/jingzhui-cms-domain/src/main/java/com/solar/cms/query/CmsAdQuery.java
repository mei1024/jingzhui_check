package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "广告查询参数")
public class CmsAdQuery extends BaseQuery {
	
	@ApiModelProperty(name = "type", value = "类型: CONTENT_DETAILS_BANNER=咨讯,视频详情轮播", required = true)
    private String type;

}