package com.cyj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.SignIn;

public interface SignInDao {
	
	public List<SignIn> getSignIn(SignIn u);// 查询所有签到信息
	
	/**
	 * 考勤自助查询
	 * @param u 咨询师表对象
	 * @return
	 */
	public Integer getSignInBySign(SignIn s);
	
	public Integer getSignInByTime(@Param("signTime") String signTime,@Param("signState") List<String> signState);// 查询所有签到信息
	
	public SignIn getSign(@Param("userId") int userId,@Param("signTime") String signTime);// 根据userId查询签到信息
	
	public Integer getSignInCount(SignIn u);// 带条件分页查询结果集总条数
	
	public int updSignIn(SignIn u);// 修改签到信息
	
	public int updSignInTime(@Param("signState") String signState,@Param("signTime") Date signTime,@Param("signOutTime") Date signOutTime);// 修改签到信息
	
	public int addSignIn(SignIn u);// 添加签到信息
	
	public String getSinByAskerName(@Param("signTime") String signTime,@Param("askerName") String askerName);
	
}
