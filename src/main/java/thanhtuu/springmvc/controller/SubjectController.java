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

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Classes;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Student;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Student_Subject;
import thanhtuu.springmvc.Domain.Subject;
import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.SubjectServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Service.ClassServiceLocal;
import thanhtuu.springmvc.Service.SubjectsServiceLocal;
import thanhtuu.springmvc.Service.QuestionServiceLocal;
import thanhtuu.springmvc.Service.ExamsServiceLocal;
import thanhtuu.springmvc.Temporary.CSVStudent;
import thanhtuu.springmvc.Temporary.ChapterList;
import thanhtuu.springmvc.Temporary.ClassesList;
import thanhtuu.springmvc.Temporary.ExamsList;
import thanhtuu.springmvc.Temporary.StudentExams;
import thanhtuu.springmvc.Temporary.TargetList;


@RestController
public class SubjectController {
	@Autowired
	private SubjectServiceLocal subjectService;
	@Autowired
	private UsersServiceLocal usersService;
	@Autowired
	private ClassServiceLocal classService;
	@Autowired
	private SubjectsServiceLocal subjectsService;
	@Autowired
	private QuestionServiceLocal questionService;
	@Autowired
	private ExamsServiceLocal examsService;
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/removeTargetFromSubject", method = RequestMethod.POST)
	public int removeTargetFromSubject(@RequestBody Target target, Principal principal) {
		System.out.println("Target : " + target.getId());
		return subjectService.removeTargetFromSubject(target);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/removeChapterFromSubject", method = RequestMethod.POST)
	public int removeChapterFromSubject(@RequestBody Chapter chapter, Principal principal) {
		return subjectService.removeChapterFromSubject(chapter);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createChapter", method = RequestMethod.POST)
	public int createChapter(@RequestBody Chapter chapter, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		chapter.setTeacherid(userlogin.getId().intValue());

		return subjectService.createChapter(chapter);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/isSuperTeacherOfSubject/{subjectId}", method = RequestMethod.GET)
	public boolean isSuperTeacherOfSubject(@PathVariable("subjectId") long subjectId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		return subjectService.isSuperTeacherOfSubject(userlogin.getId(), subjectId);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/assignStudentToSubject", method = RequestMethod.POST)
	public int assignStudentToSubject(@RequestBody Student_Subject studentSubject) {
		return subjectService.assignStudentToSubject(studentSubject);
	}
	
	
	/************ Fetch function **************/
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllChapterBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<ChapterList> getAllChapterBySubjectId(@PathVariable("subjectId") int subjectId) {
		List<Chapter> chapterList = subjectService.getAllChapterBySubjectId(subjectId);
		List<Integer> chapterIdList = new ArrayList<Integer>();
		for (int i = 0; i < chapterList.size(); i++) {
			chapterIdList.add(chapterList.get(i).getId().intValue());
		}
		ChapterList chapteresList = new ChapterList();
		chapteresList.chapterList = chapterList;
		chapteresList.questionL1CountList = questionService.getQuestionL1CountByChapterIdList(chapterIdList);
		chapteresList.questionL2CountList = questionService.getQuestionL2CountByChapterIdList(chapterIdList);
		chapteresList.questionL3CountList = questionService.getQuestionL3CountByChapterIdList(chapterIdList);
		chapteresList.questionL4CountList = questionService.getQuestionL4CountByChapterIdList(chapterIdList);
		chapteresList.questionL5CountList = questionService.getQuestionL5CountByChapterIdList(chapterIdList);
		return new ResponseEntity<ChapterList>(chapteresList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllTargetBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<List<Target>> getAllTargetBySubjectId(@PathVariable("subjectId") int subjectId) {
		List<Target> targetList = subjectService.getAllTargetBySubjectId(subjectId);
		return new ResponseEntity<List<Target>>(targetList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllTargetBySubjectIdChapterId/{subjectId}/{chapterId}", method = RequestMethod.GET)
	public ResponseEntity<List<Target>> getAllTargetBySubjectIdChapterId(@PathVariable("subjectId") int subjectId, @PathVariable("chapterId") int chapterId) {
		List<Target> targetList = subjectService.getAllTargetBySubjectIdChapterId(subjectId, chapterId);
		return new ResponseEntity<List<Target>>(targetList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllTargetBySubjectIdChapterId1/{subjectId}/{chapterId}", method = RequestMethod.GET)
	public ResponseEntity<List<TargetList>> getAllTargetBySubjectIdChapterId1(@PathVariable("subjectId") int subjectId, @PathVariable("chapterId") int chapterId) {
		List<TargetList> targetList = subjectService.getAllTargetBySubjectIdChapterId1(subjectId, chapterId);
		System.out.println("controller Subject");
		return new ResponseEntity<List<TargetList>>(targetList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllExamsBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<ExamsList> getAllExamsBySubjectId(@PathVariable("subjectId") int subjectId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		List<Exams> examsList = subjectService.getAllExamsBySubjectId(subjectId);
		List<Integer> examsIdList = new ArrayList<Integer>();
		List<StudentExams> studentExamList = new ArrayList<StudentExams>();
		for (int i = 0; i < examsList.size(); i++) {
			StudentExams studentExam = new StudentExams();
			studentExam.exams = examsList.get(i);
			studentExam.times = examsService.getExamsCountOfStudentBySubjectId(subjectId, userlogin.getId(), studentExam.exams.getId());
			examsIdList.add(examsList.get(i).getId().intValue());
			studentExamList.add(studentExam);
		}
		ExamsList examsesList = new ExamsList();
		examsesList.examsList = studentExamList;
		examsesList.studentCount = examsService.getStudentExamsCountBySubjectId(subjectId, examsIdList);
		return new ResponseEntity<ExamsList>(examsesList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllStudentBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudentBySubjectId(@PathVariable("subjectId") int subjectId) {
		List<Student> studentList = subjectService.getAllStudentBySubjectId(subjectId);
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllClassBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<ClassesList> getAllClassBySubjectId(@PathVariable("subjectId") int subjectId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		
		List<Classes> classList = subjectService.getAllClassBySubjectId(userlogin.getId(), subjectId);
		List<Integer> classIdList = new ArrayList<Integer>(); 
		for (int i = 0; i < classList.size(); i++) {
			classIdList.add(classList.get(i).getId().intValue());
		}
		List<Integer> studentCountList = classService.getStudentCountByClassIdList(classIdList);
		List<Integer> examsCountList = classService.getExamsCountByClassIdList(classIdList);
		ClassesList classesList = new ClassesList();
		classesList.classesList = classList;
		classesList.studentCountList = studentCountList;
		classesList.examsCountList = examsCountList;
		return new ResponseEntity<ClassesList>(classesList, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getSubjectBySubjectId/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<Subject> getSubjectBySubjectId(@PathVariable("subjectId") int subjectId) {
		List<Integer> subjectIdList = new ArrayList<Integer>();
		subjectIdList.add(subjectId);
		List<Subject> subjectList = subjectsService.getSubjectInList(subjectIdList);
		Subject subject = new Subject();
		if (subjectList.size() > 0) {
			subject = subjectList.get(0);
		}
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/createTarget", method = RequestMethod.POST)
	public int createTarget(@RequestBody Target target) {
		return subjectService.createTarget(target);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/assign", method = RequestMethod.POST)
	public int assign(@RequestBody Classes_Exams classExams) {
		return subjectService.assign(classExams);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fileStudentToSubject", method = RequestMethod.POST)
	public int assign(@RequestBody CSVStudent csvStudent) {
		return subjectService.fileStudentToSubject(csvStudent.subjectId, csvStudent.content);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/removeStudentFromSubject", method = RequestMethod.POST)
	public int removeStudentFromSubject(@RequestBody Student_Subject studentSubject) {
		return subjectService.removeStudentFromSubject(studentSubject);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/removeStudentFromClass", method = RequestMethod.POST)
	public int removeStudentFromClass(@RequestBody Class_Member classMember) {
		return subjectService.removeStudentFromClass(classMember);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/closeClass", method = RequestMethod.POST)
	public int closeClass(@RequestBody Classes classes) {
		return subjectService.closeClass(classes);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchExamsResult/{subjectId}/{examsId}", method = RequestMethod.GET)
	public List<Student_Exam> fetchExamsResult(@PathVariable("subjectId") int subjectId, @PathVariable("examsId") int examsId) {
		return subjectService.fetchExamsResult(subjectId, examsId);
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchExamsResultClass/{classId}/{examsId}", method = RequestMethod.GET)
	public List<Student_Exam> fetchExamsResultClass(@PathVariable("classId") int classId, @PathVariable("examsId") int examsId) {
		return subjectService.fetchExamsResultClass(classId, examsId);
	}
}