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
import com.solar.cms.entity.QaCategroy;
import com.solar.cms.dal.crud.QaCategroyCrudService;

/**
 * QaCategroy CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public class QaCategroyCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private QaCategroyCrudService qaCategroyCrudService;
 
	@Test
	public void testQueryById() {
		QaCategroy qaCategroy = qaCategroyCrudService.queryById(qaCategroyCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<QaCategroy> list = qaCategroyCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<QaCategroy> list = qaCategroyCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		QaCategroy result = qaCategroyCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		QaCategroy result = qaCategroyCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<QaCategroy> list = qaCategroyCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<QaCategroy> list = qaCategroyCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<QaCategroy> list = qaCategroyCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = qaCategroyCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = qaCategroyCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		QaCategroy qaCategroy = new QaCategroy();
		qaCategroy.setCreator("1009");
		qaCategroy.setLastModifier("1009");
		qaCategroy.setCreateDate(System.currentTimeMillis());
		QaCategroy result = qaCategroyCrudService.save(qaCategroy);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		QaCategroy qaCategroy = qaCategroyCrudService.queryById(qaCategroyCrudService.selectMaxId());
		//qaCategroy.setStatus((short) 2);
		
		QaCategroy result = qaCategroyCrudService.update(qaCategroy);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		QaCategroy qaCategroy = qaCategroyCrudService.queryById(qaCategroyCrudService.selectMaxId());
		//qaCategroy.setStatus((short) 2);
		
		QaCategroy result = qaCategroyCrudService.updateNull(qaCategroy);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		qaCategroyCrudService.deleteById(qaCategroyCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		qaCategroyCrudService.deleteByProperty("id", qaCategroyCrudService.selectMaxId());
	}	
}
