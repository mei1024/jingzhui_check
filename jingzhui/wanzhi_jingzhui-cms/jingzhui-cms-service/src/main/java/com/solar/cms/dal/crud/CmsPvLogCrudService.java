package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsPvLog;
import com.solar.cms.dal.dao.CmsPvLogDao;
/**
 * PV记录 CmsPvLog CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsPvLogCrudService")
public class CmsPvLogCrudService extends AbstractCrudService<CmsPvLog> {
	
	@Autowired
	private CmsPvLogDao cmsPvLogDao;

	public ModelDao<CmsPvLog> getCrudDao() {
		return cmsPvLogDao;
	}
	
}
