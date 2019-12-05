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
import com.solar.cms.dto.QaCategroyDto;
import com.solar.cms.query.QaCategroyQuery;
import com.solar.cms.service.QaCategroyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]问题分类", tags = { "cms" })
@RestController
@RequestMapping("/qa/categroy")
public class QaCategroyController {
	
	@Autowired
	private QaCategroyService qaCategroyService;
	
	@ApiOperation(value = "问题分类查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<QaCategroyDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		QaCategroyQuery query = new QaCategroyQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<QaCategroyDto> pagination = qaCategroyService.queryPageQaCategroy(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "问题分类创建", httpMethod = "POST", notes = "")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<QaCategroyDto> create(
			@RequestBody @ApiParam(name = "问题分类创建对象", required = true) QaCategroyDto qaCategroy) {
		
		QaCategroyDto result = qaCategroyService.saveQaCategroy(qaCategroy);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "问题分类删除", httpMethod = "DELETE", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		qaCategroyService.deleteQaCategroyById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "问题分类修改", httpMethod = "PUT", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "问题分类修改对象", required = true) QaCategroyDto qaCategroy) {
		
		qaCategroy.setId(id);
		qaCategroyService.updateQaCategroyById(qaCategroy);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "问题分类信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<QaCategroyDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		QaCategroyDto result = qaCategroyService.queryQaCategroyById(id);
		
		return Responses.newOK(result);
	}
	
}
