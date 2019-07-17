package com.cyj.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
public class Permission {
	private int permissionId;
	private String permissionValue;
	private String permissionModule;
	private String permissionName;
	private boolean checked;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp permissionLastUpdateTime;

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public String getPermissionModule() {
		return permissionModule;
	}

	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Timestamp getPermissionLastUpdateTime() {
		return permissionLastUpdateTime;
	}

	public void setPermissionLastUpdateTime(Timestamp permissionLastUpdateTime) {
		this.permissionLastUpdateTime = permissionLastUpdateTime;
	}

	public Permission(String permissionValue, String permissionModule,
			String permissionName) {
		super();
		this.permissionValue = permissionValue;
		this.permissionModule = permissionModule;
		this.permissionName = permissionName;
	}

	public Permission() {
		super();
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId
				+ ", permissionValue=" + permissionValue
				+ ", permissionModule=" + permissionModule
				+ ", permissionName=" + permissionName + ", checked=" + checked
				+ ", permissionLastUpdateTime=" + permissionLastUpdateTime
				+ "]";
	}

	
	
}
