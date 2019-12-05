package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsContentAttribute;
import com.solar.cms.dal.dao.CmsContentAttributeDao;
/**
 * 内容扩展 CmsContentAttribute CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsContentAttributeCrudService")
public class CmsContentAttributeCrudService extends AbstractCrudService<CmsContentAttribute> {
	
	@Autowired
	private CmsContentAttributeDao cmsContentAttributeDao;

	public ModelDao<CmsContentAttribute> getCrudDao() {
		return cmsContentAttributeDao;
	}
	
}
