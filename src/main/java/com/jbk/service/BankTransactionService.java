package com.jbk.service;

import java.util.List;

import com.jbk.entity.BankTransaction;
import com.jbk.entity.Payment;
import com.jbk.model.TransactionHistory;

public interface BankTransactionService {

	public String performTransaction(BankTransaction bankTransaction);
	public BankTransaction getTransactionDetails(int transactionId);
	public String performAccountToAcountTransactions(BankTransaction bankTransaction);
	public List<TransactionHistory> getTransactionDetailsOfAccountNumber(String accountNumber);
	public boolean perfomPayment(Payment payment);
}
