package com.solar.job.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.solar.job.converter.JobCronTimerConverterFactory;
import com.solar.job.dal.crud.JobCronTimerCrudService;
import com.solar.job.dto.JobCronTimerDto;
import com.solar.job.entity.JobCronTimer;
import com.solar.job.enums.TimerCronEnum;
import com.solar.job.query.JobCronTimerQuery;

/**
 * 任务定时器 JobCronTimer 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Service("jobCronTimerService")
public class JobCronTimerServiceImpl extends BaseServiceImpl implements JobCronTimerService {
	
	@Autowired
	private JobCronTimerCrudService jobCronTimerCrudService;
	
	@Override
	public JobCronTimerDto queryJobCronTimerById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		JobCronTimer jobCronTimer = jobCronTimerCrudService.queryById(id);
		return JobCronTimerConverterFactory.convertJobCronTimerDTO(jobCronTimer);
	}
 
	@Override
	public List<JobCronTimerDto> queryJobCronTimerAllList() throws BizException {
		List<JobCronTimer> list = jobCronTimerCrudService.queryAllList();

		return JobCronTimerConverterFactory.convertJobCronTimerListDTO(list);
	}
	
	@Override
	public List<JobCronTimerDto> queryJobCronTimerList(final JobCronTimerQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<JobCronTimer> list = jobCronTimerCrudService.queryList(search.getParameters());
		
		return JobCronTimerConverterFactory.convertJobCronTimerListDTO(list);
	}
	
	@Override
	public Pagination<JobCronTimerDto> queryPageJobCronTimer(final JobCronTimerQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<JobCronTimer> list = jobCronTimerCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = jobCronTimerCrudService.count(search.getParameters());

		List<JobCronTimerDto> resultList = JobCronTimerConverterFactory.convertJobCronTimerListDTO(list);
		
		return new Pagination<JobCronTimerDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public JobCronTimerDto saveJobCronTimer(final JobCronTimerDto jobCronTimer) throws BizException {
		BizAssert.notNull(jobCronTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobCronTimer为空");
		
		return (JobCronTimerDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				JobCronTimer createModel = JobCronTimerConverterFactory.convertJobCronTimerEntity(jobCronTimer);
				jobCronTimerCrudService.save(createModel);
				
				JobCronTimerDto result = JobCronTimerConverterFactory.convertJobCronTimerDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateJobCronTimerById(final JobCronTimerDto jobCronTimer) throws BizException {
		BizAssert.notNull(jobCronTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobCronTimer为空");
		BizAssert.notEmpty(jobCronTimer.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (jobCronTimerCrudService.queryById(jobCronTimer.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + jobCronTimer.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				JobCronTimer updateModel = JobCronTimerConverterFactory.convertJobCronTimerEntity(jobCronTimer);
				jobCronTimerCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}

	@Override
	public void updateAllTimerStatusReady() throws BizException {
		
		Map<String,Object> updateStatusFieldMap = new HashMap<>();
		updateStatusFieldMap.put("status", TimerCronEnum.Status.READY);
		
		Map<String,Object> updateStatusConditionMap = new HashMap<>();
		updateStatusConditionMap.put("status", TimerCronEnum.Status.RUNNING);
		
		jobCronTimerCrudService.updateByCondition(updateStatusFieldMap, updateStatusConditionMap);
	}
 
	@Override
	public void deleteJobCronTimerById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (jobCronTimerCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = jobCronTimerCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteJobCronTimerByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteJobCronTimerById(id);
		}
	}

}
