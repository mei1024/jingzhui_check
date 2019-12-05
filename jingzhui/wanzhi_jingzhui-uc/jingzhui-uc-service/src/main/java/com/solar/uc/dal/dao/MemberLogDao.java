package com.solar.uc.dal.dao;


import com.nebula.common.biz.dal.dao.ModelDao;
import com.nebula.common.biz.dal.annotation.MyBatisRepository;

import com.solar.uc.entity.MemberLog;

/**
 * 会员日志 MemberLog DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@MyBatisRepository
public interface MemberLogDao extends ModelDao<MemberLog> {
	
}