package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsExpertVideo;
import com.solar.cms.dal.dao.CmsExpertVideoDao;
/**
 * 专家视频 CmsExpertVideo CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsExpertVideoCrudService")
public class CmsExpertVideoCrudService extends AbstractCrudService<CmsExpertVideo> {
	
	@Autowired
	private CmsExpertVideoDao cmsExpertVideoDao;

	public ModelDao<CmsExpertVideo> getCrudDao() {
		return cmsExpertVideoDao;
	}
	
}
