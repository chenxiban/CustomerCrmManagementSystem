package com.cyj.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.DyNamicMessages;
import com.cyj.service.DyNamicMessagesService;

public class DyNamicMessagesTest {
	private ApplicationContext context = null;
	private DyNamicMessagesService d = null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		d = (DyNamicMessagesService) context.getBean("dyNamicMessagesServiceImpl");
	}

	@Test
	public void showDyNamicMessages() {// 查询模块
		
		
		DyNamicMessages n=new DyNamicMessages();
		List<String> ids = d.selDyNamicMessages("咨询师3");
		n.setPage(1);
		n.setRows(10);
		n.setIds(ids);
		System.out.println(d.getDyNamicMessages(n));
		//		System.out.println(d.getDyNamicMessages(n,ids));
	}
}
