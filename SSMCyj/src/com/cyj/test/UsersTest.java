package com.cyj.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.Users;
import com.cyj.md5.PasswordEncoder;
import com.cyj.service.UsersService;

public class UsersTest {
	private ApplicationContext context = null;
	private UsersService u = null;

	@Before
	public void aa() {
		/**
		 * ApplicationContext包括 BeanFactory 的全部功能，
		 * 除非应用程序对性能要求很高时才考虑BeanFactory，
		 * 其他情况下建议优先使用ApplicationContext。 应用中出现多个配置文件时，
		 * 应采用BeanFactory的子接口ApplicationContext创建 BeanFactory的实例。
		 * ApplicationContext通常使用ClassPathXmlApplicationContext实现类
		 */
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");//创建Spring容器的代码
		u = (UsersService) context.getBean("usersServiceImpl");//从Spring容器中获取一个bean对象
	}

	@Test
	public void showUsers(){// 查询用户
		Users users=new Users();
		users.setLoginName("胖");
		String loginName="胖胖";
		System.out.println(u.getUser(loginName));
		System.out.println(u.getUserCount(users));
	}

	@Test
	public void updUsers() {// 修改用户
		Date a1 = new Date();
		Users a = new Users();
		a.setId(1);
		a.setLockTime(a1);
		System.out.println(u.updUsers(a));
	}

	@Test
	public void addUsers() {// 添加用户
		Users a = new Users();
		a.setLoginName("王大哈");
		a.setPasswords("1111");
		a.setCreateTime(new Date());
		a.setProtectEMail("213213@qq.com");
		a.setProtectMTel("12345678910");
		System.out.println(u.addUsers(a));
	}

	@Test
	public void delUsers() {// 删除用户
		List<String> list = new ArrayList<String>();
		String id = "6,7";
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(u.delUsers(list));
	}

	@Test
	public void MD5() {// 用户密码加密
		PasswordEncoder encoder = new PasswordEncoder("小旺", "MD5");
		System.out.println(encoder.encode("666"));
		System.out.println(u.queryPermissionValueByUserId(1));
	}
	
	@Test
	public void getUserList() {// 用户密码加密
		Users a=new Users();
		a.setLoginName("小");
		System.out.println(u.showUsers(a));
	}
	
	@Test
	public void getUserCount() {// 用户密码加密
		/*Users a=new Users();
		a.setLoginName("小");
		System.out.println(u.getUserCount(a));*/
	}
	
	private final StringBuffer ass=new StringBuffer("My name is YongJia ");
	
	@Test
	public void Finals() {// 用户密码加密
		ass.append("Chen !");
		System.out.println(ass);
		String css="a"+"b"+"c"+"d";
		System.out.println(css);
	}

}
