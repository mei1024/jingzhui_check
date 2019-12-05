package com.solar.job.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.nebula.common.biz.util.BizAssert;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.job.dto.JobOnceTimerDto;
import com.solar.job.entity.JobOnceTimer;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class JobOnceTimerConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static JobOnceTimerDto convertJobOnceTimerDTO(JobOnceTimer jobOnceTimer) {
		BizAssert.notNull(jobOnceTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobOnceTimer null");

		JobOnceTimerDto result = new JobOnceTimerDto();
		result.setId(jobOnceTimer.getId());
		result.setJobName(jobOnceTimer.getJobName());
		result.setJobGroup(jobOnceTimer.getJobGroup());
		result.setJobData(jobOnceTimer.getJobData());
		result.setProc(jobOnceTimer.getProc());
		result.setStatus(jobOnceTimer.getStatus());
		result.setNextExeTime(jobOnceTimer.getNextExeTime());
		result.setLastExeStartTime(jobOnceTimer.getLastExeStartTime());
		result.setLastExeEndTime(jobOnceTimer.getLastExeEndTime());
		result.setExeStartTime(jobOnceTimer.getExeStartTime());
		result.setExeEndTime(jobOnceTimer.getExeEndTime());
		result.setExeCount(jobOnceTimer.getExeCount());
		result.setRetryMaxAttempts(jobOnceTimer.getRetryMaxAttempts());
		result.setRetryMaxInterval(jobOnceTimer.getRetryMaxInterval());
		result.setResult(jobOnceTimer.getResult());
		result.setMemo(jobOnceTimer.getMemo());
		result.setCreatedAt(jobOnceTimer.getCreateDate());
		result.setModifiedAt(jobOnceTimer.getLastModDate());
		
		return result;
	}

	public static List<JobOnceTimerDto> convertJobOnceTimerListDTO(List<JobOnceTimer> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<JobOnceTimerDto>(0);
		}
		
		List<JobOnceTimerDto> resultList = new ArrayList<JobOnceTimerDto>();
		for (JobOnceTimer po : list) {
			resultList.add(convertJobOnceTimerDTO(po));
		}
		return resultList;
	}
	

	public static JobOnceTimer convertJobOnceTimerEntity(JobOnceTimerDto jobOnceTimer) {
		BizAssert.notNull(jobOnceTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobOnceTimer null");
		
		JobOnceTimer result = new JobOnceTimer();
		result.setId(jobOnceTimer.getId());
		result.setJobName(jobOnceTimer.getJobName());
		result.setJobGroup(jobOnceTimer.getJobGroup());
		result.setJobData(jobOnceTimer.getJobData());
		result.setProc(jobOnceTimer.getProc());
		result.setStatus(jobOnceTimer.getStatus());
		result.setNextExeTime(jobOnceTimer.getNextExeTime());
		result.setLastExeStartTime(jobOnceTimer.getLastExeStartTime());
		result.setLastExeEndTime(jobOnceTimer.getLastExeEndTime());
		result.setExeStartTime(jobOnceTimer.getExeStartTime());
		result.setExeEndTime(jobOnceTimer.getExeEndTime());
		result.setExeCount(jobOnceTimer.getExeCount());
		result.setRetryMaxAttempts(jobOnceTimer.getRetryMaxAttempts());
		result.setRetryMaxInterval(jobOnceTimer.getRetryMaxInterval());
		result.setResult(jobOnceTimer.getResult());
		result.setMemo(jobOnceTimer.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
	}
	
}