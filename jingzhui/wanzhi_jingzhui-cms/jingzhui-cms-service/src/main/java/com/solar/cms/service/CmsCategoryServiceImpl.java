package com.solar.cms.service;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.nebula.sequence.SequenceService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.cms.converter.CmsCategoryConverterFactory;
import com.solar.cms.converter.CmsTagConverterFactory;
import com.solar.cms.service.CmsCategoryService;
import com.solar.cms.dal.crud.CmsCategoryCrudService;
import com.solar.cms.entity.CmsCategory;
import com.solar.cms.entity.CmsTag;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.dto.CmsCategoryDto;
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.query.CmsCategoryQuery;

/**
 * 分类 CmsCategory 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsCategoryService")
public class CmsCategoryServiceImpl extends BaseServiceImpl implements CmsCategoryService {
	
	@Autowired
	private CmsCategoryCrudService cmsCategoryCrudService;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public CmsCategoryDto queryCmsCategoryById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsCategory cmsCategory = cmsCategoryCrudService.queryById(id);
		return CmsCategoryConverterFactory.convertCmsCategoryDTO(cmsCategory);
	}
	
	@Override
	public List<CmsCategoryDto> queryCmsCategoryListByParentId(String parentId) throws BizException {
		if (StringUtils.isEmpty(parentId)) {
			return null;
		}
		List<CmsCategory> list = cmsCategoryCrudService.queryListByProperty("parent_id", parentId);

		return CmsCategoryConverterFactory.convertCmsCategoryListDTO(list);
	}

	@Override
	public List<CmsCategoryDto> queryCmsCategoryAllList() throws BizException {
		List<CmsCategory> list = cmsCategoryCrudService.queryAllList();

		return CmsCategoryConverterFactory.convertCmsCategoryListDTO(list);
	}
	
	@Override
	public List<CmsCategoryDto> queryCmsCategoryList(final CmsCategoryQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsCategory> list = cmsCategoryCrudService.queryList(search.getParameters());
		
		return CmsCategoryConverterFactory.convertCmsCategoryListDTO(list);
	}
	
	@Override
	public Pagination<CmsCategoryDto> queryPageCmsCategory(final CmsCategoryQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsCategory> list = cmsCategoryCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsCategoryCrudService.count(search.getParameters());

		List<CmsCategoryDto> resultList = CmsCategoryConverterFactory.convertCmsCategoryListDTO(list);
		
		return new Pagination<CmsCategoryDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsCategoryDto saveCmsCategory(final CmsCategoryDto cmsCategory) throws BizException {
		BizAssert.notNull(cmsCategory, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategory为空");
		BizAssert.notEmpty(cmsCategory.getName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:name为空");
		
		return (CmsCategoryDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				String categoryId = sequenceService.getLongNextVal(CmsSequenceKeyEnum.CMS_CATEGORY_ID.getKey()) + "";
				if (StringUtils.isEmpty(cmsCategory.getParentId())) {
					cmsCategory.setParentId("0");
				}
				
				CmsCategory createModel = CmsCategoryConverterFactory.convertCmsCategoryEntity(cmsCategory);
				createModel.setId(categoryId);
				createModel.setContents(0);
				cmsCategoryCrudService.save(createModel);
				
				CmsCategoryDto result = CmsCategoryConverterFactory.convertCmsCategoryDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsCategoryById(final CmsCategoryDto cmsCategory) throws BizException {
		BizAssert.notNull(cmsCategory, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategory为空");
		BizAssert.notEmpty(cmsCategory.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryCrudService.queryById(cmsCategory.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsCategory.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsCategory updateModel = CmsCategoryConverterFactory.convertCmsCategoryEntity(cmsCategory);
				cmsCategoryCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsCategoryById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsCategoryCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsCategoryByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsCategoryById(id);
		}
	}
 
}
