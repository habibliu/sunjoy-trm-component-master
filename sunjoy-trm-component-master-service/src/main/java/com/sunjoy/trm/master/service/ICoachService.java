package com.sunjoy.trm.master.service;

import java.util.List;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.master.dao.criteria.CoachCriteria;
import com.sunjoy.trm.master.dao.entity.Coach;

public interface ICoachService {
	/**
	 * 分页查询
	 */
	public Page<Coach> queryByPage(CoachCriteria criteria);
	
	/**
	 * 不分页查询
	 * @param criteria
	 * @return
	 */
	public List<Coach> query(CoachCriteria criteria);
	
	/**
	 * 更新学员资料
	 * @param Coach
	 * @return
	 */
	public Coach update(Coach Coach);
	
	/**
	 * 新增学员档案
	 * @param Coach
	 * @return
	 */
	public Coach add(Coach Coach);
	/**
	 * 根据主键删除学员档案(逻辑删除)
	 * @param id
	 * @return
	 */
	public int remove(String  id);
	
	public Coach get(String id);
}
