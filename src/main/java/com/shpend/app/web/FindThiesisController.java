package com.shpend.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.ThiesisRepository;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;

@Controller
public class FindThiesisController {

	@Autowired
	ThiesisService thiesisService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@Autowired
	UserService userService;
	@Autowired
	CourseRepository courseRepo;
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/teacher/{userId}/find/{thiesisId}")
	public String getThiesis(@PathVariable Long thiesisId, @PathVariable Long userId, ModelMap map){
		Thiesis thiesis = thiesisService.findById(thiesisId);
		List<Course> findAll = courseRepo.findAll();
		map.put("id", userId);
		map.put("courses", findAll);
		map.put("thiesis",thiesis);
		return "find_thiesis";
	}
	
	@PostMapping("/teacher/{userId}/find/{thiesisId}")
	public String updateThiesis(Thiesis thiesis,@PathVariable Long userId) {
		thiesisService.save(thiesis);
		return "redirect:/teacher/"+ userId;
	}
	
	@PostMapping("/delete_thiesis/{userId}/{thiesisId}")
	public String deleteThiesis(@PathVariable Long thiesisId, @PathVariable Long userId) {
		Thiesis findById = thiesisService.findById(thiesisId);
		thiesisRepo.delete(findById);
		return "redirect:/teacher/"+ userId;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
		
}
