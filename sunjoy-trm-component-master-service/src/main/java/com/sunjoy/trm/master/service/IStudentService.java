package com.sunjoy.trm.master.service;

import java.util.List;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.trm.master.dao.criteria.StudentCriteria;
import com.sunjoy.trm.master.dao.entity.Student;

public interface IStudentService {
	/**
	 * 分页查询
	 */
	public Page<Student> queryByPage(StudentCriteria criteria);

	/**
	 * 不分页查询
	 * 
	 * @param criteria
	 * @return
	 */
	public List<Student> query(StudentCriteria criteria);

	/**
	 * 更新学员资料
	 * 
	 * @param student
	 * @return
	 */
	public Student update(Student student);

	/**
	 * 新增学员档案
	 * 
	 * @param student
	 * @return
	 */
	public Student add(Student student);

	/**
	 * 根据主键删除学员档案(逻辑删除)
	 * 
	 * @param id
	 * @return
	 */
	public int remove(String id);

	/**
	 * 根据主键获取学员档案
	 * @param id
	 * @return
	 */
	public Student get(String id);
}
