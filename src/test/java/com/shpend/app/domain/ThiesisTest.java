package com.shpend.app.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@EntityScan("com.shpend")
class ThiesisTest {
	@Autowired
	EntityManager em;
	@Test
	void test() {
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t join t.question q where q.id IS NOT NULL", Thiesis.class);
		assertNotNull(q.getResultList());
	}

}
