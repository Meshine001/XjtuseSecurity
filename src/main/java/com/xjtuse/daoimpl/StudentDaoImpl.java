package com.xjtuse.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xjtuse.dao.StudentDAO;
import com.xjtuse.entity.Student;
import com.xjtuse.entity.User;

@Repository
public class StudentDaoImpl implements StudentDAO{

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Student addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		session.flush();
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		List<Student> list =  session.createQuery("from Student").list();
		session.flush();
		return list;
	}

	@Override
	public Student getStudent(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		return student;
	}

	@Override
	public void deleteStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
	    session.delete(student);
	    session.flush();
		
	}

}
