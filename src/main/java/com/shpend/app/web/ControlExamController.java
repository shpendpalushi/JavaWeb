package com.shpend.app.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.AnswerThiesisRepository;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.ThiesisRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.CourseService;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;

@Controller
public class ControlExamController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	TeacherService teacherService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@Autowired
	AnswerThiesisRepository answerThiesisRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping("/teacher/{userId}/view_course_exam/{courseId}/view_answers/{thiesisId}/control_exam/{answerThiesisId}")
	public String controlExam(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long thiesisId, 
			@PathVariable Long answerThiesisId, ModelMap model) {
		TypedQuery<AnswerThiesis> q= em.createQuery("Select ath from AnswerThiesis ath where ath.id=:answerThiesisId", AnswerThiesis.class)
				.setParameter("answerThiesisId", answerThiesisId);
		AnswerThiesis answerThiesis = q.getResultList().get(0);
		Thiesis thiesis = thiesisService.findById(thiesisId);
		model.put("questions", Generalized.getQuestionsFromThiesis(thiesis));
		model.put("answers", answerThiesis);
		return "view_student_answers";
	}
	
	@PostMapping("/teacher/{userId}/view_course_exam/{courseId}/view_answers/{thiesisId}/control_exam/{answerThiesisId}")
	public String finishControling(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long thiesisId, 
			@PathVariable Long answerThiesisId,@ModelAttribute(value="answers") AnswerThiesis answerThiesis) {
		System.out.println(answerThiesis.getId()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>&&&&&&&&&&&&");
		answerThiesis.setPassive((short)1);
		answerThiesisRepo.save(answerThiesis);
		return "redirect:/teacher/"+ userId.toString() + "/view_course_exam/"+courseId.toString()+"/view_answers/"+thiesisId.toString();
	}
	
	@GetMapping("/teacher/{userId}/view_course_exam/{courseId}/view_answers/{thiesisId}/view_exam/{answerThiesisId}")
	public String viewExam(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long thiesisId, 
			@PathVariable Long answerThiesisId, ModelMap model) {
		TypedQuery<AnswerThiesis> q= em.createQuery("Select ath from AnswerThiesis ath where ath.id=:answerThiesisId", AnswerThiesis.class)
				.setParameter("answerThiesisId", answerThiesisId);
		AnswerThiesis answerThiesis = q.getResultList().get(0);
		Thiesis thiesis = thiesisService.findById(thiesisId);
		model.put("questions", Generalized.getQuestionsFromThiesis(thiesis));
		model.put("answers", answerThiesis);
		return "view_student_answers_controlled";
	}
	
}
