package com.cyj.entity;

import java.util.Date;

public class Askers {

	private int id;
	private String askerName;
	private String checkState;
	private Date checkInTime;
	private String changeState;
	private int weight;
	private String roleName;
	private String remark;

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

	

	public String getAskerName() {
		return askerName;
	}

	public void setAskerName(String askerName) {
		this.askerName = askerName;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getChangeState() {
		return changeState;
	}

	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "askers [id=" + id + ", askerName=" + askerName
				+ ", checkState=" + checkState + ", checkInTime=" + checkInTime
				+ ", changeState=" + changeState + ", weight=" + weight
				+ ", roleName=" + roleName + ", remark=" + remark + ", page="
				+ page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}

}
