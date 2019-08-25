package com.shpend.app.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shpend.app.domain.User;

@Repository
@Transactional
public class UserRepositoryGo {
	
	@Autowired
	EntityManager em;
	
	public void insertUser( User user) {
		em.merge(user);
	}
}
