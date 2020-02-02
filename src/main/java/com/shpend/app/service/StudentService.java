package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Student;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.UserRepository;

@Service
public class StudentService  {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	UserRepository userRepo;
	
	public Student save(Student student, Long id)
	{
		User user;
		Optional<User> ifUser = userRepo.findById(id);
		if(ifUser.isPresent()) {
			user= ifUser.get();
			user.
			setStudent(student);
			user.setCompletedInfo(1);
			student.setUser(user);
			Date date = new Date();
			student.setCreatedAt(new Timestamp(date.getTime()));
			return studentRepo.save(student);
		}else {
			System.out.println("Couldn't find any user here...");
			return null;
		}
			
		
	}
	
	public Student save(Student student) {
		return studentRepo.save(student);
	}
	
	
}
