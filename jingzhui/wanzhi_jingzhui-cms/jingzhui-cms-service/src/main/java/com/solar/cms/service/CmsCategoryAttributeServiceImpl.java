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
import com.solar.cms.converter.CmsCategoryAttributeConverterFactory;
import com.solar.cms.service.CmsCategoryAttributeService;
import com.solar.cms.dal.crud.CmsCategoryAttributeCrudService;
import com.solar.cms.entity.CmsCategoryAttribute;
import com.solar.cms.dto.CmsCategoryAttributeDto;
import com.solar.cms.query.CmsCategoryAttributeQuery;

/**
 * 分类扩展 CmsCategoryAttribute 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsCategoryAttributeService")
public class CmsCategoryAttributeServiceImpl extends BaseServiceImpl implements CmsCategoryAttributeService {
	
	@Autowired
	private CmsCategoryAttributeCrudService cmsCategoryAttributeCrudService;
	
	@Override
	public CmsCategoryAttributeDto queryCmsCategoryAttributeById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsCategoryAttribute cmsCategoryAttribute = cmsCategoryAttributeCrudService.queryById(id);
		return CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeDTO(cmsCategoryAttribute);
	}
 
	@Override
	public List<CmsCategoryAttributeDto> queryCmsCategoryAttributeAllList() throws BizException {
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryAllList();

		return CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeListDTO(list);
	}
	
	@Override
	public List<CmsCategoryAttributeDto> queryCmsCategoryAttributeList(final CmsCategoryAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryList(search.getParameters());
		
		return CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeListDTO(list);
	}
	
	@Override
	public Pagination<CmsCategoryAttributeDto> queryPageCmsCategoryAttribute(final CmsCategoryAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsCategoryAttributeCrudService.count(search.getParameters());

		List<CmsCategoryAttributeDto> resultList = CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeListDTO(list);
		
		return new Pagination<CmsCategoryAttributeDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsCategoryAttributeDto saveCmsCategoryAttribute(final CmsCategoryAttributeDto cmsCategoryAttribute) throws BizException {
		BizAssert.notNull(cmsCategoryAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryAttribute为空");
		
		return (CmsCategoryAttributeDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsCategoryAttribute createModel = CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeEntity(cmsCategoryAttribute);
				cmsCategoryAttributeCrudService.save(createModel);
				
				CmsCategoryAttributeDto result = CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsCategoryAttributeById(final CmsCategoryAttributeDto cmsCategoryAttribute) throws BizException {
		BizAssert.notNull(cmsCategoryAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryAttribute为空");
		BizAssert.notEmpty(cmsCategoryAttribute.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryAttributeCrudService.queryById(cmsCategoryAttribute.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsCategoryAttribute.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsCategoryAttribute updateModel = CmsCategoryAttributeConverterFactory.convertCmsCategoryAttributeEntity(cmsCategoryAttribute);
				cmsCategoryAttributeCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsCategoryAttributeById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsCategoryAttributeCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsCategoryAttributeCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsCategoryAttributeByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsCategoryAttributeById(id);
		}
	}
 
}
