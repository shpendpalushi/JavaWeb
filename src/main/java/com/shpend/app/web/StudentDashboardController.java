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

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;

@Controller
public class StudentDashboardController implements RegisterRole {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/dashboard/{userId}")
	  public String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
		 Optional<User> userOptional = 	userRepo.findById(userId);
		 if(userOptional.isPresent())
		 {
			 
			 User user = userOptional.get();
			 map.put("user", user);
			 map.put("student", user.getStudent());
		 }else {
		      response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + userId + " was not found");
		      return "error";
		    }
		 
		 return "dashboard";
	  }
}
