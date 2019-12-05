package com.solar.cms.service;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.enums.EnabledEnum;
import com.solar.cms.base.BaseServiceTest;
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.entity.CmsAd;
import com.solar.cms.service.CmsAdService;

/**
 * CmsAd 业务接口 TEST
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
public class CmsAdServiceTest extends BaseServiceTest {
	
	@Autowired
	private CmsAdService cmsAdService;
 
	 
	@Test
	public void testSave() {
		CmsAdDto cmsAd = new CmsAdDto();
		cmsAd.setName("小程序轮播图");
		cmsAd.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573018998857&di=561e176dcf8aab4734942373e4ed5f22&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F53%2F62%2F82a58PIC6MA_1024.jpg%2521%2Ffw%2F1024%2Fwatermark%2Furl%2FL2ltYWdlcy93YXRlcm1hcmsveGlhb3R1LnBuZw%3D%3D%2Falign%2Fcenter");
		cmsAd.setType("WXMA_HOME_BANNER");
		cmsAd.setStatus(EnabledEnum.ENABLED.getCode());
		cmsAdService.saveCmsAd(cmsAd);
	}

}
