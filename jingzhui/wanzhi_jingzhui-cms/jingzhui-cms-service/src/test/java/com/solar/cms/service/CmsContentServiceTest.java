package com.solar.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.solar.cms.base.BaseServiceTest;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.dto.CmsNewsDto;

/**
 * CmsContent 业务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public class CmsContentServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsContentService cmsContentService;
 
	 
	@Test
	public void testSaveCmsContent() {
		
		CmsContentDto news = new CmsContentDto();
		news.setCategoryId("303");
		news.setStatus("0");
		news.setUserId("101125");
		news.setTitle("联系我们");
		news.setCopied("N");
		news.setOnlyUrl("N");
		news.setHasImages("N");
		news.setHasFiles("N");
		news.setHasStatic("N");
		news.setUrl("");
		news.setText("关于我们");
		news.setWordCount(100);
		news.setSortno(0);
		cmsContentService.saveCmsContent(news);
	}

}
