package com.solar.job.web.controller;

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
import com.solar.job.dto.JobOnceTimerDto;
import com.solar.job.query.JobOnceTimerQuery;
import com.solar.job.service.JobOnceTimerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[JOB]一次性定时器", tags = { "JOB" })
@RestController
@RequestMapping("/timer/once")
public class JobOnceTimerController {
	
	@Autowired
	private JobOnceTimerService jobOnceTimerService;
	
	@ApiOperation(value = "一次性定时器查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<JobOnceTimerDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		JobOnceTimerQuery query = new JobOnceTimerQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<JobOnceTimerDto> pagination = jobOnceTimerService.queryPageJobOnceTimer(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "一次性定时器创建", httpMethod = "POST", notes = "")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<JobOnceTimerDto> create(
			@RequestBody @ApiParam(name = "一次性定时器创建对象", required = true) JobOnceTimerDto jobOnceTimer) {
		JobOnceTimerDto result = jobOnceTimerService.saveJobOnceTimer(jobOnceTimer);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "一次性定时器删除", httpMethod = "DELETE", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		jobOnceTimerService.deleteJobOnceTimerById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "一次性定时器修改", httpMethod = "PUT", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "一次性定时器修改对象", required = true) JobOnceTimerDto jobOnceTimer) {
		
		jobOnceTimer.setId(id);
		jobOnceTimerService.updateJobOnceTimerById(jobOnceTimer);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "一次性定时器信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<JobOnceTimerDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		JobOnceTimerDto result = jobOnceTimerService.queryJobOnceTimerById(id);
		
		return Responses.newOK(result);
	}
	
}
