package com.sunjoy.trm.master.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.utils.BeanUtils;
import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.trm.master.dao.criteria.StudentCriteria;
import com.sunjoy.trm.master.dao.entity.Student;
import com.sunjoy.trm.master.dao.mapper.StudentMapper;

@Repository
public class StudentDao extends BaseDao<StudentMapper, Student> {
	
	@Override
	protected void setMapperClass() {
		super.setMapperClass(StudentMapper.class);
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Student.class);
	}

	/**
	 * 学生分页查询
	 * 
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Student> queryStudentByPage(StudentCriteria criteria) {
		BeanUtils.checkEmptyFields(criteria,"currentPage","pageSize");
		return this.getMapper().queryStudentByPage(criteria);

	}

	/**
	 * 获取学员表的总记录数
	 * @param criteria 过虑条件
	 * @return
	 */
	public long getStudentTotalCount(StudentCriteria criteria) {
		return this.getMapper().getStudentTotalCount(criteria);
	}

	/**
	 * 不分页查询
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Student> queryStudent(StudentCriteria criteria) {

		return this.getMapper().queryStudent(criteria);

	}
	
	public void  addStudent(Student student) {
		logger.debug("addStudent:\n{}",student);
		this.getMapper().addStudent(student);
	}
	
	public int updateStudent(Student student) {
		logger.debug("updateStudent:\n{}",student);
		return this.getMapper().updateStudent(student);
	}
	
	public Student findOne(String uuid) {
		return this.getMapper().findOne(uuid);
	}
}
