package com.shpend.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepositoryGo;

@SpringBootApplication
public class FirstRealAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FirstRealAppApplication.class, args);
	}
	
	@Autowired
	UserRepositoryGo repo;

	@Override
	public void run(String... args) throws Exception {
		
		repo.insertUser(new User(10001L, "shpendpalushi","boss", "shpend"));
		
	}

}
