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
	private Short viti;
	private String niveli;
	private String hobbies;
	private String about;
	@OneToOne()
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="course")
	private Set<StudentCourseTaken> takenCourses;

	public User getUser() {
		return user;
	}
	
	
	
	public Student(Long id, String dega, Short viti, String niveli, String hobbies, String about, User user,
			Date createdAt, Set<StudentCourseTaken> takenCourses) {
		super();
		this.id = id;
		this.dega = dega;
		this.viti = viti;
		this.niveli = niveli;
		this.hobbies = hobbies;
		this.about = about;
		this.user = user;
		this.createdAt = createdAt;
		this.takenCourses = takenCourses;
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

	public Short getViti() {
		return viti;
	}

	public void setViti(Short viti) {
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

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
