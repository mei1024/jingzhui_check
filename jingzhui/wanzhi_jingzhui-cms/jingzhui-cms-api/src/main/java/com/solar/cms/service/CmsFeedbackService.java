package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsFeedbackDto;
import com.solar.cms.query.CmsFeedbackQuery;

/**
 * 意见反馈 CmsFeedback 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181218
 *
 */
public interface CmsFeedbackService {
	
	/**
	 * 根据ID查询意见反馈
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsFeedbackDto queryCmsFeedbackById(String id) throws BizException;
	
	/**
	 * 查询全部意见反馈
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsFeedbackDto> queryCmsFeedbackAllList() throws BizException;
	
	/**
	 * 带条件查询意见反馈
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsFeedbackDto> queryCmsFeedbackList(CmsFeedbackQuery query) throws BizException;
	
	/**
	 * 分页查询cmsFeedback
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
	Pagination<CmsFeedbackDto> queryPageCmsFeedback(CmsFeedbackQuery query) throws BizException;

	/**
	 * 保存意见反馈
	 * 
	 * @param cmsFeedback
	 * @return
	 * @throws BizException
	 */
	CmsFeedbackDto saveCmsFeedback(CmsFeedbackDto cmsFeedback) throws BizException;
	
	/**
	 * 更新意见反馈
	 * 
	 * @param cmsFeedback
	 * @throws BizException
	 */
	void updateCmsFeedbackById(CmsFeedbackDto cmsFeedback) throws BizException;

	/**
	 * 根据ID删除意见反馈
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsFeedbackById(String id) throws BizException;
	
	/**
	 * 批量删除意见反馈
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsFeedbackByIds(List<String> ids) throws BizException;
	
}