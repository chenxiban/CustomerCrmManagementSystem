package com.cyj.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.Students;
import com.cyj.service.StudentsService;

public class StudentsTest {
	private ApplicationContext context = null;
	private StudentsService s = null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		s = (StudentsService) context.getBean("studentsServiceImpl");
	}

	@Test
	public void showSignIn() {// 查询模块
		Students u=new Students();
		u.setPage(1);
		u.setRows(10);
		System.out.println(s.getStudents(u));
	}
	
	@Test
	public void updStudents(){// 修改角色信息
		/*Students u=new Students();
		u.setId(21);
		u.setName("网吧");
		u.setAskerContent("你好啊确实不错");
		System.out.println(s.updStudents(u));*/
		/*String id="22,23";
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		String ziXunName="咨询师1";
		
		System.out.println(s.updStudentsByIds(ziXunName,list));*/
		System.out.println(s.getStudentsCounts("郑州"));
	}
}
