package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.PermissionDao;
import com.cyj.entity.Modules;
import com.cyj.entity.Node;
import com.cyj.entity.Permission;
import com.cyj.entity.queryentity.SysPermissionQuery;
import com.cyj.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDao mapper;

	
	public List<Permission> query(SysPermissionQuery permission) {
		return mapper.query(permission);
	}

	
	public List<String> queryAll() {
		return mapper.queryAll();
	}

	
	public int batchInsert(List<Permission> pList) {
		return mapper.batchInsert(pList);
	}
	
	
	public List<Node> queryNode(){
		return mapper.queryNode();
	}

	/**
	 *  RoleSetPermissionTree
	 * 查询模块  RoleSetPermissionTree 树形数据表格
	 */
	
	public List<Modules> queryRoleSetPermissionTree(List<Integer> roleId) {
		List<Integer> permissionId=mapper.getPermissionsRoles(roleId);
		System.out.println(permissionId);
		List<Modules> rootList=mapper.queryChildrenById(null);
		this.setRoleSetPermisssionTreeChildrens(rootList, permissionId);
		System.out
		.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^最终得到的菜单列表=>"
				+ rootList);
		return rootList;
	}

	private void setRoleSetPermisssionTreeChildrens(List<Modules> rootList,
			List<Integer> permissionId) {
		
	}

	
	public List<Integer> queryPermissionIdsByRoleIds(List<Integer> roleIds) {
		return mapper.queryPermissionIdsByRoleIds(roleIds);
	}
	
	/**
	 * 根据角色Ids查询出角色设置的权限树
	 */
	
	public List<Node> queryRoleSetPermission(List<Integer> roleIds) {
		List<Integer> permissionIds = mapper.queryPermissionIdsByRoleIds(roleIds);//查询出角色拥有的权限Ids
		List<Node> permissionTree = mapper.queryNode();//查询出所有的权限树
		//把角色拥有的权限树设置为选中
		this.setPermissionTreeChecked(permissionTree, permissionIds);
		return permissionTree;
	}
	
	/**
	 * 把角色拥有的权限树设置为选中
	 * @param permissionTree
	 * @param permissionIds
	 */
	private void setPermissionTreeChecked(List<Node> permissionTree,List<Integer> permissionIds){
		for(Node module:permissionTree){//遍历出所有权限所属模块
			for(Node p:module.getChildren()){//遍历出所有权限节点
				if(permissionIds.contains(p.getId()))p.setChecked(true);//角色拥有的权限勾选中
			}
			
		}
	}

	
	
	public int setRolePermission(Integer roleId, List<Integer> permissionIds) {
		mapper.deleteRolePermissionByRoleId(roleId);
		return mapper.setRolePermission(roleId, permissionIds);
	}

}
