//package com.solar.uc.web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nebula.common.util.Pagination;
//import com.nebula.common.web.http.protocol.Response;
//import com.nebula.common.web.http.protocol.Responses;
//import com.solar.uc.dto.MemberLogDto;
//import com.solar.uc.query.MemberLogQuery;
//import com.solar.uc.service.MemberLogService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
//@Api(value = "[uc]会员日志", tags = { "uc" })
//@RestController
//@RequestMapping("/user/memberLog")
//public class MemberLogController {
//	
//	@Autowired
//	private MemberLogService memberLogService;
//	
//	@ApiOperation(value = "会员日志查询", httpMethod = "GET", notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response<Pagination<MemberLogDto>> list(
//			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
//			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
//			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
//			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
//			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {
//
//		MemberLogQuery query = new MemberLogQuery();
//		query.setKeyword(keyword);
//		query.setOrder(order);
//		query.setSortby(sortby);
//		query.setCount(pageSize);
//		query.setPage(num);
//		
//		Pagination<MemberLogDto> pagination = memberLogService.queryPageMemberLog(query);
//
//		return Responses.newOK(pagination);
//	}
//
//	@ApiOperation(value = "会员日志创建", httpMethod = "POST", notes = "")
//	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response<MemberLogDto> create(
//			@RequestBody @ApiParam(name = "会员日志创建对象", required = true) MemberLogDto memberLog) {
//		MemberLogDto result = memberLogService.saveMemberLog(memberLog);
//		return Responses.newOK(result);
//	}
//	
//	@ApiOperation(value = "会员日志删除", httpMethod = "DELETE", notes = "")
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response<String> delete(
//			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
//		
//		memberLogService.deleteMemberLogById(id);
//		
//		return Responses.newOK();
//	}
//	
//	@ApiOperation(value = "会员日志修改", httpMethod = "PUT", notes = "")
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response<String> update(
//			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
//			@RequestBody @ApiParam(name = "会员日志修改对象", required = true) MemberLogDto memberLog) {
//		
//		memberLog.setId(id);
//		memberLogService.updateMemberLogById(memberLog);
//		
//		return Responses.newOK();
//	}
//	
//	@ApiOperation(value = "会员日志信息", httpMethod = "GET", notes = "")
//	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response<MemberLogDto> get(
//			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
//		
//		MemberLogDto result = memberLogService.queryMemberLogById(id);
//		
//		return Responses.newOK(result);
//	}
//	
//}
