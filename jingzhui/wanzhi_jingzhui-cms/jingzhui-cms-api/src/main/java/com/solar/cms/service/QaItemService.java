package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.QaItemDto;
import com.solar.cms.query.QaItemQuery;

/**
 * 问题答案 QaItem 业务API接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public interface QaItemService {
	
	/**
	 * 根据ID查询问题答案
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	QaItemDto queryQaItemById(String id) throws BizException;
	
	/**
	 * 查询全部问题答案
	 * 
	 * @return
	 * @throws BizException
	 */
	List<QaItemDto> queryQaItemAllList() throws BizException;
	
	/**
	 * 带条件查询问题答案
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<QaItemDto> queryQaItemList(QaItemQuery query) throws BizException;
	
	/**
	 * 分页查询qaItem
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
	Pagination<QaItemDto> queryPageQaItem(QaItemQuery query) throws BizException;

	/**
	 * 保存问题答案
	 * 
	 * @param qaItem
	 * @return
	 * @throws BizException
	 */
	QaItemDto saveQaItem(QaItemDto qaItem) throws BizException;
	
	/**
	 * 更新问题答案
	 * 
	 * @param qaItem
	 * @throws BizException
	 */
	void updateQaItemById(QaItemDto qaItem) throws BizException;

	/**
	 * 根据ID删除问题答案
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteQaItemById(String id) throws BizException;
	
	/**
	 * 批量删除问题答案
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteQaItemByIds(List<String> ids) throws BizException;
	
}