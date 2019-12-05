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
import com.solar.cms.entity.CmsContent;
import com.solar.cms.dal.crud.CmsContentCrudService;

/**
 * CmsContent CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsContentCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsContentCrudService cmsContentCrudService;
 
	@Test
	public void testQueryById() {
		CmsContent cmsContent = cmsContentCrudService.queryById(cmsContentCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsContent> list = cmsContentCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContent> list = cmsContentCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsContent result = cmsContentCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsContent result = cmsContentCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsContent> list = cmsContentCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContent> list = cmsContentCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsContent> list = cmsContentCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsContentCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsContentCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsContent cmsContent = new CmsContent();
		cmsContent.setCreator("1009");
		cmsContent.setLastModifier("1009");
		cmsContent.setCreateDate(System.currentTimeMillis());
		CmsContent result = cmsContentCrudService.save(cmsContent);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsContent cmsContent = cmsContentCrudService.queryById(cmsContentCrudService.selectMaxId());
		//cmsContent.setStatus((short) 2);
		
		CmsContent result = cmsContentCrudService.update(cmsContent);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsContent cmsContent = cmsContentCrudService.queryById(cmsContentCrudService.selectMaxId());
		//cmsContent.setStatus((short) 2);
		
		CmsContent result = cmsContentCrudService.updateNull(cmsContent);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsContentCrudService.deleteById(cmsContentCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsContentCrudService.deleteByProperty("id", cmsContentCrudService.selectMaxId());
	}	
}
