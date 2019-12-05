package com.solar.cms.service;
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
import com.nebula.sequence.SequenceService;
import com.solar.cms.converter.CmsFeedbackConverterFactory;
import com.solar.cms.dal.crud.CmsFeedbackCrudService;
import com.solar.cms.dto.CmsFeedbackDto;
import com.solar.cms.entity.CmsFeedback;
import com.solar.cms.enums.CmsFeedbackEnum;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.query.CmsFeedbackQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 意见反馈 CmsFeedback 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20181218
 * 
 */
@Service("cmsFeedbackService")
public class CmsFeedbackServiceImpl extends BaseServiceImpl implements CmsFeedbackService {
	
	@Autowired
	private CmsFeedbackCrudService cmsFeedbackCrudService;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public CmsFeedbackDto queryCmsFeedbackById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		CmsFeedback cmsFeedback = cmsFeedbackCrudService.queryById(id);
		return CmsFeedbackConverterFactory.convertCmsFeedbackDTO(cmsFeedback);
	}
 
	@Override
	public List<CmsFeedbackDto> queryCmsFeedbackAllList() throws BizException {
		List<CmsFeedback> list = cmsFeedbackCrudService.queryAllList();

		return CmsFeedbackConverterFactory.convertCmsFeedbackListDTO(list);
	}
	
	@Override
	public List<CmsFeedbackDto> queryCmsFeedbackList(final CmsFeedbackQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<CmsFeedback> list = cmsFeedbackCrudService.queryList(search.getParameters());
		
		return CmsFeedbackConverterFactory.convertCmsFeedbackListDTO(list);
	}
	
	@Override
	public Pagination<CmsFeedbackDto> queryPageCmsFeedback(final CmsFeedbackQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<CmsFeedback> list = cmsFeedbackCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = cmsFeedbackCrudService.count(search.getParameters());

		List<CmsFeedbackDto> resultList = CmsFeedbackConverterFactory.convertCmsFeedbackListDTO(list);
		
		return new Pagination<CmsFeedbackDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public CmsFeedbackDto saveCmsFeedback(final CmsFeedbackDto cmsFeedback) throws BizException {
		BizAssert.notNull(cmsFeedback, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsFeedback为空");
		BizAssert.notEmpty(cmsFeedback.getCategoryId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:categoryId为空");
		BizAssert.notEmpty(cmsFeedback.getContent(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:content为空");
		BizAssert.notEmpty(cmsFeedback.getUserId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:userId为空");
		
		return (CmsFeedbackDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {
				
				String feedbackId = sequenceService.getLongNextVal(CmsSequenceKeyEnum.CMS_FEEDBACK_ID.name()) + "";
				
				CmsFeedback createModel = CmsFeedbackConverterFactory.convertCmsFeedbackEntity(cmsFeedback);
				createModel.setId(feedbackId);
				createModel.setStatus(CmsFeedbackEnum.Status.READY.getCode());
				cmsFeedbackCrudService.save(createModel);
				
				CmsFeedbackDto result = CmsFeedbackConverterFactory.convertCmsFeedbackDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateCmsFeedbackById(final CmsFeedbackDto cmsFeedback) throws BizException {
		BizAssert.notNull(cmsFeedback, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:cmsFeedback为空");
		BizAssert.notEmpty(cmsFeedback.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsFeedbackCrudService.queryById(cmsFeedback.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + cmsFeedback.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				CmsFeedback updateModel = CmsFeedbackConverterFactory.convertCmsFeedbackEntity(cmsFeedback);
				cmsFeedbackCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteCmsFeedbackById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (cmsFeedbackCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = cmsFeedbackCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteCmsFeedbackByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteCmsFeedbackById(id);
		}
	}
 
}
