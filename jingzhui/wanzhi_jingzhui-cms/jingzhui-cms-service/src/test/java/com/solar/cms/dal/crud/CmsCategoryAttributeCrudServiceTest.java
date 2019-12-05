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
import com.solar.cms.entity.CmsCategoryAttribute;
import com.solar.cms.dal.crud.CmsCategoryAttributeCrudService;

/**
 * CmsCategoryAttribute CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsCategoryAttributeCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsCategoryAttributeCrudService cmsCategoryAttributeCrudService;
 
	@Test
	public void testQueryById() {
		CmsCategoryAttribute cmsCategoryAttribute = cmsCategoryAttributeCrudService.queryById(cmsCategoryAttributeCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsCategoryAttribute result = cmsCategoryAttributeCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsCategoryAttribute result = cmsCategoryAttributeCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsCategoryAttribute> list = cmsCategoryAttributeCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsCategoryAttributeCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsCategoryAttributeCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsCategoryAttribute cmsCategoryAttribute = new CmsCategoryAttribute();
		cmsCategoryAttribute.setCreator("1009");
		cmsCategoryAttribute.setLastModifier("1009");
		cmsCategoryAttribute.setCreateDate(System.currentTimeMillis());
		CmsCategoryAttribute result = cmsCategoryAttributeCrudService.save(cmsCategoryAttribute);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsCategoryAttribute cmsCategoryAttribute = cmsCategoryAttributeCrudService.queryById(cmsCategoryAttributeCrudService.selectMaxId());
		//cmsCategoryAttribute.setStatus((short) 2);
		
		CmsCategoryAttribute result = cmsCategoryAttributeCrudService.update(cmsCategoryAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsCategoryAttribute cmsCategoryAttribute = cmsCategoryAttributeCrudService.queryById(cmsCategoryAttributeCrudService.selectMaxId());
		//cmsCategoryAttribute.setStatus((short) 2);
		
		CmsCategoryAttribute result = cmsCategoryAttributeCrudService.updateNull(cmsCategoryAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsCategoryAttributeCrudService.deleteById(cmsCategoryAttributeCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsCategoryAttributeCrudService.deleteByProperty("id", cmsCategoryAttributeCrudService.selectMaxId());
	}	
}
