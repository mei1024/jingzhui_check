package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsVideoDto;
import com.solar.cms.query.CmsVideoQuery;

/**
 * 视频 CmsVideo 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181026
 *
 */
public interface CmsVideoService {
	
	/**
	 * 根据ID查询视频
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsVideoDto queryCmsVideoById(String id) throws BizException;
	
	/**
	 * 查询全部视频
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsVideoDto> queryCmsVideoAllList() throws BizException;
	
	/**
	 * 带条件查询视频
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsVideoDto> queryCmsVideoList(CmsVideoQuery query) throws BizException;
	
	/**
	 * 分页查询cmsVideo
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
	Pagination<CmsVideoDto> queryPageCmsVideo(CmsVideoQuery query) throws BizException;

	/**
	 * 保存视频
	 * 
	 * @param cmsVideo
	 * @return
	 * @throws BizException
	 */
	CmsVideoDto saveCmsVideo(CmsVideoDto cmsVideo) throws BizException;
	
	/**
	 * 更新视频
	 * 
	 * @param cmsVideo
	 * @throws BizException
	 */
	void updateCmsVideoById(CmsVideoDto cmsVideo) throws BizException;

	/**
	 * 根据ID删除视频
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsVideoById(String id) throws BizException;
	
	/**
	 * 批量删除视频
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsVideoByIds(List<String> ids) throws BizException;

	/**
	 * 删除缓存中的数据
	 * 
	 * @param id
	 * @throws BizException
	 */
	void deleteCmsVideoCacheById(String id) throws BizException;
	
}