package com.cyj.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.CaiWu;
import com.cyj.entity.KaoQin;
import com.cyj.entity.Result;
import com.cyj.entity.SignIn;
import com.cyj.entity.Users;
import com.cyj.entity.Vacation;
import com.cyj.service.SignInService;
import com.cyj.service.UsersService;
import com.cyj.service.VacationService;
import com.cyj.util.IsEmptyUtils;
import com.cyj.util.PoiUtils;

/**
 * 
 * @Description: 考勤模块控制器
 * @author Cyj
 * @Date 2018-5-16
 * @Email 867647213@qq.com
 */
@RestController
@RequestMapping(value = "/vacation", name = "考勤模块")
public class VacationController {

	@Autowired
	private VacationService vService = null;
	@Autowired
	private UsersService servcie;
	@Autowired
	private SignInService sService = null;

	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类

	/**
	 * http://localhost:8080/SSMCyj/vacation/getVacation.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVacation.php", produces = "text/plain;charset=UTF-8", name = "所有请假信息")
	public Object getStudents(Vacation v) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", vService.getVacationCount(v));
		map.put("rows", vService.getVacation(v));
		return map;
	}

	/**
	 * http://localhost:8080/SSMCyj/vacation/getVacationByName.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVacationByName.php", produces = "text/plain;charset=UTF-8", name = "我的请假信息")
	public Object getVacationByName(Vacation v) {
		return vService.getVacationByName(v);
	}

	/**
	 * http://localhost:8080/SSMCyj/vacation/addVacation.php
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addVacation.php", produces = "text/plain;charset=UTF-8", name = "申请请假")
	public Object addVacation(Vacation v) throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		v.setLeaveTime(currentTime);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, v.getLeaveDate());

		Date aDates = new Date();
		aDates = ca.getTime();

		String enddate = formatter.format(aDates);
		Date aDate = formatter.parse(enddate);
		v.setEndTime(aDate);

		if (vService.addVacation(v)) {
			Users u = new Users();
			u.setId(v.getUserId());
			u.setLeaveBoolean("否");
			servcie.updUsers(u);

			return new Result(true, "申请成功!");
		} else {
			return new Result(false, "申请失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/vacation/updVacation.php
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updVacation.php", produces = "text/plain;charset=UTF-8", name = "审批请假")
	public Object updVacation(Vacation v) throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		v.setLeaveTime(currentTime);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, v.getLeaveDate());

		Date aDates = new Date();
		aDates = ca.getTime();

		String enddate = formatter.format(aDates);
		Date aDate = formatter.parse(enddate);
		v.setEndTime(aDate);

		if (v.getIsAgree().equals("不准许")) {
			Users u = new Users();
			u.setId(v.getUserId());
			u.setLeaveState("否");
			u.setLeaveBoolean("是");
			servcie.updUsers(u);
		}

		if (v.getIsAgree().equals("准假")) {
			Users u = new Users();
			u.setId(v.getUserId());
			u.setLeaveState("是");
			servcie.updUsers(u);
		}

		if (vService.updVacation(v)) {
			return new Result(true, "审批成功!");
		} else {
			return new Result(false, "审批失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/vacation/updVacationByState.php
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updVacationByState.php", produces = "text/plain;charset=UTF-8", name = "请假报道")
	public Object updVacationByState(Vacation v) {

		Users u = new Users();
		u.setId(v.getUserId());
		u.setLeaveState("否");
		u.setLeaveBoolean("是");

		if (servcie.updUsers(u)) {
			return new Result(true, "报道成功!");
		} else {
			return new Result(false, "报道失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/vacation/getCheckingIn.php
	 * 
	 * @param 请假表对象
	 *            v
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getCheckingIn.php", produces = "text/plain;charset=UTF-8", name = "考勤自助查询")
	public Object getCheckingIn(SignIn s) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();

		SignIn s1 = new SignIn();
		s1.setLoginName(s.getLoginName());
		s1.setSignStars("是");
		s1.setSignState("已签退");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			s1.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			s1.setKqYue(kqYue);
		}
		map.put("cd", sService.getSignInBySign(s1));

		SignIn s2 = new SignIn();
		s2.setLoginName(s.getLoginName());
		s2.setSignStar("是");
		s2.setSignState("已签退");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			s2.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			s2.setKqYue(kqYue);
		}
		map.put("zt", sService.getSignInBySign(s2));

		SignIn s3 = new SignIn();
		s3.setLoginName(s.getLoginName());
		s3.setSignStars("否");
		s3.setSignStar("否");
		s3.setSignState("已签退");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			s3.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			s3.setKqYue(kqYue);
		}
		map.put("zc", sService.getSignInBySign(s3));

		Vacation v = new Vacation();
		v.setName(s.getLoginName());
		v.setLeaveType("病假");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			v.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			v.setKqYue(kqYue);
		}
		Vacation v3 = vService.getVacationName(v);
		if (!IsEmptyUtils.isEmpty(v3)) {
			map.put("bj", v3.getLeaveDate());
		} else {
			map.put("bj", 0);
		}

		Vacation v2 = new Vacation();
		v2.setName(s.getLoginName());
		v2.setLeaveType("事假");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			v2.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			v2.setKqYue(kqYue);
		}
		Vacation v4 = vService.getVacationName(v2);
		if (!IsEmptyUtils.isEmpty(v4)) {
			map.put("sj", v4.getLeaveDate());
		} else {
			map.put("sj", 0);
		}

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		int b = sService.getSignInBySign(s3);
		int c = maxDate - b;
		map.put("lq", c);

		return new Result(true, map);
	}

	/**
	 * Excel导出数据 http://localhost:8080/SSMCyj/vacation/download.php
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/download.php", produces = "text/html;charset=UTF-8", name = "导出Excel")
	public String download(String loginNames, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String downloadName = "导出的考勤情况.xls";

		loginNames = java.net.URLDecoder.decode(
				request.getParameter("loginNames"), "utf-8");
		System.out.println(loginNames);

		SignIn s1 = new SignIn();
		s1.setLoginName(loginNames);
		s1.setSignStars("是");
		s1.setSignState("已签退");
		int a = sService.getSignInBySign(s1);

		SignIn s2 = new SignIn();
		s2.setLoginName(loginNames);
		s2.setSignStar("是");
		s2.setSignState("已签退");
		int b = sService.getSignInBySign(s2);

		SignIn s3 = new SignIn();
		s3.setLoginName(loginNames);
		s3.setSignStars("否");
		s3.setSignStar("否");
		s3.setSignState("已签退");
		int c = sService.getSignInBySign(s3);

		Vacation v = new Vacation();
		v.setName(loginNames);
		v.setLeaveType("病假");
		Vacation v3 = vService.getVacationName(v);
		int bj = 0;
		if (!IsEmptyUtils.isEmpty(v3)) {
			bj = v3.getLeaveDate();
		}

		Vacation v2 = new Vacation();
		v2.setName(loginNames);
		v2.setLeaveType("事假");
		Vacation v4 = vService.getVacationName(v2);
		int sj = 0;
		if (!IsEmptyUtils.isEmpty(v4)) {
			sj = v4.getLeaveDate();
		}

		Calendar as = Calendar.getInstance();
		as.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		as.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = as.get(Calendar.DATE);
		int bs = sService.getSignInBySign(s3);
		int cs = maxDate - bs;

		KaoQin k = new KaoQin();
		k.setName(loginNames);
		k.setCd(a);
		k.setZt(b);
		k.setZc(c);
		k.setBj(bj);
		k.setSj(sj);
		k.setLq(cs);

		System.out.println("kkkkkkkkkkkkk>=" + k);

		List<KaoQin> list = new ArrayList<KaoQin>();// 要导出的数据集合
		list.add(k);
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, KaoQin.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * http://localhost:8080/SSMCyj/vacation/getVacationGz.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVacationGz.php", produces = "text/plain;charset=UTF-8", name = "自助查询财务")
	public Object getVacationGz(SignIn s) {
		List<CaiWu> list=new ArrayList<CaiWu>();
		CaiWu caiWu=new CaiWu();
		
		SignIn s1 = new SignIn();
		s1.setLoginName(s.getLoginName());
		s1.setSignStars("否");
		s1.setSignStar("否");
		s1.setSignState("已签退");
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			s1.setKqYue(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			s.setKqYue(kqYue);
		}
		int qdday=sService.getSignInBySign(s1);
		
		caiWu.setJiBen(qdday*300);// 基本工资计算
		
		if (!IsEmptyUtils.isEmpty(s.getKqYue())) {
			caiWu.setFaDate(s.getKqYue().substring(0, 7));
		} else {
			Date aDate = new Date();
			String year = String.format("%tY", aDate);
			String mon = String.format("%tm", aDate);

			String kqYue = year + "-" + mon;
			caiWu.setFaDate(kqYue);
		}
		
		if (qdday > 20) {
			caiWu.setDaBiao(3000);
		}else {
			caiWu.setDaBiao(0);
		}
		
		int a=(int) (qdday*300 * 0.15);
		caiWu.setSiJin(a);
		
		int b=qdday*300+caiWu.getDaBiao()-a;
		int d=0;
		
		if (b <= 1500) {
			d=(int) (b*0.03);
			b=b-d;
			caiWu.setGeShui(d);
		} else if (1500 < b && b <= 4500) {
			d=(int) (b*0.1);
			b=b-d;
			caiWu.setGeShui(d);
		} else if (4500 < b && b <= 9000) {
			d=(int) (b*0.2);
			b=b-d;
			caiWu.setGeShui(d);
		}
		
		caiWu.setXianJin(b);
		
		caiWu.setZongJi(qdday*300+caiWu.getDaBiao());
		list.add(caiWu);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", 1);
		map.put("rows", list);
		return map;
	}

}
