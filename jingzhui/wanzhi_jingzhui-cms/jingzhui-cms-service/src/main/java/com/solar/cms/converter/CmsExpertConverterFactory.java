package com.solar.cms.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.nebula.common.biz.util.BizAssert;
import com.solar.cms.dto.CmsExpertDto;
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.entity.CmsExpert;
import com.solar.cms.service.CmsTagService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsExpertConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	private static CmsTagService cmsTagService;
	
	public static CmsExpertDto convertCmsExpertDTO(CmsExpert cmsExpert) {
		BizAssert.notNull(cmsExpert, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpert null");

		CmsExpertDto result = new CmsExpertDto();
		result.setId(cmsExpert.getId());
		result.setRealName(cmsExpert.getRealName());
		result.setAvatarUrl(cmsExpert.getAvatarUrl());
		result.setDuty(cmsExpert.getDuty());
		if (StringUtils.isNotEmpty(cmsExpert.getDuty())) {
			CmsTagDto dutyTag = cmsTagService.queryCmsTagById(cmsExpert.getDuty());
			result.setDuty(dutyTag != null ? dutyTag.getName() : "");
		}

		result.setOrganization(cmsExpert.getOrganization());
		result.setIntroduction(cmsExpert.getIntroduction());
		result.setGeniusTagIds(cmsExpert.getGeniusTagIds());
		result.setGenius(cmsExpert.getGenius());
		result.setSortno(cmsExpert.getSortno());
		result.setHidden(cmsExpert.getHidden());
		result.setCreatedAt(cmsExpert.getCreateDate());
		result.setModifiedAt(cmsExpert.getLastModDate());
		// 擅长领域标签
		if (StringUtils.isNotEmpty(cmsExpert.getGeniusTagIds())) {
			List<String> tagNames = cmsTagService.queryCmsTagNamesByIds(StringUtils.split(cmsExpert.getGeniusTagIds(), ","));
			result.setGeniusTagNames(tagNames);
		}
		
		return result;
	}

	public static List<CmsExpertDto> convertCmsExpertListDTO(List<CmsExpert> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsExpertDto>(0);
		}
		
		List<CmsExpertDto> resultList = new ArrayList<CmsExpertDto>();
		for (CmsExpert po : list) {
			resultList.add(convertCmsExpertDTO(po));
		}
		return resultList;
	}
	

	public static CmsExpert convertCmsExpertEntity(CmsExpertDto cmsExpert) {
		BizAssert.notNull(cmsExpert, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpert null");
		
		CmsExpert result = new CmsExpert();
		result.setId(cmsExpert.getId());
		result.setRealName(cmsExpert.getRealName());
		result.setAvatarUrl(cmsExpert.getAvatarUrl());
		result.setDuty(cmsExpert.getDuty());
		result.setOrganization(cmsExpert.getOrganization());
		result.setIntroduction(cmsExpert.getIntroduction());
		result.setGeniusTagIds(cmsExpert.getGeniusTagIds());
		result.setGenius(cmsExpert.getGenius());
		result.setSortno(cmsExpert.getSortno());
		result.setHidden(cmsExpert.getHidden());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
		cmsTagService = applicationContext.getBean(CmsTagService.class);
	}
	
}