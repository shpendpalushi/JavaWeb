package com.shpend.app.service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
//import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.CourseRepository;
//import com.shpend.app.repository.QuestionRepository;
import com.shpend.app.repository.ThiesisRepository;

@Service
public class ThiesisService {
	
	@Autowired
	ThiesisRepository thiesisRepo;
	@Autowired
	CourseRepository courseRepo;
//	@Autowired
//	QuestionRepository questionRepo;
	
	public Thiesis save(Thiesis thiesis)
	{
		Optional<Course> course =courseRepo.findById(thiesis.getCourse().getId());
		if(course.isPresent()) {
			Course myCourse = course.get();
			thiesis.setCourse(myCourse);
			
			Date date = new Date();
			thiesis.setCreatedAt(new Timestamp(date.getTime()));
			
//			List<String> questionsThiesis = getQuestionsThiesis(thiesis);
//			for(String q: questionsThiesis) {
//				//Question question = new Question();
//				question.setQuestion(q);
//				question.setThiesis(thiesis);
//				questionRepo.save(question);
//				thiesis.getQuestion().add(question);
//			}
			Thiesis save = thiesisRepo.save(thiesis);
			return save;
			
			
		}
		else {
			System.out.println("Couldn't save the thiesis");
			return null;
		}
		
	}
	
	public Thiesis findById(Long id) {
		Optional<Thiesis> findById = thiesisRepo.findById(id);
		if(findById.isPresent()) 
			return findById.get();
		return null;
	}

	private List<String> getQuestionsThiesis(Thiesis thiesis){
		String questions = thiesis.getQuestions();
		String[] arrayQuestions = questions.split("\n");
		return Arrays.asList(arrayQuestions);
	}
}
