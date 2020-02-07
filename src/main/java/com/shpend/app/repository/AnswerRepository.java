package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	
}
