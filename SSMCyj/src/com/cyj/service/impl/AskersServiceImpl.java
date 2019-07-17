package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.AskersDao;
import com.cyj.entity.Askers;
import com.cyj.service.AskersService;

@Service
public class AskersServiceImpl implements AskersService {
	
	@Autowired
	private AskersDao askersDao;

	public List<Askers> getAskers(Askers u) {
		return askersDao.getAskers(u);
	}

	public Integer getAskersCount(Askers u) {
		return askersDao.getAskersCount(u);
	}

	public boolean updAskers(Askers u) {
		return askersDao.updAskers(u) > 0 ? true : false;
	}

	
	public boolean addAskers(Askers u) {
		return askersDao.addAskers(u) > 0 ? true : false;
	}

	
	public boolean delAskers(List<String> askerName) {
		return askersDao.delAskers(askerName) > 0 ? true : false;
	}

	
	public Askers getAskersById(Integer id) {
		return askersDao.getAskersById(id);
	}

	
	public boolean updAskersByName(Askers u) {
		return askersDao.updAskersByName(u) > 0 ? true : false;
	}

	
	public Askers getAskersByName(String askerName) {
		return askersDao.getAskersByName(askerName);
	}
	
}
