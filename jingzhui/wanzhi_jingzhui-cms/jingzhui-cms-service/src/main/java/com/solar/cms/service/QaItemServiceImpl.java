package com.solar.cms.service;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.solar.common.core.enums.ResultCodeEnum;
import com.solar.cms.converter.QaItemConverterFactory;
import com.solar.cms.service.QaItemService;
import com.solar.cms.dal.crud.QaItemCrudService;
import com.solar.cms.entity.QaItem;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.dto.QaItemDto;
import com.solar.cms.query.QaItemQuery;

/**
 * 问题答案 QaItem 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Service("qaItemService")
public class QaItemServiceImpl extends BaseServiceImpl implements QaItemService {
	
	@Autowired
	private QaItemCrudService qaItemCrudService;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public QaItemDto queryQaItemById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		QaItem qaItem = qaItemCrudService.queryById(id);
		return QaItemConverterFactory.convertQaItemDTO(qaItem);
	}
 
	@Override
	public List<QaItemDto> queryQaItemAllList() throws BizException {
		List<QaItem> list = qaItemCrudService.queryAllList();

		return QaItemConverterFactory.convertQaItemListDTO(list);
	}
	
	@Override
	public List<QaItemDto> queryQaItemList(final QaItemQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<QaItem> list = qaItemCrudService.queryList(search.getParameters());
		
		return QaItemConverterFactory.convertQaItemListDTO(list);
	}
	
	@Override
	public Pagination<QaItemDto> queryPageQaItem(final QaItemQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("categroyId", query.getCategroyId());

		String order =  StringUtils.isNotEmpty(query.getOrder()) ? query.getOrder() : "createDate";
		SortByEnum sortby = StringUtils.isNotEmpty(query.getSortby()) ? SortByEnum.valueOf(query.getSortby().toUpperCase()) : SortByEnum.ASC;
		
		// query list
		List<QaItem> list = qaItemCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), order, sortby);
		// query totals
		int totals = qaItemCrudService.count(search.getParameters());

		List<QaItemDto> resultList = QaItemConverterFactory.convertQaItemListDTO(list);
		
		return new Pagination<QaItemDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public QaItemDto saveQaItem(final QaItemDto qaItem) throws BizException {
		BizAssert.notNull(qaItem, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaItem为空");
		
		return (QaItemDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {

				String id= sequenceService.getLongNextVal(CmsSequenceKeyEnum.BASE_QA_ITEM_ID.getKey()) + "";
				QaItem createModel = QaItemConverterFactory.convertQaItemEntity(qaItem);
				createModel.setId(id);
				qaItemCrudService.save(createModel);
				
				QaItemDto result = QaItemConverterFactory.convertQaItemDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateQaItemById(final QaItemDto qaItem) throws BizException {
		BizAssert.notNull(qaItem, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaItem为空");
		BizAssert.notEmpty(qaItem.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (qaItemCrudService.queryById(qaItem.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + qaItem.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				QaItem updateModel = QaItemConverterFactory.convertQaItemEntity(qaItem);
				qaItemCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteQaItemById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (qaItemCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = qaItemCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteQaItemByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteQaItemById(id);
		}
	}
 
}
