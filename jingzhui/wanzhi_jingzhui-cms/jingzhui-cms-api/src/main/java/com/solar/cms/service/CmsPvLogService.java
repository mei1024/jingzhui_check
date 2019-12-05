package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.CmsPvLogDto;
import com.solar.cms.query.CmsPvLogQuery;

/**
 * PV记录 CmsPvLog 业务API接口
 * 
 * @author codegen
 * 
 * @version 20181022
 *
 */
public interface CmsPvLogService {
	
	/**
	 * 根据ID查询PV记录
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	CmsPvLogDto queryCmsPvLogById(String id) throws BizException;
	
	/**
	 * 查询全部PV记录
	 * 
	 * @return
	 * @throws BizException
	 */
	List<CmsPvLogDto> queryCmsPvLogAllList() throws BizException;
	
	/**
	 * 带条件查询PV记录
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<CmsPvLogDto> queryCmsPvLogList(CmsPvLogQuery query) throws BizException;
	
	/**
	 * 分页查询cmsPvLog
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
	Pagination<CmsPvLogDto> queryPageCmsPvLog(CmsPvLogQuery query) throws BizException;

	/**
	 * 保存PV记录
	 * 
	 * @param cmsPvLog
	 * @return
	 * @throws BizException
	 */
	void saveCmsPvLog(CmsPvLogDto cmsPvLog) throws BizException;
	
	/**
	 * 更新PV记录
	 * 
	 * @param cmsPvLog
	 * @throws BizException
	 */
	void updateCmsPvLogById(CmsPvLogDto cmsPvLog) throws BizException;

	/**
	 * 根据ID删除PV记录
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteCmsPvLogById(String id) throws BizException;
	
	/**
	 * 批量删除PV记录
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteCmsPvLogByIds(List<String> ids) throws BizException;
	
}