package com.cyj.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.NetFollows;
import com.cyj.service.NetFollowsService;

public class NetFollowsTest {
	private ApplicationContext context = null;
	private NetFollowsService s = null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		s = (NetFollowsService) context.getBean("netFollowsServiceImpl");
	}

	@Test
	public void showNetFollows() {// 查询模块
		NetFollows u=new NetFollows();
		u.setStudentId(12);
		u.setPage(1);
		u.setRows(10);
		System.out.println(s.getNetFollows(u));
	}
}
