package com.shpend.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="course")
public class Course  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String department;
	private Short year;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@ManyToMany(mappedBy = "courses")
	private List<Teacher> teachers = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="course")
	private List<Thiesis> thiesis = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="course")
	private Set<StudentCourseTaken> takenCourses;
	public Course() {
		
	}
	
	
	public Course(Long id,String name,String departament)
	{
		this.name = name;
		this.id = id;
		this.department = departament;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartament() {
		return department;
	}

	public void setDepartament(String department) {
		this.department = department;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Short getYear() {
		return year;
	}


	public void setYear(Short year) {
		this.year = year;
	}


	public Set<StudentCourseTaken> getTakenCourses() {
		return takenCourses;
	}


	public void setTakenCourses(Set<StudentCourseTaken> takenCourses) {
		this.takenCourses = takenCourses;
	}


	public List<Thiesis> getThiesis() {
		return thiesis;
	}


	public void setThiesis(List<Thiesis> thiesis) {
		this.thiesis = thiesis;
	}


	public List<Teacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
