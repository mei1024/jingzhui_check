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
import com.solar.cms.dto.CmsContentFileDto;
import com.solar.cms.entity.CmsContentFile;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsContentFileConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsContentFileDto convertCmsContentFileDTO(CmsContentFile cmsContentFile) {
		BizAssert.notNull(cmsContentFile, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentFile null");

		CmsContentFileDto result = new CmsContentFileDto();
		result.setId(cmsContentFile.getId());
		result.setContentId(cmsContentFile.getContentId());
		result.setAttachmentId(cmsContentFile.getAttachmentId());
		result.setUserId(cmsContentFile.getUserId());
		result.setName(cmsContentFile.getName());
		result.setUrl(cmsContentFile.getUrl());
		result.setPath(cmsContentFile.getPath());
		result.setSize(cmsContentFile.getSize());
		result.setImage(cmsContentFile.getImage());
		result.setClicks(cmsContentFile.getClicks());
		result.setSortno(cmsContentFile.getSortno());
		result.setMemo(cmsContentFile.getMemo());
			
		return result;
	}

	public static List<CmsContentFileDto> convertCmsContentFileListDTO(List<CmsContentFile> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsContentFileDto>(0);
		}
		
		List<CmsContentFileDto> resultList = new ArrayList<CmsContentFileDto>();
		for (CmsContentFile po : list) {
			resultList.add(convertCmsContentFileDTO(po));
		}
		return resultList;
	}
	

	public static CmsContentFile convertCmsContentFileEntity(CmsContentFileDto cmsContentFile) {
		BizAssert.notNull(cmsContentFile, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentFile null");
		
		CmsContentFile result = new CmsContentFile();
		result.setId(cmsContentFile.getId());
		result.setContentId(cmsContentFile.getContentId());
		result.setAttachmentId(cmsContentFile.getAttachmentId());
		result.setUserId(cmsContentFile.getUserId());
		result.setName(cmsContentFile.getName());
		result.setUrl(cmsContentFile.getUrl());
		result.setPath(cmsContentFile.getPath());
		result.setSize(cmsContentFile.getSize());
		result.setImage(cmsContentFile.getImage());
		result.setClicks(cmsContentFile.getClicks());
		result.setSortno(cmsContentFile.getSortno());
		result.setMemo(cmsContentFile.getMemo());
		
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