package com.solar.uc.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.nebula.common.biz.util.BizAssert;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;
import com.solar.uc.dto.MemberDto;
import com.solar.uc.dto.MemberQueryDto;
import com.solar.uc.entity.Member;
import com.solar.uc.service.MemberAttributeService;

@Component
@Lazy(false) 
public final class MemberConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;
	
	private static MemberAttributeService memberAttributeService;

	public static MemberQueryDto convertMemberQueryDTO(Member member) {
		BizAssert.notNull(member, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:member null");

		MemberQueryDto result = new MemberQueryDto();
		result.setId(member.getId());
		result.setNickName(member.getNickName());
		result.setAvatarUrl(member.getAvatarUrl());
		result.setSex(member.getSex());
		result.setProfession(member.getProfession());
		
		return result;
	}
	
	public static MemberDto convertMemberDTO(Member member) {
		BizAssert.notNull(member, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:member null");

		MemberDto result = new MemberDto();
		result.setId(member.getId());
		result.setUserName(member.getUserName());
		result.setStatus(member.getStatus());
		result.setRank(member.getRank());
		result.setRealName(member.getRealName());
		result.setNickName(member.getNickName());
		result.setSex(member.getSex());
		result.setBirthday(member.getBirthday());
		result.setIdType(member.getIdType());
		result.setIdCard(member.getIdCard());
		result.setAvatarUrl(member.getAvatarUrl());
		result.setTel(member.getTel());
		result.setMobile(member.getMobile());
		result.setEmail(member.getEmail());
		result.setQq(member.getQq());
		result.setHomePhone(member.getHomePhone());
		result.setHomeAddress(member.getHomeAddress());
		result.setIntro(member.getIntro());
		result.setWorkYears(member.getWorkYears());
		result.setProfession(member.getProfession());
		result.setOfficeAddress(member.getOfficeAddress());
		result.setProvinceId(member.getProvinceId());
		result.setCityId(member.getCityId());
		result.setDistrictId(member.getDistrictId());
		result.setRegSource(member.getRegSource());
		result.setRegIp(member.getRegIp());
		result.setRegTime(member.getRegTime());
		result.setRegArea(member.getRegArea());
		result.setLastLoginIp(member.getLastLoginIp());
		result.setLastLoginTime(member.getLastLoginTime());
		result.setLastLoginArea(member.getLastLoginArea());
		result.setMobileVerification(member.getMobileVerification());
		result.setEmailVerification(member.getEmailVerification());
		result.setInviteCode(member.getInviteCode());
		result.setInviter(member.getInviter());
		result.setIsAgent(member.getIsAgent());
		result.setMemo(member.getMemo());
		result.setCreatedAt(member.getCreateDate());
		result.setCreator(userService.queryUserById(member.getCreator()));
		result.setModifiedAt(member.getLastModDate());
		result.setModifier(userService.queryUserById(member.getLastModifier()));
		// 附加属性
		result.setAttribute(memberAttributeService.queryMemberAttributeById(result.getId()));
		
		return result;
	}

	public static List<MemberDto> convertMemberListDTO(List<Member> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<MemberDto>(0);
		}
		
		List<MemberDto> resultList = new ArrayList<MemberDto>();
		for (Member po : list) {
			resultList.add(convertMemberDTO(po));
		}
		return resultList;
	}
	

	public static Member convertMemberEntity(MemberDto member) {
		BizAssert.notNull(member, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:member null");
		
		Member result = new Member();
		result.setId(member.getId());
		result.setUserName(member.getUserName());
		result.setStatus(member.getStatus());
		result.setRank(member.getRank());
		result.setRealName(member.getRealName());
		result.setNickName(member.getNickName());
		result.setSex(member.getSex());
		result.setBirthday(member.getBirthday());
		result.setIdType(member.getIdType());
		result.setIdCard(member.getIdCard());
		result.setAvatarUrl(member.getAvatarUrl());
		result.setTel(member.getTel());
		result.setMobile(member.getMobile());
		result.setEmail(member.getEmail());
		result.setQq(member.getQq());
		result.setHomePhone(member.getHomePhone());
		result.setHomeAddress(member.getHomeAddress());
		result.setIntro(member.getIntro());
		result.setWorkYears(member.getWorkYears());
		result.setProfession(member.getProfession());
		result.setOfficeAddress(member.getOfficeAddress());
		result.setProvinceId(member.getProvinceId());
		result.setCityId(member.getCityId());
		result.setDistrictId(member.getDistrictId());
		result.setRegSource(member.getRegSource());
		result.setRegIp(member.getRegIp());
		result.setRegTime(member.getRegTime());
		result.setRegArea(member.getRegArea());
		result.setLastLoginIp(member.getLastLoginIp());
		result.setLastLoginTime(member.getLastLoginTime());
		result.setLastLoginArea(member.getLastLoginArea());
		result.setMobileVerification(member.getMobileVerification());
		result.setEmailVerification(member.getEmailVerification());
		result.setInviteCode(member.getInviteCode());
		result.setInviter(member.getInviter());
		result.setIsAgent(member.getIsAgent());
		result.setMemo(member.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
		memberAttributeService = applicationContext.getBean(MemberAttributeService.class);
	}
	
}