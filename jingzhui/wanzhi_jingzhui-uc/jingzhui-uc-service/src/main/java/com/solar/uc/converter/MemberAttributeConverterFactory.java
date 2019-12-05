package com.solar.uc.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.nebula.common.biz.util.BizAssert;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.uc.dto.MemberAttributeDto;
import com.solar.uc.entity.MemberAttribute;

@Component
@Lazy(false) 
public final class MemberAttributeConverterFactory implements ApplicationContextAware, DisposableBean {

	public static MemberAttributeDto convertMemberAttributeDTO(MemberAttribute memberAttribute) {
		BizAssert.notNull(memberAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberAttribute null");

		MemberAttributeDto result = new MemberAttributeDto();
		result.setId(memberAttribute.getId());
		result.setAge(memberAttribute.getAge());
		result.setHeight(memberAttribute.getHeight());
		result.setWeight(memberAttribute.getWeight());
		result.setMemo(memberAttribute.getMemo());
		result.setCreatedAt(memberAttribute.getCreateDate());
		result.setModifiedAt(memberAttribute.getLastModDate());
		
		return result;
	}

	public static List<MemberAttributeDto> convertMemberAttributeListDTO(List<MemberAttribute> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<MemberAttributeDto>(0);
		}
		
		List<MemberAttributeDto> resultList = new ArrayList<MemberAttributeDto>();
		for (MemberAttribute po : list) {
			resultList.add(convertMemberAttributeDTO(po));
		}
		return resultList;
	}
	

	public static MemberAttribute convertMemberAttributeEntity(MemberAttributeDto memberAttribute) {
		BizAssert.notNull(memberAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:memberAttribute null");
		
		MemberAttribute result = new MemberAttribute();
		result.setId(memberAttribute.getId());
		result.setAge(memberAttribute.getAge());
		result.setHeight(memberAttribute.getHeight());
		result.setWeight(memberAttribute.getWeight());
		result.setMemo(memberAttribute.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	}
	
}