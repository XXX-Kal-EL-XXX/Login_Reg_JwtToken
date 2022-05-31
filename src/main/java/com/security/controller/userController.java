package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.modal.LoginForm;
import com.security.modal.Users;
import com.security.service.UserService;

@RestController
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> loginUser(@RequestBody LoginForm data) throws Exception{
		return userService.loginUser(data);
	}
	
	@PostMapping("/registration")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> registration(@RequestBody Users user){
		return userService.registration(user);
		
		
	}

}
