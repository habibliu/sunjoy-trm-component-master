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
import com.sunjoy.trm.master.dao.criteria.CourseCriteria;
import com.sunjoy.trm.master.dao.entity.Course;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.ICourseService;

public class CourseServiceImplTest extends AbstractUnitTestSupport{

	@Autowired
	private ICourseService courseService;
	
	private MockConfig mockConfig;
	
	private List<Course> courses=new ArrayList<Course>();
	
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
		initCourses();
		for(Course course:courses) {
			this.courseService.add(course);
		}
		CourseCriteria criteria = new CourseCriteria();
		criteria.setPageSize(10);
		Page<Course> page=courseService.queryByPage(criteria);
		
		Assert.assertTrue(page != null);
		assertTrue(page.size()==10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testQuery() {
		initCourses();
		for(Course course:courses) {
			this.courseService.add(course);
		}
		CourseCriteria criteria = new CourseCriteria();
		criteria.setName(this.courses.get(0).getName().substring(0, 1));
		criteria.setCurrentPage(null);
		criteria.setPageSize(null);
		List<Course> queryCourses=courseService.query(criteria);
		assertTrue(queryCourses.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Course course = JMockData.mock(Course.class, mockConfig);
		course.setId(null);
		Course newOne=this.courseService.add(course);
		newOne.setName(RandomUtils.getChineseName());
		this.courseService.update(newOne);
		//assertTrue(courses.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Course course = JMockData.mock(Course.class, mockConfig);
		course.setId(null);
		Course newOne=this.courseService.add(course);
		assertTrue(newOne.getId()!=null);
		assertTrue(BaseStatus.VALID.getCode().equals(newOne.getStatus()));
	}
	
	@Test(expected=CommonException.class)
	@Transactional
	@Rollback(true)
	public void testAdd_CodeDuplicated() {
		Course course = JMockData.mock(Course.class, mockConfig);
		course.setId(null);
		this.courseService.add(course);
		this.courseService.add(course);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		Course course = JMockData.mock(Course.class, mockConfig);
		course.setId(null);
		Course newOne=this.courseService.add(course);
		
		int count=this.courseService.remove(newOne.getId());
		assertTrue(count>0);
	}

	private void initCourses() {
		if(courses==null ||courses.isEmpty()) {
			String code=JMockData.mock(java.lang.String.class);
			for(int i=0;i<21;i++) {
				Course course = JMockData.mock(Course.class, mockConfig);
				course.setId(null);
				course.setName(code+i);
				courses.add(course);
			}
		}
	}

}
