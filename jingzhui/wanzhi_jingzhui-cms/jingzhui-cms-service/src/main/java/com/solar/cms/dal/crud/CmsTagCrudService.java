package com.solar.cms.dal.crud;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebula.common.biz.dal.crud.AbstractCrudService;
import com.nebula.common.biz.dal.dao.ModelDao;

import com.solar.cms.entity.CmsTag;
import com.solar.cms.dal.dao.CmsTagDao;
/**
 * 标签 CmsTag CRUD 数据服务接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
@Repository("cmsTagCrudService")
public class CmsTagCrudService extends AbstractCrudService<CmsTag> {
	
	@Autowired
	private CmsTagDao cmsTagDao;

	public ModelDao<CmsTag> getCrudDao() {
		return cmsTagDao;
	}
	
}
