package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbk.entity.User;
import com.jbk.exception.NoContentExceptionHandle;
import com.jbk.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/save-user")
	public ResponseEntity<Boolean> saveUser(@RequestBody User user) {
		boolean isSaved = service.saveUser(user);
		return ResponseEntity.ok(isSaved);
	}
	
	@GetMapping("/get-userby-username")
	public ResponseEntity<User> getUserByUserId(@RequestParam("userName") String userName,@RequestParam("password") String password) {
		User dbUser =  service.getUserByUserName(userName,password);
		if(dbUser!=null) {
			return ResponseEntity.ok(dbUser);
		}else {
			throw new NoContentExceptionHandle("Invalid Credentials");
		}
	}
	
	@GetMapping("/get-accountBalance")
	public ResponseEntity<Double> getAccountBalance(@RequestParam("userName") String userName,@RequestParam("password") String password){
		double balance =0;
		User dbUser = service.getUserByUserName(userName, password);
		if(dbUser!=null) {
		 balance = service.getAccountBalance(dbUser.getUserId());
		 return ResponseEntity.ok(balance);
		}else
			throw new NoContentExceptionHandle("Invalid Credentials");
		
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> list =service.getAllUsers();
		if(list.isEmpty())
			throw new NoContentExceptionHandle("User is not registered yet...!!!");
		else
			return ResponseEntity.ok(list);
	}
	
}
