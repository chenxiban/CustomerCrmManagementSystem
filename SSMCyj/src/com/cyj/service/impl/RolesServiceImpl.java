package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.RolesDao;
import com.cyj.entity.RoleModules;
import com.cyj.entity.Roles;
import com.cyj.entity.UserRoles;
import com.cyj.entity.Users;
import com.cyj.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesDao roles = null;
	
	
	public List<Roles> getRolesList(List<Integer> ids){
		return roles.getRolesList(ids);
	}
	
	
	public List<Roles> getRolesLists(){
		return roles.getRolesLists();
	}
	
	
	public List<Roles> showRoles(Roles r) {
		return roles.getRoles(r);
	}
	
	
	public int getRolesCount(Roles r){
		return roles.getRolesCount(r);
	}

	
	public boolean addRoles(Roles r) {
		return roles.addRoles(r) > 0 ? true : false;
	}

	
	public boolean delRoles(List<String> id) {
		return roles.delRoles(id) > 0 ? true : false;
	}

	
	public boolean updRoles(Roles r) {
		return roles.updRoles(r) > 0 ? true : false;
	}

	
	public List<UserRoles> getUserRolesByUserId(Integer id) {
		return roles.getUserRolesByUserId(id);
	}

	
	public boolean addUserRoles(UserRoles ur) {
		return roles.addUserRoles(ur) > 0 ? true : false;
	}

	
	public boolean delUserRole(List<String> roleId,int userId) {
		return roles.delUserRole(roleId,userId) > 0 ? true : false;
	}
	

	
	public int setRoleModule(Integer roleId, List<Integer> moduleIds) {
		roles.delRoleModule(roleId);
		return roles.setRoleModule(roleId, moduleIds);
	}

	
	public int setRolePermission(Integer roleId, List<Integer> permissionIds) {
		roles.delRolePermission(roleId);
		return roles.setRolePermission(roleId, permissionIds);
	}

	
	public Roles selRoleByName(String name) {
		return roles.selRoleByName(name);
	}

	
	public List<RoleModules> getparameterTypeByRoleId(List<String> id) {
		return roles.getparameterTypeByRoleId(id);
	}

	
	public List<RoleModules> getRoleModulesByRoleId(List<String> moduleId) {
		return roles.getRoleModulesByRoleId(moduleId);
	}

	
	public List<Users> selUsersByRoleName(String name) {
		return roles.selUsersByRoleName(name);
	}
}
