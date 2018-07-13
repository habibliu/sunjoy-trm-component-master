package com.sunjoy.trm.master.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.framework.exception.CommonException;
import com.sunjoy.framework.utils.BeanUtils;
import com.sunjoy.framework.utils.RandomUtils;
import com.sunjoy.trm.master.dao.CourseDao;
import com.sunjoy.trm.master.dao.criteria.CourseCriteria;
import com.sunjoy.trm.master.dao.entity.Course;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.ICourseService;
import com.sunjoy.trm.master.utils.ExceptionConstant;

@Service(value = "courseService")
@Transactional
public class CourseServiceImpl implements ICourseService{

	@Autowired
	private CourseDao courseDao;

	@Override
	public Page<Course> queryByPage(CourseCriteria criteria,PageInfo page) {
		List<Course> courses = courseDao.queryCourseByPage(criteria,page);
		long count = courseDao.getCourseTotalCount(criteria);
		Page<Course> returnPage = new Page<>(page);
		returnPage.setCount(count);
		if (courses != null && !courses.isEmpty()) {
			returnPage.setRows(courses);
		}
		return returnPage;
	}

	@Override
	public List<Course> query(CourseCriteria criteria) {
		return courseDao.queryCourse(criteria);
	}

	@Override
	public Course update(Course course) {
		// step 1, 非空检查，加上UUID
		BeanUtils.checkEmptyFields(course, "id",  "name","level","phase","numberPerTerm","pricePerTerm");
		// step 2, 编号重复检验,如果编号被其他学员使用了，即抛异常
		CourseCriteria criteria = new CourseCriteria();
		criteria.setName(course.getName());
		List<Course> existsCourses = this.courseDao.queryCourse(criteria);
		if(!existsCourses.isEmpty()) {
			for(Course std:existsCourses) {
				if(std.getName().equals(course.getName()) && !course.getId().equals(std.getId())) {
					throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
				}
			}
		}
		this.courseDao.updateCourse(course);
		return course;
	}

	@Override
	public Course add(Course course) {
		// step 1, 非空检查
		BeanUtils.checkEmptyFields(course, "name","level","phase","numberPerTerm","pricePerTerm");
		// step 2, 编号重复检验
		CourseCriteria criteria = new CourseCriteria();
		criteria.setName(course.getName());
		List<Course> existsCourses = this.courseDao.queryCourse(criteria);
		if (existsCourses != null && !existsCourses.isEmpty()) {
			throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
		}
		// step 3, 保存到数据库
		if (StringUtils.isEmpty(course.getId())) {
			course.setId(RandomUtils.createUUID());
		}
		course.setStatus(BaseStatus.VALID.getCode());
		this.courseDao.addCourse(course);
		return course;
	}

	@Override
	public int remove(String id) {
		if(!BeanUtils.isEmpty(id)) {
			Course course=this.courseDao.findOne(id);
			course.setStatus(BaseStatus.DELETED.getCode());
			return this.courseDao.updateCourse(course);
		}
		return 0;
	}

	@Override
	public Course get(String id) {
		if(!BeanUtils.isEmpty(id)) {
			return this.courseDao.findOne(id);
		}
		return null;
	}

}
