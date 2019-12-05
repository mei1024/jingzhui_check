package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsVideo;
import com.solar.cms.dal.dao.CmsVideoDao;
/**
 * 视频 CmsVideo CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
@Repository("cmsVideoCrudService")
public class CmsVideoCrudService extends AbstractCrudService<CmsVideo> {
	
	@Autowired
	private CmsVideoDao cmsVideoDao;

	public ModelDao<CmsVideo> getCrudDao() {
		return cmsVideoDao;
	}
	
}
