package com.solar.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.nebula.user.User;
import com.nebula.user.UserContext;
import com.solar.cms.dto.CmsNewsDto;
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.enums.CmsPvLogEnum;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsNewsQuery;
import com.solar.cms.service.CmsNewsService;
import com.solar.cms.service.CmsPvLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]咨讯", tags = { "cms" })
@RestController
@RequestMapping("/cms/news")
public class CmsNewsController {
	
	@Autowired
	private CmsNewsService cmsNewsService;
	
	@Autowired
	private CmsPvLogService cmsPvLogService;	
	
	@ApiOperation(value = "咨讯查询（展现端）", httpMethod = "GET", notes = "")
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsNewsDto>> query(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "provinceId", value = "省份ID", required = false) @RequestParam(value = "provinceId", required = false) String provinceId,
			@ApiParam(name = "cityId", value = "市ID", required = false) @RequestParam(value = "cityId", required = false) String cityId,
			@ApiParam(name = "districtId", value = "区ID", required = false) @RequestParam(value = "districtId", required = false) String districtId,
			@ApiParam(name = "villagesId", value = "乡镇ID", required = false) @RequestParam(value = "villagesId", required = false) String villagesId,
			@ApiParam(name = "hamletId", value = "村ID", required = false) @RequestParam(value = "hamletId", required = false) String hamletId,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {
		
		CmsNewsQuery query = new CmsNewsQuery();
		query.setCategoryId(categoryId);
		query.setKeyword(keyword);
		query.setStatus(CmsContentEnum.Status.PUBLISHED.getCode());
		query.setProvinceId(provinceId);
		query.setCityId(cityId);
		query.setDistrictId(districtId);
		query.setVillagesId(villagesId);
		query.setHamletId(hamletId);
		query.setTagId(tagId);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsNewsDto> pagination = cmsNewsService.queryPageCmsNews(query);

		return Responses.newOK(pagination);
	}
	
	@ApiOperation(value = "咨讯查询（管理端）", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_NEWS_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsNewsDto>> list(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "status", value = "状态：0、草稿 1、已发布 2、待审核", required = false) @RequestParam(value = "status", required = false) String status,
			@ApiParam(name = "provinceId", value = "省份ID", required = false) @RequestParam(value = "provinceId", required = false) String provinceId,
			@ApiParam(name = "cityId", value = "市ID", required = false) @RequestParam(value = "cityId", required = false) String cityId,
			@ApiParam(name = "districtId", value = "区ID", required = false) @RequestParam(value = "districtId", required = false) String districtId,
			@ApiParam(name = "villagesId", value = "乡镇ID", required = false) @RequestParam(value = "villagesId", required = false) String villagesId,
			@ApiParam(name = "hamletId", value = "村ID", required = false) @RequestParam(value = "hamletId", required = false) String hamletId,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "minDate", value = "查询开始日期 YYYY-MM-DD", required = false) @RequestParam(value = "minDate", required = false) String minDate,
			@ApiParam(name = "maxDate", value = "查询结束日期 YYYY-MM-DD", required = false) @RequestParam(value = "maxDate", required = false) String maxDate,			
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsNewsQuery query = new CmsNewsQuery();
		query.setCategoryId(categoryId);
		query.setKeyword(keyword);
		query.setStatus(status);
		query.setProvinceId(provinceId);
		query.setCityId(cityId);
		query.setDistrictId(districtId);
		query.setVillagesId(villagesId);
		query.setHamletId(hamletId);
		query.setTagId(tagId);
		query.setMinDate(minDate);
		query.setMaxDate(maxDate);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsNewsDto> pagination = cmsNewsService.queryPageCmsNews(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "咨讯创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_NEWS_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsNewsDto> create(
			@RequestBody @ApiParam(name = "咨讯创建对象", required = true) CmsNewsDto cmsNews) {
		
		cmsNews.setUserId(UserContext.get().getUserId());
		
		CmsNewsDto result = cmsNewsService.saveCmsNews(cmsNews);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "咨讯删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_NEWS_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsNewsService.deleteCmsNewsById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "咨讯修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_NEWS_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "咨讯修改对象", required = true) CmsNewsDto cmsNews) {
		
		cmsNews.setId(id);
		cmsNews.setUserId(UserContext.get().getUserId());
		cmsNewsService.updateCmsNewsById(cmsNews);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "咨讯信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsNewsDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			HttpServletRequest request
			) {
		
		CmsNewsDto result = cmsNewsService.queryCmsNewsById(id);
		
		// 记录访问日志,后台访问用户忽略
        String ua = request.getHeader("user-agent");
		if (ua.contains("MicroMessenger")) {
			User user = UserContext.get();
			CmsPvLogDto cmsPvLog = new CmsPvLogDto();
			cmsPvLog.setObjectId(id);
			cmsPvLog.setObjectType(CmsPvLogEnum.ObjectType.NEWS.name());
			cmsPvLog.setUserId(user != null ? user.getUserId() : "0");

			cmsPvLogService.saveCmsPvLog(cmsPvLog);
		}
		
		return Responses.newOK(result);
	}
	
}
