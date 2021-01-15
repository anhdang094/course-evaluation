package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.ExamsServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Temporary.CreateExams;
import thanhtuu.springmvc.Temporary.ExamsInfo;

@RestController
public class ExamsController {
	@Autowired
	private ExamsServiceLocal examService;
	@Autowired
	private UsersServiceLocal usersService;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getExams/{examsId}", method = RequestMethod.GET)
	public ExamsInfo getExams(@PathVariable("examsId") long examsId) {
		return examService.getExams(examsId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createExams", method = RequestMethod.POST)
	public long createExamsStep1(@RequestBody CreateExams exams, Principal principal) {
		System.out.println("==================== createExams================" );
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		exams.exams.setTeacherid(userlogin.getId().intValue());	
		return examService.createExams(exams);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createRootExams", method = RequestMethod.POST)
	public long createRootExams(@RequestBody CreateExams exams, Principal principal) {
		System.out.println("==================== createRootExams================" );
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		exams.exams.setTeacherid(userlogin.getId().intValue());		
		return examService.createRootExams(exams);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/approveExams", method = RequestMethod.POST)
	public int approve(@RequestBody long examsId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		if (userlogin.getRole().equals("teacher")) {
			return examService.approve(examsId, userlogin.getId());
		}
		return 0;
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchResultExam/{classId}/{examsId}", method = RequestMethod.GET)
	public List<Student_Exam> fetchResultExam(@PathVariable("classId") long classId, @PathVariable("examsId") long examsId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		if (userlogin.getRole().equals("student")) {
			return examService.getResultExam(classId, userlogin.getId(), examsId);
		}
		return new ArrayList<Student_Exam>();
	}
}
