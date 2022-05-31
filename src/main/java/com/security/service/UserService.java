package com.security.service;

import org.springframework.http.ResponseEntity;

import com.security.modal.LoginForm;
import com.security.modal.Users;

public interface UserService {
	
	public ResponseEntity<?> loginUser(LoginForm data) throws Exception;
	
	public ResponseEntity<?> registration(Users user);

}
