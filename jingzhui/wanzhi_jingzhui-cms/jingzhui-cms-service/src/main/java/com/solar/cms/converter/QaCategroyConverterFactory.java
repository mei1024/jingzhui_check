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
import com.solar.cms.dto.QaCategroyDto;
import com.solar.cms.entity.QaCategroy;
import com.solar.common.core.enums.ResultCodeEnum;

@Component
@Lazy(false) 
public final class QaCategroyConverterFactory implements ApplicationContextAware, DisposableBean {

	public static QaCategroyDto convertQaCategroyDTO(QaCategroy qaCategroy) {
		BizAssert.notNull(qaCategroy, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaCategroy null");

		QaCategroyDto result = new QaCategroyDto();
		result.setId(qaCategroy.getId());
		result.setParentId(qaCategroy.getParentId());
		result.setTypeName(qaCategroy.getTypeName());
		result.setSortNo(qaCategroy.getSortNo());
		result.setMemo(qaCategroy.getMemo());
		
		return result;
	}

	public static List<QaCategroyDto> convertQaCategroyListDTO(List<QaCategroy> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<QaCategroyDto>(0);
		}
		
		List<QaCategroyDto> resultList = new ArrayList<QaCategroyDto>();
		for (QaCategroy po : list) {
			resultList.add(convertQaCategroyDTO(po));
		}
		return resultList;
	}
	

	public static QaCategroy convertQaCategroyEntity(QaCategroyDto qaCategroy) {
		BizAssert.notNull(qaCategroy, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaCategroy null");
		
		QaCategroy result = new QaCategroy();
		result.setId(qaCategroy.getId());
		result.setParentId(qaCategroy.getParentId());
		result.setTypeName(qaCategroy.getTypeName());
		result.setSortNo(qaCategroy.getSortNo());
		result.setMemo(qaCategroy.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	}
	
}