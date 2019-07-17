package com.cyj.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyj.entity.Askers;
import com.cyj.entity.Roles;
import com.cyj.entity.UserRoles;
import com.cyj.entity.Users;
import com.cyj.service.AskersService;
import com.cyj.service.RolesService;
import com.cyj.service.UserRolesService;

public class RolesTest {
	private ApplicationContext context = null;
	private RolesService r = null;
	private UserRolesService ur = null;
	private AskersService a=null;

	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-servlet.xml");
		r = (RolesService) context.getBean("rolesServiceImpl");
		ur = (UserRolesService) context.getBean("userRolesServiceImpl");
		a = (AskersService) context.getBean("askersServiceImpl");
	}
	
	@Test
	public void showRoles(){// 查询角色
		/*Roles rs=new Roles();
		System.out.println(r.showRoles(rs));*/
		/*List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(2);
		System.out.println(r.getRolesList(list));*/
		List<Users> list=r.selUsersByRoleName("咨询师");
		Random rand=new Random();
		int a=rand.nextInt(list.size());
		System.out.println(a);
		System.out.println(list.get(a).getLoginName());
		/*for (int i = 0; i < list.size(); i++) {
		    list.get(i).getLoginName();
			System.out.println(list.get(i).getLoginName());
		}*/
	}
	
	@Test
	public void suijishu(){// 查询角色
		/*Random rand=new Random();
		int a=rand.nextInt(3);
		System.out.println(a);*/
		Askers askers=new Askers();
		askers.setAskerName("万达哈");
		askers.setCheckState("未签到");
		askers.setRoleName("咨询师");
		System.out.println(a.addAskers(askers));
	}
	
	@Test
	public void addRoles(){//添加角色
		Roles o=new Roles();
		o.setName("fa23");
		System.out.println(r.addRoles(o));
	}
	
	@Test
	public void updRoles(){// 修改角色信息
		Roles o=new Roles();
		o.setId(18);
		o.setName("项目经理");
		System.out.println(r.updRoles(o));
	}
	
	@Test
	public void delRoles(){// 批量删除
		List<String> list=new ArrayList<String>();
		String id="17";
		String [] ids=id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(r.delRoles(list));
	}
	
	@Test
	public void getUserRolesByUserId(){// 根据用户ID获取角色
		Integer id=3;
		System.out.println(r.getUserRolesByUserId(id));
	}
	
	@Test
	public void addUserRoles(){//添加用户角色
		UserRoles o=new UserRoles();
		o.setUserId(5);
		o.setRoleId(12);
		System.out.println(r.addUserRoles(o));
	}
	
	@Test
	public void delUserRole(){// 批量删除用户角色
		List<String> rlist=new ArrayList<String>();
		String roleId="9";
		String [] rid=roleId.split(",");
		for (String rdids : rid) {
			rlist.add(rdids);
		}
		System.out.println(r.delUserRole(rlist,14));
	}
	
	@Test
	public void delUserRoles(){
		List<Integer> id=ur.getUserRole(1);
		System.out.println(id);
		System.out.println(r.getRolesList(id));
	}
	
}
