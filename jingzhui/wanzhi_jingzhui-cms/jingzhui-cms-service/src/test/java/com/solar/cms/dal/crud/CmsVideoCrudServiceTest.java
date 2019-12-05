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
import com.solar.cms.entity.CmsVideo;
import com.solar.cms.dal.crud.CmsVideoCrudService;

/**
 * CmsVideo CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
public class CmsVideoCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsVideoCrudService cmsVideoCrudService;
 
	@Test
	public void testQueryById() {
		CmsVideo cmsVideo = cmsVideoCrudService.queryById(cmsVideoCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsVideo> list = cmsVideoCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsVideo> list = cmsVideoCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsVideo result = cmsVideoCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsVideo result = cmsVideoCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsVideo> list = cmsVideoCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsVideo> list = cmsVideoCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsVideo> list = cmsVideoCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsVideoCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsVideoCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsVideo cmsVideo = new CmsVideo();
		cmsVideo.setCreator("1009");
		cmsVideo.setLastModifier("1009");
		cmsVideo.setCreateDate(System.currentTimeMillis());
		CmsVideo result = cmsVideoCrudService.save(cmsVideo);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsVideo cmsVideo = cmsVideoCrudService.queryById(cmsVideoCrudService.selectMaxId());
		//cmsVideo.setStatus((short) 2);
		
		CmsVideo result = cmsVideoCrudService.update(cmsVideo);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsVideo cmsVideo = cmsVideoCrudService.queryById(cmsVideoCrudService.selectMaxId());
		//cmsVideo.setStatus((short) 2);
		
		CmsVideo result = cmsVideoCrudService.updateNull(cmsVideo);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsVideoCrudService.deleteById(cmsVideoCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsVideoCrudService.deleteByProperty("id", cmsVideoCrudService.selectMaxId());
	}	
}
