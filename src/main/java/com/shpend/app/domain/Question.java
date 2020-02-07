//package com.shpend.app.domain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Transient;
//
//import org.hibernate.validator.constraints.Length;
//
//@Entity
//public class Question {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;
//	@Length(max=1000)
//	private String question;
//	@ManyToOne(cascade=CascadeType.ALL)
//	private Thiesis thiesis;
//	@OneToMany(mappedBy="question")
//	private List<Answer> answers = new ArrayList<>();
//	@Transient
//	private String tmpAnswers;
//	
//	public Question() {
//		
//	}
//
//	public Question(Long id, String question, Thiesis thiesis) {
//		super();
//		this.id = id;
//		this.question = question;
//		this.thiesis = thiesis;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(String question) {
//		this.question = question;
//	}
//
//	public Thiesis getThiesis() {
//		return thiesis;
//	}
//
//	public void setThiesis(Thiesis thiesis) {
//		this.thiesis = thiesis;
//	}
//
//	public List<Answer> getAnswers() {
//		return answers;
//	}
//
//	public void setAnswers(List<Answer> answers) {
//		this.answers = answers;
//	}
//
//	public String getTmpAnswers() {
//		return tmpAnswers;
//	}
//
//	public void setTmpAnswers(String tmpAnswers) {
//		this.tmpAnswers = tmpAnswers;
//	}
//	
//	
//
//}
