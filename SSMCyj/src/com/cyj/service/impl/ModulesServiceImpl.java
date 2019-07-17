package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.ModulesDao;
import com.cyj.entity.Modules;
import com.cyj.service.ModulesService;

@Service
public class ModulesServiceImpl implements ModulesService {

	@Autowired
	private ModulesDao modules = null;

	
	public List<Modules> showModules() {// 查询所有模块
		// 查询出所有根菜单
		List<Modules> rootList = modules.queryChildren(0);
		// 递归设置子菜单
		this.setChildrens(rootList);
		System.out
				.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^最终得到的菜单列表=>"
						+ rootList);
		return rootList;
	}

	/**
	 * 给菜单设置子菜单
	 * 
	 * @param parentList
	 */
	private void setChildrens(List<Modules> parentList) {
		for (Modules m : parentList) {
			// 查询出子菜单
			List<Modules> childrenList = this.queryChildrens(m.getId());
			System.out
					.println("*****************************************************设置子菜单=>"
							+ m.getName());
			// 如果没有子菜单则递归结束
			if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
				// 设置子菜单
				System.out.println("设置的子菜单是=>" + childrenList);
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setChildrens(childrenList);
			}
		}
	}

	/**
	 * 查询孩子菜单
	 */
	
	public List<Modules> queryChildrens(int parentId) {
		return modules.queryChildren(parentId);
	}

	/**
	 * 左侧树结构
	 */
	
	public List<Modules> getModulesRole(List<Integer> roleId) {
		// 根据角色查询模块ids
		List<Integer> moduleId = modules.getModulesRoles(roleId);

		System.out.println("拥有的模块id   >=" + moduleId);

		List<Modules> rootList = modules.queryChildrenById(0, moduleId);
		// 递归设置子菜单
		this.setChildren(rootList, moduleId);
		System.out
				.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^最终得到的菜单列表=>"
						+ rootList);
		return rootList;
	}

	/**
	 * 给模块设置孩子
	 * 
	 * @param parentList
	 * @param moduleId
	 */
	public void setChildren(List<Modules> parentList, List<Integer> moduleId) {
		for (Modules m : parentList) {
			// 查询出子菜单
			List<Modules> childrenList = this.queryChildrenByIds(m.getId(),
					moduleId);
			// 如果没有子菜单则递归结束
			if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
				// 设置子菜单
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setChildren(childrenList, moduleId);
			}
		}
	}

	
	public List<Modules> queryChildrenByIds(int parentList,
			List<Integer> moduleId) {
		return modules.queryChildrenById(parentList, moduleId);
	}

	
	public boolean delModules(List<String> id) {// 批量删除模块
		return modules.delModules(id) > 0 ? true : false;
	}

	
	public boolean addModules(Modules u) {// 添加新模块
		return modules.addModules(u) > 0 ? true : false;
	}

	
	public boolean updModules(Modules m) {// 修改模块
		return modules.updModules(m) > 0 ? true : false;
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 给角色设置模块:-> 查询 RoleSetModuleTree
	// 给角色设置模块
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	/**
	 * RoleSetModuleTree 查询模块 RoleSetModuleTree 树形数据表格
	 */
	
	public List<Modules> queryRoleSetModuleTree(List<Integer> roleId) {
		// 根据角色查询模块ids
		List<Integer> moduleId = modules.getModulesRoles(roleId);
		System.out.println(moduleId);

		//
		List<Modules> rootList = modules.queryChildrenById(0, null);
		// 递归设置子菜单
		this.setRoleSetModuleTreeChildrens(rootList, moduleId);
		System.out
				.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^最终得到的菜单列表=>"
						+ rootList);
		return rootList;
	}

	/**
	 * RoleSetModuleTree 给菜单模块 设置孩子
	 * 
	 * @param parentList
	 */
	private void setRoleSetModuleTreeChildrens(List<Modules> parentList,
			List<Integer> moduleId) {
		for (Modules m : parentList) {
			// 查询出子菜单
			List<Modules> childrenList = this.queryChildrenByIds(m.getId(),
					null);
			// 如果没有子菜单则递归结束
			if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
				// 设置子菜单
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setRoleSetModuleTreeChildrens(childrenList, moduleId);
			} else {// 没有孩子;是叶子节点,且角色有用该模块,则选中.
					// tree控件默认级联选中:选中父节点则默认选中全部子节点,选中一个子节点则默认选中父节点.所以只选中叶子节点即可.
					// 设置是否选中,把角色拥有的模块选中
				if (moduleId.contains(m.getId()))
					m.setChecked(true);
			}
		}
	}

	
	public Modules queryModules(String name) {
		return modules.queryModules(name);
	}

	
	public List<String> getChildrenByParentId(List<String> parentId) {
		return modules.getChildrenByParentId(parentId);
	}
	
	
	public List<String> getChildrenByParentIds(List<String> parentId) {
		return modules.getChildrenByParentIds(parentId);
	}

}
