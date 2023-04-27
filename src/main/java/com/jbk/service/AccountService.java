package com.jbk.service;

import com.jbk.entity.Account;


public interface AccountService {

	public boolean saveAccount(Account account);
	public Account getAccountByAccountNumber(String accountNumber);
	public Account getAccountByUserId(long userId);
	public boolean deleteUserByUserId(long userId);
}
