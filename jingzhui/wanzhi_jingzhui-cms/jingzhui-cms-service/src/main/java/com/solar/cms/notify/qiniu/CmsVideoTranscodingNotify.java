package com.solar.cms.notify.qiniu;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solar.cms.dal.crud.CmsVideoCrudService;
import com.solar.cms.entity.CmsVideo;
import com.solar.cms.service.CmsVideoService;
import com.solar.ds.qiniu.notify.QiniuNotify;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 视频转码回调接口业务处理
 * 
 * @author tllen
 *
 */
@Slf4j
@Component
public class CmsVideoTranscodingNotify implements QiniuNotify {
	
	public static String TASK_TYPE = "cms_video_adapt";
	
	@Autowired
	private CmsVideoCrudService cmsVideoCrudService;

	@Autowired
	private CmsVideoService cmsVideoService;
	
	@Override
	public void notify(String taskId, String fileKey, String content, boolean success) {
		log.info("视频处理通知,taskId={},文件ID={},success={},", taskId, fileKey, success);
		if (!success) {
			log.info("视频处理失败,文件ID={}", fileKey);
			return ;
		}
		
		// 视频信息
		CmsVideo video = cmsVideoCrudService.queryOneByProperty("vkey", fileKey);
		
		// 转码完成&&缩略图生成完成,设置视频状态=处理完成
		if (video != null && StringUtils.equalsIgnoreCase(video.getVavthumbTaskId(), taskId)) {
			video.setVavthumbStatus("Y");
			if (StringUtils.equalsIgnoreCase(video.getVavthumbStatus(), "Y")
					&& StringUtils.equalsIgnoreCase(video.getVsampleStatus(), "Y")
					) {
				video.setVstatus("0");
			}
			
			cmsVideoCrudService.update(video);
			cmsVideoService.deleteCmsVideoCacheById(video.getId());
		}
		
	}

	@Override
	public boolean support(String type) {
		return StringUtils.equalsIgnoreCase(type, TASK_TYPE);
	}

}