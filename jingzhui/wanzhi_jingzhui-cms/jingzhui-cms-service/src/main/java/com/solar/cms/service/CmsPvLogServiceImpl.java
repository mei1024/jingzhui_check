package com.solar.cms.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.converter.CmsPvLogConverterFactory;
import com.solar.cms.dal.crud.CmsContentCrudService;
import com.solar.cms.dal.crud.CmsPvLogCrudService;
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.entity.CmsContent;
import com.solar.cms.entity.CmsPvLog;
import com.solar.cms.query.CmsPvLogQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * PV记录 CmsPvLog 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsPvLogService")
public class CmsPvLogServiceImpl extends BaseServiceImpl implements CmsPvLogService {
	
	@Autowired
	private CmsPvLogCrudService cmsPvLogCrudService;
	
	@Autowired
	private CmsContentCrudService cmsContentCrudService;

	@Override
	public CmsPvLogDto queryCmsPvLogById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsPvLog cmsPvLog = cmsPvLogCrudService.queryById(id);
		return CmsPvLogConverterFactory.convertCmsPvLogDTO(cmsPvLog);
	}
 
	@Override
	public List<CmsPvLogDto> queryCmsPvLogAllList() throws BizException {
		List<CmsPvLog> list = cmsPvLogCrudService.queryAllList();

		return CmsPvLogConverterFactory.convertCmsPvLogListDTO(list);
	}
	
	@Override
	public List<CmsPvLogDto> queryCmsPvLogList(final CmsPvLogQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsPvLog> list = cmsPvLogCrudService.queryList(search.getParameters());
		
		return CmsPvLogConverterFactory.convertCmsPvLogListDTO(list);
	}
	
	@Override
	public Pagination<CmsPvLogDto> queryPageCmsPvLog(final CmsPvLogQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsPvLog> list = cmsPvLogCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsPvLogCrudService.count(search.getParameters());

		List<CmsPvLogDto> resultList = CmsPvLogConverterFactory.convertCmsPvLogListDTO(list);
		
		return new Pagination<CmsPvLogDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Async
	@Override
	public void saveCmsPvLog(final CmsPvLogDto cmsPvLog) throws BizException {
		BizAssert.notNull(cmsPvLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsPvLog为空");
		BizAssert.notEmpty(cmsPvLog.getObjectType(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:objectType为空");
		BizAssert.notEmpty(cmsPvLog.getObjectId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:objectId为空");
		BizAssert.notEmpty(cmsPvLog.getUserId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:userId为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsContent content = cmsContentCrudService.queryById(cmsPvLog.getObjectId());
				if (content == null) {
					return false;
				}
				
				// 1. 保存访问明细
				CmsPvLog createModel = CmsPvLogConverterFactory.convertCmsPvLogEntity(cmsPvLog);
				createModel.setPvtime(new Date());
				cmsPvLogCrudService.save(createModel);

				// 2. 统计PV
				Map<String,Object> countPv = new HashMap<>();
				countPv.put("object_type", cmsPvLog.getObjectType());
				countPv.put("object_id", cmsPvLog.getObjectId());
				Integer clicks = cmsPvLogCrudService.count(countPv);
				
				// 2. 更新PV数量
				CmsContent updateContentPvCount = new CmsContent();
				updateContentPvCount.setId(cmsPvLog.getObjectId());
				updateContentPvCount.setClicks(clicks);
				cmsContentCrudService.update(updateContentPvCount);

				
				return true;
			}
			
		});
	}
	
	@Override
	public void updateCmsPvLogById(final CmsPvLogDto cmsPvLog) throws BizException {
		BizAssert.notNull(cmsPvLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsPvLog为空");
		BizAssert.notEmpty(cmsPvLog.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsPvLogCrudService.queryById(cmsPvLog.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsPvLog.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsPvLog updateModel = CmsPvLogConverterFactory.convertCmsPvLogEntity(cmsPvLog);
				cmsPvLogCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsPvLogById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsPvLogCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsPvLogCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsPvLogByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsPvLogById(id);
		}
	}
 
}
