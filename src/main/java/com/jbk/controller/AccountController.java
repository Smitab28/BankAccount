package com.jbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbk.entity.Account;
import com.jbk.entity.BankTransaction;
import com.jbk.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@PostMapping("/save-account")
	public ResponseEntity<Boolean> saveUser(@RequestBody Account account) {
		boolean isSaved = service.saveAccount(account);
		return ResponseEntity.ok(isSaved);
	}
	
	@GetMapping("/get-accountby-account-number/{accountNumber}")
	public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
		Account account =  service.getAccountByAccountNumber(accountNumber);
		if(account!=null) {
			return ResponseEntity.ok(account);
		}else {
			return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/get-accountby-userid/{userId}")
	public ResponseEntity<Account> getAccountByUserId(@PathVariable long userId) {
		Account account =  service.getAccountByUserId(userId);
		if(account!=null) {
			return ResponseEntity.ok(account);
		}else {
			return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/delete-user-account-by-userId/{userId}")
	public ResponseEntity<Boolean> deleteUserByUserId(@PathVariable long userId){
		boolean isDeleted =service.deleteUserByUserId(userId);
		return ResponseEntity.ok(isDeleted);
	}
	
}
