package com.solar.job.scheduling;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTimerScheduler implements InitializingBean, DisposableBean  {

	protected static final Logger LOG = LoggerFactory.getLogger(AbstractTimerScheduler.class);

	@Autowired
    private Scheduler scheduler;
    
	public abstract void initialization();
	
	public void addJob(JobDetail jobDetail, CronTrigger trigger) {
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			LOG.error("add job error", e);
		}
	}
	
	public void deleteJob(String jobGroup, String jobName) {
		try {
			if (checkExists(jobGroup, jobName)) {
				scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
			}
		} catch (SchedulerException e) {
			LOG.error("delete job error", e);
		}
	}
	
	public boolean checkExists (String jobGroup,String jobName) {
		try {
			return scheduler.checkExists(JobKey.jobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			LOG.error("add job check exists error", e);
			return false;
		}
	}
	
//	/**
//	 * 任务启动
//	 */
//	public void start() {
//		try {
//			if (!scheduler.isShutdown()) {
//				scheduler.start();
//			}
//		} catch (SchedulerException e) {
//			LOG.error("start job error", e);
//			return;
//		}
//	}
//	
//	/**
//	 * 任务停止
//	 */
//	public void shutdown() {
//		try {
//			if (!scheduler.isStarted()) {
//				scheduler.shutdown();
//			}
//		} catch (SchedulerException e) {
//			LOG.error("shutdown job error", e);
//			return;
//		}
//	}
		
	@Override
	public void afterPropertiesSet() throws Exception {
		initialization();
	}
	
	@Override
	public void destroy() throws Exception {
	} 
	
	/**
	 * 一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。 
		按顺序依次为 
		秒（0~59） 
		分钟（0~59） 
		小时（0~23） 
		天（月）（0~31，但是你需要考虑你月的天数） 
		月（0~11） 
		天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT） 
		年份（1970－2099）
	 * 
	 * @param date
	 * @return
	 */
    protected String parseCronExpression(Long  date){  
        String dateFormat="ss mm HH dd MM ? yyyy";  
        return DateFormatUtils.format(date, dateFormat);  
    }



}
