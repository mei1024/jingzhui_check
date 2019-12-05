package com.solar.cms.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.query.CmsAdQuery;

/**
 * 广告 CmsAd 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
public interface CmsAdService {
	
	/**
	 * 根据ID查询广告
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsAdDto queryCmsAdById(String id) throws BizException;
	
	/**
	 * 查询指定类型指定内容ID广告
	 * 
	 * @param type 广告类型
	 * @param contentId 内容ID
	 * @return
	 * @throws BizException
	 */
	List<CmsAdDto> queryCmsAdListByTypeAndContentId(String type, String contentId) throws BizException;
	
	/**
	 * 根据类型查询广告
	 * 
	 * @param type 广告类型
	 * @return
	 * @throws BizException
	 */
	List<CmsAdDto> queryCmsAdListByType(String type) throws BizException;
	
	/**
	 * 查询全部广告
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsAdDto> queryCmsAdAllList() throws BizException;
	
	/**
	 * 带条件查询广告
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsAdDto> queryCmsAdList(CmsAdQuery query) throws BizException;
	
	/**
	 * 分页查询cmsAd
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
	Pagination<CmsAdDto> queryPageCmsAd(CmsAdQuery query) throws BizException;

	/**
	 * 保存广告
	 * 
	 * @param cmsAd
	 * @return
	 * @throws BizException
	 */
	CmsAdDto saveCmsAd(CmsAdDto cmsAd) throws BizException;
	
	/**
	 * 更新广告
	 * 
	 * @param cmsAd
	 * @throws BizException
	 */
	void updateCmsAdById(CmsAdDto cmsAd) throws BizException;

	/**
	 * 根据ID删除广告
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsAdById(String id) throws BizException;
	
	/**
	 * 删除指定类型指定内容ID广告
	 * 
	 * @param type 类型
	 * @param contentId 内容ID 
	 * @throws BizException
	 */
	void deleteCmsAdByTypeAndContentId(String type, String contentId) throws BizException;
	
	/**
	 * 批量删除广告
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsAdByIds(List<String> ids) throws BizException;
	
}