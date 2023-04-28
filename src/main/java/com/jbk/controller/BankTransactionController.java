package com.jbk.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbk.entity.BankTransaction;
import com.jbk.entity.Payment;
import com.jbk.exception.NoContentExceptionHandle;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.model.TransactionHistory;
import com.jbk.service.BankTransactionService;

@Controller
@RequestMapping("/transactions")
public class BankTransactionController {

	@Autowired
	BankTransactionService service;
	
	@PostMapping("/perform-transaction")
	public ResponseEntity<String> performTransactions(@RequestBody BankTransaction bankTransaction){
		String msg =service.performTransaction(bankTransaction);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/get-transaction-details/{transactionId}")
	public ResponseEntity<BankTransaction> getTransactionDetails(@PathVariable int transactionId){
		BankTransaction bankTransaction = service.getTransactionDetails(transactionId);
		return ResponseEntity.ok(bankTransaction);
	}
	
	@PostMapping("/perform-account-transaction")
	public ResponseEntity<String> performAccountToAcountTransactions(@RequestBody BankTransaction bankTransaction){
		String msg = service.performAccountToAcountTransactions(bankTransaction);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/get-account-transaction-details/{accountNumber}")
	public ResponseEntity<List<TransactionHistory>> getTransactionDetailsOfAccountNumber(@PathVariable String accountNumber){
		List<TransactionHistory> transactionHistory = service.getTransactionDetailsOfAccountNumber(accountNumber);
		return ResponseEntity.ok(transactionHistory);
	}
	
	@GetMapping("/perfom-payment")
	public ResponseEntity<Boolean> perfomPayment(Payment payment){
		boolean isSaved = service.perfomPayment(payment);
		if(isSaved)
			return ResponseEntity.ok(isSaved);
		else
			throw new ResourceAlreadyExistException("Resource Already exist");
	}
	
	@GetMapping("/get-all-transactions")
	public ResponseEntity<List<TransactionHistory>> getAllTransactionDetails(){
		List <TransactionHistory> listOfTransactions = service.getAllTransactionDetails();
		if(listOfTransactions.isEmpty())
			throw new NoContentExceptionHandle("Transactions not performed yet...!!!");
		else
			return ResponseEntity.ok(listOfTransactions);
	}
	
	 @GetMapping("/export-to-excel")
	    public ResponseEntity<String> exportIntoExcelFile() throws IOException {
	        List <TransactionHistory> listOfTransactions = service.getAllTransactionDetails();
	        for (TransactionHistory transactionHistory : listOfTransactions) {
				System.out.println(transactionHistory);
			}
	        //ExcelGenerator generator = new ExcelGenerator(listOfProducts);
	        String msg = service.generateExcelFile(listOfTransactions);
	        return  new ResponseEntity<String>(msg,HttpStatus.OK);
	    }
}
