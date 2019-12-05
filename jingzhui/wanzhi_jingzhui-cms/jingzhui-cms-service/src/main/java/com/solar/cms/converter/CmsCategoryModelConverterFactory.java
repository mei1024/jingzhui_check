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
import com.solar.cms.dto.CmsCategoryModelDto;
import com.solar.cms.entity.CmsCategoryModel;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsCategoryModelConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsCategoryModelDto convertCmsCategoryModelDTO(CmsCategoryModel cmsCategoryModel) {
		BizAssert.notNull(cmsCategoryModel, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryModel null");

		CmsCategoryModelDto result = new CmsCategoryModelDto();
		result.setId(cmsCategoryModel.getId());
		result.setModelId(cmsCategoryModel.getModelId());
		result.setTemplatePath(cmsCategoryModel.getTemplatePath());
		result.setCreatedAt(cmsCategoryModel.getCreateDate());
		result.setCreator(userService.queryUserById(cmsCategoryModel.getCreator()));
		result.setModifiedAt(cmsCategoryModel.getLastModDate());
		result.setModifier(userService.queryUserById(cmsCategoryModel.getLastModifier()));
		
		return result;
	}

	public static List<CmsCategoryModelDto> convertCmsCategoryModelListDTO(List<CmsCategoryModel> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsCategoryModelDto>(0);
		}
		
		List<CmsCategoryModelDto> resultList = new ArrayList<CmsCategoryModelDto>();
		for (CmsCategoryModel po : list) {
			resultList.add(convertCmsCategoryModelDTO(po));
		}
		return resultList;
	}
	

	public static CmsCategoryModel convertCmsCategoryModelEntity(CmsCategoryModelDto cmsCategoryModel) {
		BizAssert.notNull(cmsCategoryModel, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsCategoryModel null");
		
		CmsCategoryModel result = new CmsCategoryModel();
		result.setId(cmsCategoryModel.getId());
		result.setModelId(cmsCategoryModel.getModelId());
		result.setTemplatePath(cmsCategoryModel.getTemplatePath());
		
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