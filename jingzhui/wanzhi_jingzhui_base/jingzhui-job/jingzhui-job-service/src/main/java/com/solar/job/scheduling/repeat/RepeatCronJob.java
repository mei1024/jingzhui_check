package com.solar.job.scheduling.repeat;

import org.quartz.Job;

/**
 * 
 * 重复执行的任务
 * 
 * RepeatCronJob   
 *  
 * @author tllen  
 * @date   Oct 29, 2019 10:50:33 AM   
 *
 */
public interface RepeatCronJob {

	String name();
	
	String group();
	
	Class<? extends Job> getJobClass();
	
	/**
	 * 
	 * @return
	 */
	String cronExpression();
}
