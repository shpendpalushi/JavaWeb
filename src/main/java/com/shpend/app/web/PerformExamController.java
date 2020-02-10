package com.shpend.app.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Answer;
import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.domain.Course;
import com.shpend.app.domain.Student;
import com.shpend.app.domain.StudentCourseKey;
import com.shpend.app.domain.StudentCourseTaken;
//import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.AnswerRepository;
import com.shpend.app.repository.AnswerThiesisRepository;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.StudentCourseTakenRepository;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.StudentService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;

@Controller
public class PerformExamController {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	AnswerRepository answerRepo;
	@Autowired
	UserService userService;
	@Autowired
	AnswerThiesisRepository answerThiesisRepo;
	@PersistenceContext
	EntityManager em;
	@Autowired
	StudentCourseTakenRepository sRepo;
	
	@GetMapping("/dashboard/{userId}/thiesis/{courseId}")
	public String enterExam(ModelMap model, @PathVariable Long courseId, @PathVariable Long userId) {
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t where t.active=1 and t.course.id=:courseId", Thiesis.class)
				.setParameter("courseId", courseId);
		
		if(q.getResultList()!=null) {
			List<Thiesis> resultList = q.getResultList();
			
			Thiesis thiesis = resultList.get(0);
			
			List<String> questionsThiesis = Generalized.getQuestionsFromThiesis(thiesis);// getQuestionsThiesis(thiesis);
			System.out.println(questionsThiesis.size());
			ArrayList<Answer> answers = new ArrayList<>();
			AnswerThiesis ath = new AnswerThiesis();
			for (String s : questionsThiesis) {
				answers.add(new Answer());
			}
			ath.setAnswers(answers);
			
			model.put("questions", questionsThiesis);
			model.put("thiesis", thiesis);
			//TypedQuery<Question> ques = em.createQuery("Select q from Question q where q.thiesis.id=:thiesisId", Question.class)
				//	.setParameter("thiesisId", thiesis.getId());
			model.put("answerThiesis", ath);
			
			
			
		}
		return "perform_exam";
	}
	
	@PostMapping("/dashboard/{userId}/thiesis/{thiesisId}")
	public String submitAnswers(@PathVariable Long userId, @PathVariable Long thiesisId,
			@ModelAttribute(value="thiesis")Thiesis thiesis, @ModelAttribute(value="answerThiesis")AnswerThiesis ath) {
			Student studentByUser = getStudentByUser(userId);
			List<Answer> answers = getAnswers(ath);
			
			AnswerThiesis answerThiesis = new AnswerThiesis();
			answerThiesis.setAnswers(answers);
			for (Answer answer : answers) {
				answer.setAnswerThiesis(answerThiesis);
				answerRepo.save(answer);
			}
			answerThiesis.setPassive((short)0);
			answerThiesis.setStudent(studentByUser);
			answerThiesis.setThiesis(thiesis);
			thiesis.getAnswerThiesis().add(answerThiesis);
			thiesisService.save(thiesis);
			answerThiesisRepo.save(answerThiesis);
			Course course = thiesis.getCourse();
			StudentCourseKey k = new StudentCourseKey(studentByUser.getId(), course.getId());
			StudentCourseTaken st = new StudentCourseTaken(k, studentByUser, course);
			st.setCompleted((short)1);
			sRepo.save(st);
			
		return "redirect:/dashboard/"+ userId;
	}
	
	@GetMapping("/teacher/{user}/view_course_exam/{courseId}/view_answers")
	
	
	
	private Student getStudentByUser(Long userId) {
		return userService.get(userId).getStudent();
	}
	
	private List<Answer> getAnswers(AnswerThiesis ath){
		List<Answer> answers = new ArrayList<>();
		for(Answer a: ath.getAnswers()) {
			answers.add(a);
		}
		return answers;
	}

}
