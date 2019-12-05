package com.solar.cms.service;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.nebula.sequence.SequenceService;
import com.solar.cms.CmsCacheConstants;
import com.solar.cms.converter.CmsExpertConverterFactory;
import com.solar.cms.dal.crud.CmsExpertCrudService;
import com.solar.cms.dal.crud.CmsExpertVideoCrudService;
import com.solar.cms.dal.dao.ext.CmsExpertExtDao;
import com.solar.cms.dto.CmsExpertDto;
import com.solar.cms.entity.CmsExpert;
import com.solar.cms.enums.CmsResultCodeEnum;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.query.CmsExpertQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 专家 CmsExpert 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsExpertService")
public class CmsExpertServiceImpl extends BaseServiceImpl implements CmsExpertService {
	
	@Autowired
	private CmsExpertCrudService cmsExpertCrudService;
	
	@Autowired
	private CmsExpertVideoCrudService cmsExpertVideoCrudService;

	@Autowired
	private CmsExpertExtDao cmsExpertExtDao;

	@Autowired
	private SequenceService sequenceService;

	@Override
	@Cacheable(value = CmsCacheConstants.CMS_EXPERT_CACHE_NAMES, key="#id", unless="#result == null", condition="#id!=null")
	public CmsExpertDto queryCmsExpertById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsExpert cmsExpert = cmsExpertCrudService.queryById(id);
		return CmsExpertConverterFactory.convertCmsExpertDTO(cmsExpert);
	}
 
	@Override
	public List<CmsExpertDto> queryCmsExpertAllList() throws BizException {
		List<CmsExpert> list = cmsExpertCrudService.queryAllList();

		return CmsExpertConverterFactory.convertCmsExpertListDTO(list);
	}
	
	@Override
	public List<CmsExpertDto> queryCmsExpertList(final CmsExpertQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("duty", query.getDuty());
		search.safeAddParamter("geniusTagId", query.getGeniusTagId());
		search.safeAddParamter("hidden", query.getHidden());
		search.safeAddParamter("organization", query.getOrganization());
	
		try {
			if (StringUtils.isNotEmpty(query.getMinDate()) && StringUtils.isNotEmpty(query.getMaxDate())) {
				Date startDate = DateUtils.parseDate(query.getMinDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getMaxDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("minCreateDate", startDate.getTime());
				search.safeAddParamter("maxCreateDate", endDate.getTime());
			}
		} catch (ParseException e) {
		}
		
		List<CmsExpert> list = cmsExpertCrudService.queryList(search.getParameters());
		
		return CmsExpertConverterFactory.convertCmsExpertListDTO(list);
	}
	
	@Override
	public Pagination<CmsExpertDto> queryPageCmsExpert(final CmsExpertQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("duty", query.getDuty());
		search.safeAddParamter("geniusTagId", query.getGeniusTagId());
		search.safeAddParamter("hidden", query.getHidden());
		search.safeAddParamter("organization", query.getOrganization());
	
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
		List<CmsExpert> list = cmsExpertExtDao.queryPage(getContextParams(), search.getParameters(), search.offset(), search.getCount(), "sortno", SortByEnum.ASC.name());
		// query totals
		int totals = cmsExpertExtDao.count(getContextParams(), search.getParameters());
		if (totals > 0 && CollectionUtils.isEmpty(list)) {
			search.setPage(1);
			list = cmsExpertExtDao.queryPage(getContextParams(), search.getParameters(), 1, search.getCount(), "sortno", SortByEnum.ASC.name());
		}

		List<CmsExpertDto> resultList = CmsExpertConverterFactory.convertCmsExpertListDTO(list);
		
		return new Pagination<CmsExpertDto>(search.getPage(), search.getCount(), totals, resultList);
	}
	
	@Override
	public boolean checkCmsExpertName(String expertname) throws BizException {
		BizAssert.notEmpty(expertname, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:expertname为空");
		return cmsExpertCrudService.queryOneByProperty("real_name", expertname) != null;
	}

	@Override
	public CmsExpertDto saveCmsExpert(final CmsExpertDto cmsExpert) throws BizException {
		BizAssert.notNull(cmsExpert, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpert为空");
		BizAssert.notEmpty(cmsExpert.getRealName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:realName为空");
		BizAssert.notEmpty(cmsExpert.getDuty(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:duty为空");
		BizAssert.notEmpty(cmsExpert.getOrganization(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:organization为空");
		BizAssert.notEmpty(cmsExpert.getIntroduction(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:introduction为空");
		
		return (CmsExpertDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
				if (checkCmsExpertName(cmsExpert.getRealName())) {
					throw new BizException(CmsResultCodeEnum.EXPERT_NAME_EXISTS.name(), CmsResultCodeEnum.EXPERT_NAME_EXISTS.getCode(), "专家名称[" + cmsExpert.getRealName() + "]已存在");
				}

			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1. 保存专家信息
				String expertId = sequenceService.getLongNextVal(CmsSequenceKeyEnum.CMS_EXPERT_ID.getKey()) + "";
				CmsExpert createModel = CmsExpertConverterFactory.convertCmsExpertEntity(cmsExpert);
				createModel.setId(expertId);
				createModel.setHidden("N");
				cmsExpertCrudService.save(createModel);
				
				
				CmsExpertDto result = CmsExpertConverterFactory.convertCmsExpertDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_EXPERT_CACHE_NAMES, key = "#cmsExpert.id", condition="#cmsExpert.id!=null")
	public void updateCmsExpertById(final CmsExpertDto cmsExpert) throws BizException {
		BizAssert.notNull(cmsExpert, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpert为空");
		BizAssert.notEmpty(cmsExpert.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsExpert.getRealName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:realName为空");
		BizAssert.notEmpty(cmsExpert.getDuty(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:duty为空");
		BizAssert.notEmpty(cmsExpert.getOrganization(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:organization为空");
		BizAssert.notEmpty(cmsExpert.getIntroduction(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:introduction为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				CmsExpert checkExpert = cmsExpertCrudService.queryById(cmsExpert.getId());
				if (checkExpert == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsExpert.getId() + "]");
				}		
				
				if (!StringUtils.equals(checkExpert.getRealName(), cmsExpert.getRealName()) &&
						checkCmsExpertName(cmsExpert.getRealName())) {
					throw new BizException(CmsResultCodeEnum.EXPERT_NAME_EXISTS.name(), CmsResultCodeEnum.EXPERT_NAME_EXISTS.getCode(), "专家名称[" + cmsExpert.getRealName() + "]已存在");
				}

			}
			
			@Override
			public Object execute() throws BizException {
				CmsExpert updateModel = CmsExpertConverterFactory.convertCmsExpertEntity(cmsExpert);
				cmsExpertCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_EXPERT_CACHE_NAMES, key = "#id", condition="#id!=null")
	public void deleteCmsExpertById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsExpertCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1.删除专家对应的视频记录
				Map<String, Object> deleteExpertVideoParamter = new HashMap<>();
				deleteExpertVideoParamter.put("expertId", id);
				cmsExpertVideoCrudService.deleteByCondition(deleteExpertVideoParamter);

				// 2.删除专家信息
				int result = cmsExpertCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsExpertByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsExpertById(id);
		}
	}


}
