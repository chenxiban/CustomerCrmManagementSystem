package com.cyj.entity;

import java.sql.Timestamp;

public class RoleModules {

	private int id;
	private int roleId;
	private int moduleId;
	private Timestamp moduleLastUpdateTime;
	private Modules modules;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public Timestamp getModuleLastUpdateTime() {
		return moduleLastUpdateTime;
	}

	public void setModuleLastUpdateTime(Timestamp moduleLastUpdateTime) {
		this.moduleLastUpdateTime = moduleLastUpdateTime;
	}

	public Modules getModules() {
		return modules;
	}

	public void setModules(Modules modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "RoleModules [id=" + id + ", roleId=" + roleId + ", moduleId="
				+ moduleId + ", moduleLastUpdateTime=" + moduleLastUpdateTime
				+ ", modules=" + modules + "]";
	}

}
