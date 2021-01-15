package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.ClassServiceLocal;
import thanhtuu.springmvc.Service.SubjectsServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Service.ExamsServiceLocal;
import thanhtuu.springmvc.Temporary.ClassesList;
import thanhtuu.springmvc.Temporary.ExamsList;
import thanhtuu.springmvc.Temporary.StudentExams;

@RestController
public class ClassController {
	@Autowired
	private ClassServiceLocal classService;
	@Autowired
	private UsersServiceLocal usersService;
	@Autowired
	private ExamsServiceLocal examsService;
	@Autowired
	private SubjectsServiceLocal subjectsService;
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getClassOfUser", method = RequestMethod.GET)
	public ResponseEntity<ClassesList> getClassOfUser(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		List<Classes> classList = new ArrayList<Classes>();
		ClassesList classesList = new ClassesList();
		
		if(userlogin.getRole().equals("teacher")) {
			classList = classService.getClassByTeacherId(userlogin.getId());
			classesList.classesList = classList;
			
			List<Integer> classIdList = new ArrayList<Integer>(); 
			for (int i = 0; i < classList.size(); i++) {
				classIdList.add(classList.get(i).getId().intValue());
			}
			
			List<Integer> subjectIdList = new ArrayList<Integer>(); 
			for (int i = 0; i < classList.size(); i++) {
				subjectIdList.add(classList.get(i).getSubjectid());
			}
			
			List<Integer> studentCountList = classService.getStudentCountByClassIdList(classIdList);
			List<Integer> examsCountList = classService.getExamsCountByClassIdList(classIdList);
			List<Subject> subjectList = subjectsService.getSubjectInList(subjectIdList);
			
			classesList.studentCountList = studentCountList;
			classesList.examsCountList = examsCountList;
			classesList.examsCountList = examsCountList;
			classesList.subjectList = subjectList;
		}
		
		if(userlogin.getRole().equals("student")) {
			classList = classService.getClassByStudentId(userlogin.getId());
			classesList.classesList = classList;
		}

		return new ResponseEntity<ClassesList>(classesList, HttpStatus.OK);
	}

	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createClass", method = RequestMethod.POST)
	public int createClass(@RequestBody Classes classes, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		classes.setTeacherid(userlogin.getId().intValue());
		return classService.createClass(classes);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/assignStudentToClass", method = RequestMethod.POST)
	public int assignStudentToClass(@RequestBody List<Class_Member> classMemberList) {
		return classService.assignStudentToClass(classMemberList);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getStudentOfClass/{classId}", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> getStudentOfClass(@PathVariable("classId") int classId) {
		List<Users> studentList = classService.getStudentOfClass(classId);
		return new ResponseEntity<List<Users>>(studentList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getClassByClassId/{classId}", method = RequestMethod.GET)
	public ResponseEntity<Classes> getClassByClassId(@PathVariable("classId") long classId) {
		Classes classes = classService.getClassByClassId(classId);
		return new ResponseEntity<Classes>(classes, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllExamsByClassId/{classId}", method = RequestMethod.GET)
	public ResponseEntity<ExamsList> getAllExamsByClassId(@PathVariable("classId") long classId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		List<StudentExams> examsList = new ArrayList<StudentExams>();
		
		if(userlogin.getRole().equals("teacher")) {
			examsList = classService.getAllExamsByClassIdForTeacher(classId);
		}
		
		if(userlogin.getRole().equals("student")) {
			examsList = classService.getAllExamsByClassIdForStudent(classId);
			for (int i = 0; i < examsList.size(); i++) {
				examsList.get(i).times = examsService.getExamsCountOfStudentByClassId(classId, userlogin.getId(), examsList.get(i).exams.getId());
				System.out.println(examsList.get(i).times);
			}
		}
		 
		List<Integer> examsIdList = new ArrayList<Integer>(); 
		for (int i = 0; i < examsList.size(); i++) {
			examsIdList.add(examsList.get(i).exams.getId().intValue());
		}
		
		ExamsList examsesList = new ExamsList();
		examsesList.examsList = examsList;
		
		if(userlogin.getRole().equals("teacher")) {
			examsesList.studentCount = examsService.getStudentExamsCountByClassId(classId, examsIdList);
		}
		return new ResponseEntity<ExamsList>(examsesList, HttpStatus.OK);
	}
}
