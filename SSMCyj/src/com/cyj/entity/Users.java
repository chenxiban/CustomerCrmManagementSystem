package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Users {

	/**
	 * 用户实体类
	 */
	private int id;
	private String loginName;
	private String pass;
	private String password;
	private String passwords;
	private String isLookout;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastLoginTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String birthStart;// 创建日期开始范围
	private String birthEnd;// 创建日期结束范围
	private int psdWrongTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lockTime;
	private String protectEMail;
	private String protectMTel;
	private String leaveState;
	private String leaveBoolean;
	
	// ---------------------------分页参数----------------------------------
	private Integer page = 0;// 当前第几页
	private Integer rows = 10;// 每页大小
	private Integer startIndex = 0;// Mysql分页查询limit第一个参数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getIsLookout() {
		return isLookout;
	}

	public void setIsLookout(String isLookout) {
		this.isLookout = isLookout;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBirthEnd() {
		return birthEnd;
	}

	public void setBirthEnd(String birthEnd) {
		this.birthEnd = birthEnd;
	}

	public String getBirthStart() {
		return birthStart;
	}

	public void setBirthStart(String birthStart) {
		this.birthStart = birthStart;
	}

	public int getPsdWrongTime() {
		return psdWrongTime;
	}

	public void setPsdWrongTime(int psdWrongTime) {
		this.psdWrongTime = psdWrongTime;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getProtectEMail() {
		return protectEMail;
	}

	public void setProtectEMail(String protectEMail) {
		this.protectEMail = protectEMail;
	}

	public String getProtectMTel() {
		return protectMTel;
	}

	public void setProtectMTel(String protectMTel) {
		this.protectMTel = protectMTel;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartIndex() {
		return (page - 1) * rows;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getLeaveState() {
		return leaveState;
	}

	public void setLeaveState(String leaveState) {
		this.leaveState = leaveState;
	}

	public String getLeaveBoolean() {
		return leaveBoolean;
	}

	public void setLeaveBoolean(String leaveBoolean) {
		this.leaveBoolean = leaveBoolean;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", loginName=" + loginName + ", pass="
				+ pass + ", password=" + password + ", passwords=" + passwords
				+ ", isLookout=" + isLookout + ", lastLoginTime="
				+ lastLoginTime + ", createTime=" + createTime
				+ ", birthStart=" + birthStart + ", birthEnd=" + birthEnd
				+ ", psdWrongTime=" + psdWrongTime + ", lockTime=" + lockTime
				+ ", protectEMail=" + protectEMail + ", protectMTel="
				+ protectMTel + ", leaveState=" + leaveState
				+ ", leaveBoolean=" + leaveBoolean + ", page=" + page
				+ ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}
	
}