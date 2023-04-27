package com.jbk.serviceIMPL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jbk.dao.AccountDao;
import com.jbk.entity.Account;
import com.jbk.service.AccountService;

@Service
public class AccountServiceIMPL implements AccountService{

	@Autowired
	AccountDao dao;
	

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		
		return dao.getAccountByAccountNumber(accountNumber);
	}

	@Override
	public boolean saveAccount(Account account) {
		String accountNumber = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS").format(LocalDateTime.now());
		account.setAccountNumber(accountNumber);
		Account existingAccount = getAccountByUserId(account.getUser().getUserId());
		if(existingAccount!=null) {
			account.setAccountBalance(account.getAccountBalance()+existingAccount.getAccountBalance());
		}
		account.setCreatedAt(LocalDateTime.now());
		account.setUpdatedAt(LocalDateTime.now());
		boolean isSaved = dao.saveAccount(account);
		return isSaved;
	}

	@Override
	public Account getAccountByUserId(long userId) {
		
		return dao.getAccountByUserId(userId);
	}

	@Override
	public boolean deleteUserByUserId(long userId) {
		
		return dao.deleteUserByUserId(userId);
	}	

}
