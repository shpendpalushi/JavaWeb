package com.shpend.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String answer;
	@ManyToOne(cascade=CascadeType.ALL)
	private AnswerThiesis answerThiesis;

	public Answer() {
		
	}
	
		
	public Answer(Long id, String answer) {
		super();
		this.id = id;
		this.answer = answer;
		
	}


	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public AnswerThiesis getAnswerThiesis() {
		return answerThiesis;
	}


	public void setAnswerThiesis(AnswerThiesis answerThiesis) {
		this.answerThiesis = answerThiesis;
	}

	
//	public Question getQuestion() {
//		return question;
//	}
//	public void setQuestion(Question question) {
//		this.question = question;
//	}



}