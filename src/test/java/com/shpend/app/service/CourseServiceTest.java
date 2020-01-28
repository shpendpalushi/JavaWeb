package com.shpend.app.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Transactional
@EntityScan("com.shpend")
public class CourseServiceTest {
	
	
	@Autowired
	CourseService service;
	
	
	

	@Test
	public void test() {
		
		Course course = new Course();
		long id= 1; 
		
		course.setId(id);
		course.setName("OPP");
		course.setDepartament("DII");
		course.setCreatedAt(new Date());
		
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setDepartament("DII");
		User user = new User();
		user.setId(id);
		teacher.setUser(user);
		
		
		assertNotNull(service.save(course, teacher.getUser().getId()));
		
	}

}
