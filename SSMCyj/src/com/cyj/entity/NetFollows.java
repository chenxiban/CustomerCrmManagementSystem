package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class NetFollows {
	private int id;
	private int studentId;
	private String studentName;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp followTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp nextFollowTime;
	private String content;
	private String userId;
	private String followType;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String birthStart;// 创建日期开始范围
	private String birthEnd;// 创建日期结束范围
	private String followState;

	// ---------------------------分页参数----------------------------------
	private Integer page = 0;// 当前第几页
	private Integer rows = 10;// 每页大小
	private Integer startIndex = 0;// Mysql分页查询limit第一个参数

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

	public Timestamp getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Timestamp followTime) {
		this.followTime = followTime;
	}

	public Timestamp getNextFollowTime() {
		return nextFollowTime;
	}

	public void setNextFollowTime(Timestamp nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowType() {
		return followType;
	}

	public void setFollowType(String followType) {
		this.followType = followType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFollowState() {
		return followState;
	}

	public void setFollowState(String followState) {
		this.followState = followState;
	}

	public String getBirthStart() {
		return birthStart;
	}

	public void setBirthStart(String birthStart) {
		this.birthStart = birthStart;
	}

	public String getBirthEnd() {
		return birthEnd;
	}

	public void setBirthEnd(String birthEnd) {
		this.birthEnd = birthEnd;
	}

	@Override
	public String toString() {
		return "NetFollows [id=" + id + ", studentId=" + studentId
				+ ", studentName=" + studentName + ", followTime=" + followTime
				+ ", nextFollowTime=" + nextFollowTime + ", content=" + content
				+ ", userId=" + userId + ", followType=" + followType
				+ ", createTime=" + createTime + ", birthStart=" + birthStart
				+ ", birthEnd=" + birthEnd + ", followState=" + followState
				+ ", page=" + page + ", rows=" + rows + ", startIndex="
				+ startIndex + "]";
	}

}
