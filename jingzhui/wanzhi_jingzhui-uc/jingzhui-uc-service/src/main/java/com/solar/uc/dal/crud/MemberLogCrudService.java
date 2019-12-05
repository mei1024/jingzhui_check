package com.solar.uc.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.uc.entity.MemberLog;
import com.solar.uc.dal.dao.MemberLogDao;
/**
 * 会员日志 MemberLog CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20190904
 *
 */
@Repository("memberLogCrudService")
public class MemberLogCrudService extends AbstractCrudService<MemberLog> {
	
	@Autowired
	private MemberLogDao memberLogDao;

	public ModelDao<MemberLog> getCrudDao() {
		return memberLogDao;
	}
	
}
