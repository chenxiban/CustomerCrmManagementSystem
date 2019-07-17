package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Askers;

/**
 * 咨询师接口
 * @author lenovo
 *
 */
public interface AskersDao {
	
	/**
	 * 查询所有签到信息
	 * @param u 咨询师表对象
	 * @return
	 */
	public List<Askers> getAskers(Askers u);
	
	public Askers getAskersById(@Param("id")  Integer id);// 根据当前登录id查询咨询师
	
	public Askers getAskersByName(@Param("askerName")  String askerName);// 根据名称查询咨询师状态
	
	public Integer getAskersCount(Askers u);// 带条件分页查询结果集总条数
	
	public int updAskers(Askers u);
	
	public int updAskersByName(Askers u);
	
	public int addAskers(Askers u);
	
	public int delAskers(@Param("askerName") List<String> askerName);
}
