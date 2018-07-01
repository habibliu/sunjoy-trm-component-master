package com.sunjoy.trm.master.dao.criteria;

import com.sunjoy.trm.master.dao.entity.Student;

/**
 * 学生查询条件类
 *
 */
public class StudentCriteria extends Student {


	private String agePhase;// 年龄阶段，格式：ageStart-ageEnd,如13-19

	public String getAgePhase() {
		return agePhase;
	}

	public void setAgePhase(String agePhase) {
		this.agePhase = agePhase;
	}

}
