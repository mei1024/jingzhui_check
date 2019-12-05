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
import com.solar.cms.entity.CmsTag;
import com.solar.cms.dal.crud.CmsTagCrudService;

/**
 * CmsTag CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsTagCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsTagCrudService cmsTagCrudService;
 
	@Test
	public void testQueryById() {
		CmsTag cmsTag = cmsTagCrudService.queryById(cmsTagCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsTag> list = cmsTagCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsTag> list = cmsTagCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsTag result = cmsTagCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsTag result = cmsTagCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsTag> list = cmsTagCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsTag> list = cmsTagCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsTag> list = cmsTagCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsTagCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsTagCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsTag cmsTag = new CmsTag();
		cmsTag.setCreator("1009");
		cmsTag.setLastModifier("1009");
		cmsTag.setCreateDate(System.currentTimeMillis());
		CmsTag result = cmsTagCrudService.save(cmsTag);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsTag cmsTag = cmsTagCrudService.queryById(cmsTagCrudService.selectMaxId());
		//cmsTag.setStatus((short) 2);
		
		CmsTag result = cmsTagCrudService.update(cmsTag);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsTag cmsTag = cmsTagCrudService.queryById(cmsTagCrudService.selectMaxId());
		//cmsTag.setStatus((short) 2);
		
		CmsTag result = cmsTagCrudService.updateNull(cmsTag);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsTagCrudService.deleteById(cmsTagCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsTagCrudService.deleteByProperty("id", cmsTagCrudService.selectMaxId());
	}	
}
