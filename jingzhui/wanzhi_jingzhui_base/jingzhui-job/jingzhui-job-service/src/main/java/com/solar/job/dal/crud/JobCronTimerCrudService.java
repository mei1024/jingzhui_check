package com.solar.job.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.job.entity.JobCronTimer;
import com.solar.job.dal.dao.JobCronTimerDao;
/**
 * 任务定时器 JobCronTimer CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
@Repository("jobCronTimerCrudService")
public class JobCronTimerCrudService extends AbstractCrudService<JobCronTimer> {
	
	@Autowired
	private JobCronTimerDao jobCronTimerDao;

	public ModelDao<JobCronTimer> getCrudDao() {
		return jobCronTimerDao;
	}
	
}
