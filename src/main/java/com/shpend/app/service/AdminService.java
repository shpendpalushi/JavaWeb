package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Admin;
import com.shpend.app.domain.User;
import com.shpend.app.repository.AdminRepository;
import com.shpend.app.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService; 
	

	
	public Admin save(Admin admin, Long id)
	{
		Optional<User> userOptional = 	userRepo.findById(id);
		 if(userOptional.isPresent())
		 {
			 	User user = userOptional.get();
			 	user.setCompletedInfo(1);
				admin.setUser(user);
				user.setAdmin(admin);
				Date date = new Date();
				admin.setCreatedAt(new Timestamp(date.getTime()));
				return adminRepo.save(admin);
		 }else {
			 System.out.println("NULL not registered");
			 return null;
		 }
		
	}
	
	


}
