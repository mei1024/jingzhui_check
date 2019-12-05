package com.solar.cms.service;
import java.util.List;

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
import com.solar.cms.converter.CmsTagTypeConverterFactory;
import com.solar.cms.dal.crud.CmsTagTypeCrudService;
import com.solar.cms.dto.CmsTagTypeDto;
import com.solar.cms.entity.CmsTagType;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.query.CmsTagTypeQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 标签类型 CmsTagType 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsTagTypeService")
public class CmsTagTypeServiceImpl extends BaseServiceImpl implements CmsTagTypeService {
	
	@Autowired
	private CmsTagTypeCrudService cmsTagTypeCrudService;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	@Cacheable(value = CmsCacheConstants.CMS_TAGTYPE_CACHE_NAMES, key="#id", unless="#result == null", condition="#id!=null")
	public CmsTagTypeDto queryCmsTagTypeById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsTagType cmsTagType = cmsTagTypeCrudService.queryById(id);
		return CmsTagTypeConverterFactory.convertCmsTagTypeDTO(cmsTagType);
	}
 
	@Override
	public List<CmsTagTypeDto> queryCmsTagTypeAllList() throws BizException {
		List<CmsTagType> list = cmsTagTypeCrudService.queryAllList();

		return CmsTagTypeConverterFactory.convertCmsTagTypeListDTO(list);
	}
	
	@Override
	public List<CmsTagTypeDto> queryCmsTagTypeList(final CmsTagTypeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsTagType> list = cmsTagTypeCrudService.queryList(search.getParameters());
		
		return CmsTagTypeConverterFactory.convertCmsTagTypeListDTO(list);
	}
	
	@Override
	public Pagination<CmsTagTypeDto> queryPageCmsTagType(final CmsTagTypeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsTagType> list = cmsTagTypeCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsTagTypeCrudService.count(search.getParameters());

		List<CmsTagTypeDto> resultList = CmsTagTypeConverterFactory.convertCmsTagTypeListDTO(list);
		
		return new Pagination<CmsTagTypeDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsTagTypeDto saveCmsTagType(final CmsTagTypeDto cmsTagType) throws BizException {
		BizAssert.notNull(cmsTagType, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTagType为空");
		BizAssert.notEmpty(cmsTagType.getName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:name为空");
		
		return (CmsTagTypeDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				String tagId = sequenceService.getLongNextVal(CmsSequenceKeyEnum.CMS_TAG_TYPE_ID.getKey()) + "";
				CmsTagType createModel = CmsTagTypeConverterFactory.convertCmsTagTypeEntity(cmsTagType);
				createModel.setId(tagId);
				createModel.setCount(0);
				cmsTagTypeCrudService.save(createModel);
				
				CmsTagTypeDto result = CmsTagTypeConverterFactory.convertCmsTagTypeDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_TAGTYPE_CACHE_NAMES, key = "#cmsTagType.id", condition="#cmsTagType.id!=null")
	public void updateCmsTagTypeById(final CmsTagTypeDto cmsTagType) throws BizException {
		BizAssert.notNull(cmsTagType, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTagType为空");
		BizAssert.notEmpty(cmsTagType.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsTagType.getName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:name为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsTagTypeCrudService.queryById(cmsTagType.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsTagType.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsTagType updateModel = cmsTagTypeCrudService.queryById(cmsTagType.getId());
				updateModel.setName(cmsTagType.getName());
				
				cmsTagTypeCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_TAGTYPE_CACHE_NAMES, key = "#id", condition="#id!=null")
	public void deleteCmsTagTypeById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsTagTypeCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsTagTypeCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsTagTypeByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsTagTypeById(id);
		}
	}
 
}
