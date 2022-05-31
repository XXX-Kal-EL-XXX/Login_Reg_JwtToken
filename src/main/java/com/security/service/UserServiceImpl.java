package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.exception.ErrorResponse;
import com.security.modal.AuthenticationResponse;
import com.security.modal.LoginForm;
import com.security.modal.Users;
import com.security.repository.UsersRepository;
import com.security.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UsersDetailService usersDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public ResponseEntity<?> loginUser(LoginForm data) throws Exception {
	
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
			
		}
		catch (BadCredentialsException e) {
			System.out.println("bad Credetials"+e.getStackTrace());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid Credentials"));
		}
		
		catch (Exception e) {
        	System.out.println(e);
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid Credentials"));
        }
		final UserDetails user = usersDetailService.loadUserByUsername(data.getUsername());
		Users u = usersRepository.findByUsername(data.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AuthenticationResponse(jwt, u.getUsername(),u.getEmail(),u.getUserId()));
		
	}

	@Override
	public ResponseEntity<?> registration(Users user) {
	    System.out.println(user.toString());
	    user.setPassword(pe.encode(user.getPassword()));
	    
	    if(usersRepository.existsByUsername(user.getUsername())) {
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("UserName Already Exist"));
	    	
	    }
	    Users u = usersRepository.save(user);
	    if(u==null) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error while saving information"));
	    }
	    else {
	    	return ResponseEntity.status(HttpStatus.OK).body(u);
	    }
		
	}
	
	

}
