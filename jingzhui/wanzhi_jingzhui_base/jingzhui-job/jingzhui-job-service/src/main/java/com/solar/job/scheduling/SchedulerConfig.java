package com.solar.job.scheduling;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.nebula.log.Log;

@Configuration
public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent>{    
	
	@Autowired
	private JobFactory jobFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Log.info("任务已经启动..." + event.getSource());
	}
	
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

	@Bean(name = "schedulerFactoryBean")
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		// 创建SchedulerFactoryBean
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setQuartzProperties(quartzProperties());
		// 使用数据源，自定义数据源
		// factory.setDataSource(this.primaryDataSource);
		factory.setJobFactory(jobFactory);
		factory.setWaitForJobsToCompleteOnShutdown(true);// 这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
		factory.setOverwriteExistingJobs(false);
		factory.setStartupDelay(1);
		return factory;
	}

	/*
	 * 通过SchedulerFactoryBean获取Scheduler的实例
	 */
	@Bean(name = "scheduler")
	public Scheduler scheduler() throws IOException {
		return schedulerFactoryBean().getScheduler();
	}
}