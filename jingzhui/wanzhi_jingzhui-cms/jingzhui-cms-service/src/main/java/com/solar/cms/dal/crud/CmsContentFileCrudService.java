package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsContentFile;
import com.solar.cms.dal.dao.CmsContentFileDao;
/**
 * 内容附件 CmsContentFile CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsContentFileCrudService")
public class CmsContentFileCrudService extends AbstractCrudService<CmsContentFile> {
	
	@Autowired
	private CmsContentFileDao cmsContentFileDao;

	public ModelDao<CmsContentFile> getCrudDao() {
		return cmsContentFileDao;
	}
	
}
