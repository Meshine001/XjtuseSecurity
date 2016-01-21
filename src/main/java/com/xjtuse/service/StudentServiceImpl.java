package com.xjtuse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtuse.dao.StudentDAO;
import com.xjtuse.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentDAO studentDao;
	
	@Autowired
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.addStudent(student);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
		return studentDao.getStudent(id);
	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		studentDao.deleteStudent(student);
	}

}
