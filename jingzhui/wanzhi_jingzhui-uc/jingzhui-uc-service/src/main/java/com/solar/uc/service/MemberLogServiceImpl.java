package com.solar.uc.service;
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
import com.nebula.sequence.SequenceService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.uc.converter.MemberLogConverterFactory;
import com.solar.uc.dal.crud.MemberLogCrudService;
import com.solar.uc.dto.MemberLogDto;
import com.solar.uc.entity.MemberLog;
import com.solar.uc.enums.SequenceKeyEnum;
import com.solar.uc.query.MemberLogQuery;

/**
 * 会员日志 MemberLog 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@Service("memberLogService")
public class MemberLogServiceImpl extends BaseServiceImpl implements MemberLogService {
	
	@Autowired
	private MemberLogCrudService memberLogCrudService;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public MemberLogDto queryMemberLogById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		MemberLog memberLog = memberLogCrudService.queryById(id);
		return MemberLogConverterFactory.convertMemberLogDTO(memberLog);
	}
 
	@Override
	public List<MemberLogDto> queryMemberLogAllList() throws BizException {
		List<MemberLog> list = memberLogCrudService.queryAllList();

		return MemberLogConverterFactory.convertMemberLogListDTO(list);
	}
	
	@Override
	public List<MemberLogDto> queryMemberLogList(final MemberLogQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<MemberLog> list = memberLogCrudService.queryList(search.getParameters());
		
		return MemberLogConverterFactory.convertMemberLogListDTO(list);
	}
	
	@Override
	public Pagination<MemberLogDto> queryPageMemberLog(final MemberLogQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<MemberLog> list = memberLogCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = memberLogCrudService.count(search.getParameters());

		List<MemberLogDto> resultList = MemberLogConverterFactory.convertMemberLogListDTO(list);
		
		return new Pagination<MemberLogDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public MemberLogDto saveMemberLog(final MemberLogDto memberLog) throws BizException {
		BizAssert.notNull(memberLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberLog为空");
		
		return (MemberLogDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				String id = sequenceService.getLongNextVal(SequenceKeyEnum.UC_MEMBER_LOG_ID.getKey()) + "";
				MemberLog createModel = MemberLogConverterFactory.convertMemberLogEntity(memberLog);
				createModel.setId(id);
				memberLogCrudService.save(createModel);
				
				MemberLogDto result = MemberLogConverterFactory.convertMemberLogDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateMemberLogById(final MemberLogDto memberLog) throws BizException {
		BizAssert.notNull(memberLog, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberLog为空");
		BizAssert.notEmpty(memberLog.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberLogCrudService.queryById(memberLog.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + memberLog.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				MemberLog updateModel = MemberLogConverterFactory.convertMemberLogEntity(memberLog);
				memberLogCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteMemberLogById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberLogCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = memberLogCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteMemberLogByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteMemberLogById(id);
		}
	}
 
}
