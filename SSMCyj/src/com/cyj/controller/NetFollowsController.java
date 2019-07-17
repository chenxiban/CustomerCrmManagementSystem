package com.cyj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.NetFollows;
import com.cyj.entity.Result;
import com.cyj.service.NetFollowsService;


@RestController
@RequestMapping(value = "/netfloows", name = "网络跟踪模块")
public class NetFollowsController {
	
	@Autowired
	private NetFollowsService netFollowsService;
	
	/**
	 * http://localhost:8080/SSMCyj/netfloows/getNetfloows.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNetfloows.php", produces = "text/plain;charset=UTF-8", name = "查询跟踪信息")
	public Object getNetfloows(NetFollows n) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", netFollowsService.getNetFollowsCount(n));
		map.put("rows", netFollowsService.getNetFollows(n));
		System.out.println("total"+netFollowsService.getNetFollows(n));
		return map;
	}
	
	/**
	 * http://localhost:8080/SSMCyj/netfloows/addNetFollows.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addNetFollows.php", produces = "text/plain;charset=UTF-8", name = "添加跟踪信息")
	public Object addNetFollows(NetFollows n) {
		n.setCreateTime(new Date());
		if (netFollowsService.addNetFollows(n)) {
			return new Result(true,"跟踪信息,添加成功!");
		} else {
			return new Result(false,"跟踪信息,添加失败!");
		}
	}

}
