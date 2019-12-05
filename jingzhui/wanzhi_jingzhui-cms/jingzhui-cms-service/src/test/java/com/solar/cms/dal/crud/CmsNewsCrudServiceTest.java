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
import com.solar.cms.entity.CmsNews;
import com.solar.cms.dal.crud.CmsNewsCrudService;

/**
 * CmsNews CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsNewsCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsNewsCrudService cmsNewsCrudService;
 
	@Test
	public void testQueryById() {
		CmsNews cmsNews = cmsNewsCrudService.queryById(cmsNewsCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsNews> list = cmsNewsCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsNews> list = cmsNewsCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsNews result = cmsNewsCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsNews result = cmsNewsCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsNews> list = cmsNewsCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsNews> list = cmsNewsCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsNews> list = cmsNewsCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsNewsCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsNewsCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsNews cmsNews = new CmsNews();
		cmsNews.setCreator("1009");
		cmsNews.setLastModifier("1009");
		cmsNews.setCreateDate(System.currentTimeMillis());
		CmsNews result = cmsNewsCrudService.save(cmsNews);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsNews cmsNews = cmsNewsCrudService.queryById(cmsNewsCrudService.selectMaxId());
		//cmsNews.setStatus((short) 2);
		
		CmsNews result = cmsNewsCrudService.update(cmsNews);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsNews cmsNews = cmsNewsCrudService.queryById(cmsNewsCrudService.selectMaxId());
		//cmsNews.setStatus((short) 2);
		
		CmsNews result = cmsNewsCrudService.updateNull(cmsNews);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsNewsCrudService.deleteById(cmsNewsCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsNewsCrudService.deleteByProperty("id", cmsNewsCrudService.selectMaxId());
	}	
}
