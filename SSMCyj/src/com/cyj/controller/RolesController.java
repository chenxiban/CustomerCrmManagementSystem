package com.cyj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Askers;
import com.cyj.entity.Result;
import com.cyj.entity.RoleModules;
import com.cyj.entity.Roles;
import com.cyj.entity.UserRoles;
import com.cyj.jpa.entity.User;
import com.cyj.service.RolesService;
import com.cyj.service.UserRolesService;
import com.cyj.service.UsersService;
import com.cyj.util.IsEmptyUtils;

@RestController
@RequestMapping(value = "/roles", name = "角色模块")
/*
 * @SessionAttributes({ "roles", "error" })
 */
public class RolesController {

	@Autowired
	private RolesService service;
	@Autowired
	private UsersService servcie;
	@Autowired
	private UserRolesService ursservice;

	/**
	 * http://localhost:8080/SSMCyj/roles/getRoles.php 带分页的查询
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getRoles.php", produces = "text/plain;charset=UTF-8", name = "查询角色")
	public Object getRoles(Roles r) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", service.getRolesCount(r));
		map.put("rows", service.showRoles(r));
		return map;
	}
	
	/**
	 * http://localhost:8080/SSMCyj/roles/GetUsersByRoleName.php
	 * 
	 * @return
	 */

	@RequestMapping(value = "/GetUsersByRoleName.php", produces = "text/plain;charset=UTF-8", name = "查询咨询师")
	public Object GetUsersByRoleName(String name) {
		return service.selUsersByRoleName(name);
	}

	/**
	 * http://localhost:8080/SSMCyj/roles/getRolesList.php 不带分页
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getRolesList.php", produces = "text/plain;charset=UTF-8", name = "不分页查询角色")
	public Object getRolesList(
			@RequestParam(value = "id", required = false) int id) {
		List<Integer> urRoles = ursservice.getUserRole(id);
		System.out.println(urRoles);
		if (!IsEmptyUtils.isEmpty(urRoles)) {
			return service.getRolesList(urRoles);
		} else {
			return service.getRolesLists();
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/roles/addRoles.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addRoles.php", produces = "text/plain;charset=UTF-8", name = "添加角色")
	public Object addRoles(Roles r) {
		Roles m = service.selRoleByName(r.getName());
			if (IsEmptyUtils.isEmpty(m)) {
				r.setCreateTime(new Date());
				if (service.addRoles(r)) {
					return new Result(true, "角色添加成功");
				} else {
					return new Result(false, "角色添加失败");
				}
			} else {
				return new Result(false, "角色名称重复,请重新填写");
			}
	}

	/**
	 * http://localhost:8080/SSMCyj/Roles/delRoles.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delRoles.php", produces = "text/plain;charset=UTF-8", name = "删除角色")
	public Object delRoles(String id) {
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		List<RoleModules> roleModules = service.getparameterTypeByRoleId(list);
		System.out.println(roleModules);
		if (IsEmptyUtils.isEmpty(roleModules)) {
			if (service.delRoles(list)) {
				return new Result(true, "角色删除成功");
			} else {
				return new Result(false, "角色删除失败!");
			}
		} else {
			return new Result(false, "当前角色拥有模块,删除失败!");
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/roles/updRoles.php
	 * 
	 * @return
	 */

	@RequestMapping(value = "/updRoles.php", produces = "text/plain;charset=UTF-8", name = "修改角色")
	public Object updRoles(Roles r) {
		Roles m = service.selRoleByName(r.getName());
		if (IsEmptyUtils.isEmpty(m)) {
			if (service.updRoles(r)) {
				return new Result(true, "角色修改成功");
			} else {
				return new Result(false, "角色修改失败");
			}
		} else {
			return new Result(false, "角色名称重复,请重新填写!");
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/users/getUserRolesByUserId.php
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getUserRolesByUserId.php", produces = "text/plain;charset=UTF-8", name = "获取用户角色")
	public Object getUserRolesByUserId(Integer id) {
		return service.getUserRolesByUserId(id);
	}

	/**
	 * http://localhost:8080/SSMCyj/roles/addUserRoles.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUserRoles.php", produces = "text/plain;charset=UTF-8", name = "添加用户角色")
	public Object addUserRoles(Askers a,UserRoles ur) {
		ur.getUserId();
		if (service.addUserRoles(ur)) {
			// 根据用户Id查询出该用户的所有权限
			List<String> permissionValueList = servcie
					.queryPermissionValueByUserId(ur.getId());
			List<Integer> urRoles = ursservice.getUserRole(ur.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleIds", urRoles);
			map.put("permission", permissionValueList);// 响应给客户端的当前用户权限
			return new Result(true, map);
		} else {
			return new Result(false, "用户角色删除失败");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/roles/delUserRoles.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delUserRoles.php", produces = "text/plain;charset=UTF-8", name = "删除用户角色")
	public Object delUserRoles(
			@RequestParam(value = "roleId[]") String[] roleId, int userId,
			int id) {
		List<String> list = new ArrayList<String>();
		for (String dids : roleId) {
			list.add(dids);
		}
		System.out.println(list);
		if (service.delUserRole(list, userId)) {
			// 根据用户Id查询出该用户的所有权限
			List<String> permissionValueList = servcie
					.queryPermissionValueByUserId(id);
			List<Integer> urRoles = ursservice.getUserRole(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleIds", urRoles);
			map.put("permission", permissionValueList);// 响应给客户端的当前用户权限
			return new Result(true, map);
		} else {
			return new Result(false, "用户角色删除失败");
		}
	}

	/**
	 * http://localhost:8080/liugetusimple/role/setRoleModule?roleId=1&moduleIds
	 * =1&moduleIds=3&moduleIds=5
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setRoleModule.php", name = "角色设置菜单模块")
	public Object setRoleModule(
			Integer roleId,
			@RequestParam(value = "moduleId", required = false) List<Integer> moduleId) {
		int k = service.setRoleModule(roleId, moduleId);
		String msg = "角色roleId=>" + roleId + "->成功设置" + k + "个菜单模块.";
		return new Result(true, msg);// 设置成功
	}

	/**
	 * http://localhost:8080/liugetusimple/role/setRolePermission?roleId=1&
	 * permissionIds=1&permissionIds=3&permissionIds=5
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setRolePermission.php", name = "角色设置操作权限")
	public Object setRolePermission(
			Integer roleId,
			@RequestParam(value = "permissionId", required = false) List<Integer> permissionId) {
		int k = service.setRolePermission(roleId, permissionId);
		String msg = "角色roleId=>" + roleId + "->成功设置" + k + "个操作权限.";
		return new Result(true, msg);// 设置成功
	}
	
}
