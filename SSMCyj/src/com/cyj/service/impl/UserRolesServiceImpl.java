package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.UserRolesDao;
import com.cyj.entity.UserRoles;
import com.cyj.service.UserRolesService;

@Service
public class UserRolesServiceImpl implements UserRolesService {
	
	@Autowired
	private UserRolesDao uroles=null;
	
	
	public List<UserRoles> getUserRoles(){
		return uroles.getUserRoles();
	}
	
	
	public List<Integer> getUserRole(Integer uid){// 根据当前登录用户id查询角色
		return uroles.getUserRole(uid);
	}

	
	public List<String> getUserId() {
		return uroles.getUserId();
	}

	
	public List<UserRoles> getUserRolesByRoleId(List<String> userId) {
		return uroles.getUserRolesByRoleId(userId);
	}
	
}
