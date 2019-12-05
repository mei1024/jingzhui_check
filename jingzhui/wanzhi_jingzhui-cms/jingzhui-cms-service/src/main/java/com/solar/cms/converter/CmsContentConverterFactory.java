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
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.entity.CmsContent;
import com.solar.cms.service.CmsContentAttributeService;
import com.solar.cms.service.CmsContentFileService;
import com.solar.cms.service.CmsContentService;
import com.solar.cms.service.CmsTagService;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsContentConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;
	private static CmsContentAttributeService cmsContentAttributeService;
	private static CmsContentFileService cmsContentFileService;
	private static CmsTagService cmsTagService;

	public static CmsContentDto convertCmsContentDTO(CmsContent cmsContent) {
		return convertCmsContentDTO(cmsContent, false);
	}
	
	public static CmsContentDto convertCmsContentDTO(CmsContent cmsContent, boolean islist) {
		BizAssert.notNull(cmsContent, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContent null");

		CmsContentDto result = new CmsContentDto();
		result.setId(cmsContent.getId());
		result.setSiteId(cmsContent.getSiteId());
		result.setTitle(cmsContent.getTitle());
		result.setUserId(cmsContent.getUserId());
		result.setCheckUserId(cmsContent.getCheckUserId());
		result.setCategoryId(cmsContent.getCategoryId());
		result.setModelId(cmsContent.getModelId());
		result.setParentId(cmsContent.getParentId());
		result.setCopied(cmsContent.getCopied());
		result.setAuthor(cmsContent.getAuthor());
		result.setEditor(cmsContent.getEditor());
		result.setOnlyUrl(cmsContent.getOnlyUrl());
		result.setHasImages(cmsContent.getHasImages());
		result.setHasFiles(cmsContent.getHasFiles());
		result.setHasStatic(cmsContent.getHasStatic());
		result.setUrl(cmsContent.getUrl());
		result.setDescription(cmsContent.getDescription());
		result.setTagIds(cmsContent.getTagIds());
		result.setCover(cmsContent.getCover());
		result.setComments(cmsContent.getComments());
		result.setClicks(cmsContent.getClicks());
		result.setPublishDate(cmsContent.getPublishDate());
		result.setCheckDate(cmsContent.getCheckDate());
		result.setSortno(cmsContent.getSortno());
		result.setStatus(cmsContent.getStatus());
		result.setCreatedAt(cmsContent.getCreateDate());
		result.setCreator(userService.queryUserById(cmsContent.getCreator()));
		result.setModifiedAt(cmsContent.getLastModDate());
		result.setModifier(userService.queryUserById(cmsContent.getLastModifier()));
		
		if (!islist) {
			// 内容扩展属性
			CmsContentAttributeDto contentAttribute = cmsContentAttributeService.queryCmsContentAttributeById(cmsContent.getId());
			if (contentAttribute != null) {
				result.setSource(contentAttribute.getSource());
				result.setSourceUrl(contentAttribute.getSourceUrl());
				result.setText(contentAttribute.getText());
				result.setWordCount(contentAttribute.getWordCount());
			}
		}
		// 内容标签
		if (StringUtils.isNotEmpty(cmsContent.getTagIds())) {
			List<String> tagNames = cmsTagService.queryCmsTagNamesByIds(StringUtils.split(cmsContent.getTagIds(), ","));
			result.setTagNames(tagNames);
		}
		
		return result;
	}

	public static List<CmsContentDto> convertCmsContentListDTO(List<CmsContent> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsContentDto>(0);
		}
		
		List<CmsContentDto> resultList = new ArrayList<CmsContentDto>();
		for (CmsContent po : list) {
			resultList.add(convertCmsContentDTO(po, true));
		}
		return resultList;
	}
	

	public static CmsContent convertCmsContentEntity(CmsContentDto cmsContent) {
		BizAssert.notNull(cmsContent, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContent null");
		
		CmsContent result = new CmsContent();
		result.setId(cmsContent.getId());
		result.setSiteId(cmsContent.getSiteId());
		result.setTitle(cmsContent.getTitle());
		result.setUserId(cmsContent.getUserId());
		result.setCheckUserId(cmsContent.getCheckUserId());
		result.setCategoryId(cmsContent.getCategoryId());
		result.setModelId(cmsContent.getModelId());
		result.setParentId(cmsContent.getParentId());
		result.setCopied(cmsContent.getCopied());
		result.setAuthor(cmsContent.getAuthor());
		result.setEditor(cmsContent.getEditor());
		result.setOnlyUrl(cmsContent.getOnlyUrl());
		result.setHasImages(cmsContent.getHasImages());
		result.setHasFiles(cmsContent.getHasFiles());
		result.setHasStatic(cmsContent.getHasStatic());
		result.setUrl(cmsContent.getUrl());
		result.setDescription(cmsContent.getDescription());
		result.setTagIds(cmsContent.getTagIds());
		result.setCover(cmsContent.getCover());
		result.setComments(cmsContent.getComments());
		result.setClicks(cmsContent.getClicks());
		result.setPublishDate(cmsContent.getPublishDate());
		result.setCheckDate(cmsContent.getCheckDate());
		result.setSortno(cmsContent.getSortno());
		result.setStatus(cmsContent.getStatus());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
		
		cmsContentAttributeService = applicationContext.getBean(CmsContentAttributeService.class);
		cmsContentFileService = applicationContext.getBean(CmsContentFileService.class);
		cmsTagService = applicationContext.getBean(CmsTagService.class);

	}
	
}