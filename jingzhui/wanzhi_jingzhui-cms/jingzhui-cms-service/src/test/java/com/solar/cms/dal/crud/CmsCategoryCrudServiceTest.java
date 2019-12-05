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
import com.solar.cms.entity.CmsCategory;
import com.solar.cms.dal.crud.CmsCategoryCrudService;

/**
 * CmsCategory CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsCategoryCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsCategoryCrudService cmsCategoryCrudService;
 
	@Test
	public void testQueryById() {
		CmsCategory cmsCategory = cmsCategoryCrudService.queryById(cmsCategoryCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsCategory> list = cmsCategoryCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategory> list = cmsCategoryCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsCategory result = cmsCategoryCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsCategory result = cmsCategoryCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsCategory> list = cmsCategoryCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategory> list = cmsCategoryCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsCategory> list = cmsCategoryCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsCategoryCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsCategoryCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsCategory cmsCategory = new CmsCategory();
		cmsCategory.setCreator("1009");
		cmsCategory.setLastModifier("1009");
		cmsCategory.setCreateDate(System.currentTimeMillis());
		CmsCategory result = cmsCategoryCrudService.save(cmsCategory);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsCategory cmsCategory = cmsCategoryCrudService.queryById(cmsCategoryCrudService.selectMaxId());
		//cmsCategory.setStatus((short) 2);
		
		CmsCategory result = cmsCategoryCrudService.update(cmsCategory);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsCategory cmsCategory = cmsCategoryCrudService.queryById(cmsCategoryCrudService.selectMaxId());
		//cmsCategory.setStatus((short) 2);
		
		CmsCategory result = cmsCategoryCrudService.updateNull(cmsCategory);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsCategoryCrudService.deleteById(cmsCategoryCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsCategoryCrudService.deleteByProperty("id", cmsCategoryCrudService.selectMaxId());
	}	
}
