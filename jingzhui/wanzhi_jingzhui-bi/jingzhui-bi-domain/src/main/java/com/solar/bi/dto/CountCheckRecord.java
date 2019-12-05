package com.solar.bi.dto;


import com.solar.common.bean.CodeText;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @declaration 统计检查记录 数据DTO对象
 * @author mw
 * @time 2019-12-05 10:59
 */
@Getter 
@Setter
@ApiModel(value = "统计检查记录")
public class CountCheckRecord {

	@ApiModelProperty(name = "checkType", value = "检查类型  JZ:颈椎 YZ:腰椎", required = false)
    private CodeText checkType;

	@ApiModelProperty(name = "personNumber", value = "使用人数", required = false)
    private Integer personNumber;

	@ApiModelProperty(name = "useNumber", value = "使用次数", required = false)
    private Integer useNumber;

}