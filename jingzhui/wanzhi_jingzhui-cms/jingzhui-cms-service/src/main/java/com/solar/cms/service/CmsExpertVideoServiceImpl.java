package com.solar.cms.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.cms.converter.CmsExpertVideoConverterFactory;
import com.solar.cms.dal.crud.CmsExpertVideoCrudService;
import com.solar.cms.dto.CmsExpertVideoDto;
import com.solar.cms.entity.CmsExpertVideo;
import com.solar.cms.query.CmsExpertVideoQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 专家视频 CmsExpertVideo 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsExpertVideoService")
public class CmsExpertVideoServiceImpl extends BaseServiceImpl implements CmsExpertVideoService {
	
	@Autowired
	private CmsExpertVideoCrudService cmsExpertVideoCrudService;
	
	@Override
	public CmsExpertVideoDto queryCmsExpertVideoById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsExpertVideo cmsExpertVideo = cmsExpertVideoCrudService.queryById(id);
		if (cmsExpertVideo == null) {
			return null;
		}
		return CmsExpertVideoConverterFactory.convertCmsExpertVideoDTO(cmsExpertVideo);
	}

	@Override
	public CmsExpertVideoDto queryCmsExpertVideoByVideoId(String videoId) throws BizException {
		BizAssert.notEmpty(videoId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:videoId为空");
		
		Map<String, Object> queryExpertVideoParamter = new HashMap<>();
		queryExpertVideoParamter.put("videoId", videoId);

		CmsExpertVideo cmsExpertVideo = cmsExpertVideoCrudService.queryOne(queryExpertVideoParamter);
		
		if (cmsExpertVideo == null) {
			return null;
		}
		return CmsExpertVideoConverterFactory.convertCmsExpertVideoDTO(cmsExpertVideo);
	}
 
	@Override
	public List<CmsExpertVideoDto> queryCmsExpertVideoAllList() throws BizException {
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryAllList();

		return CmsExpertVideoConverterFactory.convertCmsExpertVideoListDTO(list);
	}
	
	@Override
	public List<CmsExpertVideoDto> queryCmsExpertVideoList(final CmsExpertVideoQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryList(search.getParameters());
		
		return CmsExpertVideoConverterFactory.convertCmsExpertVideoListDTO(list);
	}
	
	@Override
	public Pagination<CmsExpertVideoDto> queryPageCmsExpertVideo(final CmsExpertVideoQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsExpertVideo> list = cmsExpertVideoCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsExpertVideoCrudService.count(search.getParameters());

		List<CmsExpertVideoDto> resultList = CmsExpertVideoConverterFactory.convertCmsExpertVideoListDTO(list);
		
		return new Pagination<CmsExpertVideoDto>(search.getPage(), search.getCount(), totals, resultList);
	}
	
	
	@Override
	public void saveCmsExpertVideo(List<String> expertIds, String videoId) throws BizException {
		BizAssert.notNull(expertIds, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:expertIds为空");
		BizAssert.notEmpty(videoId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:videoId为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				List<CmsExpertVideo> expertVideoList = new ArrayList<>();
				for (String expertId : expertIds) {
					// 检查是否存在
					if (checkExistsBind(expertId, videoId)) {
						continue;
					}
					
					CmsExpertVideo expertVideo = new CmsExpertVideo();
					expertVideo.setVideoId(videoId);
					expertVideo.setExpertId(expertId);
					expertVideo.setCreateDate(System.currentTimeMillis());
					expertVideo.setLastModDate(System.currentTimeMillis());
					expertVideo.setDstatus("N");

					expertVideoList.add(expertVideo);
				}
				if (!CollectionUtils.isEmpty(expertVideoList)) {
					cmsExpertVideoCrudService.batchSave(expertVideoList);
				}
				
				return true;
			}
			
		});
	} 
	
	
	@Override
	public void saveCmsExpertVideo(String expertId, List<String> videoIds) throws BizException {
		BizAssert.notEmpty(expertId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:expertId为空");
		BizAssert.notNull(videoIds, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:videoIds为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				List<CmsExpertVideo> expertVideoList = new ArrayList<>();
				for (String videoId : videoIds) {
					// 检查是否存在
					if (checkExistsBind(expertId, videoId)) {
						continue;
					}

					CmsExpertVideo expertVideo = new CmsExpertVideo();
					expertVideo.setVideoId(videoId);
					expertVideo.setExpertId(expertId);
					expertVideo.setCreateDate(System.currentTimeMillis());
					expertVideo.setLastModDate(System.currentTimeMillis());
					expertVideo.setDstatus("N");
					expertVideo.setVersion("1");
					
					expertVideoList.add(expertVideo);
				}
				if (!CollectionUtils.isEmpty(expertVideoList)) {
					cmsExpertVideoCrudService.batchSave(expertVideoList);
				}
				
				return true;
			}
			
		});
	} 
	
	@Override
	public CmsExpertVideoDto saveCmsExpertVideo(final CmsExpertVideoDto cmsExpertVideo) throws BizException {
		BizAssert.notNull(cmsExpertVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpertVideo为空");
		
		return (CmsExpertVideoDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsExpertVideo createModel = CmsExpertVideoConverterFactory.convertCmsExpertVideoEntity(cmsExpertVideo);
				cmsExpertVideoCrudService.save(createModel);
				
				CmsExpertVideoDto result = CmsExpertVideoConverterFactory.convertCmsExpertVideoDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsExpertVideoById(final CmsExpertVideoDto cmsExpertVideo) throws BizException {
		BizAssert.notNull(cmsExpertVideo, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsExpertVideo为空");
		BizAssert.notEmpty(cmsExpertVideo.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsExpertVideoCrudService.queryById(cmsExpertVideo.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsExpertVideo.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsExpertVideo updateModel = CmsExpertVideoConverterFactory.convertCmsExpertVideoEntity(cmsExpertVideo);
				cmsExpertVideoCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsExpertVideoById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsExpertVideoCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsExpertVideoCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsExpertVideoByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsExpertVideoById(id);
		}
	}
	
	@Override
	public void deleteCmsExpertVideo(String expertId, List<String> videoIds) throws BizException {
		BizAssert.notEmpty(expertId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:expertId为空");
		BizAssert.notNull(videoIds, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:videoIds为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				Map<String, Object> deleteExpertVideoParamter = new HashMap<>();
				
				for (String videoId : videoIds) {
					deleteExpertVideoParamter.put("expertId", expertId);
					deleteExpertVideoParamter.put("videoId", videoId);

					cmsExpertVideoCrudService.deleteByCondition(deleteExpertVideoParamter);
				}
			
				return true;
			}
			
		});
		
	}

	@Override
	public void deleteCmsExpertVideoByVideoId(String videoId) throws BizException {
		BizAssert.notEmpty(videoId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:videoId为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				Map<String, Object> deleteExpertVideoParamter = new HashMap<>();
				
				deleteExpertVideoParamter.put("videoId", videoId);

				cmsExpertVideoCrudService.deleteByCondition(deleteExpertVideoParamter);
			
				return true;
			}
			
		});

	}
	
	private boolean checkExistsBind(String expertId, String videoId) throws BizException {
		Map<String, Object> checkExistsBindParamter = new HashMap<>();
		checkExistsBindParamter.put("expertId", expertId);
		checkExistsBindParamter.put("videoId", videoId);
		
		return cmsExpertVideoCrudService.queryOne(checkExistsBindParamter) != null;
	}

	
	
}
