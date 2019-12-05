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
import com.solar.cms.converter.CmsContactConverterFactory;
import com.solar.cms.service.CmsContactService;
import com.solar.cms.dal.crud.CmsContactCrudService;
import com.solar.cms.entity.CmsContact;
import com.solar.cms.dto.CmsContactDto;
import com.solar.cms.query.CmsContactQuery;

/**
 * 联系我们 CmsContact 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181113
 * 
 */
@Service("cmsContactService")
public class CmsContactServiceImpl extends BaseServiceImpl implements CmsContactService {
	
	@Autowired
	private CmsContactCrudService cmsContactCrudService;
	
	@Override
	public CmsContactDto queryCmsContactById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsContact cmsContact = cmsContactCrudService.queryById(id);
		return CmsContactConverterFactory.convertCmsContactDTO(cmsContact);
	}
 
	@Override
	public List<CmsContactDto> queryCmsContactAllList() throws BizException {
		List<CmsContact> list = cmsContactCrudService.queryAllList();

		return CmsContactConverterFactory.convertCmsContactListDTO(list);
	}
	
	@Override
	public List<CmsContactDto> queryCmsContactList(final CmsContactQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsContact> list = cmsContactCrudService.queryList(search.getParameters());
		
		return CmsContactConverterFactory.convertCmsContactListDTO(list);
	}
	
	@Override
	public Pagination<CmsContactDto> queryPageCmsContact(final CmsContactQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsContact> list = cmsContactCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsContactCrudService.count(search.getParameters());

		List<CmsContactDto> resultList = CmsContactConverterFactory.convertCmsContactListDTO(list);
		
		return new Pagination<CmsContactDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsContactDto saveCmsContact(final CmsContactDto cmsContact) throws BizException {
		BizAssert.notNull(cmsContact, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContact为空");
		BizAssert.notNull(cmsContact.getFullname(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:fullname为空");
		BizAssert.notNull(cmsContact.getPhone(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:phone为空");
		BizAssert.notNull(cmsContact.getText(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:text为空");
		
		return (CmsContactDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsContact createModel = CmsContactConverterFactory.convertCmsContactEntity(cmsContact);
				cmsContactCrudService.save(createModel);
				
				CmsContactDto result = CmsContactConverterFactory.convertCmsContactDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsContactById(final CmsContactDto cmsContact) throws BizException {
		BizAssert.notNull(cmsContact, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContact为空");
		BizAssert.notEmpty(cmsContact.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContactCrudService.queryById(cmsContact.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsContact.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsContact updateModel = CmsContactConverterFactory.convertCmsContactEntity(cmsContact);
				cmsContactCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsContactById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContactCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsContactCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsContactByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsContactById(id);
		}
	}
 
}
