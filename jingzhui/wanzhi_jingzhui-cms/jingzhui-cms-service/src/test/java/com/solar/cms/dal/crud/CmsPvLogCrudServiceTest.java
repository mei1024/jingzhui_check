package com.solar.cms.dal.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.nebula.common.biz.enums.SortByEnum;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import com.solar.cms.base.BaseServiceTest;
import com.solar.cms.entity.CmsPvLog;
import com.solar.cms.dal.crud.CmsPvLogCrudService;

/**
 * CmsPvLog CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsPvLogCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsPvLogCrudService cmsPvLogCrudService;
 
	@Test
	public void testQueryById() {
		CmsPvLog cmsPvLog = cmsPvLogCrudService.queryById(cmsPvLogCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsPvLog> list = cmsPvLogCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsPvLog> list = cmsPvLogCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsPvLog result = cmsPvLogCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsPvLog result = cmsPvLogCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsPvLog> list = cmsPvLogCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsPvLog> list = cmsPvLogCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsPvLog> list = cmsPvLogCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsPvLogCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsPvLogCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsPvLog cmsPvLog = new CmsPvLog();
		cmsPvLog.setCreator("1009");
		cmsPvLog.setLastModifier("1009");
		cmsPvLog.setCreateDate(System.currentTimeMillis());
		CmsPvLog result = cmsPvLogCrudService.save(cmsPvLog);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsPvLog cmsPvLog = cmsPvLogCrudService.queryById(cmsPvLogCrudService.selectMaxId());
		//cmsPvLog.setStatus((short) 2);
		
		CmsPvLog result = cmsPvLogCrudService.update(cmsPvLog);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsPvLog cmsPvLog = cmsPvLogCrudService.queryById(cmsPvLogCrudService.selectMaxId());
		//cmsPvLog.setStatus((short) 2);
		
		CmsPvLog result = cmsPvLogCrudService.updateNull(cmsPvLog);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsPvLogCrudService.deleteById(cmsPvLogCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsPvLogCrudService.deleteByProperty("id", cmsPvLogCrudService.selectMaxId());
	}	
}
