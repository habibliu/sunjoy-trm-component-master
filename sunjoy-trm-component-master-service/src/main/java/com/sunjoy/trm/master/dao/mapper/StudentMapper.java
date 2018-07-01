package com.sunjoy.trm.master.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sunjoy.trm.master.dao.criteria.StudentCriteria;
import com.sunjoy.trm.master.dao.entity.Student;

public interface StudentMapper {
	/**
	 * 学生分页查询接口
	 * @param criteria 查询条件
	 * @return
	 */
	List<Student> queryStudentByPage(Map<String,Object> params);
	/**
	 * 学生总记录数接口
	 * @param criteria 查询条件
	 * @return
	 */
	long getStudentTotalCount(StudentCriteria criteria);
	
	/**
	 * 查询学员记录
	 * @param criteria 查询条件
	 * @return
	 */
	List<Student> queryStudent(StudentCriteria criteria);
	
	/**
	 * 新增学员
	 * @param student
	 * @return
	 */
	int addStudent(Student student);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Student findOne(String id);
	
	/**
	 * 更新学员信息
	 * @param student
	 * @return
	 */
	int updateStudent(Student student);
}
