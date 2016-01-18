package com.xjtuse.dao;

import java.util.List;

import com.xjtuse.entity.User;

public interface UserDAO {
	public User addUser(User user);

	public List<User> getAllUsers();

	public User getUser(int id);

	public void deleteUser(User user);
}
