package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.SubjectsServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Service.AdminServiceLocal;

@RestController
public class SubjectsController {
	@Autowired
	private SubjectsServiceLocal subjectsService;
	@Autowired
	private UsersServiceLocal usersService;
	@Autowired 
	private AdminServiceLocal adminService;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchAllSubjectTeachOfUser", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> fetchAllSubjectTeachOfUser(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		List<Subject> subjectList = new ArrayList<Subject>();
		if(userlogin.getRole().equals("teacher")) {
			subjectList = adminService.fetchSubjectTeachOfTeacher(userlogin.getId());
		}
		
		if(userlogin.getRole().equals("student")) {
			subjectList = subjectsService.getAllSubjectByStudentId(userlogin.getId());
		}
		
		return new ResponseEntity<List<Subject>>(subjectList, HttpStatus.OK);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchAllSubjectManageOfUser", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> fetchAllSubjectManageOfUser(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		List<Subject> subjectList = new ArrayList<Subject>();
		if(userlogin.getRole().equals("teacher")) {
			subjectList = adminService.fetchSubjectManageOfTeacher(userlogin.getId());
		}
		
		return new ResponseEntity<List<Subject>>(subjectList, HttpStatus.OK);
	}
}
