package com.jbk.daoIMPL;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.UserDao;
import com.jbk.entity.User;

@Repository
public class UserDaoIMPL implements UserDao  {

	@Autowired
	SessionFactory sf;
	
	@Override
	public boolean saveUser(User user) {
		Session session=null;
		boolean isSaved =false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
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
	public User getUserByUserName(String userName, String password) {
		Session session = null;
		User user = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(User.class);
			List<User> userList = criteria.list();
			System.out.println("Step 1");
			
			for (User user2 : userList) {
				System.out.println(user2.getPassword()+" parameter= "+password);
				if(user2.getPassword().equals(password)) {
					System.out.println("Step 2");
					user = user2;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return user;
	}


	@Override
	public List<User> getAllUsers() {
		Session session = null;
		List<User> userList= null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(User.class);
			userList =criteria.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return userList;
	}

//	@Override
//	public double getAccountBalance(long userId) {
//		
//		return 0;
//	}

	
}
