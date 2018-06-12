package com.sunjoy.trm.master.dao.criteria;

import com.sunjoy.trm.master.dao.entity.Coach;

public class CoachCriteria extends Coach{
	private Integer pageSize = 10;

	private Integer currentPage = 1;


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
