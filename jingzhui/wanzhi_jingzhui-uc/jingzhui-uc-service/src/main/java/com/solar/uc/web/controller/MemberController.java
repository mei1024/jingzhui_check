package com.solar.uc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.common.web.http.protocol.Response;
import com.nebula.common.web.http.protocol.Responses;
import com.nebula.user.UserContext;
import com.solar.uc.dto.MemberQueryDto;
import com.solar.uc.dto.MemberUpdateDto;
import com.solar.uc.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[uc]用户信息", tags = { "uc" })
@RestController
@RequestMapping("/user")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "用户信息获取", httpMethod = "GET", notes = "")
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<MemberQueryDto> getMerchantInfo(){

		MemberQueryDto member = memberService.mimiQueryMemberById(UserContext.get().getUserId());
		return Responses.newOK(member);
	}
	
	@ApiOperation(value = "用户信息修改", httpMethod = "PUT", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "会员修改对象", required = true) MemberUpdateDto member) {
		
		member.setId(id);
		memberService.updateMemberById(member);
		
		return Responses.newOK();
	}
	
}
