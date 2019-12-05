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
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.entity.CmsTag;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsTagConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsTagDto convertCmsTagDTO(CmsTag cmsTag) {
		BizAssert.notNull(cmsTag, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTag null");

		CmsTagDto result = new CmsTagDto();
		result.setId(cmsTag.getId());
		result.setSiteId(cmsTag.getSiteId());
		result.setName(cmsTag.getName());
		result.setTypeId(cmsTag.getTypeId());
		result.setSearchCount(cmsTag.getSearchCount());
		result.setCreatedAt(cmsTag.getCreateDate());
		result.setCreator(userService.queryUserById(cmsTag.getCreator()));
		result.setModifiedAt(cmsTag.getLastModDate());
		result.setModifier(userService.queryUserById(cmsTag.getLastModifier()));
		
		return result;
	}

	public static List<CmsTagDto> convertCmsTagListDTO(List<CmsTag> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsTagDto>(0);
		}
		
		List<CmsTagDto> resultList = new ArrayList<CmsTagDto>();
		for (CmsTag po : list) {
			resultList.add(convertCmsTagDTO(po));
		}
		return resultList;
	}
	

	public static CmsTag convertCmsTagEntity(CmsTagDto cmsTag) {
		BizAssert.notNull(cmsTag, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTag null");
		
		CmsTag result = new CmsTag();
		result.setId(cmsTag.getId());
		result.setSiteId(cmsTag.getSiteId());
		result.setName(cmsTag.getName());
		result.setTypeId(cmsTag.getTypeId());
		result.setSearchCount(cmsTag.getSearchCount());
		
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