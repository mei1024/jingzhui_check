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
import com.solar.cms.converter.CmsContentAttributeConverterFactory;
import com.solar.cms.service.CmsContentAttributeService;
import com.solar.cms.dal.crud.CmsContentAttributeCrudService;
import com.solar.cms.entity.CmsContentAttribute;
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.query.CmsContentAttributeQuery;

/**
 * 内容扩展 CmsContentAttribute 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsContentAttributeService")
public class CmsContentAttributeServiceImpl extends BaseServiceImpl implements CmsContentAttributeService {
	
	@Autowired
	private CmsContentAttributeCrudService cmsContentAttributeCrudService;
	
	@Override
	public CmsContentAttributeDto queryCmsContentAttributeById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsContentAttribute cmsContentAttribute = cmsContentAttributeCrudService.queryById(id);
		return CmsContentAttributeConverterFactory.convertCmsContentAttributeDTO(cmsContentAttribute);
	}
 
	@Override
	public List<CmsContentAttributeDto> queryCmsContentAttributeAllList() throws BizException {
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryAllList();

		return CmsContentAttributeConverterFactory.convertCmsContentAttributeListDTO(list);
	}
	
	@Override
	public List<CmsContentAttributeDto> queryCmsContentAttributeList(final CmsContentAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryList(search.getParameters());
		
		return CmsContentAttributeConverterFactory.convertCmsContentAttributeListDTO(list);
	}
	
	@Override
	public Pagination<CmsContentAttributeDto> queryPageCmsContentAttribute(final CmsContentAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsContentAttributeCrudService.count(search.getParameters());

		List<CmsContentAttributeDto> resultList = CmsContentAttributeConverterFactory.convertCmsContentAttributeListDTO(list);
		
		return new Pagination<CmsContentAttributeDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsContentAttributeDto saveCmsContentAttribute(final CmsContentAttributeDto cmsContentAttribute) throws BizException {
		BizAssert.notNull(cmsContentAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentAttribute为空");
		
		return (CmsContentAttributeDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsContentAttribute createModel = CmsContentAttributeConverterFactory.convertCmsContentAttributeEntity(cmsContentAttribute);
				cmsContentAttributeCrudService.save(createModel);
				
				CmsContentAttributeDto result = CmsContentAttributeConverterFactory.convertCmsContentAttributeDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsContentAttributeById(final CmsContentAttributeDto cmsContentAttribute) throws BizException {
		BizAssert.notNull(cmsContentAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentAttribute为空");
		BizAssert.notEmpty(cmsContentAttribute.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentAttributeCrudService.queryById(cmsContentAttribute.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsContentAttribute.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsContentAttribute updateModel = CmsContentAttributeConverterFactory.convertCmsContentAttributeEntity(cmsContentAttribute);
				cmsContentAttributeCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsContentAttributeById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentAttributeCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsContentAttributeCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsContentAttributeByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsContentAttributeById(id);
		}
	}
 
}
