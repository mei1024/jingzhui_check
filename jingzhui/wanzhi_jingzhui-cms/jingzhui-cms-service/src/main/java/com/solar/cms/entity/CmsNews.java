package com.solar.cms.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 咨讯 cms_news 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Getter 
@Setter
public class CmsNews extends ModelCreated {


	/** 省份ID */
    private String provinceId;

	/** 城市ID */
    private String cityId;

	/** 地区ID */
    private String districtId;

	/** 乡镇ID */
    private String villagesId;

	/** 村ID */
    private String hamletId;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("provinceId", getProvinceId())
			.append("cityId", getCityId())
			.append("districtId", getDistrictId())
			.append("villagesId", getVillagesId())
			.append("hamletId", getHamletId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsNews == false) return false;
		if(this == obj) return true;
		CmsNews other = (CmsNews)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
