package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import thanhtuu.springmvc.Domain.Exam;
import thanhtuu.springmvc.Domain.Exams_Info;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Teacher;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.ExportPDF.ExportExam;
import thanhtuu.springmvc.Service.ExamService;
import thanhtuu.springmvc.Service.ExamServiceLocal;
import thanhtuu.springmvc.Service.ExamsService;
import thanhtuu.springmvc.Service.SubjectService;
import thanhtuu.springmvc.Service.TeacherService;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Temporary.ExamsInfo;
import thanhtuu.springmvc.Temporary.Exam.ListExam;

@Controller
public class MainTemplateController {
	
	@Autowired
	private UsersServiceLocal usersService;
	
	@Autowired
	private ExamService examService;
   
	@Autowired
	private ExamsService examsService;
	
    @Autowired
    private TeacherService teacherService;
	
    @Autowired
    private SubjectService subjectService;

	@RequestMapping(value = "/wellcome",  method = RequestMethod.GET)
	public String Wellcome(Model model, Integer id, Principal principal) {
		if (principal != null){
			return "redirect:home";
		}
		else
			return "wellcome";
	}
	
	@RequestMapping(value = "/login",  method = RequestMethod.GET)
	public String UserloginGET(Model model, Integer id, Principal principal) {
		if (principal != null){
			return "redirect:home";
		}
		else
			return "login";
	}
	
	@RequestMapping(value = "/login",  method = RequestMethod.POST)
	public String UserloginPOST(Model model, Integer id, Principal principal) {
		return "";
	}
	
	@RequestMapping (value= "/notification")
	public String createNotification(Model model, Integer id, Principal principal){
		if (principal != null) {
			String email = principal.getName();
			Users userlogin = usersService.getIdByEmail(email);
			if (userlogin.getRole().equals("admin")){
				
		return "notification";
		}
			return "login";
		}
		return "login";
		
	}
	
	@RequestMapping(value = "/register/success",  method = RequestMethod.GET)
	public String registerSuccess(Model model, Integer id) {
		return "messRegister";
	}
	
	@RequestMapping(value = "/home",  method = RequestMethod.GET)
	public String getHome(Model model, Integer id, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			Users userlogin = usersService.getIdByEmail(email);
			if (userlogin.getRole().equals("admin"))
				return "redirect:admin";
			else
				return "home";
		}
		else {
			return "redirect:login";
		}
	}
	
	@RequestMapping(value = "/sign-up",  method = RequestMethod.GET)
	public String UsersignupGET(Model model, Integer id) {
		return "registerForm";
	}

	@RequestMapping(value="/admin")
	public String getAdminTemplate() {
		return "admin";
	}
	
	@RequestMapping(value="/exams/{examsId}/exam/{examId}/view")
	public String getViewExamTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/view-exam";
		}
		
		return "";
	}
	
	@RequestMapping(value="/subject")
	public String getSubjectsTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/subjects";
		}
		if(userlogin.getRole().equals("student")) {
			return "student/subjectList";
		}
		
		return "";
	}
	
	@RequestMapping(value="/subject/{id}")
	public String getSubjectTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/subject";
		}
		if(userlogin.getRole().equals("student")) {
			return "student/subject";
		}
		
		return "";
	}
	
	@RequestMapping(value="/create-exams")
	public String getCreateExamTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			//return "teacher/create-exams";
			return "teacher/tuuvt/crRootExams";
		}
		
		return "";
	}
	
	@RequestMapping(value="/Rootexams")
	public String getAllExamOfSubject(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			//return "teacher/create-exams";
			return "teacher/tuuvt/storeViewExam";
		}
		
		return "";
	}
	
	@RequestMapping(value="/class/{classId}/exams/{id}")
	public String getTestTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("student")) {
			return "student/test";
		}

		return "";
	}
	
	@RequestMapping(value="/exams/{id}")
	public String getExamsTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/exams";
		}

		return "";
	}
	
	@RequestMapping(value="/Rootexams/{id}/edit")
	public String getRootExamsTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/tuuvt/RootExams";
		}

		return "";
	}
	
	/*@RequestMapping(value="/Rootexams/{id}")
	public String getRootExamsTemplateEdit(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/tuuvt/RootExams";
		}

		return "";
	}*/
	
	@RequestMapping(value="/exams/{examsid}/exam/{examId}/edit")
	public String getExamTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/exam";
		}
		return "";
	}
	
	@RequestMapping(value="/class")
	public String getClassListTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/classList";
		}
		
		if(userlogin.getRole().equals("student")) {
			return "student/classList";
		}

		return "";
	}
	
	@RequestMapping(value="/class/{classId}")
	public String getClassTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/class";
		}
		
		if(userlogin.getRole().equals("student")) {
			return "student/class";
		}

		return "";
	}
	
	@RequestMapping(value="/create-question")
	public String getQuestionTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/create-question";
		}

		return "";
	}

	@RequestMapping(value="/edit-question")
	public String editQuestion(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);

		if(userlogin.getRole().equals("teacher")) {
			return "teacher/edit-question";
		}

		return "";
	}

	@RequestMapping(value = "/statistical",  method = RequestMethod.GET)
	public String getStatistical(Model model) {
		return "statistical";
	}

	@RequestMapping(value = "/evaluation",  method = RequestMethod.GET)
	public String getEvaluation(Model model) {
		return "evaluation";
	}
	
	@RequestMapping(value="/Rootexams/{id}/view")
	public String getViewRootExamTemplate(Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		if(userlogin.getRole().equals("teacher")) {
			return "teacher/tuuvt/viewExam";
		}
		
		return "";
	}
	
	@RequestMapping(value="/Rootexams/{id}/view/{idexam}/ExamPDF")
	public ModelAndView getExamExportPDF(@PathVariable("id") long id, @PathVariable("idexam") long idExam){
		
		ListExam listExam = null;
		listExam = examService.getRootExamPDF(id, idExam);
		
		System.out.println("List Exam : " +listExam.getExamsId() );
		ExamsInfo examInfo = null;
	    examInfo = examsService.getExams(listExam.getExamsId());
	    
	    Teacher getTeacher = teacherService.teacher(examInfo.exams.getTeacherid());
	    Exam getExam = examService.getExamFromId(idExam);
	    Subject getSubject = subjectService.getSubjectOfExam123(id);
	    ExportExam exportExam = new ExportExam();  
	    
	    System.out.println("getFaculty " + getTeacher);
	    exportExam.setFaulty(getTeacher.getFaculty());
	    exportExam.setTeacherName(getTeacher.getName());
	    exportExam.setCode(getExam.getExamcode());
	    exportExam.setExamName(examInfo.exams.getName());
	    exportExam.setSubjectName(getSubject.getName());
	    Exams_Info examInfo2 = examService.getExamInfo(id);
	    exportExam.setTime(examInfo2.getTimes());
	    exportExam.listExam = listExam;
	    exportExam.setNoteExam(examInfo2.getNoteexam());
		
	        // return a view which will be resolved by an excel view resolver
	        return new ModelAndView("pdfView", "exportExam", exportExam);
		//return null;
		
	}
	
	@RequestMapping(value="/Rootexams/{id}/view/{idexam}/AnswerPDF")
	public ModelAndView getAnswerExportPDF(@PathVariable("id") long id, @PathVariable("idexam") long idExam){
		
		ListExam listExam2 = null;
		listExam2 = examService.getRootExamPDF(id, idExam);
		
		System.out.println("List Exam : " +listExam2.getExamsId());
	    ExamsInfo examInfo = examsService.getExams(listExam2.getExamsId());
	    //System.out.println("AAAAAAAA"+examInfo.exams.getSubjectid());
	    Teacher getTeacher = teacherService.teacher(examInfo.exams.getTeacherid());
	    Exam getExam = examService.getExamFromId(idExam);
	    Subject getSubject = subjectService.getSubjectOfExam123(id);
	    ExportExam exportExam2 = new ExportExam();  
	    
	    System.out.println("getFaculty " + getTeacher);
	    exportExam2.setFaulty(getTeacher.getFaculty());
	    exportExam2.setTeacherName(getTeacher.getName());
	    exportExam2.setCode(getExam.getExamcode());
	    exportExam2.setExamName(examInfo.exams.getName());
	    exportExam2.setSubjectName(getSubject.getName());
	    Exams_Info examInfo2 = examService.getExamInfo(id);
	    exportExam2.setTime(examInfo2.getTimes());
	    exportExam2.listExam = listExam2;
	    exportExam2.setNoteExam(examInfo2.getNoteexam());
	        return new ModelAndView("pdfView", "exportExam2", exportExam2);
		//return null;
		
	}
}
