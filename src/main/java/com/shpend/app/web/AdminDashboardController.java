package com.shpend.app.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shpend.app.domain.User;
import com.shpend.app.repository.AdminRepository;
import com.shpend.app.repository.AuthorityRepository;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.security.Authority;
import com.shpend.app.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
  
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	AuthorityRepository authorityRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	AdminRepository adminRepo;
	
	
	@PersistenceContext
	EntityManager em;
	
 
  @GetMapping("/admin/{userId}")
  public String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
	  
		 User user = userService.get(userId);
		 map.put("user", user);
		 
	 return "admin_dashboard";
  }
  @GetMapping("/view_users/{userId}")
  public String getUsers(@PathVariable Long userId,ModelMap model) {
	  List<User> findAll = userRepo.findAll();
	  model.put("userId", userId);
	  model.put("users", findAll);
	  return "view_users";
  }
  
  @PostMapping("/delete/{userId}")
  public String deleteUser(@PathVariable Long userId) {
	  User user = userService.get(userId);
	  
	  user.setPassword(Generalized.randomStringGenerator(15));
	  user.setSoftDeleted((short)1);
	  userRepo.save(user);
	  return "redirect:/view_users";
  }
  
 
  @InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
  
}