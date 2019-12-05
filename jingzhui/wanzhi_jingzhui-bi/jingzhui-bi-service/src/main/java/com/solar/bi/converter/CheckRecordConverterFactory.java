package com.solar.bi.converter;

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
import com.solar.bi.dto.CheckRecordCreateDto;
import com.solar.bi.dto.CheckRecordDto;
import com.solar.bi.entity.CheckRecord;
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.common.service.UserService;

@Component
@Lazy(false) 
public final class CheckRecordConverterFactory implements ApplicationContextAware, DisposableBean {

	private static UserService userService;

	public static CheckRecordDto convertCheckRecordDTO(CheckRecord checkRecord) {
		BizAssert.notNull(checkRecord, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkRecord null");

		CheckRecordDto result = new CheckRecordDto();
		result.setId(checkRecord.getId());
		result.setUserId(checkRecord.getUserId());
		result.setCheckType(checkRecord.getCheckType());
		result.setCheckResult(checkRecord.getCheckResult());
		result.setCheckSuggest(checkRecord.getCheckSuggest());
		result.setUseTimes(checkRecord.getUseTimes());
		result.setScore(checkRecord.getScore());
		result.setMemo(checkRecord.getMemo());
		result.setCreatedAt(checkRecord.getCreateDate());
		result.setCreator(userService.queryUserById(checkRecord.getCreator()));
		result.setModifiedAt(checkRecord.getLastModDate());
		result.setModifier(userService.queryUserById(checkRecord.getLastModifier()));
		
		return result;
	}

	public static List<CheckRecordDto> convertCheckRecordListDTO(List<CheckRecord> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<CheckRecordDto>(0);
		}
		
		List<CheckRecordDto> resultList = new ArrayList<CheckRecordDto>();
		for (CheckRecord po : list) {
			resultList.add(convertCheckRecordDTO(po));
		}
		return resultList;
	}
	

	public static CheckRecord convertCheckRecordCreateEntity(CheckRecordCreateDto checkRecord) {
		BizAssert.notNull(checkRecord, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:checkRecord null");
		
		CheckRecord result = new CheckRecord();
		result.setId(checkRecord.getId());
		result.setCheckType(checkRecord.getCheckType());
		result.setCheckResult(checkRecord.getCheckResult());
		result.setCheckSuggest(checkRecord.getCheckSuggest());
		result.setUseTimes(checkRecord.getUseTimes());
		result.setScore(checkRecord.getScore());
		
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