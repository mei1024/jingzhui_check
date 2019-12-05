package com.solar.job.scheduling;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.solar.job.dto.JobOnceTimerDto;
import com.solar.job.enums.TimerOnceEnum;
import com.solar.job.scheduling.once.OnceTimerJob;
import com.solar.job.scheduling.once.OnceTimerScanJob;
import com.solar.job.service.JobOnceTimerService;

@Component("jobOnceTimerScheduler")
@Lazy(false)
public class JobOnceTimerScheduler extends AbstractTimerScheduler {

	protected static final Logger LOG = LoggerFactory.getLogger(JobOnceTimerScheduler.class);

	// 每10秒扫描一次
//	private static final String SCAN_CRON_EXPRESSION = "*/10 * * * * ?";
	// 每月1号触发一次
	private static final String SCAN_CRON_EXPRESSION = "0 0 0 1 * ?";

	@Autowired
	private JobOnceTimerService jobOnceTimerService;
	
	@Override
	public void initialization() {
		// 更新定时器状态初始状态
		jobOnceTimerService.updateAllTimerStatusReady();
		
		// 加入扫描任务
		addScanJob();
	}
	
    /**
     * 扫描待加入的定时器
     */
	public void scan() {
		List<JobOnceTimerDto> list = jobOnceTimerService.queryWattingJoinList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (JobOnceTimerDto job : list) {
				if (job.getNextExeTime() != null) {
					addJob(job);
				}
			}
		}
	}
	
	public void addJob(JobOnceTimerDto job) {
		
		// 任务时间到了后，执行时间延迟5秒后执行
		if (job.getNextExeTime() < System.currentTimeMillis()) {
			job.setNextExeTime(System.currentTimeMillis() + DateUtils.MILLIS_PER_SECOND * 5);
		}
		
		String jobName = "JOB_" + job.getJobName() + "_" + job.getId();
		String jobGroup = "JOB_GROUP_" + job.getJobGroup();
		String triggerName = "TRIGGER_" + job.getJobName() + "_" + job.getId();
		String triggerGroup = "TRIGGER_GROUP_" + job.getJobGroup();
		
		if (checkExists(jobGroup, jobName)) {
			return;
		}
		
		String cronExpression = parseCronExpression(job.getNextExeTime());
		
        JobDetail jobDetail = JobBuilder.newJob(OnceTimerJob.class)
                .withDescription(job.getMemo())
                .usingJobData("JOB_DATA", job.getJobData())
                .usingJobData("JOB_ID", job.getId())
                .usingJobData("JOB_GROUP", job.getJobGroup())
                .usingJobData("JOB_PROC", job.getProc())
                .usingJobData("JOB_NAME", job.getJobName())
                .withIdentity(jobName, jobGroup).build();
       
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withDescription(job.getMemo())
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		
        // 加入定时器
		addJob(jobDetail, trigger);

		// 更新定时器状态=待处理
		jobOnceTimerService.updateTimerStatus(job.getId(), TimerOnceEnum.Status.WATTING);
		
		LOG.info("add once job success cron={},info={}", cronExpression, job);
		
	}
	
	/**
	 * 加入默认扫描任务
	 */
	private void addScanJob() {
		String jobName = "JOB_ONCE_TIMER_SCAN";
		String jobGroup = "JOB_GROUP_SCAN";

		String triggerName = "TRIGGER_ONCE_TIMER_SCAN";
		String triggerGroup = "TRIGGER_GROUP_SCAN";

		JobDetail jobDetail = JobBuilder.newJob(OnceTimerScanJob.class).withIdentity(jobName, jobGroup).build();

		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup)
				.withSchedule(CronScheduleBuilder.cronSchedule(SCAN_CRON_EXPRESSION)).build();
		// 加入定时器
		addJob(jobDetail, trigger);
	}
	
}