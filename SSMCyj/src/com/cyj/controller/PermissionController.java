package com.cyj.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.cyj.entity.Permission;
import com.cyj.entity.Result;
import com.cyj.service.PermissionService;
import com.cyj.service.UsersService;

/**
 * @au cyj
 * @Description: 权限控制器
 */
@RestController
@RequestMapping(value = "/permission", name = "权限模块")
public class PermissionController {

	/**
	 * springmvc在启动时候将所有贴有请求映射标签：RequestMapper方法收集起来封装到该对象中
	 */
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;// SpringMVC所有控制器中的请求映射集合
	@Autowired
	private PermissionService service;
	@Autowired
	private UsersService servcie;

	/**
	 * http://localhost:8080/SSMCyj/permission/updatePermission.php 更新系统权限信息
	 */
	@RequestMapping(value = "/updatePermission.php", name = "更新系统权限")
	public Object updatePermission() {
		System.out.println("更新系统中所有权限...");
		int k = this.updateSysPermission();// 收集系统中所有权限数据更新到数据库
		System.out.println("系统中所有权限全部" + k + "条更新完毕 ^_^ ");
		return "updatePermission 成功更新权限条数=>" + k;
	}

	/**
	 * 收集系统中所有权限数据更新到数据库
	 */
	public int updateSysPermission() {

		List<String> ownList = service.queryAll();// 查询出数据库中现有的所有权限数据集合
		System.out.println("查询出数据库中现有的所有权限数据集合=>" + ownList);
		Map<RequestMappingInfo, HandlerMethod> requestMap = handlerMapping
				.getHandlerMethods();// SpringMVC所有控制器中的请求映射集合
		System.out.println("SpringMVC所有控制器中的请求映射集合=>" + requestMap);
		Collection<HandlerMethod> handlerMethods = requestMap.values();// 获取所有controller中所有带有@RequestMapper注解的方法
		if (handlerMethods == null || handlerMethods.size() < 1)
			return 0;// 成功更新0条数据
		List<Permission> pList = new ArrayList<Permission>();// 收集到的待新增的权限集合
		Permission permission = null;// 待添加的权限对象

		for (HandlerMethod method : handlerMethods) {// 遍历所有带有@RequestMapper注解的方法
			RequestMapping anno = method
					.getMethodAnnotation(RequestMapping.class);// 从控制器映射方法上取出@RequestMapper注解对象
			// @RequestMapper注解有没有name备注是作为一个权限的标志
			if (!"".equals(anno.name())) {// @RequestMapper注解写了name属性才做权限收集：所以@RequestMapper注解有没有name备注是作为一个权限的标志
				RequestMapping namespaceMapping = method.getBeanType()
						.getAnnotation(RequestMapping.class);// 带有@RequestMapper注解的方法所在控制器类上的RequestMapping注解对象
				String namespace = namespaceMapping.value()[0];// 得到RequestMapping注解的value值,即命名空间,即模块
				String permissionValue = (namespace + ":" + anno.value()[0])
						.replace("/", "");// 得到权限 ,例如：user:delete 用户模块的删除权限
				System.out.println("得到权限 ,例如：user:delete 用户模块的删除权限=>"
						+ permissionValue + "权限说明:" + anno.name());
				if (ownList.contains(permissionValue))
					continue;// 如果数据库已经存储有这个注解权限,则忽略不收集
				if (pList.contains(permissionValue))
					continue;// 如果已经收集到该权限,则忽略不再重复收集
				// 构造权限对象,三个参数:权限,模块说明,权限说明
				permission = new Permission(permissionValue,
						namespaceMapping.name(), anno.name());// 把权限控制注解@Permission解析为权限控制对象SysPermission,方便插入数据库权限表
				pList.add(permission);// 把数据库没有存储的,收集容器中也没有收集到的的权限加入收集容器中.
			}
		}
		return pList.size() > 0 ? service.batchInsert(pList) : 0;
	}

	/**
	 * http://localhost:8080/SSMCyj/permission/queryNode.php 更新系统权限信息
	 */
	@RequestMapping(value = "/queryNode.php", name = "更新系统权限信息")
	public Object queryNode() {
		return service.queryNode();
	}

	/**
	 * 查询角色设置模块 RoleSetModuleTree
	 * http://localhost:8080/SSMCyj/permission/queryRoleSetModuleTree.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryRoleSetPermissionTree.php", name = "查询角色设置模块")
	public Object queryRoleSetPermissionTree(
			@RequestParam(value = "roleId", required = false) List<Integer> roleId) {
		System.out.println("roleId=>" + roleId);
		return service.queryRoleSetPermissionTree(roleId);
	}

	/**
	 * http://localhost:8080/SSMCyj/permission/queryNodess.php?roleIds=1
	 * 更新系统权限信息
	 */
	@RequestMapping(value = "/queryNodess.php", name = "查询所有权限")
	public Object queryNodess(
			@RequestParam(value = "roleIds", required = false) List<Integer> roleIds) {
		System.out.println("roleIds=>" + roleIds);
		return service.queryRoleSetPermission(roleIds);
	}

	/**
	 * http://localhost:8080/SSMCyj/permission/setRolePermission.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setRolePermission.php", name = "角色设置操作权限")
	public Object setRolePermission(
			Integer roleId,
			@RequestParam(value = "permissionIds", required = false) List<Integer> permissionIds,
			int id) {
		System.out.println("permissionIds>=" + permissionIds);
		int k = service.setRolePermission(roleId, permissionIds);
		// 根据用户Id查询出该用户的所有权限
		List<String> permissionValueList = servcie
				.queryPermissionValueByUserId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("permission", permissionValueList);// 响应给客户端的当前用户权限
		String msg = "角色roleId=>" + roleId + "->成功设置" + k + "个操作权限.";
		return new Result(true, map);// 设置成功
	}

}
