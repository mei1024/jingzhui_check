package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * PV记录 cms_pv_log 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsPvLog extends ModelCreated {


	/** 类型ID */
    private String objectType;

	/** 类型对象ID */
    private String objectId;

	/** 访问用户ID 匿名用户=0 */
    private String userId;

	/** 访问时间 */
    private java.util.Date pvtime;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("objectType", getObjectType())
			.append("objectId", getObjectId())
			.append("userId", getUserId())
			.append("pvtime", getPvtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsPvLog == false) return false;
		if(this == obj) return true;
		CmsPvLog other = (CmsPvLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
