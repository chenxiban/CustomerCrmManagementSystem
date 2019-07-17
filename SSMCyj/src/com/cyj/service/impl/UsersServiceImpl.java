package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.UsersDao;
import com.cyj.entity.Users;
import com.cyj.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao users = null;

	
	public List<Users> showUsers(Users u) {
		return users.getUsers(u);
	}

	
	public Integer getUserCount(Users u) {
		return users.getUserCount(u);
	}

	
	public Users getUser(String loginName) {
		return users.getUser(loginName);
	}

	
	public boolean addUsers(Users u) {
		return users.addUsers(u) > 0 ? true : false; 
	}

	
	public boolean delUsers(List<String> id) {
		return users.delUsers(id) >0 ? true : false; 
	}

	
	public boolean updUsers(Users u) {
		return users.updUsers(u) > 0 ? true : false; 
	}

	
	public boolean loginUsers(Users u) {
		return users.loginUser(u) == null ? false : true;
	}

	
	public List<String> queryPermissionValueByUserId(Integer userId) {
		return users.queryPermissionValueByUserId(userId);
	}

	
	public Users getUserById(Integer id) {
		return users.getUserById(id);
	}
	
}
