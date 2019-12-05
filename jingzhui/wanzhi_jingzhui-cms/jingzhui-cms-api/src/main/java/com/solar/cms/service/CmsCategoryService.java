package com.solar.cms.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsCategoryDto;
import com.solar.cms.query.CmsCategoryQuery;

/**
 * 分类 CmsCategory 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsCategoryService {
	
	/**
	 * 根据ID查询分类
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsCategoryDto queryCmsCategoryById(String id) throws BizException;
	
	/**
	 * 根据父ID查询分类
	 * 
	 * @param parentId 父分类ID
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryDto> queryCmsCategoryListByParentId(String parentId) throws BizException;
	
	/**
	 * 查询全部分类
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryDto> queryCmsCategoryAllList() throws BizException;
	
	/**
	 * 带条件查询分类
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryDto> queryCmsCategoryList(CmsCategoryQuery query) throws BizException;
	
	/**
	 * 分页查询cmsCategory
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
	Pagination<CmsCategoryDto> queryPageCmsCategory(CmsCategoryQuery query) throws BizException;

	/**
	 * 保存分类
	 * 
	 * @param cmsCategory
	 * @return
	 * @throws BizException
	 */
	CmsCategoryDto saveCmsCategory(CmsCategoryDto cmsCategory) throws BizException;
	
	/**
	 * 更新分类
	 * 
	 * @param cmsCategory
	 * @throws BizException
	 */
	void updateCmsCategoryById(CmsCategoryDto cmsCategory) throws BizException;

	/**
	 * 根据ID删除分类
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsCategoryById(String id) throws BizException;
	
	/**
	 * 批量删除分类
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsCategoryByIds(List<String> ids) throws BizException;
	
}