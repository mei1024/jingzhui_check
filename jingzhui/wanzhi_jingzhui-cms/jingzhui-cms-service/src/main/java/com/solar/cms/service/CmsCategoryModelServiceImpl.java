package com.solar.cms.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.cms.converter.CmsCategoryModelConverterFactory;
import com.solar.cms.service.CmsCategoryModelService;
import com.solar.cms.dal.crud.CmsCategoryModelCrudService;
import com.solar.cms.entity.CmsCategoryModel;
import com.solar.cms.dto.CmsCategoryModelDto;
import com.solar.cms.query.CmsCategoryModelQuery;

/**
 * 分类模型 CmsCategoryModel 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsCategoryModelService")
public class CmsCategoryModelServiceImpl extends BaseServiceImpl implements CmsCategoryModelService {
	
	@Autowired
	private CmsCategoryModelCrudService cmsCategoryModelCrudService;
	
	@Override
	public CmsCategoryModelDto queryCmsCategoryModelById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsCategoryModel cmsCategoryModel = cmsCategoryModelCrudService.queryById(id);
		return CmsCategoryModelConverterFactory.convertCmsCategoryModelDTO(cmsCategoryModel);
	}
 
	@Override
	public List<CmsCategoryModelDto> queryCmsCategoryModelAllList() throws BizException {
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryAllList();

		return CmsCategoryModelConverterFactory.convertCmsCategoryModelListDTO(list);
	}
	
	@Override
	public List<CmsCategoryModelDto> queryCmsCategoryModelList(final CmsCategoryModelQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryList(search.getParameters());
		
		return CmsCategoryModelConverterFactory.convertCmsCategoryModelListDTO(list);
	}
	
	@Override
	public Pagination<CmsCategoryModelDto> queryPageCmsCategoryModel(final CmsCategoryModelQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsCategoryModelCrudService.count(search.getParameters());

		List<CmsCategoryModelDto> resultList = CmsCategoryModelConverterFactory.convertCmsCategoryModelListDTO(list);
		
		return new Pagination<CmsCategoryModelDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsCategoryModelDto saveCmsCategoryModel(final CmsCategoryModelDto cmsCategoryModel) throws BizException {
		BizAssert.notNull(cmsCategoryModel, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryModel为空");
		
		return (CmsCategoryModelDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsCategoryModel createModel = CmsCategoryModelConverterFactory.convertCmsCategoryModelEntity(cmsCategoryModel);
				cmsCategoryModelCrudService.save(createModel);
				
				CmsCategoryModelDto result = CmsCategoryModelConverterFactory.convertCmsCategoryModelDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsCategoryModelById(final CmsCategoryModelDto cmsCategoryModel) throws BizException {
		BizAssert.notNull(cmsCategoryModel, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryModel为空");
		BizAssert.notEmpty(cmsCategoryModel.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryModelCrudService.queryById(cmsCategoryModel.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsCategoryModel.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsCategoryModel updateModel = CmsCategoryModelConverterFactory.convertCmsCategoryModelEntity(cmsCategoryModel);
				cmsCategoryModelCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsCategoryModelById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryModelCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsCategoryModelCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsCategoryModelByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsCategoryModelById(id);
		}
	}
 
}
