package com.sunjoy.trm.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.framework.dao.paging.Page;
import com.sunjoy.framework.dao.paging.PageInfo;
import com.sunjoy.framework.exception.CommonException;
import com.sunjoy.framework.utils.BeanUtils;
import com.sunjoy.framework.utils.RandomUtils;
import com.sunjoy.trm.master.dao.CoachDao;
import com.sunjoy.trm.master.dao.criteria.CoachCriteria;
import com.sunjoy.trm.master.dao.entity.Coach;
import com.sunjoy.trm.master.dao.enums.BaseStatus;
import com.sunjoy.trm.master.service.ICoachService;
import com.sunjoy.trm.master.utils.ExceptionConstant;

@Service(value = "coachService")
@Transactional
public class CoachServiceImpl implements ICoachService {

	@Autowired
	private CoachDao coachDao;

	@Override
	public Page<Coach> queryByPage(CoachCriteria criteria,PageInfo page) {
		List<Coach> coachs = coachDao.queryCoachByPage(criteria,page);
		long count = coachDao.getCoachTotalCount(criteria);
		Page<Coach> returnPage = new Page<>(page);
		returnPage.setCount(count);
		if (coachs != null && !coachs.isEmpty()) {
			returnPage.setRows(coachs);
		}
		return returnPage;
	}

	@Override
	public List<Coach> query(CoachCriteria criteria) {
		return coachDao.queryCoach(criteria);
	}

	@Override
	public Coach update(Coach coach) {
		// step 1, 非空检查，加上UUID
		BeanUtils.checkEmptyFields(coach, "id","code", "name");
		// step 2, 编号重复检验,如果编号被其他学员使用了，即抛异常
		CoachCriteria criteria = new CoachCriteria();
		criteria.setCode(coach.getCode());
		List<Coach> existsCoachs = this.coachDao.queryCoach(criteria);
		if(!existsCoachs.isEmpty()) {
			for(Coach std:existsCoachs) {
				if(std.getCode().equals(coach.getCode()) && !coach.getId().equals(std.getId())) {
					throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
				}
			}
		}
		this.coachDao.updateCoach(coach);
		return coach;
	}

	@Override
	public Coach add(Coach coach) {
		// step 1, 非空检查
		BeanUtils.checkEmptyFields(coach,  "code","name");
		// step 2, 编号重复检验
		CoachCriteria criteria = new CoachCriteria();
		criteria.setCode(coach.getCode());
		List<Coach> existsCoachs = this.coachDao.queryCoach(criteria);
		if (existsCoachs != null && !existsCoachs.isEmpty()) {
			throw new CommonException(ExceptionConstant.MASTER_FIELD_CODE_DUPLICATED);
		}
		// step 3, 保存到数据库
		if (coach.getId() == null) {
			coach.setId(RandomUtils.createUUID());
		}
		coach.setStatus(BaseStatus.VALID.getCode());
		this.coachDao.addCoach(coach);
		return coach;
	}

	@Override
	public int remove(String id) {
		if(!BeanUtils.isEmpty(id)) {
			Coach coach=this.coachDao.findOne(id);
			coach.setStatus(BaseStatus.DELETED.getCode());
			return this.coachDao.updateCoach(coach);
		}
		return 0;
	}

	@Override
	public Coach get(String id) {
		if(!BeanUtils.isEmpty(id)) {
			return this.coachDao.findOne(id);
		}
		return null;
	}

}
