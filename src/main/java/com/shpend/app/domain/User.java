package com.shpend.app.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.OneToOne;

import com.shpend.app.security.Authority;


@Entity
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String name;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER , mappedBy="user")
	private Set<Authority> authorities = new HashSet<>();
//	private Authority authority;
	@Transient
	private String authority;
	private Integer completedInfo;
	@OneToOne(mappedBy="user")
	private Teacher teacher;
	@OneToOne(mappedBy="user")
	private Student student;
	@OneToOne(mappedBy="user")
	private Admin admin;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	

	public User(Long id, String username, String password, String name, Set<Authority> authorities, String authority,
			Integer completedInfo, Teacher teacher, Student student, Admin admin, Date createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.authorities = authorities;
		this.authority = authority;
		this.completedInfo = completedInfo;
		this.teacher = teacher;
		this.student = student;
		this.admin = admin;
		this.createdAt = createdAt;
	}



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public User() {
		
	}
	
	public User(Long id, String username, String password, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Id->"+ getId()+",Name->" + getName() + ", Username->" +
				getUsername() +",Password->"+getPassword()+",Authorities->" + getAuthorities());
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority_property) {
		this.authority = authority_property;
	}

	public Integer getCompletedInfo() {
		return completedInfo;
	}

	public void setCompletedInfo(Integer completedInfo) {
		this.completedInfo = completedInfo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
