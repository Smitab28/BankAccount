package com.jbk.dao;

import java.util.List;

import com.jbk.entity.User;

public interface UserDao {

	public boolean saveUser(User user);
	public User getUserByUserName(String userName,String password);
	//public double getAccountBalance(long userId);
	public List<User> getAllUsers();
}
