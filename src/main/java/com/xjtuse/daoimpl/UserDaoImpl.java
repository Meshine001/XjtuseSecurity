package com.xjtuse.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xjtuse.dao.UserDAO;
import com.xjtuse.entity.User;

@Repository
public class UserDaoImpl implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> list =  session.createQuery("from User").list();
		session.flush();
		return list;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	    session.delete(user);
	    session.flush();
	}

}
