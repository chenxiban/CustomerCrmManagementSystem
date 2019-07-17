package com.cyj.entity;

import java.sql.Timestamp;

public class UserRoles {

	private int id;
	private int userId;
	private int roleId;
	private Timestamp userRoleLastUpdateTime;
	private Roles roles;// 角色表

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Timestamp getUserRoleLastUpdateTime() {
		return userRoleLastUpdateTime;
	}

	public void setUserRoleLastUpdateTime(Timestamp userRoleLastUpdateTime) {
		this.userRoleLastUpdateTime = userRoleLastUpdateTime;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
