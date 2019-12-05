package com.solar.uc.dto;


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
 * 会员 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@Getter 
@Setter
@ApiModel(value = "会员")
public class MemberDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "用户ID", required = true)
    private String id;

	@ApiModelProperty(name = "userName", value = "登录账号", required = false)
    private String userName;

	@ApiModelProperty(name = "status", value = "用户状态 UNACTIVE=未激活，NORMAL=正常，LOGOUT=注销，LOCK=冻结", required = false)
    private String status;

	@ApiModelProperty(name = "rank", value = "用户等级", required = false)
    private String rank;

	@ApiModelProperty(name = "realName", value = "真实姓名", required = false)
    private String realName;

	@ApiModelProperty(name = "nickName", value = "昵称", required = false)
    private String nickName;

	@ApiModelProperty(name = "sex", value = "性别", required = false)
    private String sex;

	@ApiModelProperty(name = "birthday", value = "出生日期", required = false)
    private String birthday;

	@ApiModelProperty(name = "idType", value = "证件类型 ID:身份证", required = false)
    private String idType;

	@ApiModelProperty(name = "idCard", value = "身份证号", required = false)
    private String idCard;

	@ApiModelProperty(name = "avatarUrl", value = "头像地址", required = false)
    private String avatarUrl;

	@ApiModelProperty(name = "tel", value = "联系电话", required = false)
    private String tel;

	@ApiModelProperty(name = "mobile", value = "手机", required = false)
    private String mobile;

	@ApiModelProperty(name = "email", value = "电子邮件", required = false)
    private String email;

	@ApiModelProperty(name = "qq", value = "QQ", required = false)
    private String qq;

	@ApiModelProperty(name = "homePhone", value = "家庭电话", required = false)
    private String homePhone;

	@ApiModelProperty(name = "homeAddress", value = "家庭住址", required = false)
    private String homeAddress;

	@ApiModelProperty(name = "intro", value = "自述", required = false)
    private String intro;

	@ApiModelProperty(name = "workYears", value = "工作年限", required = false)
    private Integer workYears;

	@ApiModelProperty(name = "profession", value = "职业", required = false)
    private String profession;

	@ApiModelProperty(name = "officeAddress", value = "办公住址", required = false)
    private String officeAddress;

	@ApiModelProperty(name = "provinceId", value = "省份ID", required = false)
    private String provinceId;

	@ApiModelProperty(name = "cityId", value = "城市ID", required = false)
    private String cityId;

	@ApiModelProperty(name = "districtId", value = "区/县ID", required = false)
    private String districtId;

	@ApiModelProperty(name = "regSource", value = "注册来源 wechat_applet:小程序 pc:PC站", required = false)
    private String regSource;

	@ApiModelProperty(name = "regIp", value = "注册IP", required = false)
    private String regIp;

	@ApiModelProperty(name = "regTime", value = "注册时间", required = false)
    private Long regTime;

	@ApiModelProperty(name = "regArea", value = "注册IP区域", required = false)
    private String regArea;

	@ApiModelProperty(name = "lastLoginIp", value = "最后登陆IP", required = false)
    private String lastLoginIp;

	@ApiModelProperty(name = "lastLoginTime", value = "最后登陆时间", required = false)
    private Long lastLoginTime;

	@ApiModelProperty(name = "lastLoginArea", value = "最后登陆IP区域", required = false)
    private String lastLoginArea;

	@ApiModelProperty(name = "mobileVerification", value = "手机是否认证 Y=是 N=否", required = false)
    private String mobileVerification;

	@ApiModelProperty(name = "emailVerification", value = "邮箱是否认证 Y=是 N=否", required = false)
    private String emailVerification;

	@ApiModelProperty(name = "inviteCode", value = "我的邀请码", required = false)
    private String inviteCode;

	@ApiModelProperty(name = "inviter", value = "邀请人ID", required = false)
    private String inviter;

	@ApiModelProperty(name = "isAgent", value = "是否分销人员 Y:是 N:否", required = false)
    private String isAgent;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	
	@ApiModelProperty(name = "attribute", value = "附加属性", required = false)
    private Object attribute;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("userName", getUserName())
			.append("status", getStatus())
			.append("rank", getRank())
			.append("realName", getRealName())
			.append("nickName", getNickName())
			.append("sex", getSex())
			.append("birthday", getBirthday())
			.append("idType", getIdType())
			.append("idCard", getIdCard())
			.append("avatarUrl", getAvatarUrl())
			.append("tel", getTel())
			.append("mobile", getMobile())
			.append("email", getEmail())
			.append("qq", getQq())
			.append("homePhone", getHomePhone())
			.append("homeAddress", getHomeAddress())
			.append("intro", getIntro())
			.append("workYears", getWorkYears())
			.append("profession", getProfession())
			.append("officeAddress", getOfficeAddress())
			.append("provinceId", getProvinceId())
			.append("cityId", getCityId())
			.append("districtId", getDistrictId())
			.append("regSource", getRegSource())
			.append("regIp", getRegIp())
			.append("regTime", getRegTime())
			.append("regArea", getRegArea())
			.append("lastLoginIp", getLastLoginIp())
			.append("lastLoginTime", getLastLoginTime())
			.append("lastLoginArea", getLastLoginArea())
			.append("mobileVerification", getMobileVerification())
			.append("emailVerification", getEmailVerification())
			.append("inviteCode", getInviteCode())
			.append("inviter", getInviter())
			.append("isAgent", getIsAgent())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberDto == false) return false;
		if(this == obj) return true;
		MemberDto other = (MemberDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}