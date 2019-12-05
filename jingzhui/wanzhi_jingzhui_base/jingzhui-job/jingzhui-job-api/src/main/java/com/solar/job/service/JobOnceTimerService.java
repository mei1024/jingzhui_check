package com.solar.job.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.job.dto.JobOnceTimerDto;
import com.solar.job.enums.TimerOnceEnum;
import com.solar.job.query.JobOnceTimerQuery;

/**
 * 一次性定时器 JobOnceTimer 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
public interface JobOnceTimerService {
	
	/**
	 * 根据ID查询一次性定时器
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	JobOnceTimerDto queryJobOnceTimerById(String id) throws BizException;
	
	/**
	 * 根据任务组+任务名称查询
	 * 
	 * @param jobGroup 任务组
	 * @param jobName 任务名称
	 * @return
	 * @throws BizException
	 */
	JobOnceTimerDto queryJobOnceTimerByJobGroupAndName(String jobGroup, String jobName) throws BizException;
	
	/**
	 * 查询全部一次性定时器
	 * 
	 * @return
	 * @throws BizException
	 */
	List<JobOnceTimerDto> queryJobOnceTimerAllList() throws BizException;
	
	/**
	 * 查询等待加入的定时器记录
	 * 
	 * @return
	 * @throws BizException
	 */
	List<JobOnceTimerDto> queryWattingJoinList() throws BizException;
	
	/**
	 * 带条件查询一次性定时器
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<JobOnceTimerDto> queryJobOnceTimerList(JobOnceTimerQuery query) throws BizException;
	
	/**
	 * 分页查询jobOnceTimer
	 * 
	 * @param query 查询对象
	 * <pre>
	 * 	 count=单页显示记录数
	 * 	 page=当前页面
	 * </pre>
	 * 
	 * @return
	 * @throws BizException
	 */
	Pagination<JobOnceTimerDto> queryPageJobOnceTimer(JobOnceTimerQuery query) throws BizException;

	/**
	 * 保存一次性定时器
	 * 
	 * @param jobOnceTimer
	 * @return
	 * @throws BizException
	 */
	JobOnceTimerDto saveJobOnceTimer(JobOnceTimerDto jobOnceTimer) throws BizException;
	
	/**
	 * 更新一次性定时器
	 * 
	 * @param jobOnceTimer
	 * @throws BizException
	 */
	void updateJobOnceTimerById(JobOnceTimerDto jobOnceTimer) throws BizException;
	
	/**
	 * 更新任务定时器为初始状态
	 * 
	 * @throws BizException
	 */
	void updateAllTimerStatusReady() throws BizException;
	
	/**
	 * 定时器状态更新
	 * @param id
	 * @param status
	 * @throws BizException
	 */
	void updateTimerStatus(String id, TimerOnceEnum.Status status) throws BizException;
	
	/**
	 * 定时器状态更新
	 * 
	 * @param id 定时器ID
	 * @param status 要更新的状态
	 * @throws BizException
	 */
	void updateTimerStatus(String id, TimerOnceEnum.Status status, String result) throws BizException;
	
	/**
	 * 定时器任务重试更新
	 * 
	 * @param id 定时器ID
	 * @throws BizException
	 */
	void updateTimerStatusRetry(String jobId, long exeStartTime, long exeEndTime, String result) throws BizException;
	
	/**
	 * 任务删除 根据任务组+任务名称
	 * @param jobGroup 任务组
	 * @param jobName  任务名称
	 * @throws BizException
	 */
	void deleteJobOnceTimerByJobGroupAndName(String jobGroup, String jobName) throws BizException;
	
	/**
	 * 根据ID删除一次性定时器
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteJobOnceTimerById(String id) throws BizException;
	
	/**
	 * 批量删除一次性定时器
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteJobOnceTimerByIds(List<String> ids) throws BizException;

	
}