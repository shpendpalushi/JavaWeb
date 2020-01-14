package com.shpend.app.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;
import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;

@Controller
public class TeacherDashboardController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	UserRepository userRepo;;
	
	@GetMapping("/teacher/{userId}")
	  public String dashboard(ModelMap model) {
		System.out.println("getDashboard called");
			Set<Question> questions = new HashSet<>();
			model.put("thiesis", new Thiesis());
			model.put("questions",questions);
			return "teacher_dashboard"; 
	  }
	@PostMapping("/teacher/{userId}")
	public String addThiesis(Thiesis thiesis,String name)
	{
		System.out.println("addThiesis called");
		thiesisService.save(thiesis, name);
		System.out.println(thiesis.getId());
		return "redirect:/teacher/{userId}" ;
	}
}
