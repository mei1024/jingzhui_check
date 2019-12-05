package com.solar.uc.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.common.biz.enums.SortByEnum;
import com.nebula.common.biz.exception.BizException;
import com.nebula.common.biz.service.BaseServiceImpl;
import com.nebula.common.biz.service.ServiceCheckCallback;
import com.nebula.common.biz.util.BizAssert;
import com.nebula.common.util.Pagination;
import com.nebula.common.util.Search;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.uc.converter.MemberAttributeConverterFactory;
import com.solar.uc.service.MemberAttributeService;
import com.solar.uc.dal.crud.MemberAttributeCrudService;
import com.solar.uc.entity.MemberAttribute;
import com.solar.uc.dto.MemberAttributeDto;
import com.solar.uc.query.MemberAttributeQuery;

/**
 * 会员扩展信息 MemberAttribute 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Service("memberAttributeService")
public class MemberAttributeServiceImpl extends BaseServiceImpl implements MemberAttributeService {
	
	@Autowired
	private MemberAttributeCrudService memberAttributeCrudService;
	
	@Override
	public MemberAttributeDto queryMemberAttributeById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		MemberAttribute memberAttribute = memberAttributeCrudService.queryById(id);
		return MemberAttributeConverterFactory.convertMemberAttributeDTO(memberAttribute);
	}
 
	@Override
	public List<MemberAttributeDto> queryMemberAttributeAllList() throws BizException {
		List<MemberAttribute> list = memberAttributeCrudService.queryAllList();

		return MemberAttributeConverterFactory.convertMemberAttributeListDTO(list);
	}
	
	@Override
	public List<MemberAttributeDto> queryMemberAttributeList(final MemberAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<MemberAttribute> list = memberAttributeCrudService.queryList(search.getParameters());
		
		return MemberAttributeConverterFactory.convertMemberAttributeListDTO(list);
	}
	
	@Override
	public Pagination<MemberAttributeDto> queryPageMemberAttribute(final MemberAttributeQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<MemberAttribute> list = memberAttributeCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = memberAttributeCrudService.count(search.getParameters());

		List<MemberAttributeDto> resultList = MemberAttributeConverterFactory.convertMemberAttributeListDTO(list);
		
		return new Pagination<MemberAttributeDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public MemberAttributeDto saveMemberAttribute(final MemberAttributeDto memberAttribute) throws BizException {
		BizAssert.notNull(memberAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberAttribute为空");
		
		return (MemberAttributeDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				MemberAttribute createModel = MemberAttributeConverterFactory.convertMemberAttributeEntity(memberAttribute);
				memberAttributeCrudService.save(createModel);
				
				MemberAttributeDto result = MemberAttributeConverterFactory.convertMemberAttributeDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateMemberAttributeById(final MemberAttributeDto memberAttribute) throws BizException {
		BizAssert.notNull(memberAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberAttribute为空");
		BizAssert.notEmpty(memberAttribute.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberAttributeCrudService.queryById(memberAttribute.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + memberAttribute.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				
				MemberAttribute updateModel = MemberAttributeConverterFactory.convertMemberAttributeEntity(memberAttribute);
				memberAttributeCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteMemberAttributeById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (memberAttributeCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = memberAttributeCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteMemberAttributeByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteMemberAttributeById(id);
		}
	}
 
}
