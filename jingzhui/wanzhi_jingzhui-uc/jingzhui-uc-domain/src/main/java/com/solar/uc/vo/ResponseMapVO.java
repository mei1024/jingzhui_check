package com.solar.uc.vo;

import java.util.Map;

public class ResponseMapVO extends BaseResponseVO{
	/**
	 * Map 对象
	 */
	private Map<String,Object> data ;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
