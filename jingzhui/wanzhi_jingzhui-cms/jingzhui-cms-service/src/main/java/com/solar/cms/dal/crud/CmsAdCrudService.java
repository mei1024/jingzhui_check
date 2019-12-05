package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsAd;
import com.solar.cms.dal.dao.CmsAdDao;
/**
 * 广告 CmsAd CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
@Repository("cmsAdCrudService")
public class CmsAdCrudService extends AbstractCrudService<CmsAd> {
	
	@Autowired
	private CmsAdDao cmsAdDao;

	public ModelDao<CmsAd> getCrudDao() {
		return cmsAdDao;
	}
	
}
