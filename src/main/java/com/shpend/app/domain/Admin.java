package com.shpend.app.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Admin extends Role{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String position;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	public Admin() {
		
	}
	
	
	
public Admin(Long id, String position, User user, Date createdAt) {
		super();
		this.id = id;
		this.position = position;
		this.user = user;
		this.createdAt = createdAt;
	}



public Admin(String position) {
		this.position= position;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

}
