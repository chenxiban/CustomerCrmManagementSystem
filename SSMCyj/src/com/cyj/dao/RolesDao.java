package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.RoleModules;
import com.cyj.entity.Roles;
import com.cyj.entity.UserRoles;
import com.cyj.entity.Users;

public interface RolesDao {
	
	public List<Roles> getRolesList(@Param("ids") List<Integer> id);
	
	public List<Roles> getRolesLists();

	public List<Roles> getRoles(Roles r);// 查询所有角色

	public int getRolesCount(Roles r);// 查询总条数
	
	public int addRoles(Roles r);// 添加角色

	public int updRoles(Roles r);// 修改角色信息
	
	public Roles selRoleByName(String name);//重复角色名
	
	public List<Users> selUsersByRoleName(@Param("name")String name);// 根据角色名查询用户名
	
	public int delRoles(List<String> id);// 批量删除角色

	public List<UserRoles> getUserRolesByUserId(Integer id);//根据用户的ID来获取本用户所拥有的角色
	
	public List<RoleModules> getparameterTypeByRoleId(@Param("roleId")List<String> id);//根据角色的ID查询当前角色是否有权限
	
	//根据模块的ID查询当前角色是否有角色拥有当前模块
	public List<RoleModules> getRoleModulesByRoleId(@Param("moduleId") List<String> moduleId);
	
	public int addUserRoles(UserRoles ur);//添加用户角色
	
	public int delUserRole(@Param("list") List<String> roleId,@Param("userId") int userId);//删除用户角色
	

	//设置角色模块
	public int setRoleModule(@Param("roleId") Integer roleId,@Param("moduleIds") List<Integer> moduleId);
	//移除角色模块
	public int delRoleModule (Integer roleId);
	//设置角色权限
	public int setRolePermission(@Param("roleId") Integer roleId,@Param("permissionIds") List<Integer> permissionIds);
	//移除角色权限
	public int delRolePermission(Integer roleId);
	
}
