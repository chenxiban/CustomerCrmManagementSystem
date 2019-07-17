package com.cyj.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyj.entity.Result;
import com.cyj.entity.SignIn;
import com.cyj.entity.Token;
import com.cyj.entity.Users;
import com.cyj.md5.PasswordEncoder;
import com.cyj.service.SignInService;
import com.cyj.service.UserRolesService;
import com.cyj.service.UsersService;
import com.cyj.util.IsEmptyUtils;
import com.cyj.util.JwtToken;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * @Description: 登录 模块控制器
 * @author Cyj
 * @Date 2018-5-16
 * @Email 867647213@qq.com
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsersService servcie;
	@Autowired
	private UserRolesService service;
	@Autowired
	private SignInService sService = null;
	
	private Date date = new Date();
	private Timestamp nousedate = new Timestamp(date.getTime());
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * http://localhost:8080/SSMCyj/login/loginUsers.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginUsers.php", produces = "text/plain;charset=UTF-8", name = "登录用户")
	public Object loginUsers(Users u) {
		int pwd = 0;// 密码错误次数
		String token = null;// token
		System.out.println("loginName>=" + u.getLoginName() + ",passwords>="
				+ u.getPasswords());
		if (IsEmptyUtils.isEmpty(u.getLoginName())) {
			return new Result(false, "用户名不能为空!");
		} else if (IsEmptyUtils.isEmpty(u.getPasswords())) {
			return new Result(false, "用户密码不能为空!");
		}

		Users us = servcie.getUser(u.getLoginName());// 当前用户名的信息
		System.out.println(us);
		if (!IsEmptyUtils.isEmpty(us)) {// 查询当前用户存在不
			int uid = us.getId();// 当前用户id
			// 盐值加密
			String loginName = u.getLoginName();
			String loginPwd = u.getPasswords();
			PasswordEncoder encoder = new PasswordEncoder(loginName, "MD5");
			String password = encoder.encode(loginPwd);
			
			System.out.println(password+"|"+us.getPasswords());
			if (password.equals(us.getPasswords())) {// 验证密码是否正确
				u.setPasswords(password);
				if (us.getPsdWrongTime() == 3) {
					return new Result(false, "当前用户已被锁定请联系管理员!");
				} else if (us.getPsdWrongTime() < 3) {
					int n = 0;
					Users aUsers = new Users();
					aUsers.setPsdWrongTime(n);
					aUsers.setIsLookout("否");
					aUsers.setId(uid);
					servcie.updUsers(aUsers);// 修改密码错误次数
				}
				if (us.getIsLookout().equals("否")) {// 用户状态未锁定允许登录
					System.out.println("登录是否成功>=" + servcie.loginUsers(u));
					if (servcie.loginUsers(u) == true) {// 当前用户登录是否成功
						us.setLastLoginTime(nousedate);
						servcie.updUsers(us);// 修改登录时间
						
						// 根据用户Id查询出该用户的所有权限
						List<String> permissionValueList = servcie
								.queryPermissionValueByUserId(us.getId());
						// 构造一个token对象,存储用户和权限信息
						Token tokenObj = new Token(us.getId(),
								permissionValueList);
						try {
							token = JwtToken.sign(tokenObj, 6 * 60 * 60 * 1000);// 1*60*60*1000
																			// 1个小时有效期的token
							System.out.println("生成token大小=>" + token.length());
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
						List<Integer> urRoles=service.getUserRole(us.getId());
						String today = formatter.format(date);
						SignIn signState = sService.getSign(us.getId(), today);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("loginId", us.getId());
						map.put("loginName", us.getLoginName());
						map.put("roleIds", urRoles);
						if (IsEmptyUtils.isEmpty(signState)) {
							map.put("signState", "签到");
						} else {
							map.put("signState", signState.getSignState());
						}
						Users v=servcie.getUserById(us.getId());
						map.put("leaveState",v.getLeaveState());
						map.put("leaveBoolean",v.getLeaveBoolean());
						map.put("token", token);// 响应给客户端的token令牌
						map.put("permission", permissionValueList);// 响应给客户端的当前用户权限
						return new Result(true, map);
					} else {
						return new Result(false, "当前用户登录失败!");
					}
				} else {
					return new Result(false, "当前用户已被锁定请联系管理员!");
				}
			} else {
				pwd = us.getPsdWrongTime();
				pwd++;
				Users aUsers = new Users();
				aUsers.setPsdWrongTime(pwd);
				aUsers.setId(uid);
				servcie.updUsers(aUsers);// 修改密码错误次数

				us = servcie.getUser(u.getLoginName());// 二次查询当前用户信息,获得更新后的错误次数
				int n = us.getPsdWrongTime();// 密码错误次数
				if (n >= 3) {// 验证错误次数是否大于3
					us.setIsLookout("是");
					us.setLockTime(new Date());
					servcie.updUsers(us);
					return new Result(false, "当前用户已被锁定请联系管理员!");
				}
				return new Result(false, "用户密码错误!");
			}
		} else {
			return new Result(false, "当前用户不存在!");
		}

	}

	/**
	 * 
	 * 响应失败状态码 1未登录 2token过期 3无权访问
	 * 
	 * 
	 * 非法访问统一处理响应:无权访问 http://localhost:8080/SSMCyj/login/unLogin.php
	 * 
	 * @return
	 */
	@RequestMapping("/unLogin.php")
	public Object unLogin() {
		return new Result(false, "未登录或请求没有携带合法Token!");// 1未登录
	}

	/**
	 * 
	 * 响应失败状态码 1未登录 2token过期 3无权访问
	 * 
	 * 
	 * 非法访问统一处理响应:无权访问 http://localhost:8080/SSMCyj/login/tokenExpired.php
	 * 
	 * @return
	 */
	@RequestMapping("/tokenExpired.php")
	public Object tokenExpired() {
		return new Result(false, "请求Token过期或携带信息不安全!");// 2token过期
	}

	/**
	 * 
	 * 响应失败状态码 1未登录 2token过期 3无权访问
	 * 
	 * 
	 * 非法访问统一处理响应:无权访问 http://localhost:8080/SSMCyj/login/noPermission.php
	 * 
	 * @return
	 */
	@RequestMapping("/noPermission.php")
	public Object noPermission() {
		return new Result(false, "权限不足或无权访问请求,请获取合法身份登陆!");// 3无权访问
	}

	/**
	 * http://localhost:8080/SSMCyj/login/sysError.php 其它异常类友好提示信息
	 */
	@RequestMapping("/sysError.php")
	public Object sysError() {
		return new Result(false, "系统发生未知异常,我们已经杀了一个程序员祭天!请稍后重试!!!");
	}

	/**
	 * http://localhost:8080/SSMCyj/login/testError.php?num=4 测试其他类异常
	 */
	@RequestMapping("/testError.php")
	public Object testError(Integer num) {
		int k = 8 / num;
		return k;
	}

	public static void main(String[] args) {
		String[] pArr = { "users:addUsers.php", "user:delUsers.php" };
		List<String> pList = Arrays.asList(pArr);// 设置当前登录用户的权限集合
		String p = "users:delUsers.php";
		System.out.println(pList.contains(p));
	}

}
