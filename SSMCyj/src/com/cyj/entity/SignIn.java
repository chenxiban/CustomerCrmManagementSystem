package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SignIn {

	private int id;
	private int userId;
	private String signState;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signTime;
	private String birthStart;// 创建日期开始范围
	private String birthEnd;// 创建日期结束范围
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signOutTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastUpdateTime;
	private String loginName;
	private String signStar;
	private String signStars;
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

	public String getSignState() {
		return signState;
	}

	public void setSignState(String signState) {
		this.signState = signState;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Date getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(Date signOutTime) {
		this.signOutTime = signOutTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSignStar() {
		return signStar;
	}

	public void setSignStar(String signStar) {
		this.signStar = signStar;
	}

	public String getSignStars() {
		return signStars;
	}

	public void setSignStars(String signStars) {
		this.signStars = signStars;
	}

	public String getKqYue() {
		return kqYue;
	}

	public void setKqYue(String kqYue) {
		this.kqYue = kqYue;
	}

	@Override
	public String toString() {
		return "SignIn [id=" + id + ", userId=" + userId + ", signState="
				+ signState + ", signTime=" + signTime + ", birthStart="
				+ birthStart + ", birthEnd=" + birthEnd + ", signOutTime="
				+ signOutTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", loginName=" + loginName + ", signStar=" + signStar
				+ ", signStars=" + signStars + ", page=" + page + ", rows="
				+ rows + ", startIndex=" + startIndex + "]";
	}

}
