package com.sunjoy.trm.master.dao.criteria;

import com.sunjoy.trm.master.dao.entity.Student;

/**
 * 学生查询条件类
 *
 */
public class StudentCriteria extends Student {

	private Integer pageSize = 10;

	private Integer currentPage = 1;

	private String agePhase;// 年龄阶段，格式：ageStart-ageEnd,如13-19

	public String getAgePhase() {
		return agePhase;
	}

	public void setAgePhase(String agePhase) {
		this.agePhase = agePhase;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage ;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
