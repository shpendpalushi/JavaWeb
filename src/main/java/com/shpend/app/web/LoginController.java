package com.shpend.app.web;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.UserService;


@Controller
public class LoginController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepo;
  
  @GetMapping("/login")
  public String login() {
    return "login";
  }
  
  @GetMapping("/")
  public String intermediateLogin() {
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
		return "redirect:/login";
	}
    return "redirect:/login";
  }
}