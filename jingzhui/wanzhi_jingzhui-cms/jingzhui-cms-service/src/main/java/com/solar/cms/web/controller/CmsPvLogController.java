package com.solar.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.common.util.Pagination;
import com.nebula.common.web.http.protocol.Response;
import com.nebula.common.web.http.protocol.Responses;
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.query.CmsPvLogQuery;
import com.solar.cms.service.CmsPvLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]PV记录", tags = { "cms" })
@RestController
@RequestMapping("/cms/pv")
public class CmsPvLogController {
	
	@Autowired
	private CmsPvLogService cmsPvLogService;
	
	@ApiOperation(value = "PV记录查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsPvLogDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsPvLogQuery query = new CmsPvLogQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsPvLogDto> pagination = cmsPvLogService.queryPageCmsPvLog(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "PV记录删除", httpMethod = "DELETE", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsPvLogService.deleteCmsPvLogById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "PV记录修改", httpMethod = "PATCH", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "PV记录修改对象", required = true) CmsPvLogDto cmsPvLog) {
		
		cmsPvLog.setId(id);
		cmsPvLogService.updateCmsPvLogById(cmsPvLog);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "PV记录信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsPvLogDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsPvLogDto result = cmsPvLogService.queryCmsPvLogById(id);
		
		return Responses.newOK(result);
	}
	
}
