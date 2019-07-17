package com.cyj.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Component {

	private int id;
	private String comState;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp comUpdTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComState() {
		return comState;
	}

	public void setComState(String comState) {
		this.comState = comState;
	}

	public Timestamp getComUpdTime() {
		return comUpdTime;
	}

	public void setComUpdTime(Timestamp comUpdTime) {
		this.comUpdTime = comUpdTime;
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", comState=" + comState
				+ ", comUpdTime=" + comUpdTime + "]";
	}



}
