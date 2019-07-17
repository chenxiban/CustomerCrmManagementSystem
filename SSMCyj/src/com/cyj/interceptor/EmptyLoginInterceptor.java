package com.cyj.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cyj.entity.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 是否登录过滤
 * 
 * @Description: 登录过滤拦截器
 * @author Mashuai
 * @Date 2018-5-10 下午6:39:06
 * @Email 1119616605@qq.com
 */
public class EmptyLoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * RequestMapping 执行之前,执行preHandle
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out
				.println("EmptyLoginInterceptor preHandle 被拦截的方法是----------handler => "
						+ handler);
		response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域访问
		response.setHeader("Access-Control-Allow-Methods", "*");
		return true;
	}

	/**
	 * RequestMapping 执行之后,执行postHandle,此时并未真正分发视图
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println("EmptyLoginInterceptor postHandle ----------modelAndView => "+modelAndView);
		//modelAndView.setViewName("");//可以控制分发的视图
		response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域访问
		response.setHeader("Access-Control-Allow-Methods", "*");

	}

	/**
	 * RequestMapping 执行之后,执行postHandle,视图也分发完毕. 1. 相当于 finally 2.方法执行异常
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out
				.println("EmptyLoginInterceptor afterCompletion ----------ex => "
						+ ex);
		response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域访问
		response.setHeader("Access-Control-Allow-Methods", "*");
		// 如果有异常信息
		if (ex != null) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter print = null;
			try {
				print = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				print.print(new ObjectMapper().writeValueAsString(new Result(false, "权限不足或无权访问请求,请获取合法身份登陆!")));// 3无权访问
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

	}

}
