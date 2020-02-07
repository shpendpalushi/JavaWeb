package com.shpend.app.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shpend.app.service.CourseService;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;
import com.shpend.app.domain.Course;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.ThiesisRepository;
import com.shpend.app.repository.UserRepository;

@Controller
@RequestMapping()
public class TeacherDashboardController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	TeacherService teacherService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping("/teacher/{userId}")
	public String dashboard(ModelMap map,@PathVariable Long userId) {
		Long teacherId = getTeacherId(userId);
		List<Course> coursesForTeacher = getCoursesForTeacher(teacherId);
		if(coursesForTeacher!=null){
			List<String> courseNames = getCourseNames(coursesForTeacher);
			System.out.println(courseNames);
			map.put("courses", coursesForTeacher);
		}
		
		
		User user = userService.get(userId);
		Teacher teacher = user.getTeacher();
		map.put("user", user);
		map.put("teacher",teacher);
		map.put("id",userId);
		
		List<Thiesis> findByTeacher = thiesisRepo.findByTeacher(teacher);
		System.out.println(findByTeacher.size());
		if(findByTeacher!=null) {
			map.put("my_thiesis", findByTeacher);
		}
		
		
		
		
		return "teacher_dashboard";
	}
	
	@GetMapping("/teacher/{userId}/add_thiesis")
	  public String addThiesis(ModelMap model, @PathVariable Long userId) {
			String uId = String.valueOf(userId);
			List<Course> findAll = courseRepo.findAll();
			model.put("courses", findAll);
			model.put("id", uId);
			model.put("thiesis", new Thiesis());
			return "add_thiesis"; 
	  }
	@PostMapping("/teacher/{userId}/add_thiesis")
	public String addThiesis( @ModelAttribute(value="thiesis") Thiesis thiesis, @PathVariable Long userId)
	{
		
		Teacher teacher = getTeacher(userId);
		thiesis.setTeacher(teacher);
		Course course = courseService.findById(thiesis.getTmpCourse());
		System.out.println(course.getName());
		thiesis.setCourse(course);
		
		teacher.getThiesis().add(thiesis);
	//	teacherService.save(teacher);
		thiesisService.save(thiesis);
		
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
	
	
	private List<Course> getCoursesForTeacher(Long userId){
		Optional<Teacher> c = teacherRepo.findById(userId);
		if(c.isPresent()) {
			Teacher teacher = c.get();
			TypedQuery<Course> query = 
			         em.createQuery("select c from Course c join c.teachers t where t.id=:teacher_id", Course.class)
			         .setParameter("teacher_id", teacher.getId());
			List<Course> resultList = query.getResultList();
			if(resultList!=null) {
				return resultList;
			}
		}
		return null;
		
	}
	
	private List<String> getCourseNames(List<Course> lc)
	{
		List<String> stringList = new ArrayList<>();
		if(lc!=null) {			
			for (Course course : lc) {
				stringList.add(course.getName());
			}
		}
		
		return stringList;
	}
	
	private Long getTeacherId(Long userId) {
		Optional<User> findById = userRepo.findById(userId);
		if(findById.isPresent()) {
			User user = findById.get();
			return user.getTeacher().getId();
		}
		
		return null;
	}
	
	private Teacher getTeacher(Long userId) {
		User user = userService.get(userId);
		return user.getTeacher();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
	
	
}
