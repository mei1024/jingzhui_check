package com.solar.job.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
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
import com.solar.job.converter.JobOnceTimerConverterFactory;
import com.solar.job.dal.crud.JobOnceTimerCrudService;
import com.solar.job.dal.dao.ext.JobOnceTimerExtDao;
import com.solar.job.dto.JobOnceTimerDto;
import com.solar.job.entity.JobOnceTimer;
import com.solar.job.enums.TimerOnceEnum;
import com.solar.job.enums.TimerOnceEnum.Status;
import com.solar.job.query.JobOnceTimerQuery;
import com.solar.job.scheduling.JobOnceTimerScheduler;

/**
 * 一次性定时器 JobOnceTimer 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Service("jobOnceTimerService")
public class JobOnceTimerServiceImpl extends BaseServiceImpl implements JobOnceTimerService {
	
	@Autowired
	private JobOnceTimerCrudService jobOnceTimerCrudService;
	
	@Autowired
	private JobOnceTimerExtDao jobOnceTimerExtDao;
	
	@Autowired
	private JobOnceTimerScheduler jobOnceTimerScheduler;
	
	@Override
	public JobOnceTimerDto queryJobOnceTimerById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		JobOnceTimer jobOnceTimer = jobOnceTimerCrudService.queryById(id);
		return JobOnceTimerConverterFactory.convertJobOnceTimerDTO(jobOnceTimer);
	}
	
	@Override
	public JobOnceTimerDto queryJobOnceTimerByJobGroupAndName(String jobGroup, String jobName) throws BizException  {
		BizAssert.notEmpty(jobGroup, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobGroup为空");
		BizAssert.notEmpty(jobName, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobName为空");
		
		Map<String,Object> queryJobOnceTimerParamter = new HashMap<String,Object>();
		queryJobOnceTimerParamter.put("jobGroup", jobGroup);
		queryJobOnceTimerParamter.put("jobName", jobName);
		
		JobOnceTimer jobOnceTimer = jobOnceTimerCrudService.queryOne(queryJobOnceTimerParamter);
		if (jobOnceTimer == null) {
			return null;
		}
		return JobOnceTimerConverterFactory.convertJobOnceTimerDTO(jobOnceTimer);
	}
 
	@Override
	public List<JobOnceTimerDto> queryJobOnceTimerAllList() throws BizException {
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryAllList();

		return JobOnceTimerConverterFactory.convertJobOnceTimerListDTO(list);
	}
	
	@Override
	public List<JobOnceTimerDto> queryWattingJoinList() throws BizException {
		// 五分钟内的记录
		long maxExeTime = (System.currentTimeMillis() + DateUtils.MILLIS_PER_MINUTE * 5);
		
		Search search = new Search();
		search.safeAddParamter("maxExeTime", maxExeTime);
		search.safeAddParamter("status", TimerOnceEnum.Status.READY.name());
		List<JobOnceTimer> list = jobOnceTimerExtDao.queryList(getContextParams(), search.getParameters(),null,null);
		
		return JobOnceTimerConverterFactory.convertJobOnceTimerListDTO(list);		
	}
	
	@Override
	public List<JobOnceTimerDto> queryJobOnceTimerList(final JobOnceTimerQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryList(search.getParameters());
		
		return JobOnceTimerConverterFactory.convertJobOnceTimerListDTO(list);
	}
	
	@Override
	public Pagination<JobOnceTimerDto> queryPageJobOnceTimer(final JobOnceTimerQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = jobOnceTimerCrudService.count(search.getParameters());

		List<JobOnceTimerDto> resultList = JobOnceTimerConverterFactory.convertJobOnceTimerListDTO(list);
		
		return new Pagination<JobOnceTimerDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public JobOnceTimerDto saveJobOnceTimer(final JobOnceTimerDto jobOnceTimer) throws BizException {
		BizAssert.notNull(jobOnceTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobOnceTimer为空");
		BizAssert.notEmpty(jobOnceTimer.getJobGroup(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobGroup为空");
		BizAssert.notEmpty(jobOnceTimer.getJobName(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobName为空");
		BizAssert.notNull(jobOnceTimer.getNextExeTime(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:nextExeTime为空");
		BizAssert.notNull(jobOnceTimer.getProc(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:proc为空");
		
		return (JobOnceTimerDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				if (jobOnceTimer.getRetryMaxAttempts() == null) {
					jobOnceTimer.setRetryMaxAttempts(1);
				}
				if (jobOnceTimer.getRetryMaxInterval() == null) {
					jobOnceTimer.setRetryMaxInterval((int) DateUtils.MILLIS_PER_MINUTE);
				}
				
				// 任务定时器信息保存
				JobOnceTimer createModel = JobOnceTimerConverterFactory.convertJobOnceTimerEntity(jobOnceTimer);
				createModel.setExeCount(0);
				createModel.setStatus(TimerOnceEnum.Status.READY.name());
				jobOnceTimerCrudService.save(createModel);
				
				JobOnceTimerDto result = JobOnceTimerConverterFactory.convertJobOnceTimerDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateJobOnceTimerById(final JobOnceTimerDto jobOnceTimer) throws BizException {
		BizAssert.notNull(jobOnceTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobOnceTimer为空");
		BizAssert.notEmpty(jobOnceTimer.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (jobOnceTimerCrudService.queryById(jobOnceTimer.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + jobOnceTimer.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				JobOnceTimer updateModel = JobOnceTimerConverterFactory.convertJobOnceTimerEntity(jobOnceTimer);
				jobOnceTimerCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void updateAllTimerStatusReady() throws BizException {
		
		Map<String,Object> updateStatusFieldMap = new HashMap<>();
		updateStatusFieldMap.put("status", TimerOnceEnum.Status.READY);
		
		Map<String,Object> updateStatusConditionMap = new HashMap<>();
		updateStatusConditionMap.put("status", TimerOnceEnum.Status.WATTING);
		
		jobOnceTimerCrudService.updateByCondition(updateStatusFieldMap, updateStatusConditionMap);
	}
	
	@Override
	public void updateTimerStatus(String id, Status status) throws BizException {
		updateTimerStatus(id, status, null);
	}
	
	@Override
	public void updateTimerStatus(String id, Status status, String result) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notNull(status, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (jobOnceTimerCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(), ResultCodeEnum.DATA_NOT_FOUND.getCode(), "数据不存在:ID[" + id + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				JobOnceTimer updateModel = new JobOnceTimer();
				updateModel.setId(id);
				updateModel.setStatus(status.name());
				updateModel.setResult(result);
				jobOnceTimerCrudService.update(updateModel);
				
				return true;
			}
			
		});		
	}
	
	@Override
	public void updateTimerStatusRetry(String id, long exeStartTime, long exeEndTime, String result) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			private JobOnceTimer jobOnceTimer = null;
			
			@Override
			public void check() throws BizException {
				jobOnceTimer = jobOnceTimerCrudService.queryById(id);
				if (jobOnceTimerCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(), ResultCodeEnum.DATA_NOT_FOUND.getCode(), "数据不存在:ID[" + id + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				JobOnceTimer updateModel = new JobOnceTimer();
				updateModel.setId(id);
				updateModel.setStatus(Status.READY.name());
				if (jobOnceTimer.getExeCount() > jobOnceTimer.getRetryMaxAttempts()) {
					updateModel.setStatus(Status.FAILED.name());
				}
				updateModel.setNextExeTime(jobOnceTimer.getNextExeTime() + jobOnceTimer.getRetryMaxInterval() * jobOnceTimer.getExeCount() + 1);
				updateModel.setExeStartTime(exeStartTime);
				updateModel.setExeEndTime(exeEndTime);
				updateModel.setResult(result);
				updateModel.setExeCount(jobOnceTimer.getExeCount() +1);
				updateModel.setLastExeStartTime(jobOnceTimer.getExeStartTime());
				updateModel.setLastExeEndTime(jobOnceTimer.getExeEndTime());
				jobOnceTimerCrudService.update(updateModel);
				
				return true;
			}
			
		});		
	}
	
	@Override
	public void deleteJobOnceTimerByJobGroupAndName(String jobGroup, String jobName) throws BizException {
		// 查询定时器记录
		JobOnceTimerDto jobOnceTimer = queryJobOnceTimerByJobGroupAndName(jobGroup, jobName);
		
		// 删除定时器信息
		if (jobOnceTimer != null) {
			deleteJobOnceTimerById(jobOnceTimer.getId());
		}
	}
	
	@Override
	public void deleteJobOnceTimerById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			private JobOnceTimer jobOnceTimer = null;
			
			@Override
			public void check() throws BizException {
				jobOnceTimer = jobOnceTimerCrudService.queryById(id);
				if (jobOnceTimer == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				//  删除定时器数据库记录
				int result = jobOnceTimerCrudService.deleteById(id);
				
				// 删除定时器信息
				jobOnceTimerScheduler.deleteJob(jobOnceTimer.getJobGroup(), jobOnceTimer.getJobName());

				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteJobOnceTimerByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteJobOnceTimerById(id);
		}
	}

}
