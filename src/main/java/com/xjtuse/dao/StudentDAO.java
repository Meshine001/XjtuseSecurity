package com.xjtuse.dao;

import java.util.List;

import com.xjtuse.entity.Student;

public interface StudentDAO {
	public Student addStudent(Student student);

	public List<Student> getAllStudents();

	public Student getStudent(int id);

	public void deleteStudent(Student student);
}
