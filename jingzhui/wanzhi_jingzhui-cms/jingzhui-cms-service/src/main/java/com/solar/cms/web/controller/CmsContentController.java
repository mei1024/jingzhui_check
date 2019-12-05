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
import com.nebula.user.UserContext;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsContentQuery;
import com.solar.cms.service.CmsContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]内容", tags = { "cms" })
@RestController
@RequestMapping("/cms/content")
public class CmsContentController {
	
	@Autowired
	private CmsContentService cmsContentService;
	
	@ApiOperation(value = "内容查询（展现端）", httpMethod = "GET", notes = "")
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsContentDto>> query(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false) @RequestParam(value = "minDate", required = false) String minDate,
			@ApiParam(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false) @RequestParam(value = "maxDate", required = false) String maxDate,						
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsContentQuery query = new CmsContentQuery();
		query.setKeyword(keyword);
		query.setCategoryId(categoryId);
		query.setStatus(CmsContentEnum.Status.PUBLISHED.getCode());
		query.setTagId(tagId);
		query.setMinDate(minDate);
		query.setMaxDate(maxDate);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsContentDto> pagination = cmsContentService.queryPageCmsContent(query);

		return Responses.newOK(pagination);
	}
	
	@ApiOperation(value = "内容查询（管理端）", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CONTENT_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsContentDto>> list(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "status", value = "状态：0、草稿 1、已发布 2、待审核", required = false) @RequestParam(value = "status", required = false) String status,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false) @RequestParam(value = "minDate", required = false) String minDate,
			@ApiParam(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false) @RequestParam(value = "maxDate", required = false) String maxDate,						
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsContentQuery query = new CmsContentQuery();
		query.setKeyword(keyword);
		query.setCategoryId(categoryId);
		query.setStatus(status);
		query.setTagId(tagId);
		query.setMinDate(minDate);
		query.setMaxDate(maxDate);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsContentDto> pagination = cmsContentService.queryPageCmsContent(query);

		return Responses.newOK(pagination);
	}
	
	@ApiOperation(value = "内容创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CONTENT_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsContentDto> create(
			@RequestBody @ApiParam(name = "内容创建对象", required = true) CmsContentDto cmsContent) {
		
		cmsContent.setUserId(UserContext.get().getUserId());
		CmsContentDto result = cmsContentService.saveCmsContent(cmsContent);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "内容删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CONTENT_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsContentService.deleteCmsContentById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "内容修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CONTENT_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "内容对象", required = true) CmsContentDto cmsContent) {
		
		cmsContent.setId(id);
		cmsContent.setUserId(UserContext.get().getUserId());
		cmsContentService.updateCmsContentById(cmsContent);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "内容信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsContentDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsContentDto result = cmsContentService.queryCmsContentById(id);
		
		return Responses.newOK(result);
	}

	@ApiOperation(value = "单分类内容维护", httpMethod = "POST", notes = "")
	@RequestMapping(value = "category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_CONTENT_MANAGER+"')")
	public Response<String> updateCategoryContent(
			@RequestBody @ApiParam(name = "内容创建对象", required = true) CmsContentDto cmsContent) {
		
		cmsContent.setUserId(UserContext.get().getUserId());
		cmsContent.setStatus(CmsContentEnum.Status.PUBLISHED.getCode());
		CmsContentDto categoryContent = cmsContentService.queryCmsContentByCategoryId(cmsContent.getCategoryId());
		if (categoryContent != null) {
			cmsContent.setId(categoryContent.getId());
			cmsContentService.updateCmsContentById(cmsContent);
		} else {
			cmsContentService.saveCmsContent(cmsContent);
		}
		
		return Responses.newOK();
	}	
	
	@ApiOperation(value = "单分类内容信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsContentDto> getCategoryContent(
			@ApiParam(name = "categoryId", value = "分类ID", required = true) @RequestParam(value = "categoryId", required = true) String categoryId) {
		
		CmsContentDto categoryContent = cmsContentService.queryCmsContentByCategoryId(categoryId);
		
		return Responses.newOK(categoryContent);
	}
	
}
