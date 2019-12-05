package com.solar.cms.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.CmsCacheConstants;
import com.solar.cms.converter.CmsTagConverterFactory;
import com.solar.cms.dal.crud.CmsTagCrudService;
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.entity.CmsTag;
import com.solar.cms.query.CmsTagQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 标签 CmsTag 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsTagService")
public class CmsTagServiceImpl extends BaseServiceImpl implements CmsTagService {
	
	@Autowired
	private CmsTagCrudService cmsTagCrudService;

	@Override
	@Cacheable(value = CmsCacheConstants.CMS_TAG_CACHE_NAMES, key="#id", unless="#result == null", condition="#id!=null")
	public CmsTagDto queryCmsTagById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsTag cmsTag = cmsTagCrudService.queryById(id);
		if (cmsTag == null) {
			return null;
		}
		return CmsTagConverterFactory.convertCmsTagDTO(cmsTag);
	}
	
	@Override
	public List<CmsTagDto> queryCmsTagListByIds(String[] tagIds) throws BizException {
		List<CmsTagDto> resultList = new ArrayList<>();
		if (tagIds == null || tagIds.length < 1) {
			return resultList;
		}

		CmsTagDto cmsTag = null;
		for (String tagId : tagIds) {
			cmsTag = queryCmsTagById(tagId);
			if (cmsTag != null) {
				resultList.add(cmsTag);
			}
		}

		return resultList;
	}
	
	@Override
	public List<String> queryCmsTagNamesByIds(String[] tagIds) throws BizException {
		List<String> tagNames = new ArrayList<>();
		List<CmsTagDto> tags = queryCmsTagListByIds(tagIds);
		for (CmsTagDto tag : tags) {
			tagNames.add(tag.getName());
		}
		return tagNames;
	}
	
	@Override
	public List<CmsTagDto> queryCmsTagListByTypeId(String typeId) throws BizException {
		if (StringUtils.isEmpty(typeId)) {
			return null;
		}
		List<CmsTag> list = cmsTagCrudService.queryListByProperty("type_id", typeId);

		return CmsTagConverterFactory.convertCmsTagListDTO(list);
	}

	@Override
	public List<CmsTagDto> queryCmsTagAllList() throws BizException {
		List<CmsTag> list = cmsTagCrudService.queryAllList();

		return CmsTagConverterFactory.convertCmsTagListDTO(list);
	}
	
	@Override
	public List<CmsTagDto> queryCmsTagList(final CmsTagQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsTag> list = cmsTagCrudService.queryList(search.getParameters());
		
		return CmsTagConverterFactory.convertCmsTagListDTO(list);
	}
	
	@Override
	public Pagination<CmsTagDto> queryPageCmsTag(final CmsTagQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsTag> list = cmsTagCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsTagCrudService.count(search.getParameters());

		List<CmsTagDto> resultList = CmsTagConverterFactory.convertCmsTagListDTO(list);
		
		return new Pagination<CmsTagDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsTagDto saveCmsTag(final CmsTagDto cmsTag) throws BizException {
		BizAssert.notNull(cmsTag, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTag为空");
		BizAssert.notEmpty(cmsTag.getName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:name为空");
		BizAssert.notEmpty(cmsTag.getTypeId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:typeId为空");
		
		return (CmsTagDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsTag createModel = CmsTagConverterFactory.convertCmsTagEntity(cmsTag);
				createModel.setSearchCount(0);
				cmsTagCrudService.save(createModel);
				
				CmsTagDto result = CmsTagConverterFactory.convertCmsTagDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_TAG_CACHE_NAMES, key = "#cmsTag.id", condition="#cmsTag.id!=null")
	public void updateCmsTagById(final CmsTagDto cmsTag) throws BizException {
		BizAssert.notNull(cmsTag, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsTag为空");
		BizAssert.notEmpty(cmsTag.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsTag.getName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:name为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsTagCrudService.queryById(cmsTag.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsTag.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsTag updateModel = cmsTagCrudService.queryById(cmsTag.getId());
				updateModel.setName(cmsTag.getName());
				
				cmsTagCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_TAG_CACHE_NAMES, key = "#id", condition="#id!=null")
	public void deleteCmsTagById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsTagCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsTagCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsTagByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsTagById(id);
		}
	}
 
}
