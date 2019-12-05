package com.solar.uc.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.uc.dto.MemberLogDto;
import com.solar.uc.query.MemberLogQuery;

/**
 * 会员日志 MemberLog 业务API接口
 * 
 * @author codegen
 * 
 * @version 20190904
 *
 */
public interface MemberLogService {
	
	/**
	 * 根据ID查询会员日志
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	MemberLogDto queryMemberLogById(String id) throws BizException;
	
	/**
	 * 查询全部会员日志
	 * 
	 * @return
	 * @throws BizException
	 */
	List<MemberLogDto> queryMemberLogAllList() throws BizException;
	
	/**
	 * 带条件查询会员日志
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<MemberLogDto> queryMemberLogList(MemberLogQuery query) throws BizException;
	
	/**
	 * 分页查询memberLog
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
	Pagination<MemberLogDto> queryPageMemberLog(MemberLogQuery query) throws BizException;

	/**
	 * 保存会员日志
	 * 
	 * @param memberLog
	 * @return
	 * @throws BizException
	 */
	MemberLogDto saveMemberLog(MemberLogDto memberLog) throws BizException;
	
	/**
	 * 更新会员日志
	 * 
	 * @param memberLog
	 * @throws BizException
	 */
	void updateMemberLogById(MemberLogDto memberLog) throws BizException;

	/**
	 * 根据ID删除会员日志
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteMemberLogById(String id) throws BizException;
	
	/**
	 * 批量删除会员日志
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteMemberLogByIds(List<String> ids) throws BizException;
	
}