package com.solar.bi.dal.dao.ext;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nebula.common.biz.dal.annotation.MyBatisRepository;

/**
 * 检查记录扩展 CheckRecordExtDao DAO接口对象
 * 
 * @author codegen
 * 
 * @version 20191205
 * 
 */
@MyBatisRepository
public interface CheckRecordExtDao {
	
	/**
	 * 统计使用人数
	 * @param condition
	 * @return
	 */
	Integer personNumber(@Param("condition")Map<String, Object> condition);
	
}