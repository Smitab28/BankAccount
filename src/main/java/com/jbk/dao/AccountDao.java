package com.jbk.dao;

import com.jbk.entity.Account;

public interface AccountDao {

	public boolean saveAccount(Account account);
	public Account getAccountByAccountNumber(String accountNumber);
	public boolean updateAccountBalance(Account account);
	public Account getAccountByUserId(long userId);
	public boolean deleteUserByUserId(long userId);
}
