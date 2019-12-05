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
import com.solar.cms.converter.QaCategroyConverterFactory;
import com.solar.cms.dal.crud.QaCategroyCrudService;
import com.solar.cms.dto.QaCategroyDto;
import com.solar.cms.entity.QaCategroy;
import com.solar.cms.enums.CmsSequenceKeyEnum;
import com.solar.cms.query.QaCategroyQuery;
import com.solar.common.core.enums.ResultCodeEnum;

/**
 * 问题分类 QaCategroy 业务接口实现
 * 
 * @author codegen
 * 
 * @version 20191204
 * 
 */
@Service("qaCategroyService")
public class QaCategroyServiceImpl extends BaseServiceImpl implements QaCategroyService {
	
	@Autowired
	private QaCategroyCrudService qaCategroyCrudService;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public QaCategroyDto queryQaCategroyById(String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		QaCategroy qaCategroy = qaCategroyCrudService.queryById(id);
		return QaCategroyConverterFactory.convertQaCategroyDTO(qaCategroy);
	}
 
	@Override
	public List<QaCategroyDto> queryQaCategroyAllList() throws BizException {
		List<QaCategroy> list = qaCategroyCrudService.queryAllList();

		return QaCategroyConverterFactory.convertQaCategroyListDTO(list);
	}
	
	@Override
	public List<QaCategroyDto> queryQaCategroyList(final QaCategroyQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		List<QaCategroy> list = qaCategroyCrudService.queryList(search.getParameters());
		
		return QaCategroyConverterFactory.convertQaCategroyListDTO(list);
	}
	
	@Override
	public Pagination<QaCategroyDto> queryPageQaCategroy(final QaCategroyQuery query) throws BizException {
		BizAssert.notNull(query, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:query为空");

		Search search = new Search();
		search.setPage(query.getPage());
		search.setCount(query.getCount());
		search.safeAddParamter("keyword", query.getKeyword());
		
		// query list
		List<QaCategroy> list = qaCategroyCrudService.queryPage(search.getParameters(), search.offset(), search.getCount(), "id", SortByEnum.DESC);
		// query totals
		int totals = qaCategroyCrudService.count(search.getParameters());

		List<QaCategroyDto> resultList = QaCategroyConverterFactory.convertQaCategroyListDTO(list);
		
		return new Pagination<QaCategroyDto>(search.getPage(), search.getCount(), totals, resultList);
	}

	@Override
	public QaCategroyDto saveQaCategroy(final QaCategroyDto qaCategroy) throws BizException {
		BizAssert.notNull(qaCategroy, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaCategroy为空");
		
		return (QaCategroyDto) this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				
			}
			
			@Override
			public Object execute() throws BizException {

				String id= sequenceService.getLongNextVal(CmsSequenceKeyEnum.BASE_QA_CATEGROY_ID.getKey()) + "";
				QaCategroy createModel = QaCategroyConverterFactory.convertQaCategroyEntity(qaCategroy);
				createModel.setId(id);
				qaCategroyCrudService.save(createModel);
				
				QaCategroyDto result = QaCategroyConverterFactory.convertQaCategroyDTO(createModel);
				return result;
			}
			
		});
	}
	
	@Override
	public void updateQaCategroyById(final QaCategroyDto qaCategroy) throws BizException {
		BizAssert.notNull(qaCategroy, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:qaCategroy为空");
		BizAssert.notEmpty(qaCategroy.getId(), ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");

		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (qaCategroyCrudService.queryById(qaCategroy.getId()) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "修改数据不存在:ID[" + qaCategroy.getId() + "]");
				}				
			}
			
			@Override
			public Object execute() throws BizException {
				QaCategroy updateModel = QaCategroyConverterFactory.convertQaCategroyEntity(qaCategroy);
				qaCategroyCrudService.update(updateModel);
				
				return true;
			}
			
		});
	}
	
	@Override
	public void deleteQaCategroyById(final String id) throws BizException {
		BizAssert.notEmpty(id, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:id为空");
		
		this.serviceTemplate.executeWithTransaction(new ServiceCheckCallback(){
			
			@Override
			public void check() throws BizException {
				if (qaCategroyCrudService.queryById(id) == null) {
					throw new BizException(ResultCodeEnum.DATA_NOT_FOUND.name(),
							ResultCodeEnum.DATA_NOT_FOUND.getCode(), "删除数据不存在:ID[" + id + "]");
				}
			}
			
			@Override
			public Object execute() throws BizException {
				
				// execute delete 
				int result = qaCategroyCrudService.deleteById(id);
				
				return result == 1;
			}
			
		});
	}

	@Override
	public void deleteQaCategroyByIds(List<String> ids) throws BizException {
		BizAssert.notNull(ids, ResultCodeEnum.NULL_ARGUMENT.name(), ResultCodeEnum.NULL_ARGUMENT.getCode(), "参数:ids为空");
		
		for (String id : ids) {
			deleteQaCategroyById(id);
		}
	}
 
}
