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
import com.solar.cms.entity.CmsExpert;
import com.solar.cms.dal.crud.CmsExpertCrudService;

/**
 * CmsExpert CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsExpertCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsExpertCrudService cmsExpertCrudService;
 
	@Test
	public void testQueryById() {
		CmsExpert cmsExpert = cmsExpertCrudService.queryById(cmsExpertCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsExpert> list = cmsExpertCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsExpert> list = cmsExpertCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsExpert result = cmsExpertCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsExpert result = cmsExpertCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsExpert> list = cmsExpertCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsExpert> list = cmsExpertCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsExpert> list = cmsExpertCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsExpertCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsExpertCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsExpert cmsExpert = new CmsExpert();
		cmsExpert.setCreator("1009");
		cmsExpert.setLastModifier("1009");
		cmsExpert.setCreateDate(System.currentTimeMillis());
		CmsExpert result = cmsExpertCrudService.save(cmsExpert);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsExpert cmsExpert = cmsExpertCrudService.queryById(cmsExpertCrudService.selectMaxId());
		//cmsExpert.setStatus((short) 2);
		
		CmsExpert result = cmsExpertCrudService.update(cmsExpert);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsExpert cmsExpert = cmsExpertCrudService.queryById(cmsExpertCrudService.selectMaxId());
		//cmsExpert.setStatus((short) 2);
		
		CmsExpert result = cmsExpertCrudService.updateNull(cmsExpert);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsExpertCrudService.deleteById(cmsExpertCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsExpertCrudService.deleteByProperty("id", cmsExpertCrudService.selectMaxId());
	}	
}
