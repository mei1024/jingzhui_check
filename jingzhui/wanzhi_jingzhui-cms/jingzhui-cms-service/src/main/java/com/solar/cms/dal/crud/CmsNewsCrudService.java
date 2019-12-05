package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsNews;
import com.solar.cms.dal.dao.CmsNewsDao;
/**
 * 咨讯 CmsNews CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsNewsCrudService")
public class CmsNewsCrudService extends AbstractCrudService<CmsNews> {
	
	@Autowired
	private CmsNewsDao cmsNewsDao;

	public ModelDao<CmsNews> getCrudDao() {
		return cmsNewsDao;
	}
	
}
