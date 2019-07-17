package com.cyj.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.Askers;
import com.cyj.service.AskersService;

public class AskersTest {
		private ApplicationContext context = null;
		private AskersService a = null;

		@Before
		public void aa() {
			context = new ClassPathXmlApplicationContext(
					"applicationContext-servlet.xml");
			a = (AskersService) context.getBean("askersServiceImpl");
		}

		@Test
		public void showDyNamicMessages() {// 查询模块
			/*Askers as=a.getAskersById(17);
			System.out.println(as.getCheckState());*/
			
			Askers as = a.getAskersById(17);
			System.out.println(as);
			/*as.setCheckState("已签退");
			System.out.println(a.updAskersByName(as));*/
		}

}
