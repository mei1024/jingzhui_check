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
import com.solar.uc.dto.MemberLogDto;
import com.solar.uc.entity.MemberLog;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class MemberLogConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static MemberLogDto convertMemberLogDTO(MemberLog memberLog) {
		BizAssert.notNull(memberLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberLog null");

		MemberLogDto result = new MemberLogDto();
		result.setId(memberLog.getId());
		result.setUserId(memberLog.getUserId());
		result.setOptType(memberLog.getOptType());
		result.setOptTime(memberLog.getOptTime());
		result.setIp(memberLog.getIp());
		result.setIpArea(memberLog.getIpArea());
		result.setOptRemark(memberLog.getOptRemark());
		result.setMemo(memberLog.getMemo());
		result.setCreatedAt(memberLog.getCreateDate());
		result.setCreator(userService.queryUserById(memberLog.getCreator()));
		result.setModifiedAt(memberLog.getLastModDate());
		result.setModifier(userService.queryUserById(memberLog.getLastModifier()));
		
		return result;
	}

	public static List<MemberLogDto> convertMemberLogListDTO(List<MemberLog> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<MemberLogDto>(0);
		}
		
		List<MemberLogDto> resultList = new ArrayList<MemberLogDto>();
		for (MemberLog po : list) {
			resultList.add(convertMemberLogDTO(po));
		}
		return resultList;
	}
	

	public static MemberLog convertMemberLogEntity(MemberLogDto memberLog) {
		BizAssert.notNull(memberLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberLog null");
		
		MemberLog result = new MemberLog();
		result.setId(memberLog.getId());
		result.setUserId(memberLog.getUserId());
		result.setOptType(memberLog.getOptType());
		result.setOptTime(memberLog.getOptTime());
		result.setIp(memberLog.getIp());
		result.setIpArea(memberLog.getIpArea());
		result.setOptRemark(memberLog.getOptRemark());
		result.setMemo(memberLog.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
	}
	
}