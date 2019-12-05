package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsContact;
import com.solar.cms.dal.dao.CmsContactDao;
/**
 * 联系我们 CmsContact CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181113
 *
 */
@Repository("cmsContactCrudService")
public class CmsContactCrudService extends AbstractCrudService<CmsContact> {
	
	@Autowired
	private CmsContactDao cmsContactDao;

	public ModelDao<CmsContact> getCrudDao() {
		return cmsContactDao;
	}
	
}
