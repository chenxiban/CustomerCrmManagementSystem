package com.cyj.service;

import java.util.Date;
import java.util.List;

import com.cyj.entity.SignIn;

public interface SignInService {
	
	public List<SignIn> getSignIn(SignIn u);// 查询所有签到信息
	
	public Integer getSignInBySign(SignIn s);
	
	public SignIn getSign(int userId,String signTime);// 根据userId查询签到信息
	
	public Integer getSignInCount(SignIn u);// 带条件分页查询结果集总条数
	
	public boolean updSignIn(SignIn u);// 修改签到信息
	
	public boolean updSignInTime(String signState,Date signTime,Date signOutTime);// 修改签到信息
	
	public boolean addSignIn(SignIn u);// 添加签到信息
	
	public Integer getSignInByTime(String signTime,List<String> signState);
	
	public String getSinByAskerName(String signTime,String askerName);
	
}
