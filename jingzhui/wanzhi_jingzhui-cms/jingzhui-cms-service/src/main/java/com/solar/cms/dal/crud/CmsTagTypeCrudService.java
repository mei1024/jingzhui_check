package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsTagType;
import com.solar.cms.dal.dao.CmsTagTypeDao;
/**
 * 标签类型 CmsTagType CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsTagTypeCrudService")
public class CmsTagTypeCrudService extends AbstractCrudService<CmsTagType> {
	
	@Autowired
	private CmsTagTypeDao cmsTagTypeDao;

	public ModelDao<CmsTagType> getCrudDao() {
		return cmsTagTypeDao;
	}
	
}
