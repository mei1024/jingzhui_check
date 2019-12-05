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
import com.solar.cms.dto.CmsTagTypeDto;
import com.solar.cms.entity.CmsTagType;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsTagTypeConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsTagTypeDto convertCmsTagTypeDTO(CmsTagType cmsTagType) {
		BizAssert.notNull(cmsTagType, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTagType null");

		CmsTagTypeDto result = new CmsTagTypeDto();
		result.setId(cmsTagType.getId());
		result.setSiteId(cmsTagType.getSiteId());
		result.setName(cmsTagType.getName());
		result.setCount(cmsTagType.getCount());
		
		return result;
	}

	public static List<CmsTagTypeDto> convertCmsTagTypeListDTO(List<CmsTagType> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsTagTypeDto>(0);
		}
		
		List<CmsTagTypeDto> resultList = new ArrayList<CmsTagTypeDto>();
		for (CmsTagType po : list) {
			resultList.add(convertCmsTagTypeDTO(po));
		}
		return resultList;
	}
	

	public static CmsTagType convertCmsTagTypeEntity(CmsTagTypeDto cmsTagType) {
		BizAssert.notNull(cmsTagType, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTagType null");
		
		CmsTagType result = new CmsTagType();
		result.setId(cmsTagType.getId());
		result.setSiteId(cmsTagType.getSiteId());
		result.setName(cmsTagType.getName());
		result.setCount(cmsTagType.getCount());
		
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