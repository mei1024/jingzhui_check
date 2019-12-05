package com.solar.job.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.job.dto.JobCronTimerDto;
import com.solar.job.query.JobCronTimerQuery;

/**
 * 任务定时器 JobCronTimer 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
public interface JobCronTimerService {
	
	/**
	 * 根据ID查询任务定时器
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	JobCronTimerDto queryJobCronTimerById(String id) throws BizException;
	
	/**
	 * 查询全部任务定时器
	 * 
	 * @return
	 * @throws BizException
	 */
	List<JobCronTimerDto> queryJobCronTimerAllList() throws BizException;
	
	/**
	 * 带条件查询任务定时器
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<JobCronTimerDto> queryJobCronTimerList(JobCronTimerQuery query) throws BizException;
	
	/**
	 * 分页查询jobCronTimer
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
	Pagination<JobCronTimerDto> queryPageJobCronTimer(JobCronTimerQuery query) throws BizException;

	/**
	 * 保存任务定时器
	 * 
	 * @param jobCronTimer
	 * @return
	 * @throws BizException
	 */
	JobCronTimerDto saveJobCronTimer(JobCronTimerDto jobCronTimer) throws BizException;
	
	/**
	 * 更新任务定时器
	 * 
	 * @param jobCronTimer
	 * @throws BizException
	 */
	void updateJobCronTimerById(JobCronTimerDto jobCronTimer) throws BizException;

	/**
	 * 更新任务定时器为初始状态
	 * 
	 * @throws BizException
	 */
	void updateAllTimerStatusReady() throws BizException;
	
	/**
	 * 根据ID删除任务定时器
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteJobCronTimerById(String id) throws BizException;
	
	/**
	 * 批量删除任务定时器
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteJobCronTimerByIds(List<String> ids) throws BizException;
	
}