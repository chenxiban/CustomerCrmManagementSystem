package com.cyj.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Askers;
import com.cyj.entity.Result;
import com.cyj.service.AskersService;
import com.cyj.service.SignInService;
import com.cyj.util.IsEmptyUtils;

@RestController
@RequestMapping(value="/askers",name="咨询师模块")
public class AskersController {
	
	@Autowired
	private AskersService aService;
	@Autowired
	private SignInService sService;
	
	private Date currentTime = new Date();
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * http://localhost:8080/SSMCyj/askers/getaAskers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getaAskers.php", produces = "text/plain;charset=UTF-8", name = "查询分量模块")
	public Object getaAskers(Askers a) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", aService.getAskersCount(a));
		map.put("rows", aService.getAskers(a));
		return map;
	}
	
	/**
	 * http://localhost:8080/SSMCyj/askers/updAskers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updAskers.php", produces = "text/plain;charset=UTF-8", name = "调整分量设置")
	public Object updAskers(Askers a) {
		//注意这里用static静态修饰后,创建对象也是行不通的
		if (aService.updAskers(a)) {
			
			return new Result(true,"调整分量成功!");
		} else {
			return new Result(false,"调整分量失败!");
		}
	}
	
	/**
	 * http://localhost:8080/SSMCyj/askers/addAskers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addAskers.php", produces = "text/plain;charset=UTF-8", name = "添加咨询师")
	public Object addAskers(Askers a) {
		String today = formatter.format(currentTime);
		System.out.println(sService.getSinByAskerName(today, a.getAskerName()));
		if (IsEmptyUtils.isEmpty(sService.getSinByAskerName(today, a.getAskerName()))) {
			a.setCheckState("未签到");
			if (aService.addAskers(a)) {
				return true;
			} else {
				return false;
			}
		} else {
			a.setCheckState(sService.getSinByAskerName(today, a.getAskerName()));
			if (aService.addAskers(a)) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	/**
	 * http://localhost:8080/SSMCyj/askers/delAsker.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delAsker.php", produces = "text/plain;charset=UTF-8", name = "删除咨询师")
	public Object delAsker(@RequestParam(value = "askerName") String askerName) {
		List<String> list = new ArrayList<String>();
		String [] ids=askerName.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		if (aService.delAskers(list)) {
			return true;
		} else {
			return false;
		}
	}
	
}
