package com.cyj.entity;

public class CaiWu {

	private String faDate;
	private int jiBen;
	private int daBiao;
	private int siJin;
	private int geShui;
	private int xianJin;
	private int zongJi;

	public String getFaDate() {
		return faDate;
	}

	public void setFaDate(String faDate) {
		this.faDate = faDate;
	}

	public int getJiBen() {
		return jiBen;
	}

	public void setJiBen(int jiBen) {
		this.jiBen = jiBen;
	}

	public int getDaBiao() {
		return daBiao;
	}

	public void setDaBiao(int daBiao) {
		this.daBiao = daBiao;
	}

	public int getSiJin() {
		return siJin;
	}

	public void setSiJin(int siJin) {
		this.siJin = siJin;
	}

	public int getGeShui() {
		return geShui;
	}

	public void setGeShui(int geShui) {
		this.geShui = geShui;
	}

	public int getXianJin() {
		return xianJin;
	}

	public void setXianJin(int xianJin) {
		this.xianJin = xianJin;
	}

	public int getZongJi() {
		return zongJi;
	}

	public void setZongJi(int zongJi) {
		this.zongJi = zongJi;
	}

	@Override
	public String toString() {
		return "CaiWu [faDate=" + faDate + ", jiBen=" + jiBen + ", daBiao="
				+ daBiao + ", siJin=" + siJin + ", geShui=" + geShui
				+ ", xianJin=" + xianJin + ", zongJi=" + zongJi + "]";
	}

}
