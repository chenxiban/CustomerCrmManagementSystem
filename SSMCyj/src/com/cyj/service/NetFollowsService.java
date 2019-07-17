package com.cyj.service;

import java.util.List;

import com.cyj.entity.NetFollows;

public interface NetFollowsService {

	public List<NetFollows> getNetFollows(NetFollows n);// 查询所有签到信息
	
	public Integer getNetFollowsCount(NetFollows n);// 带条件分页查询结果集总条数
	
	public boolean addNetFollows(NetFollows n);// 添加跟踪
	
}
