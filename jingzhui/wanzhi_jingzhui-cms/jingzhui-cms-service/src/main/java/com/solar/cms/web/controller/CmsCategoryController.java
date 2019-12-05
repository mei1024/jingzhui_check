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
import com.solar.cms.dto.CmsCategoryDto;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsCategoryQuery;
import com.solar.cms.service.CmsCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]分类", tags = { "cms" })
@RestController
@RequestMapping("/cms/category")
public class CmsCategoryController {
	
	@Autowired
	private CmsCategoryService cmsCategoryService;
	
	@ApiOperation(value = "根据父ID获取子分类", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}/children", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<CmsCategoryDto>> children(
			@ApiParam(name = "id", value = "父分类ID", required = true) @PathVariable(value = "id", required = true) String id
			) {
		
		List<CmsCategoryDto> list = cmsCategoryService.queryCmsCategoryListByParentId(id);

		return Responses.newOK(list);
	}

	@ApiOperation(value = "分类查询", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CATEGORY_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsCategoryDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsCategoryQuery query = new CmsCategoryQuery();
		query.setKeyword(keyword);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsCategoryDto> pagination = cmsCategoryService.queryPageCmsCategory(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "分类创建", httpMethod = "POST", notes = "主要传入字段：名称,父分类ID")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CATEGORY_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsCategoryDto> create(
			@RequestBody @ApiParam(name = "分类创建对象", required = true) CmsCategoryDto cmsCategory) {
		CmsCategoryDto result = cmsCategoryService.saveCmsCategory(cmsCategory);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "分类删除", httpMethod = "DELETE", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CATEGORY_MANAGER+"')")
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsCategoryService.deleteCmsCategoryById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "分类修改", httpMethod = "PUT", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CATEGORY_MANAGER+"')")
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "分类修改对象", required = true) CmsCategoryDto cmsCategory) {
		
		cmsCategory.setId(id);
		cmsCategoryService.updateCmsCategoryById(cmsCategory);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "分类信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CATEGORY_MANAGER+"')")
	public Response<CmsCategoryDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsCategoryDto result = cmsCategoryService.queryCmsCategoryById(id);
		
		return Responses.newOK(result);
	}
	
	
}
