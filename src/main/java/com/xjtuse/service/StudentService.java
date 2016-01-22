package com.xjtuse.service;

import java.util.List;

import com.xjtuse.entity.Student;

public interface StudentService {
	public Student addStudent(Student student);

	public List<Student> getAllStudents();

	public Student getStudent(int id);

	public void deleteStudent(Student student);
}
