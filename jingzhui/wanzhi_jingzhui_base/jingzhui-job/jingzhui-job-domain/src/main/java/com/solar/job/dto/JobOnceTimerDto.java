package com.solar.job.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.solar.common.core.base.BaseCreatedDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 一次性定时器 数据DTO对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Getter 
@Setter
@ApiModel(value = "一次性定时器")
public class JobOnceTimerDto extends BaseCreatedDto {


	@ApiModelProperty(name = "id", value = "任务ID", required = true)
    private String id;

	@ApiModelProperty(name = "jobName", value = "任务名称", required = true)
    private String jobName;

	@ApiModelProperty(name = "jobGroup", value = "任务组", required = true)
    private String jobGroup;

	@ApiModelProperty(name = "jobData", value = "任务执行参数", required = false)
    private String jobData;

	@ApiModelProperty(name = "proc", value = "任务执行程序 url:开头为URL请求", required = false)
    private String proc;

	@ApiModelProperty(name = "status", value = "状态 READY=待加入,WATTING=待处理,SUCCESS=成功,FAILED=失败", required = true)
    private String status;

	@ApiModelProperty(name = "nextExeTime", value = "下次执行开始时间", required = true)
    private Long nextExeTime;

	@ApiModelProperty(name = "lastExeStartTime", value = "最后一次执行开始时间", required = false)
    private Long lastExeStartTime;

	@ApiModelProperty(name = "lastExeEndTime", value = "最后一次执行结束时间", required = false)
    private Long lastExeEndTime;

	@ApiModelProperty(name = "exeStartTime", value = "执行开始时间", required = false)
    private Long exeStartTime;

	@ApiModelProperty(name = "exeEndTime", value = "执行结束时间", required = false)
    private Long exeEndTime;

	@ApiModelProperty(name = "exeCount", value = "当前执行次数", required = true)
    private Integer exeCount;

	@ApiModelProperty(name = "retryMaxAttempts", value = "失败执行最大次数", required = false)
    private Integer retryMaxAttempts;

	@ApiModelProperty(name = "retryMaxInterval", value = "重试间隔（秒）", required = false)
    private Integer retryMaxInterval;

	@ApiModelProperty(name = "result", value = "执行结果", required = false)
    private String result;

	@ApiModelProperty(name = "memo", value = "备注", required = false)
    private String memo;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId())
			.append("jobName", getJobName())
			.append("jobGroup", getJobGroup())
			.append("jobData", getJobData())
			.append("proc", getProc())
			.append("status", getStatus())
			.append("nextExeTime", getNextExeTime())
			.append("lastExeStartTime", getLastExeStartTime())
			.append("lastExeEndTime", getLastExeEndTime())
			.append("exeStartTime", getExeStartTime())
			.append("exeEndTime", getExeEndTime())
			.append("exeCount", getExeCount())
			.append("retryMaxAttempts", getRetryMaxAttempts())
			.append("retryMaxInterval", getRetryMaxInterval())
			.append("result", getResult())
			.append("memo", getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobOnceTimerDto == false) return false;
		if(this == obj) return true;
		JobOnceTimerDto other = (JobOnceTimerDto)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}