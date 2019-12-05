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
import com.solar.cms.dto.CmsAdDto;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsAdQuery;
import com.solar.cms.service.CmsAdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]广告", tags = { "cms" })
@RestController
@RequestMapping("/cms/ad")
public class CmsAdController {
	
	@Autowired
	private CmsAdService cmsAdService;
	
	@ApiOperation(value = "广告展示", httpMethod = "GET", notes = "")
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<CmsAdDto>> query(
			@ApiParam(name = "type", value = "类型: WEBSITE_HOME_BANNER=网站首页轮播，WXMA_HOME_BANNER=小程序首页轮播", required = true) @RequestParam(value = "type", required = true) String type			
		) {
		
		List<CmsAdDto> list = cmsAdService.queryCmsAdListByType(type);

		return Responses.newOK(list);
	}

	@ApiOperation(value = "广告查询", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_AD_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsAdDto>> list(
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "type", value = "类型: HOME_BANNER=首页轮播", required = false) @RequestParam(value = "type", required = false) String type,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsAdQuery query = new CmsAdQuery();
		query.setKeyword(keyword);
		query.setType(type);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsAdDto> pagination = cmsAdService.queryPageCmsAd(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "广告创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_AD_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsAdDto> create(
			@RequestBody @ApiParam(name = "广告创建对象", required = true) CmsAdDto cmsAd) {
		CmsAdDto result = cmsAdService.saveCmsAd(cmsAd);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "广告删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_AD_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsAdService.deleteCmsAdById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "广告修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_AD_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "广告修改对象", required = true) CmsAdDto cmsAd) {
		
		cmsAd.setId(id);
		cmsAdService.updateCmsAdById(cmsAd);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "广告信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsAdDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		CmsAdDto result = cmsAdService.queryCmsAdById(id);
		
		return Responses.newOK(result);
	}
	
}
