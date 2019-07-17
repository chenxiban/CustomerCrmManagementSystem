package com.cyj.service;

import java.util.List;

import com.cyj.entity.Vacation;

public interface VacationService {
	
	public List<Vacation> getVacation(Vacation v);// 查询所有请假信息
	
	public List<Vacation> getVacationByName(Vacation v);// 查询所有请假信息
	
	public Vacation getVacationName(Vacation v);// 根据名字查询请假天数
	
	public Integer getVacationCount(Vacation v);// 带条件分页查询结果集总条数
	
	public boolean addVacation(Vacation v);// 添加请假

	public boolean updVacation(Vacation v);// 修改请假信息
	
	public boolean delVacation(List<String> id);// 批量删除请假信息
	
}
