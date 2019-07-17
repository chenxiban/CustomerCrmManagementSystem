package com.cyj.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.dao.ModulesDao;
import com.cyj.entity.Modules;
import com.cyj.service.ModulesService;

public class ModulesTest {
	private ApplicationContext context = null;
	private ModulesService m = null;
	private ModulesDao aDao=null;
	private Modules u=new Modules();
	
	private Date date = new Date();
	private Timestamp nousedate = new Timestamp(date.getTime());

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		m = (ModulesService) context.getBean("modulesServiceImpl");
	}

	@Test
	public void showModules() {// 查询模块
		List<Integer> roleId=new ArrayList<Integer>();
		roleId.add(1);
		roleId.add(2);
		roleId.add(3);
		roleId.add(4);
		System.out.println(m.getModulesRole(roleId));
	}
	
	@Test
	public void addModules() {// 查询模块
		u.setName("学生分配");
		u.setParentId(10);
		u.setPath(null);
		u.setWeight(22);
		u.setCreateTime(new Date());
		u.setLastUpdateTime(nousedate);
		u.setFounder("小佳");
		System.out.println(m.addModules(u));
	}
	
	@Test
	public void updModules() {// 查询模块
		u.setName("啊嗯啊嗯啊嗯嗯嗯嗯");
		u.setParentId(23);
		u.setPath("aaaaa.html");
		u.setWeight(22);
		u.setCreateTime(new Date());
		u.setLastUpdateTime(nousedate);
		u.setFounder("小佳");
		u.setId(23);
		System.out.println(m.updModules(u));
	}
	
	@Test
	public void delModules() {// 查询模块
		List<String> parertId=new ArrayList<String>();
		parertId.add("1");
		parertId.add("6");
		/*List<String> id=new ArrayList<String>();
		id.add("29");
		id.add("30");*/
		System.out.println(m.getChildrenByParentId(parertId));
		System.out.println(m.getChildrenByParentIds(parertId));
	}
	
	@Test
	public void getM() {// 查询模块
		List<Integer> roleId=new ArrayList<Integer>();
		roleId.add(12);
		System.out.println(roleId);
		System.out.println(aDao.getModulesRoles(roleId));
		System.out.println(m.queryModules("角色管理"));
	}
	
	

}
