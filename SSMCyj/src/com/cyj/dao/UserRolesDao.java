package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.UserRoles;

public interface UserRolesDao {

	public List<UserRoles> getUserRoles();
	
	public List<String> getUserId();// 获取当前表拥有的userId

	public List<Integer> getUserRole(int uid);// 根据当前登录用户id查询角色
	
	public List<UserRoles> getUserRolesByRoleId(@Param("userId")List<String> userId);
	
}
