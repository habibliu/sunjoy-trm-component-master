package com.sunjoy.trm.master.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.utils.BeanUtils;
import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.trm.master.dao.criteria.CourseCriteria;
import com.sunjoy.trm.master.dao.entity.Course;
import com.sunjoy.trm.master.dao.mapper.CourseMapper;

@Repository
public class CourseDao extends BaseDao<CourseMapper,Course>{

	@Override
	protected void setMapperClass() {
		super.setMapperClass(CourseMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Course.class);
		
	}
	
	/**
	 * 分页查询
	 * 
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Course> queryCourseByPage(CourseCriteria criteria) {
		BeanUtils.checkEmptyFields(criteria,"currentPage","pageSize");
		return this.getMapper().queryCourseByPage(criteria);

	}

	/**
	 * 获取总记录数
	 * @param criteria 过虑条件
	 * @return
	 */
	public long getCourseTotalCount(CourseCriteria criteria) {
		return this.getMapper().getCourseTotalCount(criteria);
	}

	/**
	 * 不分页查询
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Course> queryCourse(CourseCriteria criteria) {

		return this.getMapper().queryCourse(criteria);

	}
	
	public void  addCourse(Course student) {
		logger.debug("addCourse:\n{}",student);
		this.getMapper().addCourse(student);
	}
	
	public int updateCourse(Course student) {
		logger.debug("updateCourse:\n{}",student);
		return this.getMapper().updateCourse(student);
	}
	
	public Course findOne(String id) {
		return this.getMapper().findOne(id);
	}

}
