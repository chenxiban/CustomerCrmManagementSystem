package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.Students;

public interface StudentsDao {
	
	public List<Students> getStudents(Students s);
	
	public List<Students> getStudentByIds(List<String> id);
	
	public Integer getStudentsCount(Students s);// 带条件分页查询结果集总条数
	
	public Integer getStudentsCounts(@Param("address") String address);// 带条件分页查询结果集总条数
	
	public int addStudents(Students s);
	
	public int addStudentByList(Students s);
	
	public int updStudents(Students s);
	
	public int updStudentsByIds(@Param("ziXunName") String ziXunName,@Param("id") List<String> id);
	
	public int delStudents(List<String> id);
	
	
}
