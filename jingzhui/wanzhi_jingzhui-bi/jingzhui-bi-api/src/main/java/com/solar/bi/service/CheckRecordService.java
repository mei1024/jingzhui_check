package com.solar.bi.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.bi.dto.CheckRecordCreateDto;
import com.solar.bi.dto.CheckRecordDto;
import com.solar.bi.dto.CountCheckRecord;
import com.solar.bi.query.CheckRecordQuery;

/**
 * 检查记录 CheckRecord 业务API接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public interface CheckRecordService {
	
	/**
	 * 根据ID查询检查记录
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CheckRecordDto queryCheckRecordById(String id) throws BizException;
	
	/**
	 * 查询全部检查记录
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CheckRecordDto> queryCheckRecordAllList() throws BizException;
	
	/**
	 * 带条件查询检查记录
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CheckRecordDto> queryCheckRecordList(CheckRecordQuery query) throws BizException;
	
	/**
	 * 分页查询checkRecord
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
	Pagination<CheckRecordDto> queryPageCheckRecord(CheckRecordQuery query) throws BizException;

	/**
	 * 统计检查数量
	 * @param checkType
	 * @return
	 */
	CountCheckRecord countCheckRecord(String checkType) throws BizException;
	/**
	 * 保存检查记录
	 * 
	 * @param checkRecord
	 * @return
	 * @throws BizException
	 */
	CheckRecordDto saveCheckRecord(CheckRecordCreateDto checkRecord) throws BizException;
	
	/**
	 * 更新检查记录
	 * 
	 * @param checkRecord
	 * @throws BizException
	 */
	void updateCheckRecordById(CheckRecordCreateDto checkRecord) throws BizException;

	/**
	 * 根据ID删除检查记录
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCheckRecordById(String id) throws BizException;
	
	/**
	 * 批量删除检查记录
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCheckRecordByIds(List<String> ids) throws BizException;
	
}