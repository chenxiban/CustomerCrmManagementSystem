package com.cyj.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Modules;
import com.cyj.entity.Result;
import com.cyj.entity.RoleModules;
import com.cyj.service.ModulesService;
import com.cyj.service.RolesService;
import com.cyj.util.IsEmptyUtils;

/**
 * 
 * @Description: 菜单 模块控制器
 * @author Cyj
 * @Date 2018-5-18
 * @Email 867647213@qq.com
 */

@RestController
@RequestMapping(value = "/modules", name = "系统菜单")
public class ModulesController {

	@Autowired
	private ModulesService modules;
	@Autowired
	private RolesService rModules;

	private Date date = new Date();
	private Timestamp nousedate = new Timestamp(date.getTime());

	/**
	 * http://localhost:8080/SSMCyj/modules/getModules.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getModules.php", produces = "text/plain;charset=UTF-8", name = "查询模块")
	public Object getModules() {
		return modules.showModules();
	}

	/**
	 * http://localhost:8080/SSMCyj/modules/delModules.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delModules.php", produces = "text/plain;charset=UTF-8", name = "删除模块")
	public Object delModules(@RequestParam(value = "id[]") String[] id) {
		List<String> list = new ArrayList<String>();
		for (String dids : id) {
			list.add(dids);
		}
		
		List<RoleModules> roleModules=rModules.getRoleModulesByRoleId(list);
		if (IsEmptyUtils.isEmpty(roleModules)) {
			List<String> moduleId=modules.getChildrenByParentId(list);
			List<String> modulePid=modules.getChildrenByParentIds(list);
			if(IsEmptyUtils.isEmpty(moduleId) || moduleId.size()==0){
				if (modules.delModules(list)) {
					return new Result(true,"模块删除成功");
				} else {
					return new Result(false,"模块删除失败");
				}
			}
			
			if (IsEmptyUtils.isEmpty(modulePid) || modulePid.size()==0 ) {
				for (String dids : moduleId) {
					list.add(dids);
				}
				
				if (modules.delModules(list)) {
					return new Result(true,"模块删除成功");
				} else {
					return new Result(false,"模块删除失败");
				}
			} else {
				for (String dids : moduleId) {
					list.add(dids);
				}
				
				for (String dids : modulePid) {
					list.add(dids);
				}
				
				if (modules.delModules(list)) {
					return new Result(true,"模块删除成功");
				} else {
					return new Result(false,"模块删除失败");
				}
			}
		} else {
			return new Result(false,"有角色拥有当前模块,删除失败");
		}
		
	}
	
	/**
	 * http://localhost:8080/SSMCyj/modules/addModules.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addModules.php", produces = "text/plain;charset=UTF-8", name = "添加模块")
	public Object addModules(Modules m) {
		Modules module=new Modules();
		module.setName(m.getName());
		if (IsEmptyUtils.isEmpty(modules.queryModules(module.getName()))) {
			m.setCreateTime(new Date());
			m.setLastUpdateTime(nousedate);
			if (modules.addModules(m)) {
				return new Result(true,"模块添加成功");
			} else {
				return new Result(false,"模块添加失败");
			}
		} else {
			return new Result(false,"模块名称重复,请重新填写");
		}
		
	}
	
	/**
	 * http://localhost:8080/SSMCyj/modules/updModules.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updModules.php", produces = "text/plain;charset=UTF-8", name = "修改模块")
	public Object updModules(Modules m) {
		
		Modules aaModules=new Modules();
		aaModules=modules.queryModules(m.getName());
		
		if(!IsEmptyUtils.isEmpty(aaModules)) {
			if (aaModules.getName().equals(m.getName())) {
				return new Result(false,"模块名称重复,请重新填写");
			}else{
				if (modules.updModules(m)) {
					return new Result(true,"模块修改成功");
				} else {
					return new Result(false,"模块修改失败");
				}
			}
		}else{
			if (modules.updModules(m)) {
				return new Result(true,"模块修改成功");
			} else {
				return new Result(false,"模块修改失败");
			}
		}
		
	}
	
	/**
	 * 查询左侧菜单树 MenuTree
	 * http://localhost:8080/SSMCyj/modules/queryMenuTree
	 * @return
	 */
	@RequestMapping(value="/queryMenuTree.php",name="查询左侧菜单树")
	public Object queryModuleTree(@RequestParam(value="roleId",required=false) List<Integer> roleId){
		System.out.println("roleId=>"+roleId);
		return modules.getModulesRole(roleId);
	}
	
	/**
	 * 查询角色设置模块 RoleSetModuleTree
	 * http://localhost:8080/SSMCyj/modules/queryRoleSetModuleTree.php
	 * @return
	 */
	@RequestMapping(value="/queryRoleSetModuleTree.php",name="查询角色设置模块")
	public Object queryRoleSetModuleTree(@RequestParam(value="roleId",required=false) List<Integer> roleId){
		System.out.println("roleId=>"+roleId);
		return modules.queryRoleSetModuleTree(roleId);
	}

}
