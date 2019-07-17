package com.cyj.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.cyj.util.PoiHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Students {
	@PoiHandler(excelIgnore=true)
	private int id;
	@PoiHandler(excelHeader="学生姓名")
	private String name;
	@PoiHandler(excelHeader="学生年龄")
	private int age;
	@PoiHandler(excelHeader="学生性别")
	private String sex;
	@PoiHandler(excelHeader="学生电话")
	private String phone;
	@PoiHandler(excelHeader="学历")
	private String stuStatus;
	@PoiHandler(excelHeader="个人状态")
	private String perState;
	@PoiHandler(excelHeader="来源渠道")
	private String msgSource;
	@PoiHandler(excelHeader="来源网址")
	private String sourceUrl;
	@PoiHandler(excelHeader="来源关键词")
	private String sourceKeyWord;
	@PoiHandler(excelHeader="所在区域")
	private String address;
	@PoiHandler(excelHeader="网络咨询Id")
	private int netPusherId;
	@PoiHandler(excelHeader="咨询师Id")
	private String askerId;
	@PoiHandler(excelHeader="学员QQ")
	private String qq;
	@PoiHandler(excelHeader="学员微信")
	private String weiXin;
	@PoiHandler(excelHeader="备注")
	private String content;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="创建时间")
	private Date createTime;
	@PoiHandler(excelHeader="课程方向")
	private String learnForward;
	@PoiHandler(excelHeader="是否有效")
	private String isValid;
	@PoiHandler(excelHeader="打分")
	private String record;
	@PoiHandler(excelHeader="是否回访")
	private String isReturnVist;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="首次回访时间")
	private Timestamp firstVisitTime;
	@PoiHandler(excelHeader="是否上门")
	private String isHome;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="上门时间")
	private Timestamp homeTime;
	@PoiHandler(excelHeader="无效原因")
	private String lostValid;
	@PoiHandler(excelHeader="是否付款")
	private String isPay;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="付款时间")
	private Timestamp payTime;
	@PoiHandler(excelHeader="付款金额")
	private double money;
	@PoiHandler(excelHeader="是否退费")
	private String isReturnMoney;
	@PoiHandler(excelHeader="是否进班")
	private String isInClass;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="进班时间")
	private Timestamp inClassTime;
	@PoiHandler(excelHeader="进班备注")
	private String inClassContent;
	@PoiHandler(excelHeader="咨询师备注")
	private String askerContent;
	@PoiHandler(excelHeader="是否删除")
	private String isDel;
	@PoiHandler(excelHeader="来源部门")
	private String fromPart;
	@PoiHandler(excelHeader="学员关注")
	private String stuConcern;
	@PoiHandler(excelHeader="是否报备")
	private String isBaoBei;
	@PoiHandler(excelHeader="咨询师填写的姓名")
	private String ziXunName;
	@PoiHandler(excelHeader="录入人姓名")
	private String createUser;
	@PoiHandler(excelHeader="退费原因")
	private String returnMoneyReason;
	@PoiHandler(excelHeader="预付定金")
	private double preMoney;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@PoiHandler(excelHeader="预付定金时间")
	private Timestamp preMoneyTime;

	@PoiHandler(excelIgnore=true)
	private String birthStart;// 创建日期开始范围
	@PoiHandler(excelIgnore=true)
	private String birthEnd;// 创建日期结束范围

	// ---------------------------分页参数----------------------------------
	@PoiHandler(excelIgnore=true)
	private Integer page = 0;// 当前第几页
	@PoiHandler(excelIgnore=true)
	private Integer rows = 10;// 每页大小
	@PoiHandler(excelIgnore=true)
	
	private Integer startIndex = 0;// Mysql分页查询limit第一个参数

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStuStatus() {
		return stuStatus;
	}

	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus;
	}

	public String getPerState() {
		return perState;
	}

	public void setPerState(String perState) {
		this.perState = perState;
	}

	public String getMsgSource() {
		return msgSource;
	}

	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceKeyWord() {
		return sourceKeyWord;
	}

	public void setSourceKeyWord(String sourceKeyWord) {
		this.sourceKeyWord = sourceKeyWord;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNetPusherId() {
		return netPusherId;
	}

	public void setNetPusherId(int netPusherId) {
		this.netPusherId = netPusherId;
	}

	public String getAskerId() {
		return askerId;
	}

	public void setAskerId(String askerId) {
		this.askerId = askerId;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getLearnForward() {
		return learnForward;
	}

	public void setLearnForward(String learnForward) {
		this.learnForward = learnForward;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getIsReturnVist() {
		return isReturnVist;
	}

	public void setIsReturnVist(String isReturnVist) {
		this.isReturnVist = isReturnVist;
	}

	public Timestamp getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Timestamp firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	public String getIsHome() {
		return isHome;
	}

	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}

	public Timestamp getHomeTime() {
		return homeTime;
	}

	public void setHomeTime(Timestamp homeTime) {
		this.homeTime = homeTime;
	}

	public String getLostValid() {
		return lostValid;
	}

	public void setLostValid(String lostValid) {
		this.lostValid = lostValid;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getIsReturnMoney() {
		return isReturnMoney;
	}

	public void setIsReturnMoney(String isReturnMoney) {
		this.isReturnMoney = isReturnMoney;
	}

	public String getIsInClass() {
		return isInClass;
	}

	public void setIsInClass(String isInClass) {
		this.isInClass = isInClass;
	}

	public Timestamp getInClassTime() {
		return inClassTime;
	}

	public void setInClassTime(Timestamp inClassTime) {
		this.inClassTime = inClassTime;
	}

	public String getInClassContent() {
		return inClassContent;
	}

	public void setInClassContent(String inClassContent) {
		this.inClassContent = inClassContent;
	}

	public String getAskerContent() {
		return askerContent;
	}

	public void setAskerContent(String askerContent) {
		this.askerContent = askerContent;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getFromPart() {
		return fromPart;
	}

	public void setFromPart(String fromPart) {
		this.fromPart = fromPart;
	}

	public String getStuConcern() {
		return stuConcern;
	}

	public void setStuConcern(String stuConcern) {
		this.stuConcern = stuConcern;
	}

	public String getIsBaoBei() {
		return isBaoBei;
	}

	public void setIsBaoBei(String isBaoBei) {
		this.isBaoBei = isBaoBei;
	}

	public String getZiXunName() {
		return ziXunName;
	}

	public void setZiXunName(String ziXunName) {
		this.ziXunName = ziXunName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getReturnMoneyReason() {
		return returnMoneyReason;
	}

	public void setReturnMoneyReason(String returnMoneyReason) {
		this.returnMoneyReason = returnMoneyReason;
	}

	public double getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(double preMoney) {
		this.preMoney = preMoney;
	}

	public Timestamp getPreMoneyTime() {
		return preMoneyTime;
	}

	public void setPreMoneyTime(Timestamp preMoneyTime) {
		this.preMoneyTime = preMoneyTime;
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
		return "Students [id=" + id + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", phone=" + phone + ", stuStatus="
				+ stuStatus + ", perState=" + perState + ", msgSource="
				+ msgSource + ", sourceUrl=" + sourceUrl + ", sourceKeyWord="
				+ sourceKeyWord + ", address=" + address + ", netPusherId="
				+ netPusherId + ", askerId=" + askerId + ", qq=" + qq
				+ ", weiXin=" + weiXin + ", content=" + content
				+ ", createTime=" + createTime + ", birthStart=" + birthStart
				+ ", birthEnd=" + birthEnd + ", learnForward=" + learnForward
				+ ", isValid=" + isValid + ", record=" + record
				+ ", isReturnVist=" + isReturnVist + ", firstVisitTime="
				+ firstVisitTime + ", isHome=" + isHome + ", homeTime="
				+ homeTime + ", lostValid=" + lostValid + ", isPay=" + isPay
				+ ", payTime=" + payTime + ", money=" + money
				+ ", isReturnMoney=" + isReturnMoney + ", isInClass="
				+ isInClass + ", inClassTime=" + inClassTime
				+ ", inClassContent=" + inClassContent + ", askerContent="
				+ askerContent + ", isDel=" + isDel + ", fromPart=" + fromPart
				+ ", stuConcern=" + stuConcern + ", isBaoBei=" + isBaoBei
				+ ", ziXunName=" + ziXunName + ", createUser=" + createUser
				+ ", returnMoneyReason=" + returnMoneyReason + ", preMoney="
				+ preMoney + ", preMoneyTime=" + preMoneyTime + ", page=" + page + ", rows=" + rows
				+ ", startIndex=" + startIndex + "]";
	}

}
