package com.xjtuse.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xjtuse.dao.StaffDAO;
import com.xjtuse.entity.Staff;
import com.xjtuse.entity.Student;

@Repository
public class StaffDaoImpl implements StaffDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Staff addStaff(Staff staff) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(staff);
		session.flush();
		return staff;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> getAllStaffs() {
		Session session = sessionFactory.getCurrentSession();
		List<Staff> list =  session.createQuery("from Staff").list();
		session.flush();
		return list;
	}

	@Override
	public Staff getStaff(int id) {
		Session session = sessionFactory.getCurrentSession();
		Staff Staff = (Staff) session.get(Staff.class, id);
		return Staff;
	}

	@Override
	public void deleteStaff(Staff staff) {
		Session session = sessionFactory.getCurrentSession();
	    session.delete(staff);
	    session.flush();
	}

}
