package com.solar.cms.web.controller;

import java.util.List;

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
import com.solar.cms.dto.CmsTagDto;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsTagQuery;
import com.solar.cms.service.CmsTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]标签", tags = { "cms" })
@RestController
@RequestMapping("/cms/tag")
public class CmsTagController {
	
	@Autowired
	private CmsTagService cmsTagService;
	
	@ApiOperation(value = "根据标签类型查询", httpMethod = "GET", notes = "")
	@RequestMapping(value = "bytype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<CmsTagDto>> bytype(
			@ApiParam(name = "typeId", value = "标签类型ID", required = true) @RequestParam(value = "typeId", required = true) String typeId,
			@ApiParam(name = "hot", value = "1=热门", required = false) @RequestParam(value = "hot", required = false) String hot
			) {
		
		List<CmsTagDto> list = cmsTagService.queryCmsTagListByTypeId(typeId);

		return Responses.newOK(list);
	}

	@ApiOperation(value = "标签查询", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsTagDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsTagQuery query = new CmsTagQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsTagDto> pagination = cmsTagService.queryPageCmsTag(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "标签创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsTagDto> create(
			@RequestBody @ApiParam(name = "标签创建对象", required = true) CmsTagDto cmsTag) {
		CmsTagDto result = cmsTagService.saveCmsTag(cmsTag);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "标签删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsTagService.deleteCmsTagById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "标签修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "标签修改对象", required = true) CmsTagDto cmsTag) {
		
		cmsTag.setId(id);
		cmsTagService.updateCmsTagById(cmsTag);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "标签信息", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_TAG_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsTagDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsTagDto result = cmsTagService.queryCmsTagById(id);
		
		return Responses.newOK(result);
	}
	
}
