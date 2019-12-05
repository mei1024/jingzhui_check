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
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.entity.CmsContentAttribute;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsContentAttributeConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsContentAttributeDto convertCmsContentAttributeDTO(CmsContentAttribute cmsContentAttribute) {
		BizAssert.notNull(cmsContentAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentAttribute null");

		CmsContentAttributeDto result = new CmsContentAttributeDto();
		result.setId(cmsContentAttribute.getId());
		result.setSource(cmsContentAttribute.getSource());
		result.setSourceUrl(cmsContentAttribute.getSourceUrl());
		result.setText(cmsContentAttribute.getText());
		result.setWordCount(cmsContentAttribute.getWordCount());
		result.setCreatedAt(cmsContentAttribute.getCreateDate());
		result.setCreator(userService.queryUserById(cmsContentAttribute.getCreator()));
		result.setModifiedAt(cmsContentAttribute.getLastModDate());
		result.setModifier(userService.queryUserById(cmsContentAttribute.getLastModifier()));
		
		return result;
	}

	public static List<CmsContentAttributeDto> convertCmsContentAttributeListDTO(List<CmsContentAttribute> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsContentAttributeDto>(0);
		}
		
		List<CmsContentAttributeDto> resultList = new ArrayList<CmsContentAttributeDto>();
		for (CmsContentAttribute po : list) {
			resultList.add(convertCmsContentAttributeDTO(po));
		}
		return resultList;
	}
	

	public static CmsContentAttribute convertCmsContentAttributeEntity(CmsContentAttributeDto cmsContentAttribute) {
		BizAssert.notNull(cmsContentAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentAttribute null");
		
		CmsContentAttribute result = new CmsContentAttribute();
		result.setId(cmsContentAttribute.getId());
		result.setSource(cmsContentAttribute.getSource());
		result.setSourceUrl(cmsContentAttribute.getSourceUrl());
		result.setText(cmsContentAttribute.getText());
		result.setWordCount(cmsContentAttribute.getWordCount());
		
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