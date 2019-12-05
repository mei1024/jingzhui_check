package com.solar.cms.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.EnabledEnum;
import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.CmsCacheConstants;
import com.solar.cms.converter.CmsAdConverterFactory;
import com.solar.cms.dal.crud.CmsAdCrudService;
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.entity.CmsAd;
import com.solar.cms.query.CmsAdQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 广告 CmsAd 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Service("cmsAdService")
public class CmsAdServiceImpl extends BaseServiceImpl implements CmsAdService {
	
	/** 根据类型最大查询广告条数 **/
	private static final int MAX_QUERY_AD_TYPE_SIZE = 10;
	
	@Autowired
	private CmsAdCrudService cmsAdCrudService;
   
	@Autowired
    private CacheManager cacheManager;

	@Override
	@Cacheable(value = CmsCacheConstants.CMS_AD_CACHE_NAMES, key="#id", unless="#result == null", condition="#id!=null")
	public CmsAdDto queryCmsAdById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsAd cmsAd = cmsAdCrudService.queryById(id);
		return CmsAdConverterFactory.convertCmsAdDTO(cmsAd);
	}
	
	@Override
	@Cacheable(value = CmsCacheConstants.CMS_AD_CACHE_NAMES)
	public List<CmsAdDto> queryCmsAdListByTypeAndContentId(String type, String contentId) throws BizException {
		BizAssert.notEmpty(type, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:type为空");
		BizAssert.notEmpty(contentId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:contentId为空");
		
		Search search = new Search();
		search.safeAddParamter("type", type);
		search.safeAddParamter("contentId", contentId);
		
		List<CmsAd> list = cmsAdCrudService.queryList(search.getParameters());
		
		return CmsAdConverterFactory.convertCmsAdListDTO(list);
	}
 
	@Override
	@Cacheable(value = CmsCacheConstants.CMS_AD_CACHE_NAMES)
	public List<CmsAdDto> queryCmsAdListByType(String type) throws BizException {
		BizAssert.notEmpty(type, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:type为空");
		
		Search search = new Search();
		search.safeAddParamter("type", type);
		
		List<CmsAd> list = cmsAdCrudService.queryPage(search.getParameters(), 0, MAX_QUERY_AD_TYPE_SIZE, "sortno", SortByEnum.ASC);
		
		return CmsAdConverterFactory.convertCmsAdListDTO(list);
	}
	
	@Override
	public List<CmsAdDto> queryCmsAdAllList() throws BizException {
		List<CmsAd> list = cmsAdCrudService.queryAllList();

		return CmsAdConverterFactory.convertCmsAdListDTO(list);
	}
	
	@Override
	public List<CmsAdDto> queryCmsAdList(final CmsAdQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("type", query.getType());
		
		List<CmsAd> list = cmsAdCrudService.queryList(search.getParameters());
		
		return CmsAdConverterFactory.convertCmsAdListDTO(list);
	}
	
	@Override
	public Pagination<CmsAdDto> queryPageCmsAd(final CmsAdQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("type", query.getType());
		
		// query list
		List<CmsAd> list = cmsAdCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsAdCrudService.count(search.getParameters());

		List<CmsAdDto> resultList = CmsAdConverterFactory.convertCmsAdListDTO(list);
		
		return new Pagination<CmsAdDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@CacheEvict(value = CmsCacheConstants.CMS_AD_CACHE_NAMES, allEntries = true)
	@Override
	public CmsAdDto saveCmsAd(final CmsAdDto cmsAd) throws BizException {
		BizAssert.notNull(cmsAd, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsAd为空");
		BizAssert.notEmpty(cmsAd.getType(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:type为空");
		BizAssert.notEmpty(cmsAd.getImgUrl(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:imgurl为空");
		
		return (CmsAdDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsAd.getSortno() == null) {
					cmsAd.setSortno(99);
				}
				if (StringUtils.isEmpty(cmsAd.getName())) {
					cmsAd.setName("");
				}
				if (StringUtils.isEmpty(cmsAd.getStatus())) {
					cmsAd.setStatus(EnabledEnum.ENABLED.getCode());
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				// 1. 广告信息保存
				CmsAd createModel = CmsAdConverterFactory.convertCmsAdEntity(cmsAd);
				cmsAdCrudService.save(createModel);

				CmsAdDto result = CmsAdConverterFactory.convertCmsAdDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_AD_CACHE_NAMES, allEntries = true)
	public void updateCmsAdById(final CmsAdDto cmsAd) throws BizException {
		BizAssert.notNull(cmsAd, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsAd为空");
		BizAssert.notEmpty(cmsAd.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsAd.getImgUrl(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:imgurl为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			private CmsAd cmsAdModel;
			
			@Override
			public void check() throws BizException {
				cmsAdModel = cmsAdCrudService.queryById(cmsAd.getId());
				if (cmsAdModel == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsAd.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1.广告更新
				CmsAd updateModel = new CmsAd();
				updateModel.setId(cmsAd.getId());
				updateModel.setImgUrl(cmsAd.getImgUrl());
				updateModel.setLinkUrl(cmsAd.getLinkUrl());
				if (cmsAd.getSortno() != null) {
					updateModel.setSortno(cmsAd.getSortno());
				}
				if (StringUtils.isNotEmpty(cmsAd.getStatus())) {
					updateModel.setStatus(cmsAd.getStatus());
				}
				if (StringUtils.isNotEmpty(cmsAd.getName())) {
					updateModel.setName(cmsAd.getName());
				}
				cmsAdCrudService.update(updateModel);

				return true;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_AD_CACHE_NAMES, allEntries = true)
	public void deleteCmsAdById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			private CmsAd cmsAdModel;
			
			@Override
			public void check() throws BizException {
				cmsAdModel = cmsAdCrudService.queryById(id);
				
				if (cmsAdModel == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1. 广告删除
				int result = cmsAdCrudService.deleteById(id);
	
				return result == 1;
			}
			
		});
	}

	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_AD_CACHE_NAMES, allEntries = true)
	public void deleteCmsAdByTypeAndContentId(String type, String contentId) throws BizException {
		BizAssert.notEmpty(type, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:type为空");
		BizAssert.notEmpty(contentId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:contentId为空");

		Map<String, Object> deleteCmsAdParamter = new HashMap<>();
		deleteCmsAdParamter.put("type", type);
		deleteCmsAdParamter.put("contentId", contentId);

		cmsAdCrudService.deleteByCondition(deleteCmsAdParamter);
	}
	
	@Override
	public void deleteCmsAdByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsAdById(id);
		}
	}
 
}
