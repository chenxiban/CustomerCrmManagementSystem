package com.cyj.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cyj.entity.Token;
import com.cyj.tools.exception.UnLoginException;
import com.cyj.util.JwtToken;
import com.cyj.util.NoPermissionException;
import com.cyj.util.SysUtils;




/**
 * 权限过滤
 * @Description:   权限过滤拦截器
 * @author         Mashuai 
 * @Date           2018-5-10 下午6:39:06  
 * @Email          1119616605@qq.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	
	
	/**
	 * RequestMapping 执行之前,执行preHandle
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws UnLoginException,NoPermissionException,TokenExpiredException,IOException{
		System.out.println("LoginInterceptor preHandle 被拦截的方法是----------handler => "+handler);
		String path = request.getServletPath();//获取请求的当前项目下的资源 , 匹配得到:  当前项目/xxx/**/xxx.xxx 例如 /sys/login
		System.out.println("LoginInterceptor拦截器 获取请求的当前项目下的资源=>"+path);
		if(path.matches(SysUtils.NO_INTERCEPTOR_PATH)){//不拦截的URL正则表达式
			System.out.println("不拦截的URL正则表达式匹配成功,放行 ^_^ ");
			return true;
		}
		
		//请求默认的Servlet资源时handler =>   org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler
		//请求SpringMVC静态资源时handler =>  org.springframework.web.servlet.resource.ResourceHttpRequestHandler
		//请求SpringMVC控制器时handler =>   org.springframework.web.method.HandlerMethod
		if( handler instanceof HandlerMethod ){
			//当前用户所拥有的权限集合
			List<String> pList = null;//当前用户所拥有的权限集合
			String token = request.getParameter("token");//取出用户每次请求都要传递的token参数
			System.out.println("LoginInterceptor 拦截器拦截到的请求token=>"+token);
			if(token == null || "".equals(token)){
				System.out.println("未登录或请求没有携带Token,就想访问业务控制器,你是不是傻!!!");
				throw new UnLoginException("未登录或请求没有携带合法Token!");
			}
			Token tokenObject = JwtToken.unsign(token, Token.class);//从token中取出用户信息
			if(tokenObject != null){
				pList = tokenObject.getPermissionValueList();//取出token中隐藏的用户权限集合
			}
			
			System.out.print("当前登录用户的权限集合=>"+pList);
			if( pList == null ){//当前未登录
				System.out.println("还未登录,就想访问业务控制器,你是不是傻!!!");
				throw new NoPermissionException("还未登录,就想访问业务控制器!!!");//抛出无权访问异常,交给Spring容器异常处理机制
//				return false;//拦截
			}			
			HandlerMethod hMethod = (HandlerMethod) handler;//请求映射方法
			//当前请求映射方法需要拥有的权限对象字符串
			String permissionValue = SysUtils.method2PermissionValue(hMethod);//从请求映射方法上取出需要的权限对象
			if(permissionValue == null) return true;//当前请求的方法没有name属性,则该方法不需要权限就可以访问
			System.err.println(" VS 访问当前方法需要权限=>"+permissionValue);
			//当前用户没有访问当前方法的权限则拦截
			if( pList != null && !pList.contains( permissionValue ) ){
				System.out.println("想来访问你权限范围之外的控制器,相当系统超级管理员你做梦吧!!!");
				throw new NoPermissionException("你无权访问,请做一个遵纪守法的公民!!!");//抛出无权访问异常,交给Spring容器异常处理机制
//				return false;//拦截
			}
			
		}else{
			System.out.println("LoginInterceptor preHandle 被拦截的非控制器资源是----------handler => "+handler);
		}
		
		
		return true;
	}
	
	/**
	 * RequestMapping 执行之后,执行postHandle,此时并未真正分发视图
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor postHandle ----------modelAndView => "+modelAndView);
//		modelAndView.setViewName("");//可以控制分发的视图
		response.addHeader("Access-Control-Allow-Origin", "*");//允许跨域访问
		
	}
	
	

	
	/**
	 * RequestMapping 执行之后,执行postHandle,视图也分发完毕.
	 * 1. 相当于 finally
	 * 2.方法执行异常
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		System.out.println("LoginInterceptor afterCompletion ----------ex => "+ex);
		
		
	}
	
	

}
