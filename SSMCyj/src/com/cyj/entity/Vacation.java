package com.cyj.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 请假表
 */
@JsonInclude(Include.NON_NULL)
public class Vacation {
	private int id;
	private int userId;
	private String leaveType;
	private int leaveDate;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date leaveTime;
	private String isAgree;
	private String pContent;
	private String pName;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private String name;
	private String kqYue;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(int leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getpTime() {
		return pTime;
	}

	public void setpTime(Date pTime) {
		this.pTime = pTime;
	}

	public String getKqYue() {
		return kqYue;
	}

	public void setKqYue(String kqYue) {
		this.kqYue = kqYue;
	}

	@Override
	public String toString() {
		return "Vacation [id=" + id + ", userId=" + userId + ", leaveType="
				+ leaveType + ", leaveDate=" + leaveDate + ", content="
				+ content + ", leaveTime=" + leaveTime + ", isAgree=" + isAgree
				+ ", pContent=" + pContent + ", pName=" + pName + ", pTime="
				+ pTime + ", endTime=" + endTime + ", name=" + name + ", page="
				+ page + ", rows=" + rows + ", startIndex=" + startIndex + "]";
	}
	
}
