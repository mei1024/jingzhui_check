package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsContentFileDto;
import com.solar.cms.query.CmsContentFileQuery;

/**
 * 内容附件 CmsContentFile 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsContentFileService {
	
	/**
	 * 根据ID查询内容附件
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsContentFileDto queryCmsContentFileById(String id) throws BizException;
	
	/**
	 * 查询全部内容附件
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsContentFileDto> queryCmsContentFileAllList() throws BizException;
	
	/**
	 * 带条件查询内容附件
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsContentFileDto> queryCmsContentFileList(CmsContentFileQuery query) throws BizException;
	
	/**
	 * 分页查询cmsContentFile
	 * 
	 * @param query 查询对象
	 * <pre>
	 * 	 count=单页显示记录数
	 * 	 page=当前页面
	 * </pre>
	 * 
	 * @return
	 * @throws BizException
	 */
	Pagination<CmsContentFileDto> queryPageCmsContentFile(CmsContentFileQuery query) throws BizException;

	/**
	 * 保存内容附件
	 * 
	 * @param cmsContentFile
	 * @return
	 * @throws BizException
	 */
	CmsContentFileDto saveCmsContentFile(CmsContentFileDto cmsContentFile) throws BizException;
	
	/**
	 * 更新内容附件
	 * 
	 * @param cmsContentFile
	 * @throws BizException
	 */
	void updateCmsContentFileById(CmsContentFileDto cmsContentFile) throws BizException;

	/**
	 * 根据ID删除内容附件
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsContentFileById(String id) throws BizException;
	
	/**
	 * 批量删除内容附件
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsContentFileByIds(List<String> ids) throws BizException;
	
}