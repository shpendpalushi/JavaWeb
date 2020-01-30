package com.shpend.app.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.HandlerMapping;

import com.shpend.app.service.CourseService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;
import com.shpend.app.domain.Course;
import com.shpend.app.domain.Question;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;

@Controller
@RequestMapping()
public class TeacherDashboardController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	UserRepository userRepo;;
	@Autowired
	CourseService courseService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	CourseRepository courseRepo;
	
	@GetMapping("/teacher/{userId}")
	public String dashboard(ModelMap map,@PathVariable Long userId) {
		map.put("id",userId);
		return "teacher_dashboard";
	}
	
	@GetMapping("/teacher/{userId}/add_thiesis")
	  public String addThiesis(ModelMap model, @PathVariable Long userId) {
			String uId = String.valueOf(userId);
			List<Course> findAll = courseRepo.findAll();
			model.put("courses", findAll);
			ArrayList<Question> questions = new ArrayList<>();
			model.put("id", uId);
			model.put("thiesis", new Thiesis());
			model.put("questions",questions);
			return "add_thiesis"; 
	  }
	@PostMapping("/teacher/{userId}/add_thiesis")
	public String addThiesis( @ModelAttribute(value="thiesis") Thiesis thiesis,@ModelAttribute(value="questions")ArrayList<Question> q, @PathVariable Long userId)
	{
		System.out.println(q.size());
		Long tmpCourse = thiesis.getTmpCourse();
		thiesisService.save(thiesis, tmpCourse,q);
		System.out.println(thiesis.getId());
		
		return "redirect:/teacher/"+ userId;
	}
	@GetMapping("/teacher/{userId}/add_course")
	public String course(ModelMap map, HttpServletResponse resp) {
		
		map.put("course",new Course());
		return "add_course";
	}
	@PostMapping("/teacher/{userId}/add_course")
	public String addCourse (Course course, @PathVariable Long userId) {
		String uId = String.valueOf(userId);
		Optional<User> user = userRepo.findById(userId);
		Course save = courseService.save(course, userId);
		if(user!=null) {
			User myUser = user.get();
			Teacher teacher = myUser.getTeacher();
			teacher.getCourses().add(save);
		}
		
		
		return "redirect:/teacher/"+ uId;
	}
	
	
	
	
}
