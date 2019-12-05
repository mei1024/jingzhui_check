package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsExpertVideoDto;
import com.solar.cms.query.CmsExpertVideoQuery;

/**
 * 专家视频 CmsExpertVideo 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsExpertVideoService {
	
	/**
	 * 根据ID查询专家视频
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsExpertVideoDto queryCmsExpertVideoById(String id) throws BizException;
	
	/**
	 * 根据视频ID查询专家
	 * 
	 * @param videoId 视频ID
	 * @return
	 * @throws BizException
	 */
	CmsExpertVideoDto queryCmsExpertVideoByVideoId(String videoId) throws BizException;
	
	/**
	 * 查询全部专家视频
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsExpertVideoDto> queryCmsExpertVideoAllList() throws BizException;
	
	/**
	 * 带条件查询专家视频
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsExpertVideoDto> queryCmsExpertVideoList(CmsExpertVideoQuery query) throws BizException;
	
	/**
	 * 分页查询cmsExpertVideo
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
	Pagination<CmsExpertVideoDto> queryPageCmsExpertVideo(CmsExpertVideoQuery query) throws BizException;

	/**
	 * 单个视频分配多个专家
	 * 
	 * @param expertIds 专家ID集合
	 * @param videoId 视频ID
	 * @throws BizException
	 */
	void saveCmsExpertVideo(List<String> expertIds, String videoId) throws BizException;
	
	/**
	 * 单个专家分配多个视频
	 * 
	 * @param expertId 专家ID
	 * @param videoIds 视频ID集合
	 * @throws BizException
	 */
	void saveCmsExpertVideo(String expertId, List<String> videoIds) throws BizException;
	
	/**
	 * 保存专家视频
	 * 
	 * @param cmsExpertVideo
	 * @return
	 * @throws BizException
	 */
	CmsExpertVideoDto saveCmsExpertVideo(CmsExpertVideoDto cmsExpertVideo) throws BizException;
	
	/**
	 * 更新专家视频
	 * 
	 * @param cmsExpertVideo
	 * @throws BizException
	 */
	void updateCmsExpertVideoById(CmsExpertVideoDto cmsExpertVideo) throws BizException;

	/**
	 * 根据ID删除专家视频
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsExpertVideoById(String id) throws BizException;
	
	/**
	 * 批量删除专家视频
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsExpertVideoByIds(List<String> ids) throws BizException;
	
	/**
	 * 删除单个专家多个视频
	 * 
	 * @param expertId 专家ID
	 * @param videoIds 视频ID集合
	 * @throws BizException
	 */
	void deleteCmsExpertVideo(String expertId, List<String> videoIds) throws BizException;
	
	/**
	 * 删除视频专家
	 * 
	 * @param videoId 视频ID
	 * @throws BizException
	 */
	void deleteCmsExpertVideoByVideoId(String videoId) throws BizException;
	
}