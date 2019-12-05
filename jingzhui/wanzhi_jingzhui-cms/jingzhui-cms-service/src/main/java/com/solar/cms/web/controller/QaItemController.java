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
import com.solar.cms.dto.QaItemDto;
import com.solar.cms.query.QaItemQuery;
import com.solar.cms.service.QaItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]问题答案", tags = { "cms" })
@RestController
@RequestMapping("/qa/item")
public class QaItemController {
	
	@Autowired
	private QaItemService qaItemService;
	
	@ApiOperation(value = "问题答案查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<QaItemDto>> list(
			@ApiParam(name = "categroyId", value = "问题分类ID", required = false) @RequestParam(value = "categroyId", required = false) String categroyId,
			@ApiParam(name = "sortby", value = "排序顺序 desc asc", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		QaItemQuery query = new QaItemQuery();
		query.setCategroyId(categroyId);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<QaItemDto> pagination = qaItemService.queryPageQaItem(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "问题答案创建", httpMethod = "POST", notes = "")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<QaItemDto> create(
			@RequestBody @ApiParam(name = "问题答案创建对象", required = true) QaItemDto qaItem) {
		
		QaItemDto result = qaItemService.saveQaItem(qaItem);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "问题答案删除", httpMethod = "DELETE", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		qaItemService.deleteQaItemById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "问题答案修改", httpMethod = "PUT", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "问题答案修改对象", required = true) QaItemDto qaItem) {
		
		qaItem.setId(id);
		qaItemService.updateQaItemById(qaItem);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "问题答案信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<QaItemDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		QaItemDto result = qaItemService.queryQaItemById(id);
		
		return Responses.newOK(result);
	}
	
}
