package com.cyj.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.service.PermissionService;

public class PermissionTest {
	private ApplicationContext context = null;
	private PermissionService m = null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		m = (PermissionService) context.getBean("permissionServiceImpl");
	}

	@Test
	public void showModules() {// 查询模块
		System.out.println(m.queryNode());
	}
}
