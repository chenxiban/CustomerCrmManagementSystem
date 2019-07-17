package com.cyj.service;

import java.util.List;

import com.cyj.entity.Users;

public interface UsersService {
	
	public List<Users> showUsers(Users u);
	
	public Integer getUserCount(Users u);// 带条件分页查询结果集总条数
	
	public Users getUser(String loginName);
	
	public Users getUserById(Integer id);// 根据id查询用户报道状态
	
	// 根据用户Id查询出该用户的所有权限
	public List<String> queryPermissionValueByUserId(Integer userId);
	
	public boolean addUsers(Users u);
	
	public boolean delUsers(List<String> id);
	
	public boolean updUsers(Users u);
	
	public boolean loginUsers(Users u);
	
}
