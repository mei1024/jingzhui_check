package com.solar.cms.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.cms.dto.QaCategroyDto;
import com.solar.cms.query.QaCategroyQuery;

/**
 * 问题分类 QaCategroy 业务API接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public interface QaCategroyService {
	
	/**
	 * 根据ID查询问题分类
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	QaCategroyDto queryQaCategroyById(String id) throws BizException;
	
	/**
	 * 查询全部问题分类
	 * 
	 * @return
	 * @throws BizException
	 */
	List<QaCategroyDto> queryQaCategroyAllList() throws BizException;
	
	/**
	 * 带条件查询问题分类
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<QaCategroyDto> queryQaCategroyList(QaCategroyQuery query) throws BizException;
	
	/**
	 * 分页查询qaCategroy
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
	Pagination<QaCategroyDto> queryPageQaCategroy(QaCategroyQuery query) throws BizException;

	/**
	 * 保存问题分类
	 * 
	 * @param qaCategroy
	 * @return
	 * @throws BizException
	 */
	QaCategroyDto saveQaCategroy(QaCategroyDto qaCategroy) throws BizException;
	
	/**
	 * 更新问题分类
	 * 
	 * @param qaCategroy
	 * @throws BizException
	 */
	void updateQaCategroyById(QaCategroyDto qaCategroy) throws BizException;

	/**
	 * 根据ID删除问题分类
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteQaCategroyById(String id) throws BizException;
	
	/**
	 * 批量删除问题分类
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteQaCategroyByIds(List<String> ids) throws BizException;
	
}