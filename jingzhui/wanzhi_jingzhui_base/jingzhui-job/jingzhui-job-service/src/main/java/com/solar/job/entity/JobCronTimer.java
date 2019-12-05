package com.solar.job.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务定时器 job_cron_timer 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Getter 
@Setter
public class JobCronTimer extends ModelCreated {


	/** 任务名称 */
    private String jobName;

	/** 任务组 */
    private String jobGroup;

	/** 任务执行时间 */
    private String cron;

	/** 任务执行参数 */
    private String jobData;

	/** 任务执行程序 */
    private String proc;

	/** 失败执行最大次数 */
    private Integer retryMaxAttempts;

	/** 重试间隔（秒） */
    private Integer retryMaxInterval;

	/** 状态 READY=待加入,RUNNING=运行中,PAUSED=已暂停 */
    private String status;

	/** 上次执行开始时间 */
    private Long lastExeStartTime;

	/** 上次执行结束时间 */
    private Long lastExeEndTime;

	/** 备注 */
    private String memo;

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("jobName", getJobName())
			.append("jobGroup", getJobGroup())
			.append("cron", getCron())
			.append("jobData", getJobData())
			.append("proc", getProc())
			.append("retryMaxAttempts", getRetryMaxAttempts())
			.append("retryMaxInterval", getRetryMaxInterval())
			.append("status", getStatus())
			.append("lastExeStartTime", getLastExeStartTime())
			.append("lastExeEndTime", getLastExeEndTime())
			.append("memo", getMemo())
			.append("dstatus", getDstatus())
			.append("creator", getCreator())
			.append("createDate", getCreateDate())
			.append("lastModifier", getLastModifier())
			.append("lastModDate", getLastModDate())
			.append("version", getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobCronTimer == false) return false;
		if(this == obj) return true;
		JobCronTimer other = (JobCronTimer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
