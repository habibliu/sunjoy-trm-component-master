package com.sunjoy.trm.master.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Assert;
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
import com.sunjoy.trm.master.dao.criteria.StudentCriteria;
import com.sunjoy.trm.master.dao.entity.Student;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.IStudentService;

public class StudentServiceImplTest extends AbstractUnitTestSupport{

	@Autowired
	private IStudentService studentService;
	
	private MockConfig mockConfig;
	
	private List<Student> students=new ArrayList<Student>();
	
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
		initStudents();
		for(Student student:students) {
			this.studentService.add(student);
		}
		StudentCriteria criteria = new StudentCriteria();
		Page<Student> page=studentService.queryByPage(criteria,new PageInfo());
		
		Assert.assertTrue(page != null && page.getCount()>1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testQuery() {
		initStudents();
		for(Student student:students) {
			this.studentService.add(student);
		}
		StudentCriteria criteria = new StudentCriteria();
		criteria.setCode(this.students.get(0).getCode().substring(0, 1));
		List<Student> queryStudents=studentService.query(criteria);
		assertTrue(queryStudents.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Student student = JMockData.mock(Student.class, mockConfig);
		student.setId(null);
		Student newOne=this.studentService.add(student);
		newOne.setName(RandomUtils.getChineseName());
		this.studentService.update(newOne);
		//assertTrue(students.size()>10);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Student student = JMockData.mock(Student.class, mockConfig);
		student.setId(null);
		Student newOne=this.studentService.add(student);
		assertTrue(newOne.getId()!=null);
		assertTrue(BaseStatus.VALID.getCode().equals(newOne.getStatus()));
	}
	
	@Test(expected=CommonException.class)
	@Transactional
	@Rollback(true)
	public void testAdd_CodeDuplicated() {
		Student student = JMockData.mock(Student.class, mockConfig);
		student.setId(null);
		this.studentService.add(student);
		this.studentService.add(student);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		Student student = JMockData.mock(Student.class, mockConfig);
		student.setId(null);
		Student newOne=this.studentService.add(student);
		
		int count=this.studentService.remove(newOne.getId());
		assertTrue(count>0);
	}

	private void initStudents() {
		if(students==null ||students.isEmpty()) {
			String code=JMockData.mock(java.lang.String.class);
			for(int i=0;i<21;i++) {
				Student student = JMockData.mock(Student.class, mockConfig);
				student.setId(null);
				student.setCode(code+i);
				students.add(student);
			}
		}
	}
}
