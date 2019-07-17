package com.cyj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Modules;
import com.cyj.entity.Node;
import com.cyj.entity.Permission;
import com.cyj.entity.queryentity.SysPermissionQuery;

public interface PermissionService {
	
	
	/**
	 * 条件分页查询
	 * @param permission
	 * @return
	 */
	public List<Permission> query(SysPermissionQuery permission);
	
	/**
	 * 查询所有权限集合
	 * @return 权限字符串集合
	 */
	public List<String> queryAll();
	
	/**
	 * 批量插入权限数据
	 * @param pList
	 * @return 成功插入的权限数据条数
	 */
	public int batchInsert(List<Permission> pList);
	
	/**
	 * 查询所有权限操作
	 * @return
	 */
	public List<Node> queryNode();

	public List<Modules> queryRoleSetPermissionTree(List<Integer> roleId);
	
	/**
	 * 根据角色Ids 查询出权限Ids
	 * @param roleIds 角色Ids
	 * @return 权限Ids
	 */
	public List<Integer> queryPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);
	
	/**
	 * 给角色设置权限
	 * @param roleId 角色Id
	 * @param permissionIds 权限Ids集合
	 * @return
	 */
	public int setRolePermission(Integer roleId,@Param("permissionIds") List<Integer> permissionIds);
	
	/**
	 * 根据角色id查询权限
	 * @param roleIds
	 * @return
	 */
	public List<Node> queryRoleSetPermission(List<Integer> roleIds); 


}
