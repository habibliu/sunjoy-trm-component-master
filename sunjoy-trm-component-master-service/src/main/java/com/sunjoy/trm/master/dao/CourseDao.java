package com.sunjoy.trm.master.dao;

import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.trm.master.dao.entity.Course;
import com.sunjoy.trm.master.dao.mapper.CourseMapper;

public class CourseDao extends BaseDao<CourseMapper,Course>{

	@Override
	protected void setMapperClass() {
		super.setMapperClass(CourseMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Course.class);
		
	}

}
