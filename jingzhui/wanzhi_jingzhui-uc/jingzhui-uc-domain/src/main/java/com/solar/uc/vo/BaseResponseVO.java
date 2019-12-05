package com.solar.uc.vo;

public class BaseResponseVO {
	
	public static Long OK_RESULT_CODE = 0L;
	public static Long FAIL_RESULT_CODE = -1L;
	
	/**
     * 是否成功 0 成功，非0 失败
     */
	private Long resultCode;
	/**
	 * 错误原因
	 */
	private String msgDesc;
	
	public Long getResultCode() {
		return resultCode;
	}
	public void setResultCode(Long resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsgDesc() {
		return msgDesc;
	}
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	
	
	public  void setOKResponseOKVO(){
		this.setResultCode(OK_RESULT_CODE);
		this.setMsgDesc("成功");
	}
	
	public  void setOKResponseFailVO(){
		this.setResultCode(FAIL_RESULT_CODE);
		this.setMsgDesc("失败");
	}
	
}
