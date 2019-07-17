package com.cyj.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.SignIn;
import com.cyj.service.SignInService;
import com.cyj.service.VacationService;

public class SignInTest {
	private ApplicationContext context = null;
	private SignInService s = null;
	private VacationService v=null;
	private Date currentTime = new Date();
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 08:30:00");
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		s = (SignInService) context.getBean("signInServiceImpl");
		v = (VacationService) context.getBean("vacationServiceImpl");
	}

	@Test
	public void showSignIn() {// 查询模块
		/*SignIn u=new SignIn();
		u.setPage(1);
		u.setRows(10);
		u.setUserId(1);
		u.setSignState("已签到");
		u.setSignTime(new Date());*/
		/*String signTime="2018-06";
		List<String> list = new ArrayList<String>();
		String id = "已签到";
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}*/
		/*System.out.println(s.getSignInCount(u));*/
		/*System.out.println(s.getSignInByTime(signTime, list));*/
		/*System.out.println(s.addSignIn(u));*/
		String today = formatter.format(currentTime);
		String askerName="咨询师1";
		System.out.println(s.getSinByAskerName(today, askerName));
	}
	
	@Test
	public void aaaaaaaaa() throws ParseException{
		/*String signState="未签到";
		Date signTime=null;
		Date signOutTime=null;*/
		//sService.updSignInTime(signState,signTime,signOutTime);
		//System.out.println(s.updSignInTime(signState,signTime,signOutTime));
		/*String today = formatter.format(currentTime);
		System.out.println(s.getSign(1,today));*/
		/*Vacation vs=new Vacation();
		vs.setName("小佳");
		vs.setUserId(1);
		vs.setContent("回家探亲!望广大老总批准!!!");
		vs.setLeaveDate(7);	
		vs.setLeaveType("探亲假");
		vs.setLeaveTime(currentTime);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, vs.getLeaveDate());
		currentTime = ca.getTime();
		String enddate = formatter.format(currentTime);
		Date aDate=formatter.parse(enddate);
		vs.setEndTime(aDate);
		System.out.println(v.addVacation(vs));*/
		
		SignIn s3 = new SignIn();
		s3.setLoginName("小佳");
		s3.setSignStars("否");
		s3.setSignStar("否");
		s3.setSignState("已签退");
		System.out.println(s.getSignInBySign(s3));
	}
	
	/** 
	 * 得到指定月的天数 
	 * */  
	@Test
	public void getCurrentMonthLastDay()  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    System.out.println(maxDate);
	}  
	
	/** 
	 * 得到指定月的天数 
	 * */  
	@Test
	public void getCurrentMonthLastDays()  
	{  
		DateFormat dfs = new SimpleDateFormat("yyyy-MM");
		Date aDate=new Date();
		String year=String.format("%tY", aDate);
		String mon=String.format("%tm", aDate);
		
		String kqYue=year+"-"+mon;
		System.out.println(kqYue);
	} 
	
	
	
}
