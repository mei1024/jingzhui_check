package com.solar.job.entity;

import com.nebula.common.domain.ModelCreated;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 一次性定时器 job_once_timer 数据模型对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@Getter 
@Setter
public class JobOnceTimer extends ModelCreated {


	/** 任务名称 */
    private String jobName;

	/** 任务组 */
    private String jobGroup;

	/** 任务执行参数 */
    private String jobData;

	/** 任务执行程序 url:开头为URL请求 */
    private String proc;

	/** 状态 READY=待加入,WATTING=待处理,SUCCESS=成功,FAILED=失败 */
    private String status;

	/** 下次执行开始时间 */
    private Long nextExeTime;

	/** 最后一次执行开始时间 */
    private Long lastExeStartTime;

	/** 最后一次执行结束时间 */
    private Long lastExeEndTime;

	/** 执行开始时间 */
    private Long exeStartTime;

	/** 执行结束时间 */
    private Long exeEndTime;

	/** 当前执行次数 */
    private Integer exeCount;

	/** 失败执行最大次数 */
    private Integer retryMaxAttempts;

	/** 重试间隔（秒） */
    private Integer retryMaxInterval;

	/** 执行结果 */
    private String result;

	/** 备注 */
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
		if(obj instanceof JobOnceTimer == false) return false;
		if(this == obj) return true;
		JobOnceTimer other = (JobOnceTimer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
		
}
