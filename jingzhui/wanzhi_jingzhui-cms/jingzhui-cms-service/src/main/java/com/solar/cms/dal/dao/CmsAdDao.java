package com.solar.cms.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.cms.entity.CmsAd;

/**
 * 广告 CmsAd DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@MyBatisRepository
public interface CmsAdDao extends ModelDao<CmsAd> {
	
}