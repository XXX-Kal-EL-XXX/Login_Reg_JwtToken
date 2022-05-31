package com.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.modal.Users;
import com.security.repository.UsersRepository;

@Service
public class UsersDetailService implements UserDetailsService {
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		System.out.println("user found"+user);
		
		return new org.springframework.security.core.userdetails 
		   .User(user.getUsername(),user.getPassword(), new ArrayList<>());
	}
	

}
