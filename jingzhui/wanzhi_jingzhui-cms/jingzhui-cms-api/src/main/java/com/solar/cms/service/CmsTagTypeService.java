package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsTagTypeDto;
import com.solar.cms.query.CmsTagTypeQuery;

/**
 * 标签类型 CmsTagType 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsTagTypeService {
	
	/**
	 * 根据ID查询标签类型
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsTagTypeDto queryCmsTagTypeById(String id) throws BizException;
	
	/**
	 * 查询全部标签类型
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsTagTypeDto> queryCmsTagTypeAllList() throws BizException;
	
	/**
	 * 带条件查询标签类型
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsTagTypeDto> queryCmsTagTypeList(CmsTagTypeQuery query) throws BizException;
	
	/**
	 * 分页查询cmsTagType
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
	Pagination<CmsTagTypeDto> queryPageCmsTagType(CmsTagTypeQuery query) throws BizException;

	/**
	 * 保存标签类型
	 * 
	 * @param cmsTagType
	 * @return
	 * @throws BizException
	 */
	CmsTagTypeDto saveCmsTagType(CmsTagTypeDto cmsTagType) throws BizException;
	
	/**
	 * 更新标签类型
	 * 
	 * @param cmsTagType
	 * @throws BizException
	 */
	void updateCmsTagTypeById(CmsTagTypeDto cmsTagType) throws BizException;

	/**
	 * 根据ID删除标签类型
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsTagTypeById(String id) throws BizException;
	
	/**
	 * 批量删除标签类型
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsTagTypeByIds(List<String> ids) throws BizException;
	
}