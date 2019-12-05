package com.solar.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.common.util.Pagination;
import com.nebula.common.web.http.protocol.Response;
import com.nebula.common.web.http.protocol.Responses;
import com.solar.cms.dto.CmsTagTypeDto;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsTagTypeQuery;
import com.solar.cms.service.CmsTagTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]标签类型", tags = { "cms" })
@RestController
@RequestMapping("/cms/tag/type")
public class CmsTagTypeController {
	
	@Autowired
	private CmsTagTypeService cmsTagTypeService;
	
	@ApiOperation(value = "标签类型查询", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsTagTypeDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsTagTypeQuery query = new CmsTagTypeQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsTagTypeDto> pagination = cmsTagTypeService.queryPageCmsTagType(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "标签类型创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsTagTypeDto> create(
			@RequestBody @ApiParam(name = "标签类型创建对象", required = true) CmsTagTypeDto cmsTagType) {
		
		CmsTagTypeDto result = cmsTagTypeService.saveCmsTagType(cmsTagType);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "标签类型删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsTagTypeService.deleteCmsTagTypeById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "标签类型修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "标签类型修改对象", required = true) CmsTagTypeDto cmsTagType) {
		
		cmsTagType.setId(id);
		cmsTagTypeService.updateCmsTagTypeById(cmsTagType);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "标签类型信息", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsTagTypeDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsTagTypeDto result = cmsTagTypeService.queryCmsTagTypeById(id);
		
		return Responses.newOK(result);
	}
	
}
