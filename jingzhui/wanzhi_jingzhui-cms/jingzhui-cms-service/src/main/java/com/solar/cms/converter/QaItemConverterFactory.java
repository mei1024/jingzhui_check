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
import com.solar.cms.dto.QaItemDto;
import com.solar.cms.entity.QaItem;
import com.solar.common.core.enums.ResultCodeEnum;

@Component
@Lazy(false) 
public final class QaItemConverterFactory implements ApplicationContextAware, DisposableBean {

	public static QaItemDto convertQaItemDTO(QaItem qaItem) {
		BizAssert.notNull(qaItem, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaItem null");

		QaItemDto result = new QaItemDto();
		result.setId(qaItem.getId());
		result.setCategroyId(qaItem.getCategroyId());
		result.setIsShowFlag(qaItem.getIsShowFlag());
		result.setQuestion(qaItem.getQuestion());
		result.setAnswer(qaItem.getAnswer());
		result.setMemo(qaItem.getMemo());
		
		return result;
	}

	public static List<QaItemDto> convertQaItemListDTO(List<QaItem> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<QaItemDto>(0);
		}
		
		List<QaItemDto> resultList = new ArrayList<QaItemDto>();
		for (QaItem po : list) {
			resultList.add(convertQaItemDTO(po));
		}
		return resultList;
	}
	

	public static QaItem convertQaItemEntity(QaItemDto qaItem) {
		BizAssert.notNull(qaItem, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaItem null");
		
		QaItem result = new QaItem();
		result.setId(qaItem.getId());
		result.setCategroyId(qaItem.getCategroyId());
		result.setIsShowFlag(qaItem.getIsShowFlag());
		result.setQuestion(qaItem.getQuestion());
		result.setAnswer(qaItem.getAnswer());
		result.setMemo(qaItem.getMemo());
		
		return result;
	}

	@Override
	public void destroy() throws Exception {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	}
	
}