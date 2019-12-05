package com.solar.job.scheduling;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.solar.job.scheduling.repeat.RepeatCronJob;

@Component("jobCronTimerScheduler")
@Lazy(false)
public class JobCronTimerScheduler extends AbstractTimerScheduler {

	private Collection<RepeatCronJob> repeatCronJobs;

	@Override
	public void initialization() {
		if (CollectionUtils.isNotEmpty(repeatCronJobs)) {
			for (RepeatCronJob repeatCronJob : repeatCronJobs) {
				addReportJob(repeatCronJob);
			}
		}
	}

	/**
	 * 加入重复执行任务
	 * 
	 * @param repeatCronJob
	 */
	private void addReportJob (RepeatCronJob repeatCronJob) {
		LOG.info("任务 加入任务", repeatCronJob.name());
		
		String jobName = "JOB_" + repeatCronJob.name();
		String jobGroup = "JOB_GROUP_" + repeatCronJob.group();
		
		String triggerName = "TRIGGER_" + repeatCronJob.group();;
		String triggerGroup = "TRIGGER_GROUP_"+ repeatCronJob.group();

		JobDetail jobDetail = JobBuilder.newJob(repeatCronJob.getJobClass())
	                .withIdentity(jobName, jobGroup).build();
		 
		 CronTrigger trigger = TriggerBuilder.newTrigger()
	                .withIdentity(triggerName, triggerGroup)
	                .withSchedule(CronScheduleBuilder.cronSchedule(repeatCronJob.cronExpression())).build();
		// 加入定时器
		addJob(jobDetail, trigger);
	}
	
    @Autowired(required = false)
    public void setRepeatCronJobs(List<RepeatCronJob> repeatCronJobs) {
        this.repeatCronJobs = repeatCronJobs;
    }
}
