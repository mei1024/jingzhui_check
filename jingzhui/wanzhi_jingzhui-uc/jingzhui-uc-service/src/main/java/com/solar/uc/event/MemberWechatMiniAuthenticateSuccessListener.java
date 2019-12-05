package com.solar.uc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.solar.auth.dto.ThirdUserDto;
import com.solar.auth.enums.ThirdUserTypeEnum;
import com.solar.auth.event.WechatMiniAuthenticateSuccessEvent;
import com.solar.auth.weixin.WxUserInfo;
import com.solar.uc.service.MemberService;

/**
 * @declaration 微信小程序认证成功Listener
 * 1、保存用户基本信息
 * 2、保存小程序用户绑定关系
 * @author mw
 * @date 2019-12-05 15:52
 */
@Component
public class MemberWechatMiniAuthenticateSuccessListener implements ApplicationListener<WechatMiniAuthenticateSuccessEvent> {
 
	@Autowired
	private MemberService memberService;

	@Override
	public void onApplicationEvent(WechatMiniAuthenticateSuccessEvent event) {
		WxUserInfo wxuser = event.getUser();
		
		// 小程序用户保存
		ThirdUserDto thirdUser = new ThirdUserDto();
		thirdUser.setOpenId(wxuser.getOpenId());
		thirdUser.setOpenType(ThirdUserTypeEnum.WECHAT_MINI);
		thirdUser.setAvatarUrl(wxuser.getAvatarUrl());
		String nickname = wxuser.getNickName();
		thirdUser.setNickName(nickname);
		thirdUser.setDetail(JSON.toJSONString(wxuser)); 
		
		memberService.saveMember(thirdUser);
	}
 
 	 
}