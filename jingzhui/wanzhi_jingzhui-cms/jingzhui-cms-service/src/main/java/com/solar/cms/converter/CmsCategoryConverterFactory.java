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
import com.solar.cms.dto.CmsCategoryDto;
import com.solar.cms.entity.CmsCategory;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsCategoryConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsCategoryDto convertCmsCategoryDTO(CmsCategory cmsCategory) {
		BizAssert.notNull(cmsCategory, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategory null");

		CmsCategoryDto result = new CmsCategoryDto();
		result.setId(cmsCategory.getId());
		result.setSiteId(cmsCategory.getSiteId());
		result.setName(cmsCategory.getName());
		result.setParentId(cmsCategory.getParentId());
		result.setTypeId(cmsCategory.getTypeId());
		result.setChildIds(cmsCategory.getChildIds());
		result.setTagTypeIds(cmsCategory.getTagTypeIds());
		result.setCode(cmsCategory.getCode());
		result.setTemplatePath(cmsCategory.getTemplatePath());
		result.setPath(cmsCategory.getPath());
		result.setOnlyUrl(cmsCategory.getOnlyUrl());
		result.setHasStatic(cmsCategory.getHasStatic());
		result.setUrl(cmsCategory.getUrl());
		result.setContentPath(cmsCategory.getContentPath());
		result.setContainChild(cmsCategory.getContainChild());
		result.setPageSize(cmsCategory.getPageSize());
		result.setAllowContribute(cmsCategory.getAllowContribute());
		result.setSortno(cmsCategory.getSortno());
		result.setHidden(cmsCategory.getHidden());
		result.setContents(cmsCategory.getContents());
		result.setExtendId(cmsCategory.getExtendId());
		
		return result;
	}

	public static List<CmsCategoryDto> convertCmsCategoryListDTO(List<CmsCategory> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsCategoryDto>(0);
		}
		
		List<CmsCategoryDto> resultList = new ArrayList<CmsCategoryDto>();
		for (CmsCategory po : list) {
			resultList.add(convertCmsCategoryDTO(po));
		}
		return resultList;
	}
	

	public static CmsCategory convertCmsCategoryEntity(CmsCategoryDto cmsCategory) {
		BizAssert.notNull(cmsCategory, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategory null");
		
		CmsCategory result = new CmsCategory();
		result.setId(cmsCategory.getId());
		result.setSiteId(cmsCategory.getSiteId());
		result.setName(cmsCategory.getName());
		result.setParentId(cmsCategory.getParentId());
		result.setTypeId(cmsCategory.getTypeId());
		result.setChildIds(cmsCategory.getChildIds());
		result.setTagTypeIds(cmsCategory.getTagTypeIds());
		result.setCode(cmsCategory.getCode());
		result.setTemplatePath(cmsCategory.getTemplatePath());
		result.setPath(cmsCategory.getPath());
		result.setOnlyUrl(cmsCategory.getOnlyUrl());
		result.setHasStatic(cmsCategory.getHasStatic());
		result.setUrl(cmsCategory.getUrl());
		result.setContentPath(cmsCategory.getContentPath());
		result.setContainChild(cmsCategory.getContainChild());
		result.setPageSize(cmsCategory.getPageSize());
		result.setAllowContribute(cmsCategory.getAllowContribute());
		result.setSortno(cmsCategory.getSortno());
		result.setHidden(cmsCategory.getHidden());
		result.setContents(cmsCategory.getContents());
		result.setExtendId(cmsCategory.getExtendId());
		
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