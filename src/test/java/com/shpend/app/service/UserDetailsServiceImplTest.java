package com.shpend.app.service;


//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
