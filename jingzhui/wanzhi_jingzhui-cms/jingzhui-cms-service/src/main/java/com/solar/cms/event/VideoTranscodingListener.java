package com.solar.cms.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.nebula.common.security.util.SHA1Util;
import com.qiniu.util.UrlSafeBase64;
import com.solar.cms.dal.crud.CmsVideoCrudService;
import com.solar.cms.entity.CmsVideo;
import com.solar.cms.notify.qiniu.CmsVideoTranscodingNotify;
import com.solar.cms.notify.qiniu.CmsVideoVfarmeNotify;
import com.solar.cms.service.CmsVideoService;
import com.solar.ds.qiniu.config.QiniuConfiguration;
import com.solar.ds.qiniu.service.QiniuClientService;
import com.solar.ds.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 视频转码事件监听器
 * 
 * @author tllen
 *
 */
@Slf4j
@Component
public class VideoTranscodingListener implements ApplicationListener<VideoTranscodingEvent> {

	@Autowired
	private QiniuClientService qiniuClientService;
	
	@Autowired
	private CmsVideoCrudService cmsVideoCrudService;
	
	@Autowired
	private CmsVideoService cmsVideoService;

	@Async
	@Override
	public void onApplicationEvent(VideoTranscodingEvent event) {
		log.info("[视频事件]： 视频发生变化,视频ID={}.",event.getVideoId());
		
		CmsVideo cmsVideo = cmsVideoCrudService.queryById(event.getVideoId());

		// 视频编码处理
		if (cmsVideo != null) {
			CmsVideo cmsVideoUpdate = new CmsVideo();
			cmsVideoUpdate.setId(cmsVideo.getId());
			cmsVideoUpdate.setVkey(cmsVideo.getVkey());
			this.processor(cmsVideoUpdate);
		}
	}

	public void processor(CmsVideo video) {
	
		// 请求视频缩略图采样
		this.videoSample(video);
		
		// 请求视频转码
		this.videoTranscoding(video);
		
		cmsVideoCrudService.update(video);
		
		cmsVideoService.deleteCmsVideoCacheById(video.getId());
	}
	
	// 请求视频转码
	private void videoTranscoding(CmsVideo video) {
		String key480 = SHA1Util.encode(video.getVkey() + ".480");
		QiniuConfiguration qiniuConfiguration = qiniuClientService.getConfiguration();
		
		String fops = "avthumb/mp4"
				+ "/ab/192k"
				+ "/s/640x480"
				+ "|saveas/"+UrlSafeBase64.encodeToString(qiniuConfiguration.getBucket() + ":" +key480)+"/"
				+ "@@@@@@@@cms_video_adapt"; // 区分通知后业务类型

		String vavthumbTaskId = qiniuClientService.pfop(video.getVkey(), fops);
		video.setVavthumbTaskId(vavthumbTaskId);
		
		video.setKey480(key480 + ".m3u8");
		video.setUrl480(qiniuConfiguration.getDomain() + "/" + video.getKey480());
		
		video.setOriginUrl(qiniuConfiguration.getDomain() + "/" + video.getVkey());
	}
//	private void videoTranscoding(CmsVideo video) {
//		String key240 = SHA1Util.encode(video.getVkey() + ".240");
//		String key480 = SHA1Util.encode(video.getVkey() + ".480");
//		QiniuConfiguration qiniuConfiguration = qiniuClientService.getConfiguration();
//		
//		String fops = "adapt/m3u8/"
//				+ "multiResolution/320:240,640:480/"
//				+ "envBandWidth/200000,800000/"
//				+ "multiVb/200k,1200k/"
//				+ "multiPrefix/"+UrlSafeBase64.encodeToString(key240)+","+UrlSafeBase64.encodeToString(key480)+"/"
//				+ "hlstime/20/"
//				+ "@@@@@@@@" + CmsVideoTranscodingNotify.TASK_TYPE; // 区分通知后业务类型
//
//		String vavthumbTaskId = qiniuClientService.pfop(video.getVkey(), fops);
//		video.setVavthumbTaskId(vavthumbTaskId);
//		
//		video.setKey240(key240 + ".m3u8");
//		video.setKey480(key480 + ".m3u8");
//		
//		video.setUrl240(qiniuConfiguration.getDomain() + "/" + video.getKey240());
//		video.setUrl480(qiniuConfiguration.getDomain() + "/" + video.getKey480());
//		
//		video.setOriginUrl(qiniuConfiguration.getDomain() + "/" + video.getVkey());
//	}

	// 请求视频缩略图采样操作
	private void videoSample(CmsVideo video) {
		QiniuConfiguration qiniuConfiguration = qiniuClientService.getConfiguration();
		
		String imgKey = FileUtils.generalKey() + ".png";
		String saveImgEntry = String.format("%s:%s", qiniuConfiguration.getBucket(), imgKey);

		
		String coverUrl = qiniuConfiguration.getDomain() + "/" + imgKey;
		String fops = "vframe/png/offset/9/w/480/h/360"
					 + "|saveas/" + UrlSafeBase64.encodeToString(saveImgEntry)+"/"
					 + "@@@@@@@@" + CmsVideoVfarmeNotify.TASK_TYPE; // 区分通知后业务类型

		String vsampleTaskId = qiniuClientService.pfop(video.getVkey(), fops);
		video.setVsampleTaskId(vsampleTaskId);
		video.setCoverUrl(coverUrl);
	}
	
}