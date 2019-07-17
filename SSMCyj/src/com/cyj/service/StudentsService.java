package com.cyj.service;

import java.util.List;

import com.cyj.entity.Students;

public interface StudentsService {
	
	public List<Students> getStudents(Students s);
	
	public List<Students> getStudentByIds(List<String> id);
	
	public Integer getStudentsCount(Students s);// 带条件分页查询结果集总条数
	
	public Integer getStudentsCounts(String address);// 带条件分页查询结果集总条数
	
	public boolean addStudents(Students s);
	
	public boolean addStudentByList(Students s);
	
	public boolean updStudents(Students s);
	
	public boolean updStudentsByIds(String ziXunName,List<String> id);
	
	public boolean delStudents(List<String> id);
	
}
