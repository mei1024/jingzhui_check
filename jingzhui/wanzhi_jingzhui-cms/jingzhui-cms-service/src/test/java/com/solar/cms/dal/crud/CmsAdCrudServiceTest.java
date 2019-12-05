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
import com.solar.cms.entity.CmsAd;
import com.solar.cms.dal.crud.CmsAdCrudService;

/**
 * CmsAd CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
public class CmsAdCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsAdCrudService cmsAdCrudService;
 
	@Test
	public void testQueryById() {
		CmsAd cmsAd = cmsAdCrudService.queryById(cmsAdCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsAd> list = cmsAdCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsAd> list = cmsAdCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsAd result = cmsAdCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsAd result = cmsAdCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsAd> list = cmsAdCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsAd> list = cmsAdCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsAd> list = cmsAdCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsAdCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsAdCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsAd cmsAd = new CmsAd();
		cmsAd.setCreator("1009");
		cmsAd.setLastModifier("1009");
		cmsAd.setCreateDate(System.currentTimeMillis());
		CmsAd result = cmsAdCrudService.save(cmsAd);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsAd cmsAd = cmsAdCrudService.queryById(cmsAdCrudService.selectMaxId());
		//cmsAd.setStatus((short) 2);
		
		CmsAd result = cmsAdCrudService.update(cmsAd);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsAd cmsAd = cmsAdCrudService.queryById(cmsAdCrudService.selectMaxId());
		//cmsAd.setStatus((short) 2);
		
		CmsAd result = cmsAdCrudService.updateNull(cmsAd);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsAdCrudService.deleteById(cmsAdCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsAdCrudService.deleteByProperty("id", cmsAdCrudService.selectMaxId());
	}	
}
