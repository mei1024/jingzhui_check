package com.solar.job.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.solar.common.core.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务定时器 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Getter 
@Setter
@ApiModel(value = "任务定时器")
public class JobCronTimerDto extends BaseDto {


	@ApiModelProperty(name = "id", value = "任务ID", required = true)
    private String id;

	@ApiModelProperty(name = "jobName", value = "任务名称", required = true)
    private String jobName;

	@ApiModelProperty(name = "jobGroup", value = "任务组", required = true)
    private String jobGroup;

	@ApiModelProperty(name = "cron", value = "任务执行时间", required = true)
    private String cron;

	@ApiModelProperty(name = "jobData", value = "任务执行参数", required = false)
    private String jobData;

	@ApiModelProperty(name = "proc", value = "任务执行程序", required = false)
    private String proc;

	@ApiModelProperty(name = "retryMaxAttempts", value = "失败执行最大次数", required = false)
    private Integer retryMaxAttempts;

	@ApiModelProperty(name = "retryMaxInterval", value = "重试间隔（秒）", required = false)
    private Integer retryMaxInterval;

	@ApiModelProperty(name = "status", value = "状态 READY=待加入,RUNNING=运行中,PAUSED=已暂停", required = true)
    private String status;

	@ApiModelProperty(name = "lastExeStartTime", value = "上次执行开始时间", required = false)
    private Long lastExeStartTime;

	@ApiModelProperty(name = "lastExeEndTime", value = "上次执行结束时间", required = false)
    private Long lastExeEndTime;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
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
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobCronTimerDto == false) return false;
		if(this == obj) return true;
		JobCronTimerDto other = (JobCronTimerDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}