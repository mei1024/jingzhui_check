package com.solar.job.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.job.entity.JobCronTimer;

/**
 * 任务定时器 JobCronTimer DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@MyBatisRepository
public interface JobCronTimerDao extends ModelDao<JobCronTimer> {
	
}