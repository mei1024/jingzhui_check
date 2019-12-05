package com.solar.uc.service;

import java.util.List;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.util.Pagination;
import com.solar.uc.dto.MemberAttributeDto;
import com.solar.uc.query.MemberAttributeQuery;

/**
 * 会员扩展信息 MemberAttribute 业务API接口
 * 
 * @author codegen
 * 
 * @version 20191204
 *
 */
public interface MemberAttributeService {
	
	/**
	 * 根据ID查询会员扩展信息
	 * 
	 * @param id ID编号
	 * @return
	 * @throws BizException
	 */
	MemberAttributeDto queryMemberAttributeById(String id) throws BizException;
	
	/**
	 * 查询全部会员扩展信息
	 * 
	 * @return
	 * @throws BizException
	 */
	List<MemberAttributeDto> queryMemberAttributeAllList() throws BizException;
	
	/**
	 * 带条件查询会员扩展信息
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<MemberAttributeDto> queryMemberAttributeList(MemberAttributeQuery query) throws BizException;
	
	/**
	 * 分页查询memberAttribute
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
	Pagination<MemberAttributeDto> queryPageMemberAttribute(MemberAttributeQuery query) throws BizException;

	/**
	 * 保存会员扩展信息
	 * 
	 * @param memberAttribute
	 * @return
	 * @throws BizException
	 */
	MemberAttributeDto saveMemberAttribute(MemberAttributeDto memberAttribute) throws BizException;
	
	/**
	 * 更新会员扩展信息
	 * 
	 * @param memberAttribute
	 * @throws BizException
	 */
	void updateMemberAttributeById(MemberAttributeDto memberAttribute) throws BizException;

	/**
	 * 根据ID删除会员扩展信息
	 * 
	 * @param id ID编号
	 * @throws BizException
	 */
	void deleteMemberAttributeById(String id) throws BizException;
	
	/**
	 * 批量删除会员扩展信息
	 * 
	 * @param ids 批量ID集合
	 * @throws BizException
	 */
	void deleteMemberAttributeByIds(List<String> ids) throws BizException;
	
}