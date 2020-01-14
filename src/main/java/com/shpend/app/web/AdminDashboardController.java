package com.shpend.app.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
  
	@Autowired
	UserRepository userRepo; 
 
  @GetMapping("/admin_dashboard/{userId}")
  public String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
	 Optional<User> userOptional = 	userRepo.findById(userId);
	 System.out.println(userOptional.toString());
	 if(userOptional.isPresent())
	 {
		 User user = userOptional.get();
		 map.put("user", user);
	 }else {
	      response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + userId + " was not found");
	      return "dashboard";
	    }
	 
	 return "dashboard";
  }
  
}