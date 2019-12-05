package com.solar.uc.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员日志 uc_member_log 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@Getter 
@Setter
public class MemberLog extends ModelCreated {


	/** 用户id */
    private String userId;

	/** 操作类型 10 登陆 */
    private Integer optType;

	/** 登录时间 */
    private Long optTime;

	/** 操作IP */
    private String ip;

	/** ip归属地 */
    private String ipArea;

	/** 操作结果 */
    private String optRemark;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("userId", getUserId())
			.append("optType", getOptType())
			.append("optTime", getOptTime())
			.append("ip", getIp())
			.append("ipArea", getIpArea())
			.append("optRemark", getOptRemark())
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
		if(obj instanceof MemberLog == false) return false;
		if(this == obj) return true;
		MemberLog other = (MemberLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
