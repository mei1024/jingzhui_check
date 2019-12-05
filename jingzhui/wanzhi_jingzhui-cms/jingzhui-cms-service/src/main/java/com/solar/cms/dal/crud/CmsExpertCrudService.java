package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsExpert;
import com.solar.cms.dal.dao.CmsExpertDao;
/**
 * 专家 CmsExpert CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsExpertCrudService")
public class CmsExpertCrudService extends AbstractCrudService<CmsExpert> {
	
	@Autowired
	private CmsExpertDao cmsExpertDao;

	public ModelDao<CmsExpert> getCrudDao() {
		return cmsExpertDao;
	}
	
}
