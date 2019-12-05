package com.solar.bi.dal.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.nebula.common.biz.enums.SortByEnum;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import com.solar.bi.base.BaseServiceTest;
import com.solar.bi.entity.CheckRecord;
import com.solar.bi.dal.crud.CheckRecordCrudService;

/**
 * CheckRecord CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public class CheckRecordCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CheckRecordCrudService checkRecordCrudService;
 
	@Test
	public void testQueryById() {
		CheckRecord checkRecord = checkRecordCrudService.queryById(checkRecordCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CheckRecord> list = checkRecordCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CheckRecord> list = checkRecordCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CheckRecord result = checkRecordCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CheckRecord result = checkRecordCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CheckRecord> list = checkRecordCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CheckRecord> list = checkRecordCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CheckRecord> list = checkRecordCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = checkRecordCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = checkRecordCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CheckRecord checkRecord = new CheckRecord();
		checkRecord.setCreator("1009");
		checkRecord.setLastModifier("1009");
		checkRecord.setCreateDate(System.currentTimeMillis());
		CheckRecord result = checkRecordCrudService.save(checkRecord);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CheckRecord checkRecord = checkRecordCrudService.queryById(checkRecordCrudService.selectMaxId());
		//checkRecord.setStatus((short) 2);
		
		CheckRecord result = checkRecordCrudService.update(checkRecord);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CheckRecord checkRecord = checkRecordCrudService.queryById(checkRecordCrudService.selectMaxId());
		//checkRecord.setStatus((short) 2);
		
		CheckRecord result = checkRecordCrudService.updateNull(checkRecord);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		checkRecordCrudService.deleteById(checkRecordCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		checkRecordCrudService.deleteByProperty("id", checkRecordCrudService.selectMaxId());
	}	
}
