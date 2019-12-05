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
import com.solar.cms.entity.CmsExpertVideo;
import com.solar.cms.dal.crud.CmsExpertVideoCrudService;

/**
 * CmsExpertVideo CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsExpertVideoCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsExpertVideoCrudService cmsExpertVideoCrudService;
 
	@Test
	public void testQueryById() {
		CmsExpertVideo cmsExpertVideo = cmsExpertVideoCrudService.queryById(cmsExpertVideoCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsExpertVideo result = cmsExpertVideoCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsExpertVideo result = cmsExpertVideoCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsExpertVideoCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsExpertVideoCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsExpertVideo cmsExpertVideo = new CmsExpertVideo();
		cmsExpertVideo.setCreator("1009");
		cmsExpertVideo.setLastModifier("1009");
		cmsExpertVideo.setCreateDate(System.currentTimeMillis());
		CmsExpertVideo result = cmsExpertVideoCrudService.save(cmsExpertVideo);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsExpertVideo cmsExpertVideo = cmsExpertVideoCrudService.queryById(cmsExpertVideoCrudService.selectMaxId());
		//cmsExpertVideo.setStatus((short) 2);
		
		CmsExpertVideo result = cmsExpertVideoCrudService.update(cmsExpertVideo);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsExpertVideo cmsExpertVideo = cmsExpertVideoCrudService.queryById(cmsExpertVideoCrudService.selectMaxId());
		//cmsExpertVideo.setStatus((short) 2);
		
		CmsExpertVideo result = cmsExpertVideoCrudService.updateNull(cmsExpertVideo);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsExpertVideoCrudService.deleteById(cmsExpertVideoCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsExpertVideoCrudService.deleteByProperty("id", cmsExpertVideoCrudService.selectMaxId());
	}	
}
