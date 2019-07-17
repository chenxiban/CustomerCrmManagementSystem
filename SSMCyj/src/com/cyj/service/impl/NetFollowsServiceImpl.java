package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.NetFollowsDao;
import com.cyj.entity.NetFollows;
import com.cyj.service.NetFollowsService;

@Service
public class NetFollowsServiceImpl implements NetFollowsService {
	
	@Autowired
	private NetFollowsDao ndao;

	
	public List<NetFollows> getNetFollows(NetFollows n) {
		return ndao.getNetFollows(n);
	}

	
	public Integer getNetFollowsCount(NetFollows n) {
		return ndao.getNetFollowsCount(n);
	}

	
	public boolean addNetFollows(NetFollows n) {
		return ndao.addNetFollows(n) > 0 ? true : false;
	}

}
