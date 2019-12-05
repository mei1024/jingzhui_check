package com.solar.bi.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查记录 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
@ApiModel(value = "检查记录")
public class CheckRecordDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "ID", required = true)
    private String id;

	@ApiModelProperty(name = "userId", value = "前台用户id", required = false)
    private String userId;

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

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("userId", getUserId())
			.append("checkType", getCheckType())
			.append("checkResult", getCheckResult())
			.append("checkSuggest", getCheckSuggest())
			.append("useTimes", getUseTimes())
			.append("score", getScore())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CheckRecordDto == false) return false;
		if(this == obj) return true;
		CheckRecordDto other = (CheckRecordDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}