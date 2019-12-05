package com.solar.bi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.common.util.Pagination;
import com.nebula.common.web.http.protocol.Response;
import com.nebula.common.web.http.protocol.Responses;
import com.nebula.user.UserContext;
import com.solar.bi.dto.CheckRecordCreateDto;
import com.solar.bi.dto.CheckRecordDto;
import com.solar.bi.dto.CountCheckRecord;
import com.solar.bi.query.CheckRecordQuery;
import com.solar.bi.service.CheckRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[bi]检查记录", tags = { "bi" })
@RestController
@RequestMapping("/bi/check/record")
public class CheckRecordController {
	
	@Autowired
	private CheckRecordService checkRecordService;
	
	@ApiOperation(value = "检查记录查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CheckRecordDto>> list(
			@ApiParam(name = "checkType", value = "检查类型 JZ:颈椎 YZ:腰椎", required = false) @RequestParam(value = "checkType", required = false) String checkType,
			@ApiParam(name = "order", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "sortby", value = "排序顺序 desc asc", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CheckRecordQuery query = new CheckRecordQuery();
		query.setCheckType(checkType);
		query.setUserId(UserContext.get().getUserId());
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CheckRecordDto> pagination = checkRecordService.queryPageCheckRecord(query);

		return Responses.newOK(pagination);
	}
	
	@ApiOperation(value = "检查次数统计", httpMethod = "GET", notes = "")
	@RequestMapping(value = "count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CountCheckRecord> count(
			@ApiParam(name = "checkType", value = "检查类型 JZ:颈椎 YZ:腰椎", required = true) @RequestParam(value = "checkType", required = true) String checkType) {
		
		CountCheckRecord result = checkRecordService.countCheckRecord(checkType);

		return Responses.newOK(result);
	}

	@ApiOperation(value = "检查记录创建", httpMethod = "POST", notes = "")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CheckRecordDto> create(
			@RequestBody @ApiParam(name = "检查记录创建对象", required = true) CheckRecordCreateDto checkRecord) {
		
		CheckRecordDto result = checkRecordService.saveCheckRecord(checkRecord);
		return Responses.newOK(result);
	}
	
}
