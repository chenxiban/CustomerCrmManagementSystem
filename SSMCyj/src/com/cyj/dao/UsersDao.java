package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Users;

public interface UsersDao {
	
	public List<Users> getUsersList(@Param("ids") List<Integer> id);
	
	public List<Users> getUsersLists();
	
	public List<Users> getUsers(Users u);// 查询所有用户
	
	public Integer getUserCount(Users u);// 带条件分页查询结果集总条数
	
	public Users getUser(@Param("loginName") String loginName);// 根据登录名查询用户是否存在
	
	public Users getUserById(@Param("id") Integer id);// 根据id查询用户报道状态
	
	public List<String> queryPermissionValueByUserId(Integer userId);//根据用户Id查询出该用户的所有权限

	public Users loginUser(Users u);// 用户登录

	public int addUsers(Users u);// 添加用户

	public int updUsers(Users u);// 修改用户信息
	
	public int delUsers(List<String> id);// 批量删除用户
	
	public List<Users> getStudentUserByUserId(Integer id);//根据用户的ID来获取
	
	/*public List<Users> getStudentUserByUserIds(@Param("stuId") Integer id);*/
	
}
