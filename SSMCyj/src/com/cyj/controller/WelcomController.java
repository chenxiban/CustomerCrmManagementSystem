package com.cyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Description:   菜单 模块控制器
 * @author         Cyj
 * @Date           2018-5-16
 * @Email          867647213@qq.com
 */
@Controller
public class WelcomController {

	@RequestMapping("/welcom")
	public String welcome() {
		return "index";
	}

}
