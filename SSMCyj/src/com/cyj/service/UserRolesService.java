package com.cyj.service;

import java.util.List;

import com.cyj.entity.UserRoles;

public interface UserRolesService {
	
	public List<UserRoles> getUserRoles();
	
	public List<String> getUserId();

	public List<Integer> getUserRole(Integer uid);// 根据当前登录用户id查询角色
	
	public List<UserRoles> getUserRolesByRoleId(List<String> userId);// 根据用户id查询当前是否拥有角色
	
}
