package com.valid.dao;

import com.valid.model.Student;

public interface StudentDAO {
	public abstract boolean saveStudent(Student student);
	public Student getStudentDetailsByEmailAndPassword(String email,String password);
}