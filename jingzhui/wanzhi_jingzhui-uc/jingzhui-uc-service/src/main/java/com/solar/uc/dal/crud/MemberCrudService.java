package com.solar.uc.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.uc.entity.Member;
import com.solar.uc.dal.dao.MemberDao;
/**
 * 会员 Member CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20190904
 *
 */
@Repository("memberCrudService")
public class MemberCrudService extends AbstractCrudService<Member> {
	
	@Autowired
	private MemberDao memberDao;

	public ModelDao<Member> getCrudDao() {
		return memberDao;
	}
	
}
