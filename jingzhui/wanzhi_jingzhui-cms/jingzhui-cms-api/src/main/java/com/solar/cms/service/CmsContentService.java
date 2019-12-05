package com.solar.cms.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.query.CmsContentQuery;

/**
 * 内容 CmsContent 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsContentService {
	
	/**
	 * 根据ID查询内容
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsContentDto queryCmsContentById(String id) throws BizException;
	
	/**
	 * 单个分类内容信息查询
	 * 
	 * @param categoryId
	 * @return
	 * @throws BizException
	 */
	CmsContentDto queryCmsContentByCategoryId(String categoryId) throws BizException;
	
	/**
	 * 查询全部内容
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsContentDto> queryCmsContentAllList() throws BizException;
	
	/**
	 * 带条件查询内容
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsContentDto> queryCmsContentList(CmsContentQuery query) throws BizException;
	
	/**
	 * 分页查询cmsContent
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
	Pagination<CmsContentDto> queryPageCmsContent(CmsContentQuery query) throws BizException;

	/**
	 * 保存内容
	 * 
	 * @param cmsContent
	 * @return
	 * @throws BizException
	 */
	CmsContentDto saveCmsContent(CmsContentDto cmsContent) throws BizException;
	
	/**
	 * 更新内容
	 * 
	 * @param cmsContent
	 * @throws BizException
	 */
	void updateCmsContentById(CmsContentDto cmsContent) throws BizException;

	/**
	 * 根据ID删除内容
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsContentById(String id) throws BizException;
	
	/**
	 * 批量删除内容
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsContentByIds(List<String> ids) throws BizException;
	
	/**
	 * 内容发布
	 * 
	 * @param id 内容ID
	 * @param checkUserId 审核人ID
	 * @throws BizException
	 */
	void publishContent(String id, String checkUserId) throws BizException;

	/**
	 * 内容批量排序
	 * 
	 * @param idArray
	 * @param sortNoArray
	 * @throws BizException
	 */
	void sortContent(String[] idArray, String[] sortNoArray) throws BizException;
	
}