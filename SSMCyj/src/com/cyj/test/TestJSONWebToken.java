package com.cyj.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cyj.util.JwtToken;

/**
 * 
 * @Description:   JsonWebToken使用示例
 * @author         Mashuai 
 * @Date           2018-5-15 下午11:19:17  
 * @Email          1119616605@qq.com
 * 
  	基于Token的身份验证的主流程如下:
    用户通过用户名和密码发送请求；
    程序验证；
    程序返回一个签名的token 给客户端；
    客户端储存token,并且每次用于每次发送请求。
 * 
 * 
 */
public class TestJSONWebToken {
	
	
	
	public static void main(String[] args) throws IOException{
		//模拟登陆模块
				List<String> list = Arrays.asList("fdfbeccf1da5f1e0e9a3608312e01e48");
				String token = null;
				token = JwtToken.sign(list, 3000);
				System.out.println("JWT加密后得到的 token=>"+token);
				System.out.println("JWT加密后得到的 token长度=>"+token.length());
				//模拟权限校验模块
				List<String> result = null;
				 result = JwtToken.unsign(token, List.class);
				 System.out.println("Token过期    JWT解密后result=>"+result);
				 //模拟过期的token
				 List<String> expiredResult = null;
				 try {
					 System.out.println("主线程休眠五秒...");
					Thread.currentThread().sleep(5000);//休眠五秒,token过期
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				 try {
					 expiredResult = JwtToken.unsign(token, List.class);
				}catch (TokenExpiredException e) {
					System.err.println("Token过期");
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				 System.out.println("JWT解密后expiredResult=>"+expiredResult);
				 
	}
	
	
	
	
	
	
	
	

}
