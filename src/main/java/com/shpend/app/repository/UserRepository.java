package com.shpend.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.User;


public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);

}
