package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsContent;
import com.solar.cms.dal.dao.CmsContentDao;
/**
 * 内容 CmsContent CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsContentCrudService")
public class CmsContentCrudService extends AbstractCrudService<CmsContent> {
	
	@Autowired
	private CmsContentDao cmsContentDao;

	public ModelDao<CmsContent> getCrudDao() {
		return cmsContentDao;
	}
	
}
