package com.xjtuse.service;

import java.util.List;

import com.xjtuse.entity.User;

public interface UserService {
	public User addUser(User user);

	public List<User> getAllUsers();

	public User getUser(int id);

	public void deleteUser(User user);
}
