package com.solar.job.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.job.entity.JobOnceTimer;
import com.solar.job.dal.dao.JobOnceTimerDao;
/**
 * 一次性定时器 JobOnceTimer CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
@Repository("jobOnceTimerCrudService")
public class JobOnceTimerCrudService extends AbstractCrudService<JobOnceTimer> {
	
	@Autowired
	private JobOnceTimerDao jobOnceTimerDao;

	public ModelDao<JobOnceTimer> getCrudDao() {
		return jobOnceTimerDao;
	}
	
}
