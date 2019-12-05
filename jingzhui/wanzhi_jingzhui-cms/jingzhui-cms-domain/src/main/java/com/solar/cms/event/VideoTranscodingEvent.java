package com.solar.cms.event;

import com.nebula.common.event.Event;
/**
 * 
 * 视频转码Event
 * 
 * @author tllen
 *
 */
public class VideoTranscodingEvent extends Event {

	private String videoId;

	public VideoTranscodingEvent(Object source) {
		super(source);
	}

	public VideoTranscodingEvent(Object source, String videoId) {
		super(source);
		this.videoId = videoId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

}