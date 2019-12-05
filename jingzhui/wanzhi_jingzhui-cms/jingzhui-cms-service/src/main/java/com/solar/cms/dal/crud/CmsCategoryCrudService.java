package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsCategory;
import com.solar.cms.dal.dao.CmsCategoryDao;
/**
 * 分类 CmsCategory CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsCategoryCrudService")
public class CmsCategoryCrudService extends AbstractCrudService<CmsCategory> {
	
	@Autowired
	private CmsCategoryDao cmsCategoryDao;

	public ModelDao<CmsCategory> getCrudDao() {
		return cmsCategoryDao;
	}
	
}
