package com.cyj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.RoleModules;
import com.cyj.entity.Roles;
import com.cyj.entity.UserRoles;
import com.cyj.entity.Users;

public interface RolesService {
	
	//用户表
	public List<Roles> getRolesList(List<Integer> ids);
	
	public List<Roles> getRolesLists();
	
	public List<Roles> showRoles(Roles r);
	
	public int getRolesCount(Roles r);// 查询总条数
	
	public Roles selRoleByName(String name);//重复角色名
	
	public List<Users> selUsersByRoleName(@Param("name") String name);// 根据角色名查询用户名

	public boolean addRoles(Roles r);

	public boolean delRoles(List<String> id);

	public boolean updRoles(Roles r);
	
	// 角色表
	public List<UserRoles> getUserRolesByUserId(Integer id);
	
	// 关联表模块
	public List<RoleModules> getparameterTypeByRoleId(List<String> list);
	
	// 关联表模块
	public List<RoleModules> getRoleModulesByRoleId(@Param("moduleId") List<String> moduleId);
	
	public boolean addUserRoles(UserRoles ur);
	
	public boolean delUserRole(List<String> roleId,int userId);
	
	
	//设置模块
	public int setRoleModule(Integer roleId,List<Integer> moduleIds);
	//设置权限
	public int setRolePermission(Integer roleId,List<Integer> permissionIds);

}
