package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsCategoryAttribute;
import com.solar.cms.dal.dao.CmsCategoryAttributeDao;
/**
 * 分类扩展 CmsCategoryAttribute CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsCategoryAttributeCrudService")
public class CmsCategoryAttributeCrudService extends AbstractCrudService<CmsCategoryAttribute> {
	
	@Autowired
	private CmsCategoryAttributeDao cmsCategoryAttributeDao;

	public ModelDao<CmsCategoryAttribute> getCrudDao() {
		return cmsCategoryAttributeDao;
	}
	
}
