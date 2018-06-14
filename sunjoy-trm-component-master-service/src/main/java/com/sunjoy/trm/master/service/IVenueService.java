package com.sunjoy.trm.master.service;

import java.util.List;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.master.dao.criteria.VenueCriteria;
import com.sunjoy.trm.master.dao.entity.Venue;

public interface IVenueService {
	/**
	 * 分页查询
	 */
	public Page<Venue> queryByPage(VenueCriteria criteria);
	
	/**
	 * 不分页查询
	 * @param criteria
	 * @return
	 */
	public List<Venue> query(VenueCriteria criteria);
	
	/**
	 * 更新学员资料
	 * @param Venue
	 * @return
	 */
	public Venue update(Venue venue);
	
	/**
	 * 新增学员档案
	 * @param Venue
	 * @return
	 */
	public Venue add(Venue venue);
	/**
	 * 根据主键删除学员档案(逻辑删除)
	 * @param id
	 * @return
	 */
	public int remove(String  id);
	
	public Venue get(String id);
}
