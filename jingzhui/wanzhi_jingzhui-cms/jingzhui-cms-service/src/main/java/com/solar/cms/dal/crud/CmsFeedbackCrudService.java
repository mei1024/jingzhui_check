package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsFeedback;
import com.solar.cms.dal.dao.CmsFeedbackDao;
/**
 * 意见反馈 CmsFeedback CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181218
 *
 */
@Repository("cmsFeedbackCrudService")
public class CmsFeedbackCrudService extends AbstractCrudService<CmsFeedback> {
	
	@Autowired
	private CmsFeedbackDao cmsFeedbackDao;

	public ModelDao<CmsFeedback> getCrudDao() {
		return cmsFeedbackDao;
	}
	
}
