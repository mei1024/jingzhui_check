package com.solar.uc.service;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.nebula.common.web.util.WebUtils;
import com.nebula.sequence.SequenceService;
import com.solar.auth.dto.ThirdUserDto;
import com.solar.auth.dto.UserIdentityDto;
import com.solar.auth.service.ThirdUserService;
import com.solar.auth.service.UserIdentityService;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.core.enums.UserIdentityEnum;
import com.solar.uc.converter.MemberConverterFactory;
import com.solar.uc.dal.crud.MemberAttributeCrudService;
import com.solar.uc.dal.crud.MemberCrudService;
import com.solar.uc.dto.MemberAttributeDto;
import com.solar.uc.dto.MemberDto;
import com.solar.uc.dto.MemberLogDto;
import com.solar.uc.dto.MemberQueryDto;
import com.solar.uc.dto.MemberUpdateDto;
import com.solar.uc.entity.Member;
import com.solar.uc.entity.MemberAttribute;
import com.solar.uc.enums.MemberOptType;
import com.solar.uc.enums.RegSourceEnum;
import com.solar.uc.enums.SequenceKeyEnum;
import com.solar.uc.enums.StatusEnum;
import com.solar.uc.query.MemberQuery;

/**
 * 用户 Member 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20190904
 * 
 */
@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl implements MemberService {
	
	private static String memberjurisdiction = "cms:ad:manager,ds:file:upload,ds:tag:manager";
	
	@Autowired
	private MemberCrudService memberCrudService;
	
	@Autowired
	private ThirdUserService thirdUserService;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private UserIdentityService userIdentityService;
	
	@Autowired
	private MemberLogService memberLogService;
	
	@Autowired
	private MemberAttributeService memberAttributeService;
	
	@Autowired
	private MemberAttributeCrudService memberAttributeCrudService;

	@Override
	public MemberQueryDto mimiQueryMemberById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		MemberQueryDto returnMember = new MemberQueryDto();
		Member member = memberCrudService.queryById(id);
		if (member == null) {
			throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
					ResultCodeEnum.DATA_NOT_FOUND.getCode(), "账号信息不存在");
		} else if (member != null && !StringUtils.equals(member.getStatus(), StatusEnum.UserStatus.NORMAL.getCode())) {
			throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
					ResultCodeEnum.DATA_NOT_FOUND.getCode(), "账号异常，请联系管理员");
		} else {
			returnMember = MemberConverterFactory.convertMemberQueryDTO(member);
			MemberAttributeDto memberAttribute = memberAttributeService.queryMemberAttributeById(id);
			if (memberAttribute != null) {
				returnMember.setAge(memberAttribute.getAge());
				returnMember.setHeight(memberAttribute.getHeight());
				returnMember.setWeight(memberAttribute.getWeight());
			}
		}
		return returnMember;
	}
	
	@Override
	public MemberDto queryMemberById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		Member member = memberCrudService.queryById(id);
		return MemberConverterFactory.convertMemberDTO(member);
	}
 
	@Override
	public List<MemberDto> queryMemberAllList() throws BizException {
		List<Member> list = memberCrudService.queryAllList();

		return MemberConverterFactory.convertMemberListDTO(list);
	}
	
	@Override
	public List<MemberDto> queryMemberList(final MemberQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<Member> list = memberCrudService.queryList(search.getParameters());
		
		return MemberConverterFactory.convertMemberListDTO(list);
	}
	
	@Override
	public Pagination<MemberDto> queryPageMember(final MemberQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());

		try {
			if (StringUtils.isNotEmpty(query.getRegBeginDate()) && StringUtils.isNotEmpty(query.getRegEndDate())) {
				Date startDate = DateUtils.parseDate(query.getRegBeginDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getRegEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("regBeginDate", startDate.getTime());
				search.safeAddParamter("regEndDate", endDate.getTime());
			}
			if (StringUtils.isNotEmpty(query.getLastLoginBeginDate()) && StringUtils.isNotEmpty(query.getLastLoginEndDate())) {
				Date startDate = DateUtils.parseDate(query.getLastLoginBeginDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date endDate = DateUtils.parseDate(query.getLastLoginEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				search.safeAddParamter("lastLoginBeginDate", startDate.getTime());
				search.safeAddParamter("lastLoginEndDate", endDate.getTime());
			}
		} catch (Exception e) {
			
		}
		
		String order =  StringUtils.isNotEmpty(query.getOrder()) ? query.getOrder() : "id";
		SortByEnum sortby = StringUtils.isNotEmpty(query.getSortby()) ? SortByEnum.valueOf(query.getSortby().toUpperCase()) : SortByEnum.DESC;
		
		// query list
		List<Member> list = memberCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), order, sortby);
		// query totals
		int totals = memberCrudService.count(search.getParameters());

		List<MemberDto> resultList = MemberConverterFactory.convertMemberListDTO(list);
		
		return new Pagination<MemberDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public MemberDto saveMember(final ThirdUserDto thirdUser) throws BizException {
		BizAssert.notNull(thirdUser, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:thirdUser为空");
		BizAssert.notNull(thirdUser.getOpenType(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:openType为空");
		BizAssert.notEmpty(thirdUser.getOpenId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:openId为空");

		return (MemberDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				ThirdUserDto existsThirdUser = thirdUserService.queryThirdUserByOpenId(thirdUser.getOpenType(), thirdUser.getOpenId());
				MemberLogDto memberLog = new MemberLogDto();
				memberLog.setOptTime(System.currentTimeMillis());
				memberLog.setIp(WebUtils.getIp());
				if (existsThirdUser != null) {
					// 1.更新用户第三方信息
					thirdUser.setId(existsThirdUser.getId());
					thirdUser.setUserId(existsThirdUser.getUserId());
					thirdUserService.updateThirdUserById(thirdUser);
					
					// 2.更新用户信息
					Member member = memberCrudService.queryById(existsThirdUser.getUserId());
					member.setNickName(thirdUser.getNickName());
					member.setAvatarUrl(thirdUser.getAvatarUrl());
					member.setLastLoginIp(WebUtils.getIp());
					member.setLastLoginTime(System.currentTimeMillis());
					member.setLastModDate(System.currentTimeMillis());
					memberCrudService.update(member);
					
					// 3.保存用户登录日志
					memberLog.setUserId(existsThirdUser.getUserId());
					memberLog.setOptRemark("用户登录");
					memberLog.setOptType(MemberOptType.LOGIN.getCode());
					memberLogService.saveMemberLog(memberLog);

					return queryMemberById(member.getId());
				} else {
					
					// 1、用户身份认证保存
					UserIdentityDto userIdentity = new UserIdentityDto();
					userIdentity.setIdentity(UserIdentityEnum.MEMBER.getCode());
					userIdentity.setUserName(thirdUser.getOpenId());
					userIdentity.setCredentials(thirdUser.getOpenId());
					userIdentity.setRoles(memberjurisdiction);
					userIdentity.setStatus(StatusEnum.UserStatus.NORMAL.getCode());
					userIdentity = userIdentityService.saveUserIdentity(userIdentity);
					
					// 2、初始化用户基本信息
					Member member = new Member();
					member.setId(userIdentity.getId());
					member.setUserName(thirdUser.getNickName());
					member.setStatus(StatusEnum.UserStatus.NORMAL.getCode());
					member.setNickName(thirdUser.getNickName());
					member.setAvatarUrl(thirdUser.getAvatarUrl());
					member.setRegSource(RegSourceEnum.WECHAT_MINI.getCode());
					member.setRegIp(WebUtils.getIp());
					member.setRegTime(System.currentTimeMillis());
					member.setLastLoginIp(WebUtils.getIp());
					member.setLastLoginTime(System.currentTimeMillis());
					member.setRank("");
					member.setRealName("");
					member.setSex("");
					member.setBirthday("");
					member.setIdCard("");
					member.setTel("");
					member.setMobile("");
					member.setEmail("");
					member.setQq("");
					member.setHomePhone("");
					member.setHomeAddress("");
					member.setIntro("");
					member.setOfficeAddress("");
					member.setMemo("");
					memberCrudService.save(member);
					
					// 3. 初始化用户扩展属性
					MemberAttribute memberAttribute = new MemberAttribute();
					memberAttribute.setId(member.getId());
					memberAttribute.setAge("");
					memberAttribute.setHeight("");
					memberAttribute.setWeight("");
					memberAttributeCrudService.save(memberAttribute);
					MemberDto memberResult = MemberConverterFactory.convertMemberDTO(member);
					
					// 4.保存用户日志
					memberLog.setUserId(userIdentity.getId());
					memberLog.setOptRemark("用户注册");
					memberLog.setOptType(MemberOptType.REGISTER.getCode());
					memberLogService.saveMemberLog(memberLog);
					
					// 5.第三方用户信息保存
					thirdUser.setId(sequenceService.getLongNextVal(SequenceKeyEnum.AUTH_THIRD_USER_ID.getKey()) + "");
					thirdUser.setUserId(memberResult.getId());
					thirdUserService.saveThirdUser(thirdUser);
					
					return memberResult;
				}
			}
		});
	}
	
	@Override
	public void updateMemberById(final MemberUpdateDto memberQuery) throws BizException {
		BizAssert.notNull(memberQuery, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:member为空");
		BizAssert.notEmpty(memberQuery.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberCrudService.queryById(memberQuery.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + memberQuery.getId() + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// 1.用户基本信息修改
				Member updateModel = memberCrudService.queryById(memberQuery.getId());
				updateModel.setAvatarUrl(memberQuery.getAvatarUrl());
				updateModel.setSex(memberQuery.getSex());
				updateModel.setProfession(memberQuery.getProfession());
				memberCrudService.update(updateModel);
				
				// 2.用户扩展信息修改
				MemberAttributeDto updateMemberAttribute = memberAttributeService.queryMemberAttributeById(memberQuery.getId());
				updateMemberAttribute.setAge(memberQuery.getAge());
				updateMemberAttribute.setHeight(memberQuery.getHeight());
				updateMemberAttribute.setWeight(memberQuery.getWeight());
				memberAttributeService.updateMemberAttributeById(updateMemberAttribute);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteMemberById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = memberCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteMemberByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteMemberById(id);
		}
	}
	
}
