package com.solar.cms.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.query.CmsTagQuery;

/**
 * 标签 CmsTag 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsTagService {
	
	/**
	 * 根据ID查询标签
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsTagDto queryCmsTagById(String id) throws BizException;
	
	/**
	 * 根据ID集合查询标签
	 * 
	 * @param tagIds 标签ID集合
	 * @return
	 * @throws BizException
	 */
	List<CmsTagDto> queryCmsTagListByIds(String[] tagIds) throws BizException;
	
	/**
	 * 根据ID集合查询标签,返回标签名称
	 * 
	 * @param tagIds 标签ID集合
	 * @return
	 * @throws BizException
	 */	
	List<String> queryCmsTagNamesByIds(String[] tagIds) throws BizException;
	
	/**
	 * 根据类型查询标签
	 * 
	 * @param typeId 类型ID
	 * @return
	 * @throws BizException
	 */
	List<CmsTagDto> queryCmsTagListByTypeId(String typeId) throws BizException;
	
	/**
	 * 查询全部标签
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsTagDto> queryCmsTagAllList() throws BizException;
	
	/**
	 * 带条件查询标签
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsTagDto> queryCmsTagList(CmsTagQuery query) throws BizException;
	
	/**
	 * 分页查询cmsTag
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
	Pagination<CmsTagDto> queryPageCmsTag(CmsTagQuery query) throws BizException;

	/**
	 * 保存标签
	 * 
	 * @param cmsTag
	 * @return
	 * @throws BizException
	 */
	CmsTagDto saveCmsTag(CmsTagDto cmsTag) throws BizException;
	
	/**
	 * 更新标签
	 * 
	 * @param cmsTag
	 * @throws BizException
	 */
	void updateCmsTagById(CmsTagDto cmsTag) throws BizException;

	/**
	 * 根据ID删除标签
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsTagById(String id) throws BizException;
	
	/**
	 * 批量删除标签
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsTagByIds(List<String> ids) throws BizException;
	
}