package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.security.Authority;

@Service
public class UserService {
	
	
	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	public User save(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Authority authority = new Authority();
		authority.setAuthority(user.getAuthority());
		authority.setUser(user);
		Date date = new Date();
		user.setCreatedAt(new Timestamp(date.getTime()));
		user.getAuthorities().add(authority);
		return userRepo.save(user);
	}
	
	public User get(Long id)
	{
		return userRepo.getOne(id);
	}

}
