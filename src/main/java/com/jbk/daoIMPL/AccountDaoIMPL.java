package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.AccountDao;
import com.jbk.entity.Account;
import com.jbk.entity.User;

@Repository
public class AccountDaoIMPL implements AccountDao  {

	@Autowired
	SessionFactory sf;
	
	@Override
	public boolean saveAccount(Account account) {
		Session session=null;
		boolean isSaved =false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
			isSaved = true;
		}catch(PersistenceException pe) {
			isSaved = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isSaved;
	}
	

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		Session session = null;
		Account account = null;
		try {
			session = sf.openSession();
			account = session.get(Account.class, accountNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return account;
	}


	@Override
	public boolean updateAccountBalance(Account account) {
		Session session = null;
		Account updatedAccount = null;
		boolean isUpdated=false;
		try {
			session = sf.openSession();
			Transaction tr = session.beginTransaction();
			updatedAccount = session.get(Account.class,account.getAccountNumber());
			if(updatedAccount!=null) {
				updatedAccount.setAccountBalance(account.getAccountBalance());
				session.update(updatedAccount);
				tr.commit();
				isUpdated = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return isUpdated;
	}
	
	public Account getAccountByUserId(long userId) {
		//Account account = null;
		Session session = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			List<Account> list =	criteria.list();
			for (Account account : list) {
				if(account.getUser().getUserId()==userId) {
					return account;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return null;		
	}


	@Override
	public boolean deleteUserByUserId(long userId) {
		Session session = null;
		boolean isDeleted =false;
		
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			List<Account> list = criteria.list();
			for (Account account : list) {
				if(account.getUser().getUserId()==userId) {
					session.delete(account);
					session.beginTransaction().commit();
					isDeleted = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return isDeleted;
	}

}
