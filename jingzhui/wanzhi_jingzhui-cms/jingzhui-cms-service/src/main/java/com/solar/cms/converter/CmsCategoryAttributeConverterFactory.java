package com.solar.cms.converter;

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
import com.solar.cms.dto.CmsCategoryAttributeDto;
import com.solar.cms.entity.CmsCategoryAttribute;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsCategoryAttributeConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsCategoryAttributeDto convertCmsCategoryAttributeDTO(CmsCategoryAttribute cmsCategoryAttribute) {
		BizAssert.notNull(cmsCategoryAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryAttribute null");

		CmsCategoryAttributeDto result = new CmsCategoryAttributeDto();
		result.setId(cmsCategoryAttribute.getId());
		result.setTitle(cmsCategoryAttribute.getTitle());
		result.setKeywords(cmsCategoryAttribute.getKeywords());
		result.setDescription(cmsCategoryAttribute.getDescription());
		result.setData(cmsCategoryAttribute.getData());
		result.setCreatedAt(cmsCategoryAttribute.getCreateDate());
		result.setCreator(userService.queryUserById(cmsCategoryAttribute.getCreator()));
		result.setModifiedAt(cmsCategoryAttribute.getLastModDate());
		result.setModifier(userService.queryUserById(cmsCategoryAttribute.getLastModifier()));
		
		return result;
	}

	public static List<CmsCategoryAttributeDto> convertCmsCategoryAttributeListDTO(List<CmsCategoryAttribute> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsCategoryAttributeDto>(0);
		}
		
		List<CmsCategoryAttributeDto> resultList = new ArrayList<CmsCategoryAttributeDto>();
		for (CmsCategoryAttribute po : list) {
			resultList.add(convertCmsCategoryAttributeDTO(po));
		}
		return resultList;
	}
	

	public static CmsCategoryAttribute convertCmsCategoryAttributeEntity(CmsCategoryAttributeDto cmsCategoryAttribute) {
		BizAssert.notNull(cmsCategoryAttribute, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryAttribute null");
		
		CmsCategoryAttribute result = new CmsCategoryAttribute();
		result.setId(cmsCategoryAttribute.getId());
		result.setTitle(cmsCategoryAttribute.getTitle());
		result.setKeywords(cmsCategoryAttribute.getKeywords());
		result.setDescription(cmsCategoryAttribute.getDescription());
		result.setData(cmsCategoryAttribute.getData());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
		userService = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = applicationContext.getBean(UserService.class);
	}
	
}