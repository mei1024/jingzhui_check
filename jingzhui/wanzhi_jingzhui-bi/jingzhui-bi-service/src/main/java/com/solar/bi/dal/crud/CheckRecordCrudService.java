package com.solar.bi.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.bi.entity.CheckRecord;
import com.solar.bi.dal.dao.CheckRecordDao;
/**
 * 检查记录 CheckRecord CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
@Repository("checkRecordCrudService")
public class CheckRecordCrudService extends AbstractCrudService<CheckRecord> {
	
	@Autowired
	private CheckRecordDao checkRecordDao;

	public ModelDao<CheckRecord> getCrudDao() {
		return checkRecordDao;
	}
	
}
