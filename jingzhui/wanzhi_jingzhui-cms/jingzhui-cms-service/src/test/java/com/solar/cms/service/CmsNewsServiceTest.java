package com.solar.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.solar.cms.base.BaseServiceTest;
import com.solar.cms.dto.CmsNewsDto;

/**
 * CmsNews 业务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsNewsServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsNewsService cmsNewsService;
	 
	@Test
	public void testSaveCmsNews() {
		List<String> attachmentIds = new ArrayList<>();
		attachmentIds.add("1");
		attachmentIds.add("2");
		
		CmsNewsDto news = new CmsNewsDto();
		news.setCategoryId("10");
		news.setStatus("0");
		news.setUserId("101125");
		news.setTitle("测试咨讯");
		news.setProvinceId("1");
		news.setCityId("1");
		news.setDistrictId("1");
		news.setVillagesId("1");
		news.setText("测试内容");
		news.setWordCount(100);
		news.setTagIds("1001,1002,1003,1004,1005,1006,1007,1008,1009");
		news.setAttachmentIds(attachmentIds);
		CmsNewsDto result = cmsNewsService.saveCmsNews(news);

		System.out.println(JSON.toJSONString(result));
	}
	
	@Test
	public void testUpdateCmsNews() {
		List<String> attachmentIds = new ArrayList<>();
		attachmentIds.add("1");
		attachmentIds.add("2");
		
		CmsNewsDto news = new CmsNewsDto();
		news.setId("356a192b7913b04c54574d18c28d46e6395428ab");
		news.setCategoryId("10");
		news.setStatus("0");
		news.setUserId("101125");
		news.setTitle("测试咨讯");
		news.setProvinceId("1");
		news.setCityId("1");
		news.setDistrictId("1");
		news.setVillagesId("1");
		news.setText("测试内容");
		news.setWordCount(100);
		news.setTagIds("1001,1002,1003,1004,1005,1006,1007,1008,1009");
		news.setAttachmentIds(attachmentIds);
		cmsNewsService.updateCmsNewsById(news);
	}	
	
	@Test
	public void testQueryCmsNewsById() {
		String id = "356a192b7913b04c54574d18c28d46e6395428ab";
		
		CmsNewsDto result = cmsNewsService.queryCmsNewsById(id);
		System.out.println(JSON.toJSONString(result));
	}	

	
}
