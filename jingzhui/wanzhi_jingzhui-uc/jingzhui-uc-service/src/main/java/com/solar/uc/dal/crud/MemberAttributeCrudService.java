package com.solar.uc.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.uc.entity.MemberAttribute;
import com.solar.uc.dal.dao.MemberAttributeDao;
/**
 * 会员扩展信息 MemberAttribute CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
@Repository("memberAttributeCrudService")
public class MemberAttributeCrudService extends AbstractCrudService<MemberAttribute> {
	
	@Autowired
	private MemberAttributeDao memberAttributeDao;

	public ModelDao<MemberAttribute> getCrudDao() {
		return memberAttributeDao;
	}
	
}
