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
import com.solar.cms.dto.CmsExpertVideoDto;
import com.solar.cms.entity.CmsExpertVideo;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsExpertVideoConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsExpertVideoDto convertCmsExpertVideoDTO(CmsExpertVideo cmsExpertVideo) {
		BizAssert.notNull(cmsExpertVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpertVideo null");

		CmsExpertVideoDto result = new CmsExpertVideoDto();
		result.setId(cmsExpertVideo.getId());
		result.setExpertId(cmsExpertVideo.getExpertId());
		result.setVideoId(cmsExpertVideo.getVideoId());
		result.setCreatedAt(cmsExpertVideo.getCreateDate());
		result.setCreator(userService.queryUserById(cmsExpertVideo.getCreator()));
		result.setModifiedAt(cmsExpertVideo.getLastModDate());
		result.setModifier(userService.queryUserById(cmsExpertVideo.getLastModifier()));
		
		return result;
	}

	public static List<CmsExpertVideoDto> convertCmsExpertVideoListDTO(List<CmsExpertVideo> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsExpertVideoDto>(0);
		}
		
		List<CmsExpertVideoDto> resultList = new ArrayList<CmsExpertVideoDto>();
		for (CmsExpertVideo po : list) {
			resultList.add(convertCmsExpertVideoDTO(po));
		}
		return resultList;
	}
	

	public static CmsExpertVideo convertCmsExpertVideoEntity(CmsExpertVideoDto cmsExpertVideo) {
		BizAssert.notNull(cmsExpertVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpertVideo null");
		
		CmsExpertVideo result = new CmsExpertVideo();
		result.setId(cmsExpertVideo.getId());
		result.setExpertId(cmsExpertVideo.getExpertId());
		result.setVideoId(cmsExpertVideo.getVideoId());
		
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