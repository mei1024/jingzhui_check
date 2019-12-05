package com.solar.cms.web.controller;

import java.util.List;

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
import com.nebula.common.web.util.WebUtils;
import com.nebula.user.User;
import com.nebula.user.UserContext;
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.dto.CmsVideoDto;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.enums.CmsPvLogEnum;
import com.solar.cms.enums.CmsVideoEnum;
import com.solar.cms.privilege.CmsResourcePriv;
import com.solar.cms.query.CmsVideoQuery;
import com.solar.cms.service.CmsExpertVideoService;
import com.solar.cms.service.CmsPvLogService;
import com.solar.cms.service.CmsVideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "[cms]视频", tags = { "cms" })
@RestController
@RequestMapping("/cms/video")
public class CmsVideoController {
	
	@Autowired
	private CmsVideoService cmsVideoService;
	
	@Autowired
	private CmsExpertVideoService cmsExpertVideoService;
	
	@Autowired
	private CmsPvLogService cmsPvLogService;	
	
	@ApiOperation(value = "视频查询（展现端）", httpMethod = "GET", notes = "")
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsVideoDto>> query(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "expertId", value = "专家ID", required = false) @RequestParam(value = "expertId", required = false) String expertId,
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsVideoQuery query = new CmsVideoQuery();
		query.setCategoryId(categoryId);
		query.setKeyword(keyword);
		query.setStatus(CmsContentEnum.Status.PUBLISHED.getCode());	
		query.setVstatus(CmsVideoEnum.VStatus.NORMAL.getCode());
		query.setTagId(tagId);
		query.setExpertId(expertId);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsVideoDto> pagination = cmsVideoService.queryPageCmsVideo(query);

		return Responses.newOK(pagination);
	}
	
	@ApiOperation(value = "视频查询（管理端）", httpMethod = "GET", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_VIDEO_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Pagination<CmsVideoDto>> list(
			@ApiParam(name = "categoryId", value = "分类ID", required = false) @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(name = "expertId", value = "专家ID", required = false) @RequestParam(value = "expertId", required = false) String expertId,
			@ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "tagId", value = "标签ID", required = false) @RequestParam(value = "tagId", required = false) String tagId,
			@ApiParam(name = "sortby", value = "指定返回结果按照哪个属性排序", required = false) @RequestParam(value = "sortby", required = false) String sortby,
			@ApiParam(name = "order", value = "排序顺序 desc asc", required = false) @RequestParam(value = "order", required = false) String order,
			@ApiParam(name = "num", value = "当前页数", required = false) @RequestParam(value = "num", required = false, defaultValue = "1") int num,
			@ApiParam(name = "size", value = "指定返回记录的数量,默认15", required = false) @RequestParam(value = "size", required = false, defaultValue = "15") int pageSize) {

		CmsVideoQuery query = new CmsVideoQuery();
		query.setCategoryId(categoryId);
		query.setKeyword(keyword);
		query.setTagId(tagId);
		query.setExpertId(expertId);
		query.setOrder(order);
		query.setSortby(sortby);
		query.setCount(pageSize);
		query.setPage(num);
		
		Pagination<CmsVideoDto> pagination = cmsVideoService.queryPageCmsVideo(query);

		return Responses.newOK(pagination);
	}

	@ApiOperation(value = "视频创建", httpMethod = "POST", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_VIDEO_MANAGER+"')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsVideoDto> create(
			HttpServletRequest request,
			@RequestBody @ApiParam(name = "视频创建对象", required = true) CmsVideoDto cmsVideo) {
		
		cmsVideo.setUserId(UserContext.get().getUserId());
		cmsVideo.setUploadIp(WebUtils.getIp(request));
		CmsVideoDto result = cmsVideoService.saveCmsVideo(cmsVideo);
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "视频删除", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_VIDEO_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> delete(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id) {
		
		cmsVideoService.deleteCmsVideoById(id);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "视频修改", httpMethod = "PATCH", notes = "")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_VIDEO_MANAGER+"')")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> update(
			HttpServletRequest request,
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			@RequestBody @ApiParam(name = "视频修改对象", required = true) CmsVideoDto cmsVideo) {
		
		cmsVideo.setId(id);
		cmsVideo.setUserId(UserContext.get().getUserId());
		cmsVideo.setUploadIp(WebUtils.getIp(request));
		
		cmsVideoService.updateCmsVideoById(cmsVideo);
		
		return Responses.newOK();
	}
	
	@ApiOperation(value = "视频信息", httpMethod = "GET", notes = "")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<CmsVideoDto> get(
			@ApiParam(name = "id", value = "ID编号", required = true) @PathVariable(value = "id", required = true) String id,
			HttpServletRequest request
			) {
		
		CmsVideoDto result = cmsVideoService.queryCmsVideoById(id);
		
		// 记录访问日志,后台访问用户忽略
        String ua = request.getHeader("user-agent");
		if (ua.contains("MicroMessenger")) {
			User user = UserContext.get();
			CmsPvLogDto cmsPvLog = new CmsPvLogDto();
			cmsPvLog.setObjectId(id);
			cmsPvLog.setObjectType(CmsPvLogEnum.ObjectType.VIDEO.name());
			cmsPvLog.setUserId(user != null ? user.getUserId() : "0");

			cmsPvLogService.saveCmsPvLog(cmsPvLog);
		}
		
		return Responses.newOK(result);
	}
	
	@ApiOperation(value = "单个视频分配多个专家", httpMethod = "POST", notes = "传入：expertId,videoIds")
	@PreAuthorize("hasPermission(filterObject, 'ROLE_ADMIN')  or hasPermission(filterObject, '"+CmsResourcePriv.CMS_EXPERT_MANAGER+"')")
	@RequestMapping(value = "{id}/expert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> videoSave(
			@ApiParam(name = "videoId", value = "视频ID", required = true) @PathVariable(value = "videoId", required = true) String videoId,
			@ApiParam(name = "expertIds", value = "专家ID集合", required = false) @RequestParam(value = "expertIds", required = true) List<String> expertIds
		) {
		
		cmsExpertVideoService.saveCmsExpertVideo(expertIds, videoId);
		
		return Responses.newOK();
	}
	
	
}
