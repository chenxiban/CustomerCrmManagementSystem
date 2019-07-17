package com.cyj.service;

import java.util.List;

import com.cyj.entity.Modules;

public interface ModulesService {
	
	public List<Modules> showModules();// 显示所有菜单
	
	public List<Modules> queryChildrens(int parentId);// 根据父id查询子菜单
	
	public List<String> getChildrenByParentId(List<String> parentId);// 根据父id查询子菜单
	
	public List<String> getChildrenByParentIds(List<String> parentId);// 根据父id查询孙子菜单
	
	public Modules queryModules(String name);// 根据名字查询菜单
	
	public List<Modules> getModulesRole(List<Integer> roles);// 根据角色id查询拥有的模块

	public List<Modules> queryChildrenByIds(int parentList, List<Integer> moduleId);// 根据id查询子菜单
	
	public boolean addModules(Modules u);// 添加新模块
	
	public boolean updModules(Modules m);// 修改模块
	
	public boolean delModules(List<String> id);// 批量删除模块

	public List<Modules> queryRoleSetModuleTree(List<Integer> roleId);
	
}
