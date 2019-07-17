package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.RoleModules;

public interface RoleModulesDao {
	
	//根据模块的ID查询当前角色是否有角色拥有当前模块
	public List<RoleModules> getRoleModulesByRoleId(@Param("moduleId") List<String> id);
	
}
