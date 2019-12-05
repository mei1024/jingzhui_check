package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsContactDto;
import com.solar.cms.query.CmsContactQuery;

/**
 * 联系我们 CmsContact 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181113
 *
 */
public interface CmsContactService {
	
	/**
	 * 根据ID查询联系我们
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsContactDto queryCmsContactById(String id) throws BizException;
	
	/**
	 * 查询全部联系我们
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsContactDto> queryCmsContactAllList() throws BizException;
	
	/**
	 * 带条件查询联系我们
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsContactDto> queryCmsContactList(CmsContactQuery query) throws BizException;
	
	/**
	 * 分页查询cmsContact
	 * 
	 * @param query 查询对象
	 * <pre>
	 * 	 count=单页显示记录数
	 * 	 page=当前页面
	 * </pre>
	 * 
	 * @return
	 * @throws BizException
	 */
	Pagination<CmsContactDto> queryPageCmsContact(CmsContactQuery query) throws BizException;

	/**
	 * 保存联系我们
	 * 
	 * @param cmsContact
	 * @return
	 * @throws BizException
	 */
	CmsContactDto saveCmsContact(CmsContactDto cmsContact) throws BizException;
	
	/**
	 * 更新联系我们
	 * 
	 * @param cmsContact
	 * @throws BizException
	 */
	void updateCmsContactById(CmsContactDto cmsContact) throws BizException;

	/**
	 * 根据ID删除联系我们
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsContactById(String id) throws BizException;
	
	/**
	 * 批量删除联系我们
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsContactByIds(List<String> ids) throws BizException;
	
}