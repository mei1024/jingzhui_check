package com.solar.bi.dto;


import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查记录创建 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
@ApiModel(value = "检查记录创建")
public class CheckRecordCreateDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "checkType", value = "检查类型  JZ:颈椎 YZ:腰椎", required = false)
    private String checkType;

	@ApiModelProperty(name = "checkResult", value = "检查结果", required = false)
    private String checkResult;

	@ApiModelProperty(name = "checkSuggest", value = "检查建议", required = false)
    private String checkSuggest;

	@ApiModelProperty(name = "useTimes", value = "用时 多少秒", required = false)
    private Integer useTimes;

	@ApiModelProperty(name = "score", value = "分数 得多少分", required = false)
    private Integer score;

}