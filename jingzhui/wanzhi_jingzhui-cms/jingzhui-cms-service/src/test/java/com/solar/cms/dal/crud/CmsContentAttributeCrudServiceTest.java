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
import com.solar.cms.entity.CmsContentAttribute;
import com.solar.cms.dal.crud.CmsContentAttributeCrudService;

/**
 * CmsContentAttribute CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsContentAttributeCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsContentAttributeCrudService cmsContentAttributeCrudService;
 
	@Test
	public void testQueryById() {
		CmsContentAttribute cmsContentAttribute = cmsContentAttributeCrudService.queryById(cmsContentAttributeCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsContentAttribute result = cmsContentAttributeCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsContentAttribute result = cmsContentAttributeCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsContentAttribute> list = cmsContentAttributeCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsContentAttributeCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsContentAttributeCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsContentAttribute cmsContentAttribute = new CmsContentAttribute();
		cmsContentAttribute.setCreator("1009");
		cmsContentAttribute.setLastModifier("1009");
		cmsContentAttribute.setCreateDate(System.currentTimeMillis());
		CmsContentAttribute result = cmsContentAttributeCrudService.save(cmsContentAttribute);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsContentAttribute cmsContentAttribute = cmsContentAttributeCrudService.queryById(cmsContentAttributeCrudService.selectMaxId());
		//cmsContentAttribute.setStatus((short) 2);
		
		CmsContentAttribute result = cmsContentAttributeCrudService.update(cmsContentAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsContentAttribute cmsContentAttribute = cmsContentAttributeCrudService.queryById(cmsContentAttributeCrudService.selectMaxId());
		//cmsContentAttribute.setStatus((short) 2);
		
		CmsContentAttribute result = cmsContentAttributeCrudService.updateNull(cmsContentAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsContentAttributeCrudService.deleteById(cmsContentAttributeCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsContentAttributeCrudService.deleteByProperty("id", cmsContentAttributeCrudService.selectMaxId());
	}	
}
