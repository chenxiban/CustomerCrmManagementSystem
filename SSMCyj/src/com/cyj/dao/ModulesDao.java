package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Modules;

public interface ModulesDao {

	public List<Modules> getModule(); //获得所有模块

	public List<Modules> queryChildren(int id);// 根据id查询子菜单
	
	public List<String> getChildrenByParentId(@Param("parentId") List<String> parentId);// 根据父id查询子菜单
	
	public List<String> getChildrenByParentIds(@Param("parentId") List<String> parentId);
	
	public Modules queryModules(@Param("name") String name);// 根据名字查询菜单
	
	public List<Integer> getModulesRoles(@Param("roleId") List<Integer> roleId);// 根据角色id查询模块id

	public List<Modules> queryChildrenById(@Param("parentId") int id,@Param("moduleId") List<Integer> moduleId);// 根据id查询子菜单
	
	public int addModules(Modules m);// 添加新模块
	
	public int updModules(Modules m);// 添加新模块
	
	public int delModules(@Param("id") List<String> id);// 批量删除模块

}
