package com.madhav.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhav.demo.model.User;

//@RepositoryRestResource(collectionResourceRel = "users",path="users")
public interface UserRepo extends JpaRepository<User, Integer> {
	/* public User findByName(String name); */
}
