package com.solar.cms.service;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.security.util.SHA1Util;
import com.nebula.common.util.Check;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.nebula.sequence.SequenceService;
import com.solar.cms.converter.CmsContentConverterFactory;
import com.solar.cms.dal.crud.CmsContentCrudService;
import com.solar.cms.dal.crud.CmsContentFileCrudService;
import com.solar.cms.dal.dao.ext.CmsContentExtDao;
import com.solar.cms.dto.CmsContentAttributeDto;
import com.solar.cms.dto.CmsContentDto;
import com.solar.cms.entity.CmsContent;
import com.solar.cms.entity.CmsContentFile;
import com.solar.cms.enums.CmsContentEnum;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.query.CmsContentQuery;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.ds.dal.crud.FileInfoCrudService;
import com.solar.ds.entity.FileInfo;

/**
 * 内容 CmsContent 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181022
 * 
 */
@Service("cmsContentService")
public class CmsContentServiceImpl extends BaseServiceImpl implements CmsContentService {
	
	@Autowired
	private CmsContentCrudService cmsContentCrudService;
	
	@Autowired
	private CmsContentAttributeService cmsContentAttributeService;

	@Autowired
	private CmsContentFileCrudService cmsContentFileCrudService;

	@Autowired
	private FileInfoCrudService fileInfoCrudService;
	
	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private CmsContentExtDao cmsContentExtDao;
	
	@Override
	public CmsContentDto queryCmsContentById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsContent cmsContent = cmsContentCrudService.queryById(id);
		if (cmsContent == null) {
			return null;
		}
		
		return CmsContentConverterFactory.convertCmsContentDTO(cmsContent);
	}
	
	@Override
	public CmsContentDto queryCmsContentByCategoryId(String categoryId) throws BizException {
		BizAssert.notEmpty(categoryId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
		
		CmsContent cmsContent = cmsContentCrudService.queryOneByProperty("category_id", categoryId);
		if (cmsContent == null) {
			return null;
		}
		
		return CmsContentConverterFactory.convertCmsContentDTO(cmsContent);
	}
	
	@Override
	public List<CmsContentDto> queryCmsContentAllList() throws BizException {
		List<CmsContent> list = cmsContentCrudService.queryAllList();

		return CmsContentConverterFactory.convertCmsContentListDTO(list);
	}
	
	@Override
	public List<CmsContentDto> queryCmsContentList(final CmsContentQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("categoryId", query.getCategoryId());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("tagId", query.getTagId());
		search.safeAddParamter("status", query.getStatus());
		
		List<CmsContent> list = cmsContentCrudService.queryList(search.getParameters());
		
		return CmsContentConverterFactory.convertCmsContentListDTO(list);
	}
	
	@Override
	public Pagination<CmsContentDto> queryPageCmsContent(final CmsContentQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("categoryId", query.getCategoryId());
		search.safeAddParamter("keyword", query.getKeyword());
		search.safeAddParamter("tagId", query.getTagId());
		search.safeAddParamter("status", query.getStatus());
		try {
			if (StringUtils.isNotEmpty(query.getMinDate()) && StringUtils.isNotEmpty(query.getMaxDate())) {
				Date startDate = DateUtils.parseDate(query.getMinDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getMaxDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("minCreateDate", startDate.getTime());
				search.safeAddParamter("maxCreateDate", endDate.getTime());
			}
		} catch (ParseException e) {
		}
		
		// query list
		List<CmsContent> list = cmsContentExtDao.queryPage(getContextParams(), search.getParameters(), search.offset(), search.getCount(), "publish_date", SortByEnum.DESC.name());
		// query totals
		int totals = cmsContentExtDao.count(getContextParams(), search.getParameters());
		if (totals > 0 && CollectionUtils.isEmpty(list)) {
			search.setPage(1);
			list = cmsContentExtDao.queryPage(getContextParams(), search.getParameters(), 1, search.getCount(), "publish_date", SortByEnum.DESC.name());
		}
		List<CmsContentDto> resultList = CmsContentConverterFactory.convertCmsContentListDTO(list);
		
		return new Pagination<CmsContentDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsContentDto saveCmsContent(final CmsContentDto cmsContent) throws BizException {
		BizAssert.notNull(cmsContent, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContent为空");
		BizAssert.notEmpty(cmsContent.getTitle(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:title为空");
		BizAssert.notEmpty(cmsContent.getUserId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:userId为空");
		BizAssert.notEmpty(cmsContent.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
//		BizAssert.notEmpty(cmsContent.getText(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:text为空");
//		BizAssert.notEmpty(cmsContent.getCopied(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:copied为空");
//		BizAssert.notEmpty(cmsContent.getOnlyUrl(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:onlyUrl为空");
		BizAssert.notNull(CmsContentEnum.Status.get(cmsContent.getStatus()), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");

		return (CmsContentDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (StringUtils.isEmpty(cmsContent.getCopied())) {
					cmsContent.setCopied("N");
				}
				if (StringUtils.isEmpty(cmsContent.getOnlyUrl())) {
					cmsContent.setOnlyUrl("N");
				}
				if (StringUtils.isEmpty(cmsContent.getHasImages())) {
					cmsContent.setHasImages("N");
				}
				if (StringUtils.isEmpty(cmsContent.getHasFiles())) {
					cmsContent.setHasFiles("N");
				}
				if (StringUtils.isEmpty(cmsContent.getHasStatic())) {
					cmsContent.setHasStatic("N");
				}
				if (StringUtils.isEmpty(cmsContent.getText())) {
					cmsContent.setText("");
				}
				if (cmsContent.getSortno() == null) {
					cmsContent.setSortno(99);
				}
				if (cmsContent.getWordCount() == null) {
					cmsContent.setWordCount(0);
				}
			}
			
			@Override
			public Object execute() throws BizException {
				// 1.内容信息保存
				String contentId = SHA1Util.encode(sequenceService.getLongNextVal(CmsSequenceKeyEnum.CMS_CONTENT_ID.getKey()) + "");
				CmsContent createModel = CmsContentConverterFactory.convertCmsContentEntity(cmsContent);
				
				createModel.setId(contentId);
				if (CollectionUtils.isNotEmpty(cmsContent.getAttachmentIds())) {
					createModel.setHasFiles("Y");
				}
				createModel.setComments(0);
				createModel.setClicks(0);
				createModel.setPublishDate(new Date());
				cmsContentCrudService.save(createModel);
				
				// 2.扩展内容保存
				CmsContentAttributeDto contentAttribute = new CmsContentAttributeDto();
				contentAttribute.setId(createModel.getId());
				contentAttribute.setSource(cmsContent.getSource());
				contentAttribute.setSourceUrl(cmsContent.getSourceUrl());
				contentAttribute.setText(cmsContent.getText());
				contentAttribute.setWordCount(cmsContent.getWordCount());
				cmsContentAttributeService.saveCmsContentAttribute(contentAttribute);
				
				// 3.内容附件保存
				saveAttachment(createModel.getId(), cmsContent.getAttachmentIds());
				
				// 4.验证是否直接发布
				if (StringUtils.equalsIgnoreCase(cmsContent.getStatus(), CmsContentEnum.Status.PUBLISHED.getCode())) {
					publishContent(contentId, cmsContent.getUserId());
				}

				CmsContentDto result = CmsContentConverterFactory.convertCmsContentDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsContentById(final CmsContentDto cmsContent) throws BizException {
		BizAssert.notNull(cmsContent, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContent为空");
		BizAssert.notEmpty(cmsContent.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(cmsContent.getTitle(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:title为空");
		BizAssert.notEmpty(cmsContent.getUserId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:userId为空");
//		BizAssert.notEmpty(cmsContent.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
//		BizAssert.notEmpty(cmsContent.getText(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:text为空");
//		BizAssert.notEmpty(cmsContent.getCopied(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:copied为空");
		//BizAssert.notEmpty(cmsContent.getOnlyUrl(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:onlyUrl为空");
		BizAssert.notNull(CmsContentEnum.Status.get(cmsContent.getStatus()), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:status为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentCrudService.queryById(cmsContent.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsContent.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1.内容信息修改保存
				CmsContent updateContentModel = cmsContentCrudService.queryById(cmsContent.getId());
				updateContentModel.setTitle(cmsContent.getTitle());
				updateContentModel.setDescription(cmsContent.getDescription());
				updateContentModel.setCover(cmsContent.getCover());
				updateContentModel.setHasFiles("N");
				if (CollectionUtils.isNotEmpty(cmsContent.getAttachmentIds())) {
					updateContentModel.setHasFiles("Y");
				}
				updateContentModel.setTagIds(cmsContent.getTagIds());
				updateContentModel.setStatus(cmsContent.getStatus());
				cmsContentCrudService.update(updateContentModel);
 
				// 2.扩展内容保存
				CmsContentAttributeDto contentAttribute = cmsContentAttributeService.queryCmsContentAttributeById(cmsContent.getId());
				contentAttribute.setSource(cmsContent.getSource());
				contentAttribute.setSourceUrl(cmsContent.getSourceUrl());
				contentAttribute.setText(cmsContent.getText());
				contentAttribute.setWordCount(cmsContent.getWordCount());
				cmsContentAttributeService.updateCmsContentAttributeById(contentAttribute);
				
				// 3.内容附件保存 先进行删除
				cmsContentFileCrudService.deleteByProperty("content_id", cmsContent.getId());
				saveAttachment(cmsContent.getId(), cmsContent.getAttachmentIds());
				
				// 4.验证是否直接发布
				if (StringUtils.equalsIgnoreCase(cmsContent.getStatus(), CmsContentEnum.Status.PUBLISHED.getCode())) {
					publishContent(cmsContent.getId(), cmsContent.getUserId());
				}
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsContentById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				// 1. 删除内容扩展属性
				cmsContentAttributeService.deleteCmsContentAttributeById(id);

				// 2. 删除内容附件
				Map<String, Object> deleteContentFileParamter = new HashMap<>();
				deleteContentFileParamter.put("contentId", id);
				cmsContentFileCrudService.deleteByCondition(deleteContentFileParamter);
				
				// 3. 删除内容
				int result = cmsContentCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsContentByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsContentById(id);
		}
	}
	
	@Override
	public void publishContent(final String id, final String checkUserId) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		BizAssert.notEmpty(checkUserId, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkUserId为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsContentCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				CmsContent content = new CmsContent();
				content.setId(id);
				content.setStatus(CmsContentEnum.Status.PUBLISHED.getCode());
				content.setCheckDate(new Date());
				content.setCheckUserId(checkUserId);
				
				cmsContentCrudService.update(content);
				return true;
			}
			
		});		
	}

	@Override
	public void sortContent(String[] idArray, String[] sortNoArray) throws BizException {
		BizAssert.notNull(idArray, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:idArray为空");
		BizAssert.notNull(sortNoArray, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:sortNoArray为空");

		if (idArray.length < 0 || idArray.length != sortNoArray.length) {
			throw new BizException(ResultCodeEnum.ILLEGAL_ARGUMENT.name(), ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),
					"传入参数错误");
		}

		for (int i = 0; i < idArray.length;i++) {
			String sortNo = sortNoArray[i];
			
			CmsContent content = cmsContentCrudService.queryById(idArray[i]);
			if (!Check.isInteger(sortNo) || content == null) {
				continue;
			}
			content.setSortno(Integer.parseInt(sortNo));
			cmsContentCrudService.update(content);
		}
	}
	
	private void saveAttachment(String contentId, List<String> attachmentIds) {
		if (CollectionUtils.isNotEmpty(attachmentIds)) {
			for (String fileId : attachmentIds) {
				FileInfo fileInfo = fileInfoCrudService.queryById(fileId);
				if (fileInfo == null) {
					continue;
				}
				CmsContentFile contentFile = new CmsContentFile();
				contentFile.setContentId(contentId);
				contentFile.setAttachmentId(fileId);
				contentFile.setUserId(fileInfo.getUserId());
				contentFile.setName(fileInfo.getName());
				contentFile.setUrl(fileInfo.getUrl());
				contentFile.setPath(fileInfo.getPath());
				contentFile.setSize(fileInfo.getSize());
				contentFile.setImage(0);
				contentFile.setClicks(0);
				contentFile.setSortno(0);
				contentFile.setMemo(fileInfo.getMemo());
				contentFile.setDstatus("N");
				contentFile.setCreateDate(System.currentTimeMillis());
				contentFile.setLastModDate(System.currentTimeMillis());
				
				cmsContentFileCrudService.save(contentFile);
			}
			
		}

	}  
}
