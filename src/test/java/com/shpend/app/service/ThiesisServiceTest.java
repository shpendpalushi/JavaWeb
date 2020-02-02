package com.shpend.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.StudentCourseTaken;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class ThiesisServiceTest {
	
	@Autowired
	ThiesisService service;
	@Test
	void test() {
		Course course = new Course(Long.parseLong("1"),"web","DII",Short.parseShort("1"),
				new Date(), new HashSet<Teacher>(),new HashSet<Thiesis>(),new HashSet<StudentCourseTaken>());
		String q = new String("Hello, Hello,  Hello");
		Thiesis thiesis = new Thiesis(Long.parseLong("1"),q, course, new Date(), "Hello", Long.parseLong("3"));
		
		assertNotNull(service.save(thiesis));
		

	}

}
