package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.modal.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Users findByUsername(String userName);
	Boolean existsByUsername(String username);

}
