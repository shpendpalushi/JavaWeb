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

import com.shpend.app.domain.Admin;
import com.shpend.app.domain.Role;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.AdminRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.AdminService;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.UserService;

@Controller
public class RegisterAdminController implements RegisterRole {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	Long myId = null;
	@GetMapping("/register_admin/{userId}")
	  public String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
		myId = userId;
		Optional<User> userOptional = 	userRepo.findById(userId);
		 if(userOptional.isPresent())
		 {
			 map.put("admin", new Admin());
			return "register_admin"; 
		 }else {
		      response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + userId + " was not found");
		      return "login";
		    }
		 
	  }
	@PostMapping("/register_admin/{userId}")
	  public String register (Admin admin) {
		try {
			adminService.save(admin, myId);
		}catch(NullPointerException e) {
			System.out.println("Null pointer here");
			e.printStackTrace();
		}
	    return "redirect:/login";
	  }
}
