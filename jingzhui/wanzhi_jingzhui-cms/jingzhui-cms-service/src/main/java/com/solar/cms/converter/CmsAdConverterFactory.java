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

import com.alibaba.druid.util.StringUtils;
import com.nebula.common.biz.util.BizAssert;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.entity.CmsAd;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsAdConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsAdDto convertCmsAdDTO(CmsAd cmsAd) {
		BizAssert.notNull(cmsAd, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsAd null");

		CmsAdDto result = new CmsAdDto();
		result.setId(cmsAd.getId());
		result.setName(cmsAd.getName());
		result.setType(cmsAd.getType());
		result.setContentId(cmsAd.getContentId());
		result.setImgUrl(cmsAd.getImgUrl());
		result.setUrl(cmsAd.getImgUrl());
		result.setLinkUrl(cmsAd.getLinkUrl());
		result.setStatus(cmsAd.getStatus());
		result.setSortno(cmsAd.getSortno());
		result.setMemo(cmsAd.getMemo());
		
		return result;
	}

	public static List<CmsAdDto> convertCmsAdListDTO(List<CmsAd> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsAdDto>(0);
		}
		
		List<CmsAdDto> resultList = new ArrayList<CmsAdDto>();
		for (CmsAd po : list) {
			resultList.add(convertCmsAdDTO(po));
		}
		return resultList;
	}
	

	public static CmsAd convertCmsAdEntity(CmsAdDto cmsAd) {
		BizAssert.notNull(cmsAd, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsAd null");
		
		CmsAd result = new CmsAd();
		result.setId(cmsAd.getId());
		result.setName(cmsAd.getName());
		result.setType(cmsAd.getType());
		result.setContentId(cmsAd.getContentId());
		result.setImgUrl(cmsAd.getImgUrl());
		if (StringUtils.isEmpty(result.getImgUrl())) {
			cmsAd.setImgUrl(cmsAd.getUrl());
		}
		
		result.setLinkUrl(cmsAd.getLinkUrl());
		result.setStatus(cmsAd.getStatus());
		result.setSortno(cmsAd.getSortno());
		result.setMemo(cmsAd.getMemo());
		
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