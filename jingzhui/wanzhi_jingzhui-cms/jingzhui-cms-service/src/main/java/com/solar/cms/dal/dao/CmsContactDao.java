package com.solar.cms.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.cms.entity.CmsContact;

/**
 * 联系我们 CmsContact DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181113
 * 
 */
@MyBatisRepository
public interface CmsContactDao extends ModelDao<CmsContact> {
	
}