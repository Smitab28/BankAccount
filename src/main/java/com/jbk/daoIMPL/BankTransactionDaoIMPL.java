package com.jbk.daoIMPL;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.BankTransactionDao;
import com.jbk.entity.BankTransaction;
import com.jbk.entity.Payment;
import com.jbk.model.TransactionHistory;

@Repository
public class BankTransactionDaoIMPL implements BankTransactionDao{

	@Autowired
	SessionFactory sf;
	
	@Override
	public String performTransaction(BankTransaction bankTransaction) {
		Session session = null;
		String msg = null;
		try {
			session = sf.openSession();
			Transaction tr = session.beginTransaction();
			session.save(bankTransaction);
			tr.commit();
			if(bankTransaction.getStatus().equals("Done"))
			msg = "Transaction Successful";
			else
				msg = "Transaction Fail";
		} catch (Exception e) {
			msg="Tansaction failed...";
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}

	@Override
	public BankTransaction getTransactionDetails(int transactionId) {
		Session session = null;
		BankTransaction bankTransaction = null;
		try {
			session = sf.openSession();
			bankTransaction = session.get(BankTransaction.class, transactionId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return bankTransaction;
	}

	@Override
	public String performAccountToAcountTransactions(BankTransaction bankTransaction) {
		Session session = null;
		String msg = null;
		try {
			session = sf.openSession();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}

	@Override
	public List<TransactionHistory> getTransactionDetailsOfAccountNumber(String accountNumber) {
		Session session = null;
		TransactionHistory transactionHistory =new TransactionHistory();
		List<TransactionHistory> transactionHistoryList = new ArrayList<TransactionHistory>();
		List<BankTransaction> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(BankTransaction.class);
			//criteria.add(Restrictions.eq("accountNumber", accountNumber));
			list = criteria.list();
			for (BankTransaction bankTransaction : list) {
				if(bankTransaction.getAccount().getAccountNumber().equals(accountNumber)) {
					transactionHistory.setTransactionId(bankTransaction.getTransactionId());
					transactionHistory.setTransactionAccountNumber(accountNumber);
					transactionHistory.setUserId(bankTransaction.getAccount().getUser().getUserId());
					transactionHistory.setTransactionType(bankTransaction.getTransactionType());
					transactionHistory.setTransactionAmount(bankTransaction.getAmount());
					transactionHistory.setTransactionSource(bankTransaction.getSource());
					transactionHistory.setTransactionStatus(bankTransaction.getStatus());
					transactionHistory.setTransactionReasonCode(bankTransaction.getReasonCode());
					transactionHistory.setTransactionCreatedAt(bankTransaction.getCreatedAt());
					
				}
				transactionHistoryList.add(transactionHistory);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return transactionHistoryList;
	}
	
	public boolean perfomPayment(Payment payment) {
		Session session = null;
		boolean isSaved = false;
		try {
			session = sf.openSession();
			Transaction tr = session.beginTransaction();
			session.save(payment);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return isSaved;
	}

	
	
}
