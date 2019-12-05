package com.solar.job.scheduling.once;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solar.job.scheduling.JobOnceTimerScheduler;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class OnceTimerScanJob implements org.quartz.Job {
	
	protected static final Logger LOG = LoggerFactory.getLogger(OnceTimerScanJob.class);
	
	@Autowired
	private JobOnceTimerScheduler jobOnceTimerScheduler;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("OnceTimer 扫描定时器任务开始执行...");
		jobOnceTimerScheduler.scan();
		LOG.info("OnceTimer 扫描定时器任务执行完成...");
	}

}