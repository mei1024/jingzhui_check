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
import com.solar.job.dto.JobCronTimerDto;
import com.solar.job.entity.JobCronTimer;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class JobCronTimerConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static JobCronTimerDto convertJobCronTimerDTO(JobCronTimer jobCronTimer) {
		BizAssert.notNull(jobCronTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobCronTimer null");

		JobCronTimerDto result = new JobCronTimerDto();
		result.setId(jobCronTimer.getId());
		result.setJobName(jobCronTimer.getJobName());
		result.setJobGroup(jobCronTimer.getJobGroup());
		result.setCron(jobCronTimer.getCron());
		result.setJobData(jobCronTimer.getJobData());
		result.setProc(jobCronTimer.getProc());
		result.setRetryMaxAttempts(jobCronTimer.getRetryMaxAttempts());
		result.setRetryMaxInterval(jobCronTimer.getRetryMaxInterval());
		result.setStatus(jobCronTimer.getStatus());
		result.setLastExeStartTime(jobCronTimer.getLastExeStartTime());
		result.setLastExeEndTime(jobCronTimer.getLastExeEndTime());
		result.setMemo(jobCronTimer.getMemo());
		result.setCreatedAt(jobCronTimer.getCreateDate());
		result.setCreator(userService.queryUserById(jobCronTimer.getCreator()));
		result.setModifiedAt(jobCronTimer.getLastModDate());
		result.setModifier(userService.queryUserById(jobCronTimer.getLastModifier()));
		
		return result;
	}

	public static List<JobCronTimerDto> convertJobCronTimerListDTO(List<JobCronTimer> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<JobCronTimerDto>(0);
		}
		
		List<JobCronTimerDto> resultList = new ArrayList<JobCronTimerDto>();
		for (JobCronTimer po : list) {
			resultList.add(convertJobCronTimerDTO(po));
		}
		return resultList;
	}
	

	public static JobCronTimer convertJobCronTimerEntity(JobCronTimerDto jobCronTimer) {
		BizAssert.notNull(jobCronTimer, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:jobCronTimer null");
		
		JobCronTimer result = new JobCronTimer();
		result.setId(jobCronTimer.getId());
		result.setJobName(jobCronTimer.getJobName());
		result.setJobGroup(jobCronTimer.getJobGroup());
		result.setCron(jobCronTimer.getCron());
		result.setJobData(jobCronTimer.getJobData());
		result.setProc(jobCronTimer.getProc());
		result.setRetryMaxAttempts(jobCronTimer.getRetryMaxAttempts());
		result.setRetryMaxInterval(jobCronTimer.getRetryMaxInterval());
		result.setStatus(jobCronTimer.getStatus());
		result.setLastExeStartTime(jobCronTimer.getLastExeStartTime());
		result.setLastExeEndTime(jobCronTimer.getLastExeEndTime());
		result.setMemo(jobCronTimer.getMemo());
		
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