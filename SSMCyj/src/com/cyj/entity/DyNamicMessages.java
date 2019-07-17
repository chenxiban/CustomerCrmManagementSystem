package com.cyj.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DyNamicMessages {
	private int id;
	private int studentId;
	private String studentName;
	private String messageContent;
	private String isOpen;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;

	// ---------------------------分页参数----------------------------------
	private Integer page = 0;// 当前第几页
	private Integer rows = 10;// 每页大小
	private Integer startIndex = 0;// Mysql分页查询limit第一个参数
	
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "DyNamicMessages [id=" + id + ", studentId=" + studentId
				+ ", studentName=" + studentName + ", messageContent="
				+ messageContent + ", isOpen=" + isOpen + ", createTime="
				+ createTime + ", page=" + page + ", rows=" + rows
				+ ", startIndex=" + startIndex + "]";
	}

	
}
