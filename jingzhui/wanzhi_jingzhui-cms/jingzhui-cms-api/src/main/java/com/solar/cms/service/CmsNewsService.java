package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsNewsDto;
import com.solar.cms.query.CmsNewsQuery;

/**
 * 咨讯 CmsNews 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsNewsService {
	
	/**
	 * 根据ID查询咨讯
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsNewsDto queryCmsNewsById(String id) throws BizException;
	
	/**
	 * 查询全部咨讯
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsNewsDto> queryCmsNewsAllList() throws BizException;
	
	/**
	 * 带条件查询咨讯
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsNewsDto> queryCmsNewsList(CmsNewsQuery query) throws BizException;
	
	/**
	 * 分页查询cmsNews
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
	Pagination<CmsNewsDto> queryPageCmsNews(CmsNewsQuery query) throws BizException;

	/**
	 * 保存咨讯
	 * 
	 * @param cmsNews
	 * @return
	 * @throws BizException
	 */
	CmsNewsDto saveCmsNews(CmsNewsDto cmsNews) throws BizException;
	
	/**
	 * 更新咨讯
	 * 
	 * @param cmsNews
	 * @throws BizException
	 */
	void updateCmsNewsById(CmsNewsDto cmsNews) throws BizException;

	/**
	 * 根据ID删除咨讯
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsNewsById(String id) throws BizException;
	
	/**
	 * 批量删除咨讯
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsNewsByIds(List<String> ids) throws BizException;
	
}