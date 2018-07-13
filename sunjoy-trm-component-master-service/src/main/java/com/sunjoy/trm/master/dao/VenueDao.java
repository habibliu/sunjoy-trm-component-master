package com.sunjoy.trm.master.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.trm.master.dao.criteria.VenueCriteria;
import com.sunjoy.trm.master.dao.entity.Venue;
import com.sunjoy.trm.master.dao.mapper.VenueMapper;

@Repository
public class VenueDao extends BaseDao<VenueMapper,Venue>{
	@Override
	protected void setMapperClass() {
		super.setMapperClass(VenueMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Venue.class);
		
	}
	
	/**
	 * 教练分页查询
	 * 
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Venue> queryVenueByPage(VenueCriteria criteria,PageInfo page) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("criteria", criteria);
		params.put("page", page);
		return this.getMapper().queryVenueByPage(params);

	}

	/**
	 * 获取学员表的总记录数
	 * @param criteria 过虑条件
	 * @return
	 */
	public long getVenueTotalCount(VenueCriteria criteria) {
		return this.getMapper().getVenueTotalCount(criteria);
	}

	/**
	 * 不分页查询
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Venue> queryVenue(VenueCriteria criteria) {

		return this.getMapper().queryVenue(criteria);

	}
	
	public void  addVenue(Venue venue) {
		logger.debug("addVenue:\n{}",venue);
		this.getMapper().addVenue(venue);
	}
	
	public int updateVenue(Venue venue) {
		logger.debug("updateVenue:\n{}",venue);
		return this.getMapper().updateVenue(venue);
	}
	
	public Venue findOne(String id) {
		return this.getMapper().findOne(id);
	}

}
