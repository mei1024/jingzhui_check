package com.solar.cms.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.cms.entity.CmsCategory;

/**
 * 分类 CmsCategory DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@MyBatisRepository
public interface CmsCategoryDao extends ModelDao<CmsCategory> {
	
}