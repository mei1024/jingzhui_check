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
import com.solar.cms.entity.CmsTagType;
import com.solar.cms.dal.crud.CmsTagTypeCrudService;

/**
 * CmsTagType CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsTagTypeCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsTagTypeCrudService cmsTagTypeCrudService;
 
	@Test
	public void testQueryById() {
		CmsTagType cmsTagType = cmsTagTypeCrudService.queryById(cmsTagTypeCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsTagType> list = cmsTagTypeCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsTagType> list = cmsTagTypeCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsTagType result = cmsTagTypeCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsTagType result = cmsTagTypeCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsTagType> list = cmsTagTypeCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsTagType> list = cmsTagTypeCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsTagType> list = cmsTagTypeCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsTagTypeCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsTagTypeCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsTagType cmsTagType = new CmsTagType();
		cmsTagType.setCreator("1009");
		cmsTagType.setLastModifier("1009");
		cmsTagType.setCreateDate(System.currentTimeMillis());
		CmsTagType result = cmsTagTypeCrudService.save(cmsTagType);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsTagType cmsTagType = cmsTagTypeCrudService.queryById(cmsTagTypeCrudService.selectMaxId());
		//cmsTagType.setStatus((short) 2);
		
		CmsTagType result = cmsTagTypeCrudService.update(cmsTagType);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsTagType cmsTagType = cmsTagTypeCrudService.queryById(cmsTagTypeCrudService.selectMaxId());
		//cmsTagType.setStatus((short) 2);
		
		CmsTagType result = cmsTagTypeCrudService.updateNull(cmsTagType);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsTagTypeCrudService.deleteById(cmsTagTypeCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsTagTypeCrudService.deleteByProperty("id", cmsTagTypeCrudService.selectMaxId());
	}	
}
