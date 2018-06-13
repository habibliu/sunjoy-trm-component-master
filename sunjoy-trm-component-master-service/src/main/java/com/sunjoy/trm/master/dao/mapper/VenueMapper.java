package com.sunjoy.trm.master.dao.mapper;

import java.util.List;

import com.sunjoy.trm.master.dao.criteria.VenueCriteria;
import com.sunjoy.trm.master.dao.entity.Venue;

public interface VenueMapper {
	/**
	 * 分页查询接口
	 * @param criteria 查询条件
	 * @return
	 */
	List<Venue> queryVenueByPage(VenueCriteria criteria);
	/**
	 * 总记录数接口
	 * @param criteria 查询条件
	 * @return
	 */
	long getVenueTotalCount(VenueCriteria criteria);
	
	/**
	 * 查询学员记录
	 * @param criteria 查询条件
	 * @return
	 */
	List<Venue> queryVenue(VenueCriteria criteria);
	
	/**
	 * 新增课程
	 * @param venue
	 * @return
	 */
	int addVenue(Venue venue);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Venue findOne(String id);
	
	/**
	 * 更新课程信息
	 * @param venue
	 * @return
	 */
	int updateVenue(Venue venue);
}
