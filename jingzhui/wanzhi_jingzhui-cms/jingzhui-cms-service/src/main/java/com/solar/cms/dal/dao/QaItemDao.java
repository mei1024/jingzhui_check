package com.solar.cms.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.cms.entity.QaItem;

/**
 * 问题答案 QaItem DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@MyBatisRepository
public interface QaItemDao extends ModelDao<QaItem> {
	
}