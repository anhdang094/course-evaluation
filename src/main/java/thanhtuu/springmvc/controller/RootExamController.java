package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.AdminService;
import thanhtuu.springmvc.Service.ExamService;
import thanhtuu.springmvc.Service.ExamsService;
import thanhtuu.springmvc.Service.QuestionService;
import thanhtuu.springmvc.Service.SubjectService;
import thanhtuu.springmvc.Service.UsersService;
import thanhtuu.springmvc.Service.ViewStoresExamService;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.Exam.ExamCount;
import thanhtuu.springmvc.Temporary.Exam.ListExam;
import thanhtuu.springmvc.Temporary.Exam.RootBlocksQuestion;
import thanhtuu.springmvc.Temporary.Exam.StoreViewExam;
import thanhtuu.springmvc.Temporary.Exam.UpdateRootExam;
import thanhtuu.springmvc.Domain.Subject;


@RestController
class RootExamController {
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamsService examsServive;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ViewStoresExamService viewStoreExam;
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private AdminService adminService;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getRootExamByExamId/{examId}", method = RequestMethod.GET)
	public Exams fetchExamsByExamsId(@PathVariable("examId") int examsId) {
		return examsServive.fetchRootExamByExamId(examsId);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getRootExamByExam/{examId}", method = RequestMethod.GET)
	public List<RootBlocksQuestion> fetchRootExamsByExamsId(@PathVariable("examId") long examsId) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>PASSED RootExam");
	      return examService.getRootBlockQuestion(examsId);
		
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getListBockRootExamId/{rootexamId}", method = RequestMethod.GET)
	public List<BlockQuestion> fetchListQuestionBlockOfRootExamId(@PathVariable("rootexamId") int rootexamId){
		return questionService.getBLockQuestionOfRootExamId(rootexamId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getListQuestionRootExamId", method = RequestMethod.GET)
	public List<Questions> fetchListQuestionOfBlock(@RequestBody List<Exam_Question_BlockKey> blockQuestion){
		return questionService.getAllQuestionOfBlockQuestion(blockQuestion);
	}
	
	
	/*@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getRootBlockQuestionByKeyWord/{subjectId}/{chapterId}/{searchText}", method = RequestMethod.GET)
	public List<RootBlocksQuestion> getRootBlockQuestionByKeyWord(@PathVariable("subjectId") long subjectId, @PathVariable("chapterId") long chapterId, @PathVariable("searchText") String searchText) {
		System.out.println("subject : " + subjectId + " ChapterId : " + chapterId + "SearchText : "+ searchText);
		return examService.getRootBlockQuestionBySearchText(subjectId, chapterId, searchText);
	}*/
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getRootBlockQuestionByKeyWord/{subjectId}/{chapterId}", method = RequestMethod.GET)
	public List<RootBlocksQuestion> getRootBlockQuestionByKeyWord2(@PathVariable("subjectId") long subjectId, @PathVariable("chapterId") long chapterId) {
		//System.out.println("subject : " + subjectId + " ChapterId : " + chapterId );
		return examService.getRootBlockQuestionBySearchText(subjectId, chapterId);
	}
   
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/updateRootExam", method = RequestMethod.POST)
	public void ViewrootExam(@RequestBody UpdateRootExam updateRootExam){
	//	System.out.println("==========Check Accept Modified Exam============="+updateRootExam.blockQuestionRemoveList.get(0).getQuestionblockid());
        examService.updateRootExam(updateRootExam);
        
        ExamCount examCount = new ExamCount();
        examCount.setId(updateRootExam.IdExams);
        examCount.setExamCount(updateRootExam.countExam);
        examCount.setCodeExam(updateRootExam.getCodeExam());
        examsServive.UpdateRootExamCount(examCount);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getSubjectOfExam123/{examsId}", method = RequestMethod.GET)
	public Subject fetchSubjectOfExam123(@PathVariable("examsId") long examsId) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>PASSED RootExam");
	      return subjectService.getSubjectOfExam123(examsId);
	}
	
	/*@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/pushExamInfoByExam", method = RequestMethod.POST)
	public void PushRootExam(@RequestBody RootExamTemp examsInfo) throws InterruptedException{
		System.out.println("==========Check Accept InfoByExam=============" + examsInfo);
        examsServive.insertExamInfoByExam(examsInfo);
	}*/
	
	/*@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/updateRootExamCount", method = RequestMethod.POST)
	public void UpdateRootExamCount(@RequestBody ExamCount examsCount) throws InterruptedException{
		System.out.println("==========Check Accept InfoByExam=============" + examsCount.examCount);
		TimeUnit.SECONDS.sleep(3);
        examsServive.UpdateRootExamCount(examsCount);
	}*/

	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllExam123/{examsId}", method = RequestMethod.GET)
	public List<ListExam> getAllExam(@PathVariable("examsId")long examsId) throws InterruptedException{ 
		return examService.getAllExam(examsId);
		
	}
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAllExamOfSubjectOfTeacher", method = RequestMethod.GET)
	public List<StoreViewExam> getAllExamOfSubject(Principal principal) {
		//System.out.println("==========getAllExamOfSubjectOfTeacher=============" +examsId);
String email = principal.getName();
		
		Users userlogin = usersService.getIdByEmail(email);
		
		return viewStoreExam.viewAllBySubject(userlogin.getId().intValue());
		//return null;
		
	}
	
/*	public ResponseEntity<List<StoreViewExam>> getAllExamOfSubject(Principal principal) {
		String email = principal.getName();
		
		Users userlogin = usersService.getIdByEmail(email);
		
		List<StoreViewExam> storeViewExam = new ArrayList<StoreViewExam>();
		if(userlogin.getRole().equals("teacher")) {
			storeViewExam = viewStoreExam.viewAllBySubject(userlogin.getId().intValue());
		}	
		
		return new ResponseEntity<List<StoreViewExam>>(storeViewExam, HttpStatus.OK);
	}*/
	
	
}