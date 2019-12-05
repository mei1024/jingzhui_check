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
import com.solar.cms.entity.CmsCategoryModel;
import com.solar.cms.dal.crud.CmsCategoryModelCrudService;

/**
 * CmsCategoryModel CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsCategoryModelCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsCategoryModelCrudService cmsCategoryModelCrudService;
 
	@Test
	public void testQueryById() {
		CmsCategoryModel cmsCategoryModel = cmsCategoryModelCrudService.queryById(cmsCategoryModelCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		CmsCategoryModel result = cmsCategoryModelCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		CmsCategoryModel result = cmsCategoryModelCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<CmsCategoryModel> list = cmsCategoryModelCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = cmsCategoryModelCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = cmsCategoryModelCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		CmsCategoryModel cmsCategoryModel = new CmsCategoryModel();
		cmsCategoryModel.setCreator("1009");
		cmsCategoryModel.setLastModifier("1009");
		cmsCategoryModel.setCreateDate(System.currentTimeMillis());
		CmsCategoryModel result = cmsCategoryModelCrudService.save(cmsCategoryModel);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		CmsCategoryModel cmsCategoryModel = cmsCategoryModelCrudService.queryById(cmsCategoryModelCrudService.selectMaxId());
		//cmsCategoryModel.setStatus((short) 2);
		
		CmsCategoryModel result = cmsCategoryModelCrudService.update(cmsCategoryModel);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		CmsCategoryModel cmsCategoryModel = cmsCategoryModelCrudService.queryById(cmsCategoryModelCrudService.selectMaxId());
		//cmsCategoryModel.setStatus((short) 2);
		
		CmsCategoryModel result = cmsCategoryModelCrudService.updateNull(cmsCategoryModel);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		cmsCategoryModelCrudService.deleteById(cmsCategoryModelCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		cmsCategoryModelCrudService.deleteByProperty("id", cmsCategoryModelCrudService.selectMaxId());
	}	
}
