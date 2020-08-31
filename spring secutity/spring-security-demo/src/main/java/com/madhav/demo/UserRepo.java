package com.madhav.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhav.demo.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);

}
