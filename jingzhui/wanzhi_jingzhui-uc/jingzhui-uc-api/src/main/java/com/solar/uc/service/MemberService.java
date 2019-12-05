package com.solar.uc.service;

import java.util.List;

import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.auth.dto.ThirdUserDto;
import com.solar.uc.dto.MemberDto;
import com.solar.uc.dto.MemberQueryDto;
import com.solar.uc.dto.MemberUpdateDto;
import com.solar.uc.query.MemberQuery;

/**
 * 会员 Member 业务API接口
 * 
 * @author codegen
 * 
 * @version 20190904
 *
 */
public interface MemberService {
	
	/**
	 * 小程序根据ID查询会员
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	MemberQueryDto mimiQueryMemberById(String id) throws BizException;
	
	/**
	 * 根据ID查询会员
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	MemberDto queryMemberById(String id) throws BizException;
	
	/**
	 * 查询全部会员
	 * 
	 * @return
	 * @throws BizException
	 */
	List<MemberDto> queryMemberAllList() throws BizException;
	
	/**
	 * 带条件查询会员
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<MemberDto> queryMemberList(MemberQuery query) throws BizException;
	
	/**
	 * 分页查询member
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
	Pagination<MemberDto> queryPageMember(MemberQuery query) throws BizException;

	/**
	 * 第三方用户注册保存
	 * 
	 * @param thirdUser 三方用户信息
	 * @return
	 * @throws BizException
	 */
	MemberDto saveMember(ThirdUserDto thirdUser) throws BizException;
	
	/**
	 * 更新会员
	 * 
	 * @param member
	 * @throws BizException
	 */
	void updateMemberById(MemberUpdateDto member) throws BizException;

	/**
	 * 根据ID删除会员
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteMemberById(String id) throws BizException;
	
	/**
	 * 批量删除会员
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteMemberByIds(List<String> ids) throws BizException;
	
}