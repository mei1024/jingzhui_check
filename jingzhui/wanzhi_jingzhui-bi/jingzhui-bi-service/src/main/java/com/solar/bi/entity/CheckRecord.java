package com.solar.bi.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 检查记录 bi_check_record 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Getter 
@Setter
public class CheckRecord extends ModelCreated {


	/** 前台用户id */
    private String userId;

	/** 检查类型  JZ:颈椎 YZ:腰椎 */
    private String checkType;

	/** 检查结果 */
    private String checkResult;

	/** 检查建议 */
    private String checkSuggest;

	/** 用时 多少秒 */
    private Integer useTimes;

	/** 分数 得多少分 */
    private Integer score;

	/** 备注 */
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
			.append("dstatus", getDstatus())
			.append("creator", getCreator())
			.append("createDate", getCreateDate())
			.append("lastModifier", getLastModifier())
			.append("lastModDate", getLastModDate())
			.append("version", getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CheckRecord == false) return false;
		if(this == obj) return true;
		CheckRecord other = (CheckRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
