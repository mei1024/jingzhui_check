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
import com.solar.cms.dto.CmsContactDto;
import com.solar.cms.entity.CmsContact;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CmsContactConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CmsContactDto convertCmsContactDTO(CmsContact cmsContact) {
		BizAssert.notNull(cmsContact, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContact null");

		CmsContactDto result = new CmsContactDto();
		result.setId(cmsContact.getId());
		result.setFullname(cmsContact.getFullname());
		result.setFirstName(cmsContact.getFirstName());
		result.setLastName(cmsContact.getLastName());
		result.setEmail(cmsContact.getEmail());
		result.setPhone(cmsContact.getPhone());
		result.setCompany(cmsContact.getCompany());
		result.setText(cmsContact.getText());
		result.setMemo(cmsContact.getMemo());
		result.setCreatedAt(cmsContact.getCreateDate());
		result.setModifiedAt(cmsContact.getLastModDate());
		
		return result;
	}

	public static List<CmsContactDto> convertCmsContactListDTO(List<CmsContact> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CmsContactDto>(0);
		}
		
		List<CmsContactDto> resultList = new ArrayList<CmsContactDto>();
		for (CmsContact po : list) {
			resultList.add(convertCmsContactDTO(po));
		}
		return resultList;
	}
	

	public static CmsContact convertCmsContactEntity(CmsContactDto cmsContact) {
		BizAssert.notNull(cmsContact, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsContact null");
		
		CmsContact result = new CmsContact();
		result.setId(cmsContact.getId());
		result.setFullname(cmsContact.getFullname());
		result.setFirstName(cmsContact.getFirstName());
		result.setLastName(cmsContact.getLastName());
		result.setEmail(cmsContact.getEmail());
		result.setPhone(cmsContact.getPhone());
		result.setCompany(cmsContact.getCompany());
		result.setText(cmsContact.getText());
		result.setMemo(cmsContact.getMemo());
		
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