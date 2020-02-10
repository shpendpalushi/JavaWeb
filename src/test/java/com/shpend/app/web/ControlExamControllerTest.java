package com.shpend.app.web;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class ControlExamControllerTest {

	@PersistenceContext
	EntityManager em;
	@Autowired
	TeacherRepository tr;
	@Autowired
	TeacherService ts;
	@Autowired
	ThiesisService thiesisService;
	
	@Test
	void test() {
		
		TypedQuery<AnswerThiesis> q = em.createQuery("Select at from AnswerThiesis at where at.thiesis.course.id=:course_id", AnswerThiesis.class)
				.setParameter("course_id", (long)3);
		for (AnswerThiesis a : q.getResultList()) {
			System.out.println(a.getStudent().getUser().getName());
		}
		assertNotNull(q.getResultList());
	}

}