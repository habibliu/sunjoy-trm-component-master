package com.sunjoy.trm.master.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.sunjoy.common.exception.CommonException;
import com.sunjoy.common.utils.RandomUtils;
import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.config.AbstractUnitTestSupport;
import com.sunjoy.trm.master.dao.criteria.VenueCriteria;
import com.sunjoy.trm.master.dao.entity.Venue;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.IVenueService;

public class VenueServiceImplTest extends AbstractUnitTestSupport{

	@Autowired
	private IVenueService venueService;
	
	private MockConfig mockConfig;
	
	private List<Venue> venues=new ArrayList<Venue>();
	
	@Before
	@Override
	public void setup() {
		super.setup();
		mockConfig = new MockConfig()
			    .byteRange((byte) 0, Byte.MAX_VALUE)
			    .shortRange((short) 0,(short) 2)
			    .intRange(100, 180)
			    .floatRange(0.0f, Float.MAX_EXPONENT)
			    .doubleRange(0.0, Double.MAX_VALUE)
			    .longRange(0, Long.MAX_VALUE)
			    .dateRange("2000-01-01", "2015-12-30")
			    .sizeRange(5, 10)
			    //.stringSeed("a", "b", "c")
			    .charSeed((char) 97, (char) 98);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueryByPage() {
		initVenues();
		for(Venue venue:venues) {
			this.venueService.add(venue);
		}
		VenueCriteria criteria = new VenueCriteria();
		criteria.setPageSize(10);
		Page<Venue> page=venueService.queryByPage(criteria);
		
		Assert.assertTrue(page != null);
		assertTrue(page.getCount()==10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testQuery() {
		initVenues();
		for(Venue venue:venues) {
			this.venueService.add(venue);
		}
		VenueCriteria criteria = new VenueCriteria();
		criteria.setName(this.venues.get(0).getName().substring(0, 1));
		criteria.setCurrentPage(null);
		criteria.setPageSize(null);
		List<Venue> queryVenues=venueService.query(criteria);
		assertTrue(queryVenues.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Venue venue = JMockData.mock(Venue.class, mockConfig);
		venue.setId(null);
		Venue newOne=this.venueService.add(venue);
		newOne.setName(RandomUtils.getChineseName());
		this.venueService.update(newOne);
		//assertTrue(venues.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Venue venue = JMockData.mock(Venue.class, mockConfig);
		venue.setId(null);
		Venue newOne=this.venueService.add(venue);
		assertTrue(newOne.getId()!=null);
		assertTrue(BaseStatus.VALID.getCode().equals(newOne.getStatus()));
	}
	
	@Test(expected=CommonException.class)
	@Transactional
	@Rollback(true)
	public void testAdd_CodeDuplicated() {
		Venue venue = JMockData.mock(Venue.class, mockConfig);
		venue.setId(null);
		this.venueService.add(venue);
		this.venueService.add(venue);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		Venue venue = JMockData.mock(Venue.class, mockConfig);
		venue.setId(null);
		Venue newOne=this.venueService.add(venue);
		
		int count=this.venueService.remove(newOne.getId());
		assertTrue(count>0);
	}

	private void initVenues() {
		if(venues==null ||venues.isEmpty()) {
			String code=JMockData.mock(java.lang.String.class);
			for(int i=0;i<21;i++) {
				Venue venue = JMockData.mock(Venue.class, mockConfig);
				venue.setId(null);
				venue.setName(code+i);
				venues.add(venue);
			}
		}
	}

}
