package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Roles {

	/**
	 * 角色实体类
	 */
	private int id;
	private String name;
	private String explain;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Timestamp lastUpdateTime;

	private UserRoles u;

	// ---------------------------分页参数----------------------------------
	private Integer page = 0;// 当前第几页
	private Integer rows = 10;// 每页大小
	private Integer startIndex = 0;// Mysql分页查询limit第一个参数

	public UserRoles getU() {
		return u;
	}

	public void setU(UserRoles u) {
		this.u = u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
		return "Roles [id=" + id + ", name=" + name + ", explain=" + explain
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + ", u=" + u + ", page=" + page + ", rows="
				+ rows + ", startIndex=" + startIndex + "]";
	}

}
