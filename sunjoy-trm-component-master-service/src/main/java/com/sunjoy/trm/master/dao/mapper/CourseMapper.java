package com.sunjoy.trm.master.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sunjoy.trm.master.dao.criteria.CourseCriteria;
import com.sunjoy.trm.master.dao.entity.Course;

public interface CourseMapper {
	/**
	 * 分页查询接口
	 * @param criteria 查询条件
	 * @return
	 */
	List<Course> queryCourseByPage(Map<String,Object> params);
	/**
	 * 总记录数接口
	 * @param criteria 查询条件
	 * @return
	 */
	long getCourseTotalCount(CourseCriteria criteria);
	
	/**
	 * 查询学员记录
	 * @param criteria 查询条件
	 * @return
	 */
	List<Course> queryCourse(CourseCriteria criteria);
	
	/**
	 * 新增课程
	 * @param course
	 * @return
	 */
	int addCourse(Course course);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Course findOne(String id);
	
	/**
	 * 更新课程信息
	 * @param course
	 * @return
	 */
	int updateCourse(Course course);
}
