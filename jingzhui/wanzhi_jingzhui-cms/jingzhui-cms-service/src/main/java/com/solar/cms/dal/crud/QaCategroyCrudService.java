package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.QaCategroy;
import com.solar.cms.dal.dao.QaCategroyDao;
/**
 * 问题分类 QaCategroy CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
@Repository("qaCategroyCrudService")
public class QaCategroyCrudService extends AbstractCrudService<QaCategroy> {
	
	@Autowired
	private QaCategroyDao qaCategroyDao;

	public ModelDao<QaCategroy> getCrudDao() {
		return qaCategroyDao;
	}
	
}
