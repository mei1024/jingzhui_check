package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.QaItem;
import com.solar.cms.dal.dao.QaItemDao;
/**
 * 问题答案 QaItem CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
@Repository("qaItemCrudService")
public class QaItemCrudService extends AbstractCrudService<QaItem> {
	
	@Autowired
	private QaItemDao qaItemDao;

	public ModelDao<QaItem> getCrudDao() {
		return qaItemDao;
	}
	
}
