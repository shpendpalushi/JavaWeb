package com.shpend.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;

@Service
public class TeacherService  {
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	UserRepository userRepo;
	
	public Teacher save(Teacher teacher, Long id)
	{
		User user;
		Optional<User> ifUser = userRepo.findById(id);
		if(ifUser.isPresent()) {
			user= ifUser.get();
			user.setTeacher(teacher);
			user.setCompletedInfo(1);
			teacher.setUser(user);
			return teacherRepo.save(teacher);
		}else {
			System.out.println("Couldn't find any user here...");
			return null;
		}
			
		
	}
	
	
	
	
}
