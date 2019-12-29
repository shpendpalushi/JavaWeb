package com.shpend.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.User;
import com.shpend.app.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	//@RequestMapping(value="/login",method=RequestMethod.GET)
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute User user) {
		userService.save(user);
		
		return "redirect:/login";
	}
	
}
