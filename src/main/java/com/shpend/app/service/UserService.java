package com.shpend.app.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.security.Authority;

@Service
public class UserService {
	
	EntityManagerFactory entityManagerFactory =
	          Persistence.createEntityManagerFactory("example-unit");
	
	
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
		user.getAuthorities().add(authority);
		return userRepo.save(user);
	}
	
	public User get(long id)
	{
		return userRepo.getOne(id);
	}
	
	public void updateCompletedInfo(User user)
	{
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery("Update users u SET u.completed_info = 1 WHERE u.id = " + user.getId());
	}
	
}
