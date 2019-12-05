package com.solar.uc.dal.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.nebula.common.biz.enums.SortByEnum;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import com.solar.uc.base.BaseServiceTest;
import com.solar.uc.entity.MemberAttribute;
import com.solar.uc.dal.crud.MemberAttributeCrudService;

/**
 * MemberAttribute CRUD 数据服务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public class MemberAttributeCrudServiceTest extends BaseServiceTest {
	
	@Autowired
	private MemberAttributeCrudService memberAttributeCrudService;
 
	@Test
	public void testQueryById() {
		MemberAttribute memberAttribute = memberAttributeCrudService.queryById(memberAttributeCrudService.selectMaxId());
	}
	
	@Test
	public void testQueryAllList() {
		List<MemberAttribute> list = memberAttributeCrudService.queryAllList();
	}

	@Test
	public void testQueryList() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<MemberAttribute> list = memberAttributeCrudService.queryList(condition, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryOne() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		MemberAttribute result = memberAttributeCrudService.queryOne(condition, "id", SortByEnum.DESC);
	}
	
	@Test
	public void testQueryOneByProperty() {
		MemberAttribute result = memberAttributeCrudService.queryOneByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryListByProperty() {
		List<MemberAttribute> list = memberAttributeCrudService.queryListByProperty("status", 1, "id", SortByEnum.DESC);
	}

	@Test
	public void testQueryPage() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		List<MemberAttribute> list = memberAttributeCrudService.queryPage(condition, 0, 10, "id", SortByEnum.DESC);
	}

	@Test
	public void testLike() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", "Y");
		
		List<MemberAttribute> list = memberAttributeCrudService.like(condition);
	}

	@Test
	public void testCount() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long count = memberAttributeCrudService.count(condition);
	}
 
	@Test
	public void testSelectMaxId() {
		Map<String,Object> condition = new HashMap<String,Object>();
		
		long maxid = memberAttributeCrudService.selectMaxId(condition);
	}
 

	@Test
	public void testSave() {
		MemberAttribute memberAttribute = new MemberAttribute();
		memberAttribute.setCreator("1009");
		memberAttribute.setLastModifier("1009");
		memberAttribute.setCreateDate(System.currentTimeMillis());
		MemberAttribute result = memberAttributeCrudService.save(memberAttribute);
		
		Assert.notNull(result.getId() != null , "保存数据ID错误");
	}

	@Test
	public void testUpdate() {
		
		MemberAttribute memberAttribute = memberAttributeCrudService.queryById(memberAttributeCrudService.selectMaxId());
		//memberAttribute.setStatus((short) 2);
		
		MemberAttribute result = memberAttributeCrudService.update(memberAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testUpdateNull() {
		MemberAttribute memberAttribute = memberAttributeCrudService.queryById(memberAttributeCrudService.selectMaxId());
		//memberAttribute.setStatus((short) 2);
		
		MemberAttribute result = memberAttributeCrudService.updateNull(memberAttribute);
		Assert.notNull(result , "更新数据不存在");
	}

	@Test
	public void testDeleteById() {
		memberAttributeCrudService.deleteById(memberAttributeCrudService.selectMaxId());
	}

	@Test
	public void testDeleteByProperty() {
		memberAttributeCrudService.deleteByProperty("id", memberAttributeCrudService.selectMaxId());
	}	
}
