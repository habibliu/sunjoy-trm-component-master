package com.sunjoy.trm.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.common.exception.CommonException;
import com.sunjoy.common.utils.BeanUtils;
import com.sunjoy.common.utils.RandomUtils;
import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.trm.master.dao.StudentDao;
import com.sunjoy.trm.master.dao.criteria.StudentCriteria;
import com.sunjoy.trm.master.dao.entity.Student;
import com.sunjoy.trm.master.dao.enums.StudentStatus;
import com.sunjoy.trm.master.service.IStudentService;
import com.sunjoy.trm.master.utils.ExceptionConstant;

@Service(value = "studentService")
@Transactional
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	public Page<Student> queryByPage(StudentCriteria criteria) {
		List<Student> students = studentDao.queryStudentByPage(criteria);
		long count = studentDao.getStudentTotalCount(criteria);
		Page<Student> page = new Page<>();
		page.setCount(count);
		page.setPageSize(criteria.getPageSize());
		page.setCurrentPage(criteria.getCurrentPage());
		if (students != null && !students.isEmpty()) {
			page.setRows(students);
		}
		return page;
	}

	@Override
	public List<Student> query(StudentCriteria criteria) {
		return studentDao.queryStudent(criteria);
	}

	@Override
	public Student update(Student student) {
		// step 1, 非空检查，加上UUID
		BeanUtils.checkEmptyFields(student, "uuid", "code", "name", "parentName");
		// step 2, 编号重复检验,如果编号被其他学员使用了，即抛异常
		StudentCriteria criteria = new StudentCriteria();
		criteria.setCode(student.getCode());
		List<Student> existsStudents = this.studentDao.queryStudent(criteria);
		if(!existsStudents.isEmpty()) {
			for(Student std:existsStudents) {
				if(std.getCode().equals(student.getCode()) && !student.getId().equals(std.getId())) {
					throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
				}
			}
		}
		this.studentDao.updateStudent(student);
		return student;
	}

	@Override
	public Student add(Student student) {
		// step 1, 非空检查
		BeanUtils.checkEmptyFields(student, "code", "name", "parentName");
		// step 2, 编号重复检验
		StudentCriteria criteria = new StudentCriteria();
		criteria.setCode(student.getCode());
		List<Student> existsStudents = this.studentDao.queryStudent(criteria);
		if (existsStudents != null && !existsStudents.isEmpty()) {
			throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
		}
		// step 3, 保存到数据库
		if (student.getId() == null) {
			student.setId(RandomUtils.createUUID());
		}
		student.setStatus(StudentStatus.VALID.getCode());
		this.studentDao.addStudent(student);
		return student;
	}

	@Override
	public int remove(String uuid) {
		if(!BeanUtils.isEmpty(uuid)) {
			Student student=this.studentDao.findOne(uuid);
			student.setStatus(StudentStatus.DELETED.getCode());
			return this.studentDao.updateStudent(student);
		}
		return 0;
	}

}
