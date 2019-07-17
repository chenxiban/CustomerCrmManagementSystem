package com.cyj.tools.exception;
/**
 * 自定义异常
 * @Description:   无Token用户未登录异常
 * @author         Mashuai 
 * @Date           2018-5-17 上午12:03:48  
 * @Email          1119616605@qq.com
 */
public class UnLoginException extends Exception {
	
	
	public UnLoginException(){
		super("用户未登录异常");
	}
	
	public UnLoginException(String info){
		super(info);
	}

}
