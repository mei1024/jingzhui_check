package com.solar.cms.service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.EnabledEnum;
import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.converter.CmsNewsConverterFactory;
import com.solar.cms.dal.crud.CmsAdCrudService;
import com.solar.cms.dal.crud.CmsNewsCrudService;
import com.solar.cms.dal.dao.ext.CmsNewsExtDao;
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.dto.CmsNewsDto;
import com.solar.cms.entity.CmsAd;
import com.solar.cms.entity.CmsNews;
import com.solar.cms.enums.CmsAdEnum;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.query.CmsNewsQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 咨讯 CmsNews 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsNewsService")
public class CmsNewsServiceImpl extends BaseServiceImpl implements CmsNewsService {
	
	@Autowired
	private CmsNewsCrudService cmsNewsCrudService;
	
	@Autowired
	private CmsContentService cmsContentService;

	@Autowired
	private CmsNewsExtDao cmsNewsExtDao;

	@Autowired
	private CmsAdService cmsAdService;
	
	@Autowired
	private CmsAdCrudService cmsAdCrudService;

	@Override
	public CmsNewsDto queryCmsNewsById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsNews cmsNews = cmsNewsCrudService.queryById(id);
		return CmsNewsConverterFactory.convertCmsNewsDTO(cmsNews);
	}
 
	@Override
	public List<CmsNewsDto> queryCmsNewsAllList() throws BizException {
		List<CmsNews> list = cmsNewsCrudService.queryAllList();

		return CmsNewsConverterFactory.convertCmsNewsListDTO(list);
	}
	
	@Override
	public List<CmsNewsDto> queryCmsNewsList(final CmsNewsQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsNews> list = cmsNewsCrudService.queryList(search.getParameters());
		
		return CmsNewsConverterFactory.convertCmsNewsListDTO(list);
	}
	
	@Override
	public Pagination<CmsNewsDto> queryPageCmsNews(final CmsNewsQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("categoryId", query.getCategoryId());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("provinceId", query.getProvinceId());
		search.safeAddParamter("cityId", query.getCityId());
		search.safeAddParamter("districtId", query.getDistrictId());
		search.safeAddParamter("villagesId", query.getVillagesId());
		search.safeAddParamter("hamletId", query.getHamletId());
		search.safeAddParamter("tagId", query.getTagId());
		search.safeAddParamter("status", query.getStatus());
		try {
			if (StringUtils.isNotEmpty(query.getMinDate()) && StringUtils.isNotEmpty(query.getMaxDate())) {
				Date startDate = DateUtils.parseDate(query.getMinDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getMaxDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("minCreateDate", startDate.getTime());
				search.safeAddParamter("maxCreateDate", endDate.getTime());
			}
		} catch (ParseException e) {
		}
		
		// query list
		List<CmsNews> list = cmsNewsExtDao.queryPage(getContextParams(), search.getParameters(), search.offset(), search.getCount(), "b.publish_date", SortByEnum.DESC.name());
		// query totals
		int totals = cmsNewsExtDao.count(getContextParams(), search.getParameters());
		if (totals > 0 && CollectionUtils.isEmpty(list)) {
			search.setPage(1);
			list = cmsNewsExtDao.queryPage(getContextParams(), search.getParameters(), 1, search.getCount(), "b.publish_date", SortByEnum.DESC.name());
		}

		List<CmsNewsDto> resultList = CmsNewsConverterFactory.convertCmsNewsListDTO(list);
		
		return new Pagination<CmsNewsDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsNewsDto saveCmsNews(final CmsNewsDto cmsNews) throws BizException {
		BizAssert.notNull(cmsNews, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsNews为空");
		BizAssert.notEmpty(cmsNews.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");

//		BizAssert.notEmpty(cmsNews.getProvinceId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:provinceId为空");
//		BizAssert.notEmpty(cmsNews.getCityId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cityId为空");
//		BizAssert.notEmpty(cmsNews.getDistrictId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:districtId为空");
//		BizAssert.notEmpty(cmsNews.getVillagesId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:villagesId为空");
		BizAssert.notNull(CmsContentEnum.Status.get(cmsNews.getStatus()), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");
		
		return (CmsNewsDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				// 1.基础内容保存
				CmsContentDto baseContent = new CmsContentDto();
				baseContent.setTitle(cmsNews.getTitle());
				baseContent.setUserId(cmsNews.getUserId());
				baseContent.setCategoryId(cmsNews.getCategoryId());
				baseContent.setCopied("N");
				baseContent.setOnlyUrl("N");
				baseContent.setHasImages("N");
				baseContent.setHasFiles("N");
				baseContent.setHasStatic("N");
				baseContent.setUrl("");
				baseContent.setDescription(cmsNews.getDescription());
				baseContent.setTagIds(cmsNews.getTagIds());
				baseContent.setCover(cmsNews.getCover());
				baseContent.setPublishDate(new Date());
				baseContent.setSortno(1);
				baseContent.setStatus(CmsContentEnum.Status.get(cmsNews.getStatus()).getCode());
				baseContent.setSource(cmsNews.getSource());
				baseContent.setSourceUrl(cmsNews.getSourceUrl());
				baseContent.setText(cmsNews.getText());
				baseContent.setWordCount(cmsNews.getWordCount() != null ? cmsNews.getWordCount() : 0);
				baseContent.setAttachmentIds(cmsNews.getAttachmentIds());
				CmsContentDto resultContent =  cmsContentService.saveCmsContent(baseContent);
				
				// 2.咨讯内容保存
				CmsNews cmsNewsCreateModel = CmsNewsConverterFactory.convertCmsNewsEntity(cmsNews);
				cmsNewsCreateModel.setId(resultContent.getId());
				cmsNewsCrudService.save(cmsNewsCreateModel);
			
				// 3.轮播图保存
				saveSlideshows(resultContent.getId(), cmsNews.getSlideshows());
				
				CmsNewsDto result = CmsNewsConverterFactory.convertCmsNewsDTO(cmsNewsCreateModel);
				return result;
			}
			
		});
	}
	
	
	@Override
	public void updateCmsNewsById(final CmsNewsDto cmsNews) throws BizException {
		BizAssert.notNull(cmsNews, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsNews为空");
		BizAssert.notEmpty(cmsNews.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
//		BizAssert.notEmpty(cmsNews.getProvinceId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:provinceId为空");
//		BizAssert.notEmpty(cmsNews.getCityId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cityId为空");
//		BizAssert.notEmpty(cmsNews.getDistrictId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:districtId为空");
//		BizAssert.notEmpty(cmsNews.getVillagesId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:villagesId为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsNewsCrudService.queryById(cmsNews.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsNews.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				// 1.基础内容修改保存
				CmsContentDto baseContent = new CmsContentDto();
				baseContent.setCopied("N");
				baseContent.setOnlyUrl("N");
				baseContent.setHasImages("N");
				baseContent.setHasFiles("N");
				baseContent.setHasStatic("N");
				
				baseContent.setId(cmsNews.getId());
				baseContent.setTitle(cmsNews.getTitle());
				baseContent.setUserId(cmsNews.getUserId());
				baseContent.setCategoryId(cmsNews.getCategoryId());
				baseContent.setDescription(cmsNews.getDescription());
				baseContent.setTagIds(cmsNews.getTagIds());
				baseContent.setCover(cmsNews.getCover());
				baseContent.setSource(cmsNews.getSource());
				baseContent.setSourceUrl(cmsNews.getSourceUrl());
				baseContent.setText(cmsNews.getText());
				baseContent.setWordCount(cmsNews.getWordCount() != null ? cmsNews.getWordCount() : 0);
				baseContent.setStatus(CmsContentEnum.Status.get(cmsNews.getStatus()).getCode());
				baseContent.setAttachmentIds(cmsNews.getAttachmentIds());
				cmsContentService.updateCmsContentById(baseContent);

				// 2.咨讯内容保存
				CmsNews updateModel = CmsNewsConverterFactory.convertCmsNewsEntity(cmsNews);
				cmsNewsCrudService.update(updateModel);
				
				// 3.轮播图保存,删除历史从新进行保存
				cmsAdService.deleteCmsAdByTypeAndContentId(CmsAdEnum.Type.CONTENT_DETAILS_BANNER.name(), baseContent.getId());
				saveSlideshows(baseContent.getId(), cmsNews.getSlideshows());

				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsNewsById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsNewsCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				// 1、内容删除
				cmsContentService.deleteCmsContentById(id);
				
				// 2、咨讯删除
				int result = cmsNewsCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsNewsByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsNewsById(id);
		}
	}
	
	/**
	 * 轮播图保存
	 * 
	 * @param contentId 内容ID
	 * @param slideshows 轮播图信息
	 */
	private void saveSlideshows(String contentId, List<CmsAdDto> slideshows) {
		List<CmsAd> saveAdList = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(slideshows)) {
			for (CmsAdDto ad : slideshows) {
				if (StringUtils.isEmpty(ad.getImgUrl()) && StringUtils.isEmpty(ad.getUrl())) {
					continue;
				}
				
				CmsAd model = new CmsAd();
				model.setName("");
				model.setType(CmsAdEnum.Type.CONTENT_DETAILS_BANNER.name());
				model.setContentId(contentId);
				model.setStatus(EnabledEnum.ENABLED.name());
				model.setImgUrl(ad.getImgUrl());
				if (StringUtils.isEmpty(model.getImgUrl())) {
					model.setImgUrl(ad.getUrl());
				}

				model.setLinkUrl(ad.getLinkUrl());
				model.setSortno(0);
				model.setDstatus("N");
				model.setCreateDate(System.currentTimeMillis());
				model.setLastModDate(System.currentTimeMillis());

				saveAdList.add(model);
			}
		}
		
		if (CollectionUtils.isNotEmpty(saveAdList)) {
			cmsAdCrudService.batchSave(saveAdList);
		}
	}
 
}
