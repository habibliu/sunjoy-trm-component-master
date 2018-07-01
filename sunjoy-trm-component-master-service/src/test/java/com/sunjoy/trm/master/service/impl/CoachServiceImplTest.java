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
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.trm.config.AbstractUnitTestSupport;
import com.sunjoy.trm.master.dao.criteria.CoachCriteria;
import com.sunjoy.trm.master.dao.entity.Coach;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.ICoachService;

public class CoachServiceImplTest extends AbstractUnitTestSupport{

	@Autowired
	private ICoachService coachService;
	
	private MockConfig mockConfig;
	
	private List<Coach> coachs=new ArrayList<Coach>();
	
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
		initCoachs();
		for(Coach coach:coachs) {
			this.coachService.add(coach);
		}
		CoachCriteria criteria = new CoachCriteria();
		Page<Coach> page=coachService.queryByPage(criteria,new PageInfo());
		
		Assert.assertTrue(page != null);
		assertTrue(page.getCount()<10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testQuery() {
		initCoachs();
		for(Coach coach:coachs) {
			this.coachService.add(coach);
		}
		CoachCriteria criteria = new CoachCriteria();
		criteria.setCode(this.coachs.get(0).getCode().substring(0, 1));
		List<Coach> queryCoachs=coachService.query(criteria);
		assertTrue(queryCoachs.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Coach coach = JMockData.mock(Coach.class, mockConfig);
		coach.setId(null);
		Coach newOne=this.coachService.add(coach);
		newOne.setName(RandomUtils.getChineseName());
		this.coachService.update(newOne);
		//assertTrue(coachs.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Coach coach = JMockData.mock(Coach.class, mockConfig);
		coach.setId(null);
		Coach newOne=this.coachService.add(coach);
		assertTrue(newOne.getId()!=null);
		assertTrue(BaseStatus.VALID.getCode().equals(newOne.getStatus()));
	}
	
	@Test(expected=CommonException.class)
	@Transactional
	@Rollback(true)
	public void testAdd_CodeDuplicated() {
		Coach coach = JMockData.mock(Coach.class, mockConfig);
		coach.setId(null);
		this.coachService.add(coach);
		this.coachService.add(coach);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		Coach coach = JMockData.mock(Coach.class, mockConfig);
		coach.setId(null);
		Coach newOne=this.coachService.add(coach);
		
		int count=this.coachService.remove(newOne.getId());
		assertTrue(count>0);
	}

	private void initCoachs() {
		if(coachs==null ||coachs.isEmpty()) {
			String code=JMockData.mock(java.lang.String.class);
			for(int i=0;i<21;i++) {
				Coach coach = JMockData.mock(Coach.class, mockConfig);
				coach.setId(null);
				coach.setCode(code+i);
				coachs.add(coach);
			}
		}
	}

}
