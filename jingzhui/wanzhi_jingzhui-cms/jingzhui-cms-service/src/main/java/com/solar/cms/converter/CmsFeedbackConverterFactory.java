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
import com.solar.cms.dto.CmsFeedbackDto;
import com.solar.cms.entity.CmsFeedback;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsFeedbackConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsFeedbackDto convertCmsFeedbackDTO(CmsFeedback cmsFeedback) {
		BizAssert.notNull(cmsFeedback, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsFeedback null");

		CmsFeedbackDto result = new CmsFeedbackDto();
		result.setId(cmsFeedback.getId());
		result.setCategoryId(cmsFeedback.getCategoryId());
		result.setUserId(cmsFeedback.getUserId());
		result.setContent(cmsFeedback.getContent());
		result.setSource(cmsFeedback.getSource());
		result.setImgUrls(cmsFeedback.getImgUrls());
		result.setStatus(cmsFeedback.getStatus());
		result.setMemo(cmsFeedback.getMemo());
		result.setCreatedAt(cmsFeedback.getCreateDate());
		result.setCreator(userService.queryUserById(cmsFeedback.getCreator()));
		result.setModifiedAt(cmsFeedback.getLastModDate());
		result.setModifier(userService.queryUserById(cmsFeedback.getLastModifier()));
		
		return result;
	}

	public static List<CmsFeedbackDto> convertCmsFeedbackListDTO(List<CmsFeedback> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsFeedbackDto>(0);
		}
		
		List<CmsFeedbackDto> resultList = new ArrayList<CmsFeedbackDto>();
		for (CmsFeedback po : list) {
			resultList.add(convertCmsFeedbackDTO(po));
		}
		return resultList;
	}
	

	public static CmsFeedback convertCmsFeedbackEntity(CmsFeedbackDto cmsFeedback) {
		BizAssert.notNull(cmsFeedback, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsFeedback null");
		
		CmsFeedback result = new CmsFeedback();
		result.setId(cmsFeedback.getId());
		result.setCategoryId(cmsFeedback.getCategoryId());
		result.setUserId(cmsFeedback.getUserId());
		result.setContent(cmsFeedback.getContent());
		result.setSource(cmsFeedback.getSource());
		result.setImgUrls(cmsFeedback.getImgUrls());
		result.setStatus(cmsFeedback.getStatus());
		result.setMemo(cmsFeedback.getMemo());
		
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