package com.cyj.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Askers;
import com.cyj.entity.Result;
import com.cyj.entity.SignIn;
import com.cyj.entity.Users;
import com.cyj.service.AskersService;
import com.cyj.service.SignInService;
import com.cyj.service.UsersService;
import com.cyj.util.IsEmptyUtils;

@RestController
@RequestMapping(value = "/signin", name = "签到模块")
public class SignInController {

	@Autowired
	private SignInService sService = null;
	@Autowired
	private UsersService servcie = null;
	@Autowired
	private AskersService aService = null;

	

	/**
	 * http://localhost:8080/SSMCyj/signin/getSignin.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSignin.php", produces = "text/plain;charset=UTF-8", name = "查询签到")
	public Object getSignin(SignIn u) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", sService.getSignInCount(u));
		map.put("rows", sService.getSignIn(u));
		return map;
	}

	/**
	 * http://localhost:8080/SSMCyj/signin/updSignin.php
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/updSignin.php", produces = "text/plain;charset=UTF-8", name = "修改签到")
	public Object updSignin(SignIn u) throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 06:30:00");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Askers askers = aService.getAskersById(u.getUserId());
		
		String DATE1=formatter.format(currentTime);
		Date d2=new Date();
		String DATE2=df.format(d2);
		Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        System.out.println(dt1+"|"+dt2);

		if (u.getSignState().equals("已签退")) {
			u.setSignOutTime(new Date());
		}

		if (IsEmptyUtils.isEmpty(askers)) {
			if (dt1.getTime() > dt2.getTime()) {
				u.setSignStar("否");
	        	if (sService.updSignIn(u)) {
					return new Result(true, "签退成功!");
				} else {
					return new Result(false, "签退失败!");
				}
	        } else if (dt1.getTime() < dt2.getTime()) {
	        	u.setSignStar("是");
	        	if (sService.updSignIn(u)) {
					return new Result(true, "您当前已早退,签退成功!");
				} else {
					return new Result(false, "签退失败!");
				}
	        } else {
	        	return new Result(false, "未知!");
	        }
		} else {
			askers.setCheckState("已签退");
			if (dt1.getTime() > dt2.getTime()) {
				u.setSignStar("否");
	        	if (sService.updSignIn(u) && aService.updAskersByName(askers)) {
					return new Result(true, "签退成功!");
				} else {
					return new Result(false, "签退失败!");
				}
	        } else if (dt1.getTime() < dt2.getTime()) {
	        	u.setSignStar("是");
	        	if (sService.updSignIn(u) && aService.updAskersByName(askers)) {
					return new Result(true, "您当前已早退,签退成功!");
				} else {
					return new Result(false, "签退失败!");
				}
	        } else {
	        	return new Result(false, "未知!");
	        }
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/signin/addSignin.php
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addSignin.php", produces = "text/plain;charset=UTF-8", name = "添加签到")
	public Object addSignin(SignIn u) throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 08:30:00");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Askers askers = aService.getAskersById(u.getUserId());
		
		String DATE1=formatter.format(currentTime);
		Date d2=new Date();
		String DATE2=df.format(d2);
		Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        System.out.println(dt1+"|"+dt2);
        
        String today = formatter.format(d2);
		SignIn s = sService.getSign(u.getUserId(), today);
		
		Users v=servcie.getUserById(u.getUserId());
		if (!IsEmptyUtils.isEmpty(v) && v.getLeaveState().equals("是")) {
			return new Result(false, "请假期间无法进行签到,请报道后再来签到!");
		}
        
		if (IsEmptyUtils.isEmpty(askers)) {
			if (IsEmptyUtils.isEmpty(s)) {
				u.setSignTime(new Date());
				if (dt1.getTime() > dt2.getTime()) {
					u.setSignStars("否");
		        	if (sService.addSignIn(u)) {
						return new Result(true, "按时签到成功!");
					} else {
						return new Result(false, "按时签到失败!");
					}
				}else if (dt1.getTime() < dt2.getTime()) {
		        	u.setSignStars("是");
					if (sService.addSignIn(u)) {
						return new Result(true, "您当前已迟到,签到成功!");
					} else {
						return new Result(false, "您当前已迟到,签到失败!");
					}
		        } else {
		        	return new Result(false, "未知!");
		        }
				
			} else {
				if (s.getSignState().equals("已签到")) {
					return new Result(false, "您今天已签到!");
				} else {
					return new Result(false, "您今天已签退,请明天再来签到!");
				}
			}
		} else {
			if (IsEmptyUtils.isEmpty(s)) {
				u.setSignTime(new Date());
				askers.setCheckState("已签到");
				askers.setCheckInTime(new Date());
				if (dt1.getTime() > dt2.getTime()) {
					u.setSignStars("否");
		        	if (sService.addSignIn(u) && aService.updAskersByName(askers)) {
						return new Result(true, "按时签到成功!");
					} else {
						return new Result(false, "按时签到失败!");
					}
				}else if (dt1.getTime() < dt2.getTime()) {
					u.setSignStars("是");
					if (sService.addSignIn(u) && aService.updAskersByName(askers)) {
						return new Result(true, "您当前已迟到,签到成功!");
					} else {
						return new Result(false, "您当前已迟到,签到失败!");
					}
		        } else {
		        	return new Result(false, "未知!");
		        }
			} else {
				if (s.getSignState().equals("已签到")) {
					return new Result(false, "您今天已签到!");
				} else {
					return new Result(false, "您今天已签退,请明天再来签到!");
				}
			}
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/signin/getSignInByTime.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSignInByTime.php", produces = "text/plain;charset=UTF-8", name = "签到图表")
	public Object getSignInByTime() {
		String yi = "2018-01";
		String er = "2018-02";
		String san = "2018-03";
		String si = "2018-04";
		String wu = "2018-05";
		String liu = "2018-06";
		String qi = "2018-07";
		String ba = "2018-08";
		String jiu = "2018-09";
		String shi = "2018-10";
		String shiyi = "2018-11";
		String shier = "2018-12";

		// 双状态
		String signIns = "已签到,已签退";
		List<String> list = new ArrayList<String>();
		String[] signInss = signIns.split(",");
		for (String dids : signInss) {
			list.add(dids);
		}
		int one = sService.getSignInByTime(yi, list);
		int two = sService.getSignInByTime(er, list);
		int three = sService.getSignInByTime(san, list);
		int four = sService.getSignInByTime(si, list);
		int five = sService.getSignInByTime(wu, list);
		int six = sService.getSignInByTime(liu, list);
		int sev = sService.getSignInByTime(qi, list);
		int eight = sService.getSignInByTime(ba, list);
		int nine = sService.getSignInByTime(jiu, list);
		int ten = sService.getSignInByTime(shi, list);
		int syi = sService.getSignInByTime(shiyi, list);
		int ser = sService.getSignInByTime(shier, list);

		// 单状态
		String signOut = "已签退";
		List<String> lists = new ArrayList<String>();
		String[] signOuts = signOut.split(",");
		for (String dids : signOuts) {
			lists.add(dids);
		}
		int ones = sService.getSignInByTime(yi, lists);
		int twos = sService.getSignInByTime(er, lists);
		int threes = sService.getSignInByTime(san, lists);
		int fours = sService.getSignInByTime(si, lists);
		int fives = sService.getSignInByTime(wu, lists);
		int sixs = sService.getSignInByTime(liu, lists);
		int sevs = sService.getSignInByTime(qi, lists);
		int eights = sService.getSignInByTime(ba, lists);
		int nines = sService.getSignInByTime(jiu, lists);
		int tens = sService.getSignInByTime(shi, lists);
		int syis = sService.getSignInByTime(shiyi, lists);
		int sers = sService.getSignInByTime(shier, lists);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("one", one);
		map.put("two", two);
		map.put("three", three);
		map.put("four", four);
		map.put("five", five);
		map.put("six", six);
		map.put("sev", sev);
		map.put("eight", eight);
		map.put("nine", nine);
		map.put("ten", ten);
		map.put("syi", syi);
		map.put("ser", ser);

		map.put("ones", ones);
		map.put("twos", twos);
		map.put("threes", threes);
		map.put("fours", fours);
		map.put("fives", fives);
		map.put("sixs", sixs);
		map.put("sevs", sevs);
		map.put("eights", eights);
		map.put("nines", nines);
		map.put("tens", tens);
		map.put("syis", syis);
		map.put("sers", sers);

		Users u = new Users();
		map.put("utotal", servcie.getUserCount(u));

		return new Result(true, map);
	}

}
