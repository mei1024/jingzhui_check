package com.solar.job.scheduling.repeat;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户日统计任务
 * 
 * ReportUserDayJob TODO
 * 
 * @author tllen
 * @date Jan 4, 2019 3:39:09 PM
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class ReportUserDayJob implements org.quartz.Job, RepeatCronJob {

	protected static final Logger LOG = LoggerFactory.getLogger(ReportUserDayJob.class);
	
	private static final String GROUP_NAME = "REPORT";
	
	// 每天凌晨2点执行一次
	private static final String SCAN_CRON_EXPRESSION = "0 0 2 * * ?";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date yesterdayDate = DateUtils.addDays(new Date(), -1);
		String reportDateDay = DateFormatUtils.format(yesterdayDate, "yyyy-MM-dd");

		LOG.info("用户[{}]日统计 定时器任务开始执行...", reportDateDay);

		LOG.info("用户[{}]日统计 定时器任务执行完成...", reportDateDay);
	}

	@Override
	public String name() {
		return this.getClass().getName();
	}

	@Override
	public String group() {
		return GROUP_NAME;
	}

	@Override
	public String cronExpression() {
		return SCAN_CRON_EXPRESSION;
	}

	@Override
	public Class<? extends Job> getJobClass() {
		return this.getClass();
	}

}
