package com.cyj.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
public class RolePermission {
	private int rolePermissionId;
	private int roleId;
	private int permissionId;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp rolePermissionLastUpdateTime;
	public int getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(int rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public Timestamp getRolePermissionLastUpdateTime() {
		return rolePermissionLastUpdateTime;
	}
	public void setRolePermissionLastUpdateTime(
			Timestamp rolePermissionLastUpdateTime) {
		this.rolePermissionLastUpdateTime = rolePermissionLastUpdateTime;
	}
}
