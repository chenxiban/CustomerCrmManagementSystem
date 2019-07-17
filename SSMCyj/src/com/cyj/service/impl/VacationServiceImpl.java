package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.VacationDao;
import com.cyj.entity.Vacation;
import com.cyj.service.VacationService;

@Service
public class VacationServiceImpl implements VacationService {
	
	@Autowired
	private VacationDao vaDao;

	
	public List<Vacation> getVacation(Vacation v) {
		return vaDao.getVacation(v);
	}

	
	public Integer getVacationCount(Vacation v) {
		return vaDao.getVacationCount(v);
	}

	
	public boolean addVacation(Vacation v) {
		return vaDao.addVacation(v) > 0 ? true : false;
	}

	
	public boolean updVacation(Vacation v) {
		return vaDao.updVacation(v) > 0 ? true : false;
	}

	
	public boolean delVacation(List<String> id) {
		return vaDao.delVacation(id) > 0 ? true : false;
	}

	
	public List<Vacation> getVacationByName(Vacation v) {
		return vaDao.getVacationByName(v);
	}

	
	public Vacation getVacationName(Vacation v) {
		return vaDao.getVacationName(v);
	}
	
}
