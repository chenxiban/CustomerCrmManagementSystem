package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Modules {

	private int id;
	@JsonProperty(value ="text")
	private String name;
	private int parentId;
	private String path;
	private int weight;
	private boolean checked;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastUpdateTime;
	private String founder;
	private String updateMan;

	private Map<String, Object> attributes;

	@JsonInclude(Include.NON_NULL)
	// 如果该属性为null则不参与序列化
	private List<Modules> children;

	public Map<String, Object> getAttributes() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", this.path);
		return map;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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

	public List<Modules> getChildren() {
		return children;
	}

	public void setChildren(List<Modules> children) {
		this.children = children;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	@Override
	public String toString() {
		return "Modules [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", path=" + path + ", weight=" + weight
				+ ", checked=" + checked + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", founder=" + founder
				+ ", updateMan=" + updateMan + ", attributes=" + attributes
				+ ", children=" + children + "]";
	}

}
