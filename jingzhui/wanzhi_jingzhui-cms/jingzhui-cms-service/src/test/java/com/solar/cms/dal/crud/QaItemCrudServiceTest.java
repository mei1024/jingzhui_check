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
import com.solar.cms.entity.QaItem;
import com.solar.cms.dal.crud.QaItemCrudService;

/**
 * QaItem CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public class QaItemCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private QaItemCrudService qaItemCrudService;
 
	@Test
	public void testQueryById() {
		QaItem qaItem = qaItemCrudService.queryById(qaItemCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<QaItem> list = qaItemCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<QaItem> list = qaItemCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		QaItem result = qaItemCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		QaItem result = qaItemCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<QaItem> list = qaItemCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<QaItem> list = qaItemCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<QaItem> list = qaItemCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = qaItemCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = qaItemCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		QaItem qaItem = new QaItem();
		qaItem.setCreator("1009");
		qaItem.setLastModifier("1009");
		qaItem.setCreateDate(System.currentTimeMillis());
		QaItem result = qaItemCrudService.save(qaItem);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		QaItem qaItem = qaItemCrudService.queryById(qaItemCrudService.selectMaxId());
		//qaItem.setStatus((short) 2);
		
		QaItem result = qaItemCrudService.update(qaItem);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		QaItem qaItem = qaItemCrudService.queryById(qaItemCrudService.selectMaxId());
		//qaItem.setStatus((short) 2);
		
		QaItem result = qaItemCrudService.updateNull(qaItem);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		qaItemCrudService.deleteById(qaItemCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		qaItemCrudService.deleteByProperty("id", qaItemCrudService.selectMaxId());
	}	
}
