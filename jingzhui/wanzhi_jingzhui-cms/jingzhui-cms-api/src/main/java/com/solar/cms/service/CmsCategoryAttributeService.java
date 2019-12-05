package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsCategoryAttributeDto;
import com.solar.cms.query.CmsCategoryAttributeQuery;

/**
 * 分类扩展 CmsCategoryAttribute 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsCategoryAttributeService {
	
	/**
	 * 根据ID查询分类扩展
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsCategoryAttributeDto queryCmsCategoryAttributeById(String id) throws BizException;
	
	/**
	 * 查询全部分类扩展
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryAttributeDto> queryCmsCategoryAttributeAllList() throws BizException;
	
	/**
	 * 带条件查询分类扩展
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryAttributeDto> queryCmsCategoryAttributeList(CmsCategoryAttributeQuery query) throws BizException;
	
	/**
	 * 分页查询cmsCategoryAttribute
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
	Pagination<CmsCategoryAttributeDto> queryPageCmsCategoryAttribute(CmsCategoryAttributeQuery query) throws BizException;

	/**
	 * 保存分类扩展
	 * 
	 * @param cmsCategoryAttribute
	 * @return
	 * @throws BizException
	 */
	CmsCategoryAttributeDto saveCmsCategoryAttribute(CmsCategoryAttributeDto cmsCategoryAttribute) throws BizException;
	
	/**
	 * 更新分类扩展
	 * 
	 * @param cmsCategoryAttribute
	 * @throws BizException
	 */
	void updateCmsCategoryAttributeById(CmsCategoryAttributeDto cmsCategoryAttribute) throws BizException;

	/**
	 * 根据ID删除分类扩展
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsCategoryAttributeById(String id) throws BizException;
	
	/**
	 * 批量删除分类扩展
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsCategoryAttributeByIds(List<String> ids) throws BizException;
	
}