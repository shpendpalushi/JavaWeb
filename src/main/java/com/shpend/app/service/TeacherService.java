package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.security.Authority;


@Service
public class TeacherService  {
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService; 
	
	
	
	public Teacher save(Teacher teacher, Long id)
	{
		Optional<User> userOptional = 	userRepo.findById(id);
		 if(userOptional.isPresent())
		 {
			 	User user = userOptional.get();
			 	user.setCompletedInfo(1);
			 	teacher.setDepartament(teacher.getDepartament());
				teacher.setUser(user);
				Date date = new Date();
				teacher.setCreatedAt(new Timestamp(date.getTime()));
				return teacherRepo.save(teacher);
		 }else {
			 System.out.println("NULL not registered");
			 return null;
		 }
		
	}
	
	
	public Teacher saveAndUpdate(Teacher teacher, Long id,Course course)
	{
		Optional<User> userOptional = 	userRepo.findById(id);
		 if(userOptional.isPresent())
		 {
			 	User user = userOptional.get();
			 	user.setCompletedInfo(1);
			 	teacher.setDepartament(teacher.getDepartament());
				teacher.setUser(user);
				Date date = new Date();
				teacher.setCreatedAt(new Timestamp(date.getTime()));
				teacher.getCourses().add(course);
				return teacherRepo.save(teacher);
		 }else {
			 System.out.println("NULL not registered");
			 return null;
		 }
		
	}
	
	public Teacher save(Teacher t) {
		return teacherRepo.save(t);
	}
	
	
	
	
}
