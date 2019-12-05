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
import com.solar.cms.entity.CmsContentFile;
import com.solar.cms.dal.crud.CmsContentFileCrudService;

/**
 * CmsContentFile CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsContentFileCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsContentFileCrudService cmsContentFileCrudService;
 
	@Test
	public void testQueryById() {
		CmsContentFile cmsContentFile = cmsContentFileCrudService.queryById(cmsContentFileCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsContentFile> list = cmsContentFileCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContentFile> list = cmsContentFileCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsContentFile result = cmsContentFileCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsContentFile result = cmsContentFileCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsContentFile> list = cmsContentFileCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsContentFile> list = cmsContentFileCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsContentFile> list = cmsContentFileCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsContentFileCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsContentFileCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsContentFile cmsContentFile = new CmsContentFile();
		cmsContentFile.setCreator("1009");
		cmsContentFile.setLastModifier("1009");
		cmsContentFile.setCreateDate(System.currentTimeMillis());
		CmsContentFile result = cmsContentFileCrudService.save(cmsContentFile);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsContentFile cmsContentFile = cmsContentFileCrudService.queryById(cmsContentFileCrudService.selectMaxId());
		//cmsContentFile.setStatus((short) 2);
		
		CmsContentFile result = cmsContentFileCrudService.update(cmsContentFile);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsContentFile cmsContentFile = cmsContentFileCrudService.queryById(cmsContentFileCrudService.selectMaxId());
		//cmsContentFile.setStatus((short) 2);
		
		CmsContentFile result = cmsContentFileCrudService.updateNull(cmsContentFile);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsContentFileCrudService.deleteById(cmsContentFileCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsContentFileCrudService.deleteByProperty("id", cmsContentFileCrudService.selectMaxId());
	}	
}
