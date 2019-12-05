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
import com.solar.job.entity.JobOnceTimer;
import com.solar.job.dal.crud.JobOnceTimerCrudService;

/**
 * JobOnceTimer CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181224
 *
 */
public class JobOnceTimerCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private JobOnceTimerCrudService jobOnceTimerCrudService;
 
	@Test
	public void testQueryById() {
		JobOnceTimer jobOnceTimer = jobOnceTimerCrudService.queryById(jobOnceTimerCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		JobOnceTimer result = jobOnceTimerCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		JobOnceTimer result = jobOnceTimerCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<JobOnceTimer> list = jobOnceTimerCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<JobOnceTimer> list = jobOnceTimerCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = jobOnceTimerCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = jobOnceTimerCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		JobOnceTimer jobOnceTimer = new JobOnceTimer();
		jobOnceTimer.setCreator("1009");
		jobOnceTimer.setLastModifier("1009");
		jobOnceTimer.setCreateDate(System.currentTimeMillis());
		JobOnceTimer result = jobOnceTimerCrudService.save(jobOnceTimer);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		JobOnceTimer jobOnceTimer = jobOnceTimerCrudService.queryById(jobOnceTimerCrudService.selectMaxId());
		//jobOnceTimer.setStatus((short) 2);
		
		JobOnceTimer result = jobOnceTimerCrudService.update(jobOnceTimer);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		JobOnceTimer jobOnceTimer = jobOnceTimerCrudService.queryById(jobOnceTimerCrudService.selectMaxId());
		//jobOnceTimer.setStatus((short) 2);
		
		JobOnceTimer result = jobOnceTimerCrudService.updateNull(jobOnceTimer);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		jobOnceTimerCrudService.deleteById(jobOnceTimerCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		jobOnceTimerCrudService.deleteByProperty("id", jobOnceTimerCrudService.selectMaxId());
	}	
}
