package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.query.CmsContentAttributeQuery;

/**
 * 内容扩展 CmsContentAttribute 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsContentAttributeService {
	
	/**
	 * 根据ID查询内容扩展
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsContentAttributeDto queryCmsContentAttributeById(String id) throws BizException;
	
	/**
	 * 查询全部内容扩展
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsContentAttributeDto> queryCmsContentAttributeAllList() throws BizException;
	
	/**
	 * 带条件查询内容扩展
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsContentAttributeDto> queryCmsContentAttributeList(CmsContentAttributeQuery query) throws BizException;
	
	/**
	 * 分页查询cmsContentAttribute
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
	Pagination<CmsContentAttributeDto> queryPageCmsContentAttribute(CmsContentAttributeQuery query) throws BizException;

	/**
	 * 保存内容扩展
	 * 
	 * @param cmsContentAttribute
	 * @return
	 * @throws BizException
	 */
	CmsContentAttributeDto saveCmsContentAttribute(CmsContentAttributeDto cmsContentAttribute) throws BizException;
	
	/**
	 * 更新内容扩展
	 * 
	 * @param cmsContentAttribute
	 * @throws BizException
	 */
	void updateCmsContentAttributeById(CmsContentAttributeDto cmsContentAttribute) throws BizException;

	/**
	 * 根据ID删除内容扩展
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsContentAttributeById(String id) throws BizException;
	
	/**
	 * 批量删除内容扩展
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsContentAttributeByIds(List<String> ids) throws BizException;
	
}