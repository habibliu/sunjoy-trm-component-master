package com.sunjoy.trm.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.common.exception.CommonException;
import com.sunjoy.common.utils.BeanUtils;
import com.sunjoy.common.utils.RandomUtils;
import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.master.dao.VenueDao;
import com.sunjoy.trm.master.dao.criteria.VenueCriteria;
import com.sunjoy.trm.master.dao.entity.Venue;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.IVenueService;
import com.sunjoy.trm.master.utils.ExceptionConstant;

@Service(value = "venueService")
@Transactional
public class VenueServiceImpl implements IVenueService {

	@Autowired
	private VenueDao venueDao;

	@Override
	public Page<Venue> queryByPage(VenueCriteria criteria) {
		List<Venue> venues = venueDao.queryVenueByPage(criteria);
		long count = venueDao.getVenueTotalCount(criteria);
		Page<Venue> page = new Page<>();
		page.setCount(count);
		page.setPageSize(criteria.getPageSize());
		page.setCurrentPage(criteria.getCurrentPage());
		if (venues != null && !venues.isEmpty()) {
			page.setRows(venues);
		}
		return page;
	}

	@Override
	public List<Venue> query(VenueCriteria criteria) {
		return venueDao.queryVenue(criteria);
	}

	@Override
	public Venue update(Venue venue) {
		// step 1, 非空检查，加上UUID
		BeanUtils.checkEmptyFields(venue, "id", "code", "name");
		// step 2, 编号重复检验,如果编号被其他学员使用了，即抛异常
		VenueCriteria criteria = new VenueCriteria();
		criteria.setName(venue.getName());
		List<Venue> existsVenues = this.venueDao.queryVenue(criteria);
		if(!existsVenues.isEmpty()) {
			for(Venue std:existsVenues) {
				if(std.getName().equals(venue.getName()) && !venue.getId().equals(std.getId())) {
					throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
				}
			}
		}
		this.venueDao.updateVenue(venue);
		return venue;
	}

	@Override
	public Venue add(Venue venue) {
		// step 1, 非空检查
		BeanUtils.checkEmptyFields(venue, "code", "name");
		// step 2, 编号重复检验
		VenueCriteria criteria = new VenueCriteria();
		criteria.setName(venue.getName());
		List<Venue> existsVenues = this.venueDao.queryVenue(criteria);
		if (existsVenues != null && !existsVenues.isEmpty()) {
			throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
		}
		// step 3, 保存到数据库
		if (venue.getId() == null) {
			venue.setId(RandomUtils.createUUID());
		}
		venue.setStatus(BaseStatus.VALID.getCode());
		this.venueDao.addVenue(venue);
		return venue;
	}

	@Override
	public int remove(String id) {
		if(!BeanUtils.isEmpty(id)) {
			Venue venue=this.venueDao.findOne(id);
			venue.setStatus(BaseStatus.DELETED.getCode());
			return this.venueDao.updateVenue(venue);
		}
		return 0;
	}

	@Override
	public Venue get(String id) {
		if(!BeanUtils.isEmpty(id)) {
			return this.venueDao.findOne(id);
		}
		return null;
	}

}
