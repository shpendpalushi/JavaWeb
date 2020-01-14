package com.shpend.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String question;
	@ManyToOne(cascade=CascadeType.ALL)
	private Thiesis thiesis;
	
	public Question() {
		
	}
	
	public Question(Long id, String question, Thiesis thiesis) {
		super();
		this.id = id;
		this.question = question;
		this.thiesis = thiesis;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Thiesis getThiesis() {
		return thiesis;
	}
	public void setThiesis(Thiesis thiesis) {
		this.thiesis = thiesis;
	}
}
