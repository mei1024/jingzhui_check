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
import com.solar.cms.entity.CmsFeedback;
import com.solar.cms.dal.crud.CmsFeedbackCrudService;

/**
 * CmsFeedback CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181218
 *
 */
public class CmsFeedbackCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsFeedbackCrudService cmsFeedbackCrudService;
 
	@Test
	public void testQueryById() {
		CmsFeedback cmsFeedback = cmsFeedbackCrudService.queryById(cmsFeedbackCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsFeedback> list = cmsFeedbackCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsFeedback> list = cmsFeedbackCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsFeedback result = cmsFeedbackCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsFeedback result = cmsFeedbackCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsFeedback> list = cmsFeedbackCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsFeedback> list = cmsFeedbackCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsFeedback> list = cmsFeedbackCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsFeedbackCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsFeedbackCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsFeedback cmsFeedback = new CmsFeedback();
		cmsFeedback.setCreator("1009");
		cmsFeedback.setLastModifier("1009");
		cmsFeedback.setCreateDate(System.currentTimeMillis());
		CmsFeedback result = cmsFeedbackCrudService.save(cmsFeedback);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsFeedback cmsFeedback = cmsFeedbackCrudService.queryById(cmsFeedbackCrudService.selectMaxId());
		//cmsFeedback.setStatus((short) 2);
		
		CmsFeedback result = cmsFeedbackCrudService.update(cmsFeedback);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsFeedback cmsFeedback = cmsFeedbackCrudService.queryById(cmsFeedbackCrudService.selectMaxId());
		//cmsFeedback.setStatus((short) 2);
		
		CmsFeedback result = cmsFeedbackCrudService.updateNull(cmsFeedback);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsFeedbackCrudService.deleteById(cmsFeedbackCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsFeedbackCrudService.deleteByProperty("id", cmsFeedbackCrudService.selectMaxId());
	}	
}
