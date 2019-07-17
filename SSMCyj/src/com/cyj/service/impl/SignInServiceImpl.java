package com.cyj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.SignInDao;
import com.cyj.entity.SignIn;
import com.cyj.service.SignInService;

@Service
public class SignInServiceImpl implements SignInService {

	@Autowired
	private SignInDao signInDao=null;
	
	
	public List<SignIn> getSignIn(SignIn u) {
		return signInDao.getSignIn(u);
	}
	
	
	public Integer getSignInBySign(SignIn s){
		return signInDao.getSignInBySign(s);
	}

	
	public Integer getSignInCount(SignIn u) {
		return signInDao.getSignInCount(u);
	}

	
	public boolean updSignIn(SignIn u) {
		return signInDao.updSignIn(u) > 0 ? true : false;
	}

	
	public boolean addSignIn(SignIn u) {
		return signInDao.addSignIn(u) > 0 ? true : false;
	}

	
	public SignIn getSign(int userId,String signTime) {
		return signInDao.getSign(userId,signTime);
	}

	
	public boolean updSignInTime(String signState, Date signTime,
			Date signOutTime) {
		return signInDao.updSignInTime(signState, signTime, signOutTime) > 0 ? true : false;
	}

	
	public Integer getSignInByTime(String signTime, List<String> signState) {
		return signInDao.getSignInByTime(signTime, signState);
	}

	
	public String getSinByAskerName(String signTime, String askerName) {
		return signInDao.getSinByAskerName(signTime, askerName);
	}

}
