package com.solar.job.dal.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.nebula.common.biz.enums.SortByEnum;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import com.solar.job.base.BaseServiceTest;
import com.solar.job.entity.JobCronTimer;
import com.solar.job.dal.crud.JobCronTimerCrudService;

/**
 * JobCronTimer CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
public class JobCronTimerCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private JobCronTimerCrudService jobCronTimerCrudService;
 
	@Test
	public void testQueryById() {
		JobCronTimer jobCronTimer = jobCronTimerCrudService.queryById(jobCronTimerCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<JobCronTimer> list = jobCronTimerCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<JobCronTimer> list = jobCronTimerCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		JobCronTimer result = jobCronTimerCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		JobCronTimer result = jobCronTimerCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<JobCronTimer> list = jobCronTimerCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<JobCronTimer> list = jobCronTimerCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<JobCronTimer> list = jobCronTimerCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = jobCronTimerCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = jobCronTimerCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		JobCronTimer jobCronTimer = new JobCronTimer();
		jobCronTimer.setCreator("1009");
		jobCronTimer.setLastModifier("1009");
		jobCronTimer.setCreateDate(System.currentTimeMillis());
		JobCronTimer result = jobCronTimerCrudService.save(jobCronTimer);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		JobCronTimer jobCronTimer = jobCronTimerCrudService.queryById(jobCronTimerCrudService.selectMaxId());
		//jobCronTimer.setStatus((short) 2);
		
		JobCronTimer result = jobCronTimerCrudService.update(jobCronTimer);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		JobCronTimer jobCronTimer = jobCronTimerCrudService.queryById(jobCronTimerCrudService.selectMaxId());
		//jobCronTimer.setStatus((short) 2);
		
		JobCronTimer result = jobCronTimerCrudService.updateNull(jobCronTimer);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		jobCronTimerCrudService.deleteById(jobCronTimerCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		jobCronTimerCrudService.deleteByProperty("id", jobCronTimerCrudService.selectMaxId());
	}	
}
