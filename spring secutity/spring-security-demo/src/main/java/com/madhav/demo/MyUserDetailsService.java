package com.madhav.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.madhav.demo.model.MyUserDetails;
import com.madhav.demo.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user=repo.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("not found" + userName));
		return user.map(MyUserDetails::new).get();
	}

}
