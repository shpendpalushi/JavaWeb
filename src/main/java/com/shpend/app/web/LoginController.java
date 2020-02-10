package com.shpend.app.web;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Admin;
import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.security.Authority;
import com.shpend.app.service.AdminService;
import com.shpend.app.service.UserService;


@Controller
public class LoginController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  AdminService adminService;
  
  @GetMapping("/login")
  public String login() {
    return "login";
  }
  
  @GetMapping("/")
  public String intermediateLogin() {
	  User findByUsername = userRepo.findByUsername("shpend.palushi@fti.edu.al");
	  String encode = passwordEncoder.encode("letmein");
	  if(findByUsername==null) {
		  User user = new User();
		  user.setName("Shpend Palushi");
		  Date date = new Date();
		  user.setCreatedAt(new Timestamp(date.getTime()));
		  user.setPassword(encode);
		  user.setUsername("shpend.palushi@fti.edu.al");
		  Authority authority = new Authority();
		  authority.setAuthority("ROLE_ADMIN");
		  authority.setUser(user);
		  user.getAuthorities().add(authority);
		  userRepo.save(user);
		  System.out.println("Clicked");
		  Admin admin = new Admin();
		  admin.setPosition("Dean");
		  adminService.save(admin, user.getId());
		  
	  }
    return "index";
  }
  
  @GetMapping("/register")
  public String register (ModelMap model) {
    model.put("user", new User());
    return "register";
  }
  
  @PostMapping("/register")
  public String registerPost (User user) {
	  
	User fuser = userRepo.findByUsername(user.getUsername());
	
	if(fuser==null) {
		User savedUser = userService.save(user);
		if(savedUser.getAuthorities().iterator().next().getAuthority().equals("ROLE_TEACHER"))
		return "redirect:/register_teacher/" + user.getId();
		else if(savedUser.getAuthorities().iterator().next().getAuthority().equals("ROLE_USER"))
			return "redirect:/register_student/" + user.getId();
		else
			return "redirect:/register_admin/" + user.getId();
	}
    return "redirect:/login";
  }
}