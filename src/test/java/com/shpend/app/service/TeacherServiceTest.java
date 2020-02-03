//package com.shpend.app.service;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.fail;
//
//import org.junit.Test;
//
//import com.shpend.app.domain.Teacher;
//
//class TeacherServiceTest {
//
//	@Test
//	void test() {
//		TeacherService ts = new TeacherService();
//		Teacher save = ts.save(new Teacher(), 1L);
//		assertNotNull(save.getUser());
//	}
//
//}


package com.shpend.app.service;


//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
public class TeacherServiceTest {

	@Test	
	public void test() {
		UserService service = new UserService();
		User ifUser = service.get(1L);
		assertNotNull(ifUser);
		
	}

}
