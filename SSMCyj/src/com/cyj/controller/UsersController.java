package com.cyj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Result;
import com.cyj.entity.UserRoles;
import com.cyj.entity.Users;
import com.cyj.md5.PasswordEncoder;
import com.cyj.service.UserRolesService;
import com.cyj.service.UsersService;
import com.cyj.util.IsEmptyUtils;

/**
 * 
 * @Description: 用户 模块控制器
 * @author Cyj
 * @Date 2018-5-16
 * @Email 867647213@qq.com
 */
@RestController
@RequestMapping(value = "/users", name = "用户模块")
/*
 * @SessionAttributes({ "users", "error" })
 */
public class UsersController {

	@Autowired
	private UsersService servcie;
	@Autowired
	private UserRolesService uService;

	/**
	 * http://localhost:8080/SSMCyj/users/getUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUsers.php", produces = "text/plain;charset=UTF-8", name = "查询用户")
	public Object getUsers(Users u) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", servcie.getUserCount(u));
		map.put("rows", servcie.showUsers(u));
		return map;
	}

	/**
	 * http://localhost:8080/SSMCyj/users/lockUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lockUsers.php", produces = "text/plain;charset=UTF-8", name = "锁定用户")
	public Object lockUsers(Users u) {
		u.getIsLookout();
		u.getId();
		if (servcie.updUsers(u)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/users/addUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUsers.php", produces = "text/plain;charset=UTF-8", name = "添加用户")
	public Object addUsers(Users u) {
		PasswordEncoder encoder = new PasswordEncoder(u.getLoginName(), "MD5");
		u.setPasswords(encoder.encode("cyj123"));
		u.setCreateTime(new Date());

		Users us = servcie.getUser(u.getLoginName());
		if (IsEmptyUtils.isEmpty(us)) {
			if (servcie.addUsers(u)) {
				return new Result(true, "用户添加成功");
			} else {
				return new Result(false, "用户添加失败");
			}
		} else {
			return new Result(false, "用户名重复,请重新填写");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/users/delUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delUsers.php", produces = "text/plain;charset=UTF-8", name = "删除用户")
	public Object delUsers(String id) {
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		List<UserRoles> userRoles = uService.getUserRolesByRoleId(list);
		if (IsEmptyUtils.isEmpty(userRoles)) {
			if (servcie.delUsers(list)) {
				return new Result(true, "用户删除成功");
			} else {
				return new Result(false, "用户删除失败");
			}
		} else {
			return new Result(false, "当前用户拥有角色,删除失败!");
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/users/updUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updUsers.php", produces = "text/plain;charset=UTF-8", name = "修改用户")
	public Object updUsers(Users u) {
		if (servcie.updUsers(u)) {
			return new Result(true, "用户修改成功");
		} else {
			return new Result(false, "用户修改成功");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/users/clearUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clearUsers.php", produces = "text/plain;charset=UTF-8", name = "重置密码")
	public Object clearUsers(Users u) {
		PasswordEncoder encoder = new PasswordEncoder(u.getLoginName(), "MD5");
		u.setPasswords(encoder.encode("cyj666"));// 修改为加密后的密码
		if (servcie.updUsers(u)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/users/updUserPass.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updUserPass.php", produces = "text/plain;charset=UTF-8", name = "修改密码")
	public Object updUserPass(Users u) {
		PasswordEncoder encoder = new PasswordEncoder(u.getLoginName(), "MD5");
		String pass = encoder.encode(u.getPasswords());// 获得页面修改的密码并进行加密

		Users us = servcie.getUser(u.getLoginName());

		if (!u.getPassword().equals(u.getPasswords())) {
			return new Result(false, "两次密码不一致");
		} else {
			u.setPasswords(pass);// 修改为加密后的密码
			if (us.getPasswords().equals(encoder.encode(u.getPass()))) {
				if (servcie.updUsers(u)) {
					return new Result(true, "修改成功,即将退出返回登录页面");
				} else {
					return new Result(false, "修改失败");
				}
			} else {
				return new Result(false, "旧密码填写错误");
			}
		}

	}
}
