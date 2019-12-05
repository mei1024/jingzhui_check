package com.solar.cms.service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.event.SpringEventPublisher;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.CmsCacheConstants;
import com.solar.cms.converter.CmsVideoConverterFactory;
import com.solar.cms.dal.crud.CmsVideoCrudService;
import com.solar.cms.dal.dao.ext.CmsVideoExtDao;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.dto.CmsVideoDto;
import com.solar.cms.entity.CmsVideo;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.enums.CmsVideoEnum;
import com.solar.cms.event.VideoTranscodingEvent;
import com.solar.cms.query.CmsVideoQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 视频 CmsVideo 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181026
 * 
 */
@Service("cmsVideoService")
public class CmsVideoServiceImpl extends BaseServiceImpl implements CmsVideoService {
	
	@Autowired
	private CmsVideoCrudService cmsVideoCrudService;
	
	@Autowired
	private CmsContentService cmsContentService;

	@Autowired
	private CmsVideoExtDao cmsVideoExtDao;
	
	@Autowired
	private CmsExpertVideoService cmsExpertVideoService;

	@Autowired
	private SpringEventPublisher springEventPublisher;
	
	@Override
	@Cacheable(value = CmsCacheConstants.CMS_VIDEO_CACHE_NAMES, key="#id", unless="#result == null", condition="#id!=null")
	public CmsVideoDto queryCmsVideoById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsVideo cmsVideo = cmsVideoCrudService.queryById(id);
		return CmsVideoConverterFactory.convertCmsVideoDTO(cmsVideo);
	}
 
	@Override
	public List<CmsVideoDto> queryCmsVideoAllList() throws BizException {
		List<CmsVideo> list = cmsVideoCrudService.queryAllList();

		return CmsVideoConverterFactory.convertCmsVideoListDTO(list);
	}
	
	@Override
	public List<CmsVideoDto> queryCmsVideoList(final CmsVideoQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsVideo> list = cmsVideoCrudService.queryList(search.getParameters());
		
		return CmsVideoConverterFactory.convertCmsVideoListDTO(list);
	}
	
	@Override
	public Pagination<CmsVideoDto> queryPageCmsVideo(final CmsVideoQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("categoryId", query.getCategoryId());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("vstatus", query.getVstatus());
		search.safeAddParamter("tagId", query.getTagId());
		search.safeAddParamter("status", query.getStatus());
		search.safeAddParamter("expertId", query.getExpertId());
		try {
			if (StringUtils.isNotEmpty(query.getMinDate()) && StringUtils.isNotEmpty(query.getMaxDate())) {
				Date startDate = DateUtils.parseDate(query.getMinDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getMaxDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("minCreateDate", startDate.getTime());
				search.safeAddParamter("maxCreateDate", endDate.getTime());
			}
		} catch (ParseException e) {
		}
		// query list
		List<CmsVideo> list = cmsVideoExtDao.queryPage(getContextParams(), search.getParameters(), search.offset(), search.getCount(), "b.publish_date", SortByEnum.DESC.name());
		// query totals
		int totals = cmsVideoExtDao.count(getContextParams(), search.getParameters());
		if (totals > 0 && CollectionUtils.isEmpty(list)) {
			search.setPage(1);
			list = cmsVideoExtDao.queryPage(getContextParams(), search.getParameters(), 1, search.getCount(), "b.publish_date", SortByEnum.DESC.name());
		}

		List<CmsVideoDto> resultList = CmsVideoConverterFactory.convertCmsVideoListDTO(list);
		
		return new Pagination<CmsVideoDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsVideoDto saveCmsVideo(final CmsVideoDto cmsVideo) throws BizException {
		BizAssert.notNull(cmsVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsVideo为空");
		BizAssert.notEmpty(cmsVideo.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
		BizAssert.notEmpty(cmsVideo.getVkey(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:vkey为空");
		BizAssert.notNull(CmsContentEnum.Status.get(cmsVideo.getStatus()), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");
		
		return (CmsVideoDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				// 1.基础内容保存
				CmsContentDto baseContent = new CmsContentDto();
				baseContent.setTitle(cmsVideo.getTitle());
				baseContent.setUserId(cmsVideo.getUserId());
				baseContent.setCategoryId(cmsVideo.getCategoryId());
				baseContent.setCopied("N");
				baseContent.setOnlyUrl("N");
				baseContent.setHasImages("N");
				baseContent.setHasFiles("N");
				baseContent.setHasStatic("N");
				baseContent.setUrl("");
				baseContent.setDescription(cmsVideo.getDescription());
				baseContent.setTagIds(cmsVideo.getTagIds());
				baseContent.setCover("");
				baseContent.setPublishDate(new Date());
				baseContent.setSortno(1);
				baseContent.setStatus(CmsContentEnum.Status.get(cmsVideo.getStatus()).getCode());
				baseContent.setSource(cmsVideo.getSource());
				baseContent.setSourceUrl("");
				baseContent.setText(cmsVideo.getText());
				baseContent.setWordCount(0);
				CmsContentDto resultContent =  cmsContentService.saveCmsContent(baseContent);
				
				// 2.视频内容保存
				CmsVideo createVideoModel = CmsVideoConverterFactory.convertCmsVideoEntity(cmsVideo);
				createVideoModel.setId(resultContent.getId());
				createVideoModel.setOriginUrl("");
				createVideoModel.setVstatus(CmsVideoEnum.VStatus.DECODE.getCode());
				createVideoModel.setVavthumbStatus("N");
				createVideoModel.setVsampleStatus("N");
				createVideoModel.setUploadTime(System.currentTimeMillis());
				cmsVideoCrudService.save(createVideoModel);
				// 3.视频专家绑定
				if (StringUtils.isNotEmpty(cmsVideo.getExpertId())) {
					List<String> videoIds = new ArrayList<>();
					videoIds.add(resultContent.getId());
					cmsExpertVideoService.saveCmsExpertVideo(cmsVideo.getExpertId(), videoIds);
				}
				
				// 4.创建视频事件通知
				springEventPublisher.publish(new VideoTranscodingEvent(this, createVideoModel.getId()));
				
				CmsVideoDto result = CmsVideoConverterFactory.convertCmsVideoDTO(createVideoModel);
				return result;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_VIDEO_CACHE_NAMES, key = "#cmsVideo.id", condition="#cmsVideo.id!=null")
	public void updateCmsVideoById(final CmsVideoDto cmsVideo) throws BizException {
		BizAssert.notNull(cmsVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsVideo为空");
		BizAssert.notEmpty(cmsVideo.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsVideo.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
		BizAssert.notEmpty(cmsVideo.getVkey(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:vkey为空");
		BizAssert.notNull(CmsContentEnum.Status.get(cmsVideo.getStatus()), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsVideoCrudService.queryById(cmsVideo.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsVideo.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				// 1.基础内容修改保存
				CmsContentDto baseContent = new CmsContentDto();
				baseContent.setId(cmsVideo.getId());
				baseContent.setTitle(cmsVideo.getTitle());
				baseContent.setUserId(cmsVideo.getUserId());
				baseContent.setCategoryId(cmsVideo.getCategoryId());
				baseContent.setCopied("N");
				baseContent.setOnlyUrl("N");
				baseContent.setHasImages("N");
				baseContent.setHasFiles("N");
				baseContent.setHasStatic("N");
				baseContent.setUrl("");
				baseContent.setDescription(cmsVideo.getDescription());
				baseContent.setTagIds(cmsVideo.getTagIds());
				baseContent.setCover("");
				baseContent.setPublishDate(new Date());
				baseContent.setSortno(1);
				baseContent.setStatus(CmsContentEnum.Status.get(cmsVideo.getStatus()).getCode());
				baseContent.setSource(cmsVideo.getSource());
				baseContent.setSourceUrl("");
				baseContent.setText(cmsVideo.getText());
				baseContent.setWordCount(0);

				cmsContentService.updateCmsContentById(baseContent);

				// 2.视频内容保存
				CmsVideo updateModel = cmsVideoCrudService.queryById(cmsVideo.getId());
				String originalVkey = updateModel.getVkey();
				updateModel.setVkey(cmsVideo.getVkey());
				if (!StringUtils.equalsAnyIgnoreCase(updateModel.getVkey(), cmsVideo.getVkey())) {
					updateModel.setVstatus(CmsVideoEnum.VStatus.DECODE.getCode());
					updateModel.setVavthumbStatus("N");
					updateModel.setVavthumbTaskId("");
					updateModel.setVsampleStatus("N");
					updateModel.setVsampleTaskId("");
					updateModel.setCoverUrl("");
					updateModel.setUploadTime(System.currentTimeMillis());
					updateModel.setUploadIp(cmsVideo.getUploadIp());
				}
				cmsVideoCrudService.update(updateModel);
				
				// 3.视频专家绑定
				if (StringUtils.isNotEmpty(cmsVideo.getExpertId())) {
					cmsExpertVideoService.deleteCmsExpertVideoByVideoId(cmsVideo.getId());
					List<String> videoIds = new ArrayList<>();
					videoIds.add(cmsVideo.getId());
					cmsExpertVideoService.saveCmsExpertVideo(cmsVideo.getExpertId(), videoIds);
				}
				
				// 4. 创建视频事件通知，根据视频KEY进行验证视频是否是新文件
				if (!StringUtils.equalsIgnoreCase(originalVkey, cmsVideo.getVkey())) {
					springEventPublisher.publish(new VideoTranscodingEvent(this, updateModel.getId()));
				}

				return true;
			}
			
		});
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_VIDEO_CACHE_NAMES, key = "#id", condition="#id!=null")
	public void deleteCmsVideoById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsVideoCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsVideoCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsVideoByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsVideoById(id);
		}
	}
	
	@Override
	@CacheEvict(value = CmsCacheConstants.CMS_VIDEO_CACHE_NAMES, key = "#id", condition="#id!=null")
	public void deleteCmsVideoCacheById(String id) throws BizException {}
 
}
