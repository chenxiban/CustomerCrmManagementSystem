package com.cyj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.service.UserRolesService;

@RestController
@RequestMapping(value = "/userRoles", name = "用户角色关联")
public class UserRolesController{
	@Autowired
	private UserRolesService service;
	
	/**
	 * http://localhost:8080/SSMCyj/userRoles/getUserRole.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserRole.php", produces = "text/plain;charset=UTF-8", name = "获取用户拥有的角色")
	public Object getUserRole(int uid) {
		return service.getUserRole(uid);
	}
}
