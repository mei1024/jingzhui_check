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
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.dto.CmsExpertDto;
import com.solar.cms.dto.CmsExpertVideoDto;
import com.solar.cms.dto.CmsVideoDto;
import com.solar.cms.entity.CmsVideo;
import com.solar.cms.service.CmsContentAttributeService;
import com.solar.cms.service.CmsContentService;
import com.solar.cms.service.CmsExpertService;
import com.solar.cms.service.CmsExpertVideoService;
import com.solar.cms.service.CmsTagService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsVideoConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;
	
	private static CmsContentService cmsContentService;
	private static CmsContentAttributeService cmsContentAttributeService;
	private static CmsTagService cmsTagService;
	private static CmsExpertVideoService cmsExpertVideoService;
	private static CmsExpertService cmsExpertService;
	
	public static CmsVideoDto convertCmsVideoDTO(CmsVideo cmsVideo) {
		BizAssert.notNull(cmsVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsVideo null");

		CmsVideoDto result = new CmsVideoDto();
		result.setId(cmsVideo.getId());
		result.setOriginUrl(cmsVideo.getOriginUrl());
		
		if (StringUtils.equalsIgnoreCase(cmsVideo.getVsampleStatus(), "Y")) {
			result.setCover(cmsVideo.getCoverUrl());
		}
		
		result.setUrl240(cmsVideo.getUrl240());
		result.setUrl480(cmsVideo.getUrl480());
		result.setUrl780(cmsVideo.getUrl780());
		result.setUrl1080(cmsVideo.getUrl1080());
		result.setToken240(cmsVideo.getToken240());
		result.setToken480(cmsVideo.getToken480());
		result.setToken780(cmsVideo.getToken780());
		result.setToken1080(cmsVideo.getToken1080());
		result.setKey240(cmsVideo.getKey240());
		result.setKey480(cmsVideo.getKey480());
		result.setKey780(cmsVideo.getKey780());
		result.setKey1080(cmsVideo.getKey1080());
		result.setVstatus(cmsVideo.getVstatus());
		result.setVkey(cmsVideo.getVkey());
		result.setVavthumbTaskId(cmsVideo.getVavthumbTaskId());
		result.setVavthumbStatus(cmsVideo.getVavthumbStatus());
		result.setVsampleTaskId(cmsVideo.getVsampleTaskId());
		result.setVsampleStatus(cmsVideo.getVsampleStatus());
		result.setUploadIp(cmsVideo.getUploadIp());
		result.setUploadTime(cmsVideo.getUploadTime());
		result.setSize(cmsVideo.getSize());
		result.setLength(cmsVideo.getLength());

		// 专家信息
		CmsExpertVideoDto cmsExpertVideo = cmsExpertVideoService.queryCmsExpertVideoByVideoId(cmsVideo.getId());
		if (cmsExpertVideo != null) {
			CmsExpertDto expert = cmsExpertService.queryCmsExpertById(cmsExpertVideo.getExpertId());
			result.setExpertId(cmsExpertVideo.getExpertId());
			result.setExpert(expert);
		}
		
		// 内容信息
		CmsContentDto content = cmsContentService.queryCmsContentById(cmsVideo.getId());
		if (content != null) {
			result.setId(content.getId());
			result.setCategoryId(content.getCategoryId());
			result.setTitle(content.getTitle());
			result.setUserId(content.getUserId());
			result.setCheckUserId(content.getCheckUserId());
			result.setAuthor(content.getAuthor());
			result.setEditor(content.getEditor());
			result.setDescription(content.getDescription());
			result.setTagIds(content.getTagIds());
			result.setComments(content.getComments());
			result.setClicks(content.getClicks());
			result.setPublishDate(content.getPublishDate());
			result.setCheckDate(content.getCheckDate());
			result.setSortno(content.getSortno());
			result.setStatus(content.getStatus());
			
			result.setCreatedAt(content.getCreatedAt());
			result.setCreator(content.getCreator());
			result.setModifiedAt(content.getModifiedAt());
			result.setModifier(content.getModifier());
			
			// 内容扩展属性
			CmsContentAttributeDto contentAttribute = cmsContentAttributeService.queryCmsContentAttributeById(content.getId());
			if (contentAttribute != null) {
				result.setSource(contentAttribute.getSource());
				result.setText(contentAttribute.getText());
			}
			
			// 视频标签
			if (StringUtils.isNotEmpty(content.getTagIds())) {
				List<String> tagNames = cmsTagService.queryCmsTagNamesByIds(StringUtils.split(content.getTagIds(), ","));
				result.setTagNames(tagNames);
			}
			
		}
		
		return result;
	}

	public static List<CmsVideoDto> convertCmsVideoListDTO(List<CmsVideo> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsVideoDto>(0);
		}
		
		List<CmsVideoDto> resultList = new ArrayList<CmsVideoDto>();
		for (CmsVideo po : list) {
			resultList.add(convertCmsVideoDTO(po));
		}
		return resultList;
	}
	

	public static CmsVideo convertCmsVideoEntity(CmsVideoDto cmsVideo) {
		BizAssert.notNull(cmsVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsVideo null");
		
		CmsVideo result = new CmsVideo();
		result.setId(cmsVideo.getId());
		result.setCoverUrl(cmsVideo.getCover());
		result.setOriginUrl(cmsVideo.getOriginUrl());
		result.setUrl240(cmsVideo.getUrl240());
		result.setUrl480(cmsVideo.getUrl480());
		result.setUrl780(cmsVideo.getUrl780());
		result.setUrl1080(cmsVideo.getUrl1080());
		result.setToken240(cmsVideo.getToken240());
		result.setToken480(cmsVideo.getToken480());
		result.setToken780(cmsVideo.getToken780());
		result.setToken1080(cmsVideo.getToken1080());
		result.setKey240(cmsVideo.getKey240());
		result.setKey480(cmsVideo.getKey480());
		result.setKey780(cmsVideo.getKey780());
		result.setKey1080(cmsVideo.getKey1080());
		result.setVstatus(cmsVideo.getVstatus());
		result.setVkey(cmsVideo.getVkey());
		result.setVavthumbTaskId(cmsVideo.getVavthumbTaskId());
		result.setVavthumbStatus(cmsVideo.getVavthumbStatus());
		result.setVsampleTaskId(cmsVideo.getVsampleTaskId());
		result.setVsampleStatus(cmsVideo.getVsampleStatus());
		result.setUploadIp(cmsVideo.getUploadIp());
		result.setUploadTime(cmsVideo.getUploadTime());
		result.setSize(cmsVideo.getSize());
		result.setLength(cmsVideo.getLength());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
		
		cmsContentService = applicationContext.getBean(CmsContentService.class);
		cmsContentAttributeService = applicationContext.getBean(CmsContentAttributeService.class);
		cmsTagService = applicationContext.getBean(CmsTagService.class);

		cmsExpertVideoService = applicationContext.getBean(CmsExpertVideoService.class);
		cmsExpertService = applicationContext.getBean(CmsExpertService.class);

	}
	
}