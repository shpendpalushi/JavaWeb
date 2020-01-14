package com.shpend.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Question;
import com.shpend.app.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepo;
	
	public Question save(Question question)
	{
		return questionRepo.save(question);
	}

}
