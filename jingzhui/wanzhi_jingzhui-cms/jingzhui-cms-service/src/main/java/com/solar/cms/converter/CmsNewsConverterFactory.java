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
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.dto.CmsNewsDto;
import com.solar.cms.entity.CmsNews;
import com.solar.cms.enums.CmsAdEnum;
import com.solar.cms.service.CmsAdService;
import com.solar.cms.service.CmsContentAttributeService;
import com.solar.cms.service.CmsContentFileService;
import com.solar.cms.service.CmsContentService;
import com.solar.cms.service.CmsTagService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;
import com.solar.ds.dto.CityDto;
import com.solar.ds.dto.DistrictDto;
import com.solar.ds.dto.HamletDto;
import com.solar.ds.dto.ProvinceDto;
import com.solar.ds.dto.VillagesDto;
import com.solar.ds.service.CityService;
import com.solar.ds.service.DistrictService;
import com.solar.ds.service.HamletService;
import com.solar.ds.service.ProvinceService;
import com.solar.ds.service.VillagesService;

@Component
@Lazy(false) 
public final class CmsNewsConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	private static CmsContentService cmsContentService;
	
	private static ProvinceService provinceService;
	private static CityService cityService;
	private static DistrictService districtService;
	private static VillagesService villagesService;
	private static HamletService hamletService;
	
	
	private static CmsAdService cmsAdService;
	
	public static CmsNewsDto convertCmsNewsDTO(CmsNews cmsNews) {
		BizAssert.notNull(cmsNews, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsNews null");
		BizAssert.notNull(cmsNews.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsNews.id null");

		CmsNewsDto result = new CmsNewsDto();
		result.setId(cmsNews.getId());
		result.setProvinceId(cmsNews.getProvinceId());
		result.setCityId(cmsNews.getCityId());
		result.setDistrictId(cmsNews.getDistrictId());
		result.setVillagesId(cmsNews.getVillagesId());
		result.setHamletId(cmsNews.getHamletId());
		
		if (StringUtils.isNotEmpty(cmsNews.getProvinceId())) {
			ProvinceDto province = provinceService.queryProvinceById(cmsNews.getProvinceId());
			result.setProvinceName(province != null ? province.getName() : "");
		}
		
		if (StringUtils.isNotEmpty(cmsNews.getCityId())) {
			CityDto city = cityService.queryCityById(cmsNews.getCityId());
			result.setCityName(city != null ? city.getName() : "");
		}
		
		if (StringUtils.isNotEmpty(cmsNews.getDistrictId())) {
			DistrictDto district = districtService.queryDistrictById(cmsNews.getDistrictId());
			result.setDistrictName(district != null ? district.getName() : "");
		}
		
		if (StringUtils.isNotEmpty(cmsNews.getVillagesId())) {
			VillagesDto villages = villagesService.queryVillagesById(cmsNews.getVillagesId());
			result.setVillagesName(villages != null ? villages.getName() : "");
		}
		
		if (StringUtils.isNotEmpty(cmsNews.getHamletId())) {
			HamletDto hamlet = hamletService.queryHamletById(cmsNews.getHamletId());
			result.setHamletName(hamlet != null ? hamlet.getName() : "");
		}
		
		// 内容信息
		CmsContentDto content = cmsContentService.queryCmsContentById(cmsNews.getId());
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
			result.setCover(content.getCover());
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
			result.setSource(content.getSource());
			result.setSourceUrl(content.getSourceUrl());
			result.setText(content.getText());
			result.setWordCount(content.getWordCount());
			
			// 内容标签
			result.setTagNames(content.getTagNames());
			
			// 轮播图
			result.setSlideshows(cmsAdService.queryCmsAdListByTypeAndContentId(CmsAdEnum.Type.CONTENT_DETAILS_BANNER.name(), content.getId()));
		}
		
		return result;
	}

	public static List<CmsNewsDto> convertCmsNewsListDTO(List<CmsNews> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsNewsDto>(0);
		}
		
		List<CmsNewsDto> resultList = new ArrayList<CmsNewsDto>();
		for (CmsNews po : list) {
			resultList.add(convertCmsNewsDTO(po));
		}
		return resultList;
	}
	

	public static CmsNews convertCmsNewsEntity(CmsNewsDto cmsNews) {
		BizAssert.notNull(cmsNews, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsNews null");
		
		CmsNews result = new CmsNews();
		result.setId(cmsNews.getId());
		result.setProvinceId(cmsNews.getProvinceId());
		result.setCityId(cmsNews.getCityId());
		result.setDistrictId(cmsNews.getDistrictId());
		result.setVillagesId(cmsNews.getVillagesId());
		result.setHamletId(cmsNews.getHamletId());
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
		
		
		provinceService = applicationContext.getBean(ProvinceService.class);
		cityService = applicationContext.getBean(CityService.class);
		districtService = applicationContext.getBean(DistrictService.class);
		villagesService = applicationContext.getBean(VillagesService.class);
		hamletService = applicationContext.getBean(HamletService.class);
		cmsAdService = applicationContext.getBean(CmsAdService.class);
	}
	
}