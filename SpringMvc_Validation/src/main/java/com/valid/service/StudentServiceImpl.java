package com.valid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valid.dao.StudentDAO;
import com.valid.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public boolean registerStudent(Student student) {
		boolean isRegister=false;
		boolean saveStudent = studentDAO.saveStudent(student);
		if(saveStudent)
			isRegister=true;
		return isRegister;
	}

	@Override
	public Student validateStudentCredential(String email, String password) {
		Student student = studentDAO.getStudentDetailsByEmailAndPassword(email, password);
		return student;
	}
}