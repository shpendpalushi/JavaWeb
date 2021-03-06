package com.shpend.app.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="student")
public class Student extends Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String dega;
	private short viti;
	private String niveli;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="course")
	private Set<StudentCourseTaken> takenCourses;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Student()
	{
		
	}
	
	public Student(Long id)
	{
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public short getViti() {
		return viti;
	}

	public void setViti(short viti) {
		this.viti = viti;
	}

	public String getDega() {
		return dega;
	}

	public void setDega(String dega) {
		this.dega = dega;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getNiveli() {
		return niveli;
	}

	public void setNiveli(String niveli) {
		this.niveli = niveli;
	}

	public Set<StudentCourseTaken> getTakenCourses() {
		return takenCourses;
	}

	public void setTakenCourses(Set<StudentCourseTaken> takenCourses) {
		this.takenCourses = takenCourses;
	}
}
