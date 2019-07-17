package com.cyj.entity;

import com.cyj.util.PoiHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class KaoQin {

	@PoiHandler(excelIgnore=true)
	private int id;
	@PoiHandler(excelHeader="姓名")
	private String name;
	@PoiHandler(excelHeader="迟到(次)")
	private int cd;
	@PoiHandler(excelHeader="早退(次)")
	private int zt;
	@PoiHandler(excelHeader="病假(天)")
	private int bj;
	@PoiHandler(excelHeader="事假(天)")
	private int sj;
	@PoiHandler(excelHeader="旷工(天)")
	private int kg;
	@PoiHandler(excelHeader="漏签(次/含未签退)")
	private int lq;
	@PoiHandler(excelHeader="正常签到/退(次)")
	private int zc;

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

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	public int getBj() {
		return bj;
	}

	public void setBj(int bj) {
		this.bj = bj;
	}

	public int getSj() {
		return sj;
	}

	public void setSj(int sj) {
		this.sj = sj;
	}

	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public int getLq() {
		return lq;
	}

	public void setLq(int lq) {
		this.lq = lq;
	}

	public int getZc() {
		return zc;
	}

	public void setZc(int zc) {
		this.zc = zc;
	}

	@Override
	public String toString() {
		return "KaoQin [id=" + id + ", name=" + name + ", cd=" + cd + ", zt="
				+ zt + ", bj=" + bj + ", sj=" + sj + ", kg=" + kg + ", lq="
				+ lq + ", zc=" + zc + "]";
	}

}
