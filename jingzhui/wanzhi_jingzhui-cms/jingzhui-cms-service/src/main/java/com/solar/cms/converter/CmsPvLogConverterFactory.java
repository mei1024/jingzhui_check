package com.solar.cms.converter;

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
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.entity.CmsPvLog;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsPvLogConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsPvLogDto convertCmsPvLogDTO(CmsPvLog cmsPvLog) {
		BizAssert.notNull(cmsPvLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsPvLog null");

		CmsPvLogDto result = new CmsPvLogDto();
		result.setId(cmsPvLog.getId());
		result.setObjectType(cmsPvLog.getObjectType());
		result.setObjectId(cmsPvLog.getObjectId());
		result.setUserId(cmsPvLog.getUserId());
		result.setPvtime(cmsPvLog.getPvtime());
		result.setCreatedAt(cmsPvLog.getCreateDate());
		result.setCreator(userService.queryUserById(cmsPvLog.getCreator()));
		result.setModifiedAt(cmsPvLog.getLastModDate());
		result.setModifier(userService.queryUserById(cmsPvLog.getLastModifier()));
		
		return result;
	}

	public static List<CmsPvLogDto> convertCmsPvLogListDTO(List<CmsPvLog> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsPvLogDto>(0);
		}
		
		List<CmsPvLogDto> resultList = new ArrayList<CmsPvLogDto>();
		for (CmsPvLog po : list) {
			resultList.add(convertCmsPvLogDTO(po));
		}
		return resultList;
	}
	

	public static CmsPvLog convertCmsPvLogEntity(CmsPvLogDto cmsPvLog) {
		BizAssert.notNull(cmsPvLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsPvLog null");
		
		CmsPvLog result = new CmsPvLog();
		result.setId(cmsPvLog.getId());
		result.setObjectType(cmsPvLog.getObjectType());
		result.setObjectId(cmsPvLog.getObjectId());
		result.setUserId(cmsPvLog.getUserId());
		result.setPvtime(cmsPvLog.getPvtime());
		
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