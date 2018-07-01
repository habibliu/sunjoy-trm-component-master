package com.sunjoy.trm.master.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.utils.BeanUtils;
import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.trm.master.dao.criteria.CoachCriteria;
import com.sunjoy.trm.master.dao.entity.Coach;
import com.sunjoy.trm.master.dao.mapper.CoachMapper;
/**
 * 教练DAO
 * @author liuganchao
 *
 */
@Repository
public class CoachDao extends BaseDao<CoachMapper,Coach> {

	@Override
	protected void setMapperClass() {
		super.setMapperClass(CoachMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Coach.class);
		
	}
	
	/**
	 * 教练分页查询
	 * 
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Coach> queryCoachByPage(CoachCriteria criteria,PageInfo page) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("criteria", criteria);
		params.put("page", page);
		return this.getMapper().queryCoachByPage(params);

	}

	/**
	 * 获取学员表的总记录数
	 * @param criteria 过虑条件
	 * @return
	 */
	public long getCoachTotalCount(CoachCriteria criteria) {
		return this.getMapper().getCoachTotalCount(criteria);
	}

	/**
	 * 不分页查询
	 * @param criteria 过虑条件
	 * @return
	 */
	public List<Coach> queryCoach(CoachCriteria criteria) {

		return this.getMapper().queryCoach(criteria);

	}
	
	public void  addCoach(Coach coach) {
		logger.debug("addCoach:\n{}",coach);
		this.getMapper().addCoach(coach);
	}
	
	public int updateCoach(Coach coach) {
		logger.debug("updateCoach:\n{}",coach);
		return this.getMapper().updateCoach(coach);
	}
	
	public Coach findOne(String id) {
		return this.getMapper().findOne(id);
	}

}
