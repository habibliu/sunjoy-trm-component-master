package com.sunjoy.trm.master.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sunjoy.trm.master.dao.criteria.CoachCriteria;
import com.sunjoy.trm.master.dao.entity.Coach;

public interface CoachMapper {
	/**
	 * 教练分页查询接口
	 * @param criteria 查询条件
	 * @return
	 */
	List<Coach> queryCoachByPage(Map<String,Object> params);
	/**
	 * 教练总记录数接口
	 * @param criteria 查询条件
	 * @return
	 */
	long getCoachTotalCount(CoachCriteria criteria);
	
	/**
	 * 查询学员记录
	 * @param criteria 查询条件
	 * @return
	 */
	List<Coach> queryCoach(CoachCriteria criteria);
	
	/**
	 * 新增学员
	 * @param coach
	 * @return
	 */
	int addCoach(Coach coach);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Coach findOne(String id);
	
	/**
	 * 更新学员信息
	 * @param coach
	 * @return
	 */
	int updateCoach(Coach coach);
}
