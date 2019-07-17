package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Modules;
import com.cyj.entity.Node;
import com.cyj.entity.Permission;
import com.cyj.entity.queryentity.SysPermissionQuery;


public interface PermissionDao {
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

	public List<Integer> getPermissionsRoles(@Param("roleId") List<Integer> roleId);

	public List<Modules> queryChildrenById(@Param("permissionId") List<Integer> permissionId);
	/**
	 * 根据角色Ids 查询出权限Ids
	 * @param roleIds 角色Ids
	 * @return 权限Ids
	 */
	public List<Integer> queryPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);
	
	/**
	 * 根据角色Id清除角色权限关系
	 * @param roleId
	 * @return
	 */
	public int deleteRolePermissionByRoleId(Integer roleId);
	/**
	 * 给角色设置权限
	 * @param roleId 角色Id
	 * @param permissionIds 权限Ids集合
	 * @return
	 */
	public int setRolePermission(@Param("roleId") Integer roleId,@Param("permissionIds") List<Integer> permissionIds);
}
