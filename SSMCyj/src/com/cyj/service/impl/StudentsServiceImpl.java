package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.StudentsDao;
import com.cyj.entity.Students;
import com.cyj.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentsDao sdao;

	
	public List<Students> getStudents(Students s) {
		return sdao.getStudents(s);
	}

	
	public List<Students> getStudentByIds(List<String> id) {
		return sdao.getStudentByIds(id);
	}
	
	
	public Integer getStudentsCount(Students s) {
		return sdao.getStudentsCount(s);
	}

	
	public boolean delStudents(List<String> id) {
		return sdao.delStudents(id) > 0 ? true : false;
	}

	
	public boolean addStudents(Students s) {
		return sdao.addStudents(s) > 0 ? true : false;
	}

	
	public boolean updStudents(Students s) {
		return sdao.updStudents(s) > 0 ? true : false;
	}

	
	public boolean updStudentsByIds(String ziXunName, List<String> id) {
		return sdao.updStudentsByIds(ziXunName, id) > 0 ? true : false;
	}

	
	public boolean addStudentByList(Students s) {
		return sdao.addStudentByList(s) > 0 ? true : false;
	}

	
	public Integer getStudentsCounts(String address) {
		return sdao.getStudentsCounts(address);
	}

}
