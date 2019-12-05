package com.solar.bi.service;
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
import com.nebula.user.UserContext;
import com.solar.bi.converter.CheckRecordConverterFactory;
import com.solar.bi.dal.crud.CheckRecordCrudService;
import com.solar.bi.dal.dao.ext.CheckRecordExtDao;
import com.solar.bi.dto.CheckRecordCreateDto;
import com.solar.bi.dto.CheckRecordDto;
import com.solar.bi.dto.CountCheckRecord;
import com.solar.bi.entity.CheckRecord;
import com.solar.bi.enums.CheckTypeEnum;
import com.solar.bi.enums.SequenceKeyEnum;
import com.solar.bi.query.CheckRecordQuery;
import com.solar.common.bean.CodeText;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 检查记录 CheckRecord 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Service("checkRecordService")
public class CheckRecordServiceImpl extends BaseServiceImpl implements CheckRecordService {
	
	@Autowired
	private CheckRecordCrudService checkRecordCrudService;

	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private CheckRecordExtDao checkRecordExtDao;
	
	@Override
	public CheckRecordDto queryCheckRecordById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CheckRecord checkRecord = checkRecordCrudService.queryById(id);
		return CheckRecordConverterFactory.convertCheckRecordDTO(checkRecord);
	}
 
	@Override
	public List<CheckRecordDto> queryCheckRecordAllList() throws BizException {
		List<CheckRecord> list = checkRecordCrudService.queryAllList();

		return CheckRecordConverterFactory.convertCheckRecordListDTO(list);
	}
	
	@Override
	public List<CheckRecordDto> queryCheckRecordList(final CheckRecordQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CheckRecord> list = checkRecordCrudService.queryList(search.getParameters());
		
		return CheckRecordConverterFactory.convertCheckRecordListDTO(list);
	}
	
	@Override
	public Pagination<CheckRecordDto> queryPageCheckRecord(final CheckRecordQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("checkType", query.getCheckType());
		search.safeAddParamter("userId", query.getUserId());

		String order =  StringUtils.isNotEmpty(query.getOrder()) ? query.getOrder() : "createDate";
		SortByEnum sortby = StringUtils.isNotEmpty(query.getSortby()) ? SortByEnum.valueOf(query.getSortby().toUpperCase()) : SortByEnum.DESC;
		
		// query list
		List<CheckRecord> list = checkRecordCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), order, sortby);
		// query totals
		int totals = checkRecordCrudService.count(search.getParameters());

		List<CheckRecordDto> resultList = CheckRecordConverterFactory.convertCheckRecordListDTO(list);
		
		return new Pagination<CheckRecordDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CountCheckRecord countCheckRecord(String checkType) throws BizException {
		BizAssert.notEmpty(checkType, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkType为空");
		
		CountCheckRecord countCheckRecord = new CountCheckRecord();
		countCheckRecord.setCheckType(new CodeText(checkType, CheckTypeEnum.get(checkType).getText()));

		Search search = new Search();
		search.safeAddParamter("checkType", checkType);
		// 使用次数
		Integer useNumber = checkRecordCrudService.count(search.getParameters());
		countCheckRecord.setUseNumber(useNumber);
		// 使用人数
		Integer personNumber = checkRecordExtDao.personNumber(search.getParameters());
		countCheckRecord.setPersonNumber(personNumber);
		
		return countCheckRecord;
	}

	@Override
	public CheckRecordDto saveCheckRecord(final CheckRecordCreateDto checkRecord) throws BizException {
		BizAssert.notNull(checkRecord, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkRecord为空");
		
		return (CheckRecordDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				String id= sequenceService.getLongNextVal(SequenceKeyEnum.BI_CHECK_RECORD_ID.getKey()) + "";
				CheckRecord createModel = CheckRecordConverterFactory.convertCheckRecordCreateEntity(checkRecord);
				createModel.setUserId(UserContext.get().getUserId());
				createModel.setId(id);
				checkRecordCrudService.save(createModel);
				
				CheckRecordDto result = CheckRecordConverterFactory.convertCheckRecordDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCheckRecordById(final CheckRecordCreateDto checkRecord) throws BizException {
		BizAssert.notNull(checkRecord, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkRecord为空");
		BizAssert.notEmpty(checkRecord.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (checkRecordCrudService.queryById(checkRecord.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + checkRecord.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CheckRecord updateModel = CheckRecordConverterFactory.convertCheckRecordCreateEntity(checkRecord);
				checkRecordCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCheckRecordById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (checkRecordCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = checkRecordCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCheckRecordByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCheckRecordById(id);
		}
	}
 
}
