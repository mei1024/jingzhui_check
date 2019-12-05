package com.solar.uc.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员 uc_member 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@Getter 
@Setter
public class Member extends ModelCreated {


	/** 登录账号 */
    private String userName;

	/** 用户状态 UNACTIVE=未激活，NORMAL=正常，LOGOUT=注销，LOCK=冻结 */
    private String status;

	/** 用户等级 */
    private String rank;

	/** 真实姓名 */
    private String realName;

	/** 昵称 */
    private String nickName;

	/** 性别 */
    private String sex;

	/** 出生日期 */
    private String birthday;

	/** 证件类型 ID:身份证 */
    private String idType;

	/** 身份证号 */
    private String idCard;

	/** 头像地址 */
    private String avatarUrl;

	/** 联系电话 */
    private String tel;

	/** 手机 */
    private String mobile;

	/** 电子邮件 */
    private String email;

	/** QQ */
    private String qq;

	/** 家庭电话 */
    private String homePhone;

	/** 家庭住址 */
    private String homeAddress;

	/** 自述 */
    private String intro;

	/** 工作年限 */
    private Integer workYears;

	/** 职业 */
    private String profession;

	/** 办公住址 */
    private String officeAddress;

	/** 省份ID */
    private String provinceId;

	/** 城市ID */
    private String cityId;

	/** 区/县ID */
    private String districtId;

	/** 注册来源 wechat_applet:小程序 pc:PC站 */
    private String regSource;

	/** 注册IP */
    private String regIp;

	/** 注册时间 */
    private Long regTime;

	/** 注册IP区域 */
    private String regArea;

	/** 最后登陆IP */
    private String lastLoginIp;

	/** 最后登陆时间 */
    private Long lastLoginTime;

	/** 最后登陆IP区域 */
    private String lastLoginArea;

	/** 手机是否认证 Y=是 N=否 */
    private String mobileVerification;

	/** 邮箱是否认证 Y=是 N=否 */
    private String emailVerification;

	/** 我的邀请码 */
    private String inviteCode;

	/** 邀请人ID */
    private String inviter;

	/** 是否分销人员 Y:是 N:否 */
    private String isAgent;

	/** 备注 */
    private String memo;

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
		if(obj instanceof Member == false) return false;
		if(this == obj) return true;
		Member other = (Member)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
