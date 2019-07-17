package com.cyj.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.service.UserRolesService;

public class UsersRolesTest {
	private ApplicationContext context = null;
	private UserRolesService u = null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		u = (UserRolesService) context.getBean("userRolesServiceImpl");
	}

	@Test
	public void showUserId(){
		System.out.println(u.getUserId());
	}

}
