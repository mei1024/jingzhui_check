package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsCategoryModelDto;
import com.solar.cms.query.CmsCategoryModelQuery;

/**
 * 分类模型 CmsCategoryModel 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsCategoryModelService {
	
	/**
	 * 根据ID查询分类模型
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsCategoryModelDto queryCmsCategoryModelById(String id) throws BizException;
	
	/**
	 * 查询全部分类模型
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryModelDto> queryCmsCategoryModelAllList() throws BizException;
	
	/**
	 * 带条件查询分类模型
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsCategoryModelDto> queryCmsCategoryModelList(CmsCategoryModelQuery query) throws BizException;
	
	/**
	 * 分页查询cmsCategoryModel
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
	Pagination<CmsCategoryModelDto> queryPageCmsCategoryModel(CmsCategoryModelQuery query) throws BizException;

	/**
	 * 保存分类模型
	 * 
	 * @param cmsCategoryModel
	 * @return
	 * @throws BizException
	 */
	CmsCategoryModelDto saveCmsCategoryModel(CmsCategoryModelDto cmsCategoryModel) throws BizException;
	
	/**
	 * 更新分类模型
	 * 
	 * @param cmsCategoryModel
	 * @throws BizException
	 */
	void updateCmsCategoryModelById(CmsCategoryModelDto cmsCategoryModel) throws BizException;

	/**
	 * 根据ID删除分类模型
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsCategoryModelById(String id) throws BizException;
	
	/**
	 * 批量删除分类模型
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsCategoryModelByIds(List<String> ids) throws BizException;
	
}