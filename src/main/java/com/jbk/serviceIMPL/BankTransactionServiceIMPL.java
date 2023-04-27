package com.jbk.serviceIMPL;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jbk.dao.AccountDao;
import com.jbk.dao.BankTransactionDao;
import com.jbk.entity.Account;
import com.jbk.entity.BankTransaction;
import com.jbk.entity.Payment;
import com.jbk.model.TransactionHistory;
import com.jbk.service.BankTransactionService;

@Service
public class BankTransactionServiceIMPL implements BankTransactionService {

	@Autowired
	BankTransactionDao dao;
	@Autowired 
	AccountDao accountDao;
	
	@Override
	public String performTransaction(BankTransaction bankTransaction) {
		String accountNumber =	bankTransaction.getAccount().getAccountNumber();	
		if(bankTransaction.getTransactionType().equalsIgnoreCase("credit")) {
			Account account = accountDao.getAccountByAccountNumber(accountNumber);
			if(account!=null) {
				account.setAccountBalance(account.getAccountBalance()+bankTransaction.getAmount());
				bankTransaction.setStatus("successful");
				account.setUpdatedAt(LocalDateTime.now());
				account.setCreatedAt(account.getCreatedAt());
				bankTransaction.setReasonCode("done");
				accountDao.updateAccountBalance(account);
			}
			else {
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No such account exist");
		   }
		}else if(bankTransaction.getTransactionType().equalsIgnoreCase("debit")) {
			Account account = accountDao.getAccountByAccountNumber(accountNumber);
			if(account!=null) {
				if(account.getAccountBalance()>bankTransaction.getAmount()) {
						account.setAccountBalance(account.getAccountBalance()-bankTransaction.getAmount());
						bankTransaction.setStatus("successful");
						bankTransaction.setReasonCode("done");
						account.setUpdatedAt(LocalDateTime.now());
						account.setCreatedAt(account.getCreatedAt());
						accountDao.updateAccountBalance(account);
				}
				else {
						bankTransaction.setStatus("Fail");
						bankTransaction.setReasonCode("Insufficient Balance for this transaction");
						
				}
			}
			else {
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No such account exist");
				
		   }
		}
		bankTransaction.setSource("CASH");
		bankTransaction.setUpdatedAt(LocalDateTime.now());
		bankTransaction.setCreatedAt(LocalDateTime.now());
				
		return dao.performTransaction(bankTransaction);
	}

	@Override
	public BankTransaction getTransactionDetails(int transactionId) {
		return dao.getTransactionDetails(transactionId);
	}

	@Override
	public String performAccountToAcountTransactions(BankTransaction bankTransaction) {
		String msg =null;
		if(bankTransaction.getSource()==null)
			msg="Source is Mandatory";
		else {
			Account destinationAccount = accountDao.getAccountByAccountNumber(bankTransaction.getAccount().getAccountNumber());
			if(destinationAccount!=null) {
				Account sourceAccount = accountDao.getAccountByAccountNumber(bankTransaction.getSource());
				if(sourceAccount.getAccountBalance()>bankTransaction.getAmount()) {
					destinationAccount.setAccountBalance(destinationAccount.getAccountBalance()+bankTransaction.getAmount());
					accountDao.updateAccountBalance(destinationAccount);
					sourceAccount.setAccountBalance(sourceAccount.getAccountBalance()-bankTransaction.getAmount());
					accountDao.updateAccountBalance(sourceAccount);
					bankTransaction.setStatus("Done");
					bankTransaction.setReasonCode("Successful");
				}else {
					bankTransaction.setStatus("Fail");
					bankTransaction.setReasonCode("Insufficient funds in Source Account");
				}	
			}
			else{
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No Destination Account exist for transfer");
			}
			bankTransaction.setSource("Cash");
			bankTransaction.setCreatedAt(LocalDateTime.now());
			bankTransaction.setUpdatedAt(LocalDateTime.now());
			msg = dao.performTransaction(bankTransaction);
		}
		return msg;
	}

	@Override
	public List<TransactionHistory> getTransactionDetailsOfAccountNumber(String accountNumber) {
		
		return dao.getTransactionDetailsOfAccountNumber(accountNumber);
	}
	
	public boolean perfomPayment(Payment payment) {
		return dao.perfomPayment(payment);
	}

}
