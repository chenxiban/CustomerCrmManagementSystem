package com.cyj.service;

import java.util.List;

import com.cyj.entity.Askers;

public interface AskersService {
	
	public List<Askers> getAskers(Askers u);// 查询所有签到信息
	
	public Askers getAskersById(Integer id);// 根据当前登录id查询咨询师
	
	public Askers getAskersByName(String askerName);// 根据名称查询咨询师状态
	
	public Integer getAskersCount(Askers u);// 带条件分页查询结果集总条数
	
	public boolean updAskers(Askers u);
	
	public boolean updAskersByName(Askers u);
	
	public boolean addAskers(Askers u);
	
	public boolean delAskers(List<String> askerName);
}	
