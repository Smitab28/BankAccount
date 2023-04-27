package com.jbk.serviceIMPL;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.AccountDao;
import com.jbk.dao.UserDao;
import com.jbk.entity.Account;
import com.jbk.entity.User;
import com.jbk.service.UserService;

@Service
public class UserServiceIMPL implements UserService{

	@Autowired
	UserDao dao;
	@Autowired
	AccountDao accountDao;
	
	@Override
	public boolean saveUser(User user) {
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setVerifiedAt(LocalDateTime.now());
		return dao.saveUser(user);
	}

	@Override
	public User getUserByUserName(String userName, String password) {
		
		return dao.getUserByUserName(userName,password);
	}


	@Override
	public double getAccountBalance(long userId) {
		Account account = accountDao.getAccountByUserId(userId);
		return account.getAccountBalance();
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}


}
