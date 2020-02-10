package com.shpend.app.service;


//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
public class UserDetailsServiceImplTest {

	@Test	
	public void test() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "boss";
		String encode = encoder.encode(rawPassword);
		System.out.println(encode);
		assertNotEquals(encode,rawPassword);
	}

}











