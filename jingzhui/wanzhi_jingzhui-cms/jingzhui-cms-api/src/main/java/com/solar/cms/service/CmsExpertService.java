package com.solar.cms.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsExpertDto;
import com.solar.cms.query.CmsExpertQuery;

/**
 * 专家 CmsExpert 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsExpertService {
	
	/**
	 * 根据ID查询专家
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsExpertDto queryCmsExpertById(String id) throws BizException;
	
	/**
	 * 查询全部专家
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsExpertDto> queryCmsExpertAllList() throws BizException;
	
	/**
	 * 带条件查询专家
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsExpertDto> queryCmsExpertList(CmsExpertQuery query) throws BizException;
	
	/**
	 * 分页查询cmsExpert
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
	Pagination<CmsExpertDto> queryPageCmsExpert(CmsExpertQuery query) throws BizException;

	/**
	 * 检查专家名称是否存在
	 * 
	 * @param expertname 专家名称
	 * @return
	 * @throws BizException
	 */
	boolean checkCmsExpertName(String expertname) throws BizException;
	
	/**
	 * 保存专家
	 * 
	 * @param cmsExpert
	 * @return
	 * @throws BizException
	 */
	CmsExpertDto saveCmsExpert(CmsExpertDto cmsExpert) throws BizException;
	
	/**
	 * 更新专家
	 * 
	 * @param cmsExpert
	 * @throws BizException
	 */
	void updateCmsExpertById(CmsExpertDto cmsExpert) throws BizException;

	/**
	 * 根据ID删除专家
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsExpertById(String id) throws BizException;
	
	/**
	 * 批量删除专家
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsExpertByIds(List<String> ids) throws BizException;
	
}