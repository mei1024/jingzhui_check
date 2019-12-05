package com.solar.cms.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.cms.entity.CmsPvLog;

/**
 * PV记录 CmsPvLog DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@MyBatisRepository
public interface CmsPvLogDao extends ModelDao<CmsPvLog> {
	
}