package com.solar.job.dal.dao.ext;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.job.entity.JobOnceTimer;

/**
 * 一次性定时器 JobOnceTimer DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181224
 * 
 */
@MyBatisRepository
public interface JobOnceTimerExtDao extends ModelDao<JobOnceTimer> {
	
}