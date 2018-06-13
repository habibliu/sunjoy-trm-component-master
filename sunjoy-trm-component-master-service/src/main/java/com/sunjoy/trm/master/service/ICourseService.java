package com.sunjoy.trm.master.service;

import java.util.List;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.master.dao.criteria.CourseCriteria;
import com.sunjoy.trm.master.dao.entity.Course;

public interface ICourseService {
	/**
	 * 分页查询
	 */
	public Page<Course> queryByPage(CourseCriteria criteria);
	
	/**
	 * 不分页查询
	 * @param criteria
	 * @return
	 */
	public List<Course> query(CourseCriteria criteria);
	
	/**
	 * 更新学员资料
	 * @param Course
	 * @return
	 */
	public Course update(Course course);
	
	/**
	 * 新增学员档案
	 * @param Course
	 * @return
	 */
	public Course add(Course course);
	/**
	 * 根据主键删除学员档案(逻辑删除)
	 * @param id
	 * @return
	 */
	public int remove(String  id);
	
	public Course get(String id);
}
