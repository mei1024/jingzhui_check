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
import com.solar.cms.converter.CmsContentFileConverterFactory;
import com.solar.cms.service.CmsContentFileService;
import com.solar.cms.dal.crud.CmsContentFileCrudService;
import com.solar.cms.entity.CmsContentFile;
import com.solar.cms.dto.CmsContentFileDto;
import com.solar.cms.query.CmsContentFileQuery;

/**
 * 内容附件 CmsContentFile 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsContentFileService")
public class CmsContentFileServiceImpl extends BaseServiceImpl implements CmsContentFileService {
	
	@Autowired
	private CmsContentFileCrudService cmsContentFileCrudService;
	
	@Override
	public CmsContentFileDto queryCmsContentFileById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsContentFile cmsContentFile = cmsContentFileCrudService.queryById(id);
		return CmsContentFileConverterFactory.convertCmsContentFileDTO(cmsContentFile);
	}
 
	@Override
	public List<CmsContentFileDto> queryCmsContentFileAllList() throws BizException {
		List<CmsContentFile> list = cmsContentFileCrudService.queryAllList();

		return CmsContentFileConverterFactory.convertCmsContentFileListDTO(list);
	}
	
	@Override
	public List<CmsContentFileDto> queryCmsContentFileList(final CmsContentFileQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsContentFile> list = cmsContentFileCrudService.queryList(search.getParameters());
		
		return CmsContentFileConverterFactory.convertCmsContentFileListDTO(list);
	}
	
	@Override
	public Pagination<CmsContentFileDto> queryPageCmsContentFile(final CmsContentFileQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsContentFile> list = cmsContentFileCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsContentFileCrudService.count(search.getParameters());

		List<CmsContentFileDto> resultList = CmsContentFileConverterFactory.convertCmsContentFileListDTO(list);
		
		return new Pagination<CmsContentFileDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsContentFileDto saveCmsContentFile(final CmsContentFileDto cmsContentFile) throws BizException {
		BizAssert.notNull(cmsContentFile, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentFile为空");
		
		return (CmsContentFileDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsContentFile createModel = CmsContentFileConverterFactory.convertCmsContentFileEntity(cmsContentFile);
				cmsContentFileCrudService.save(createModel);
				
				CmsContentFileDto result = CmsContentFileConverterFactory.convertCmsContentFileDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsContentFileById(final CmsContentFileDto cmsContentFile) throws BizException {
		BizAssert.notNull(cmsContentFile, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContentFile为空");
		BizAssert.notEmpty(cmsContentFile.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentFileCrudService.queryById(cmsContentFile.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsContentFile.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsContentFile updateModel = CmsContentFileConverterFactory.convertCmsContentFileEntity(cmsContentFile);
				cmsContentFileCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsContentFileById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentFileCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsContentFileCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsContentFileByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsContentFileById(id);
		}
	}
 
}
