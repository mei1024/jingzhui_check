package com.solar.cms.query;

import com.solar.common.core.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "视频查询参数")
public class CmsVideoQuery extends BaseQuery {
	
	@ApiModelProperty(name = "categoryId", value = "分类ID", required = true)
    private String categoryId;

	@ApiModelProperty(name = "vstatus", value = "0=处理完成,1=解码处理中", required = false)
    private String vstatus;

	@ApiModelProperty(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false)
    private String minDate;
	
	@ApiModelProperty(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false)
    private String maxDate;
	
	@ApiModelProperty(name = "status", value = "状态：0、草稿 1、已发布 2、待审核", required = true)
    private String status;

	@ApiModelProperty(name = "tagId", value = "标签ID", required = false)
    private String tagId;
	
	@ApiModelProperty(name = "expertId", value = "专家ID", required = true)
    private String expertId;

}