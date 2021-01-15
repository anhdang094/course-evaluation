package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Exam;
import thanhtuu.springmvc.Domain.ExamKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Test;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.CheckServiceLocal;
import thanhtuu.springmvc.Service.ExamServiceLocal;
import thanhtuu.springmvc.Service.ExamsServiceLocal;
import thanhtuu.springmvc.Service.MyBatisService;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.UpdateExam;

@RestController
public class ExamController {
	@Autowired
	private ExamServiceLocal examService;
	@Autowired
	private ExamsServiceLocal examsService;
	@Autowired
	private UsersServiceLocal usersService;
	@Autowired
	private CheckServiceLocal checkService;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/fetchExamsByExamsId/{examsId}", method = RequestMethod.GET)
	public Exams fetchExamsByExamsId(@PathVariable("examsId") int examsId) {
		return examService.fetchExamsByExamsId(examsId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getExamByExamsId/{examsId}", method = RequestMethod.GET)
	public List<Exam> getExamByExamsId(@PathVariable("examsId") int examsId) throws InterruptedException {
		return examService.getExamByExamsId(examsId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getBlockQuestionByExamId/{examId}", method = RequestMethod.GET)
	public List<Question_BlocksWithBLOBs> getBlockQuestionByExamId(@PathVariable("examId") long examId) {
		return examService.getBlockQuestionByExamId(examId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getBlockQuestionByExamId1/{examId}", method = RequestMethod.GET)
	public List<BlockQuestion> getBlockQuestionByExamId1(@PathVariable("examId") long examId) {
		System.out.println("Controller");
		return examService.getBlockQuestionByExamId1(examId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getQuestionByBlockQuestionIdList", method = RequestMethod.POST)
	public List<Questions> getQuestionByBlockQuestionIdList(@RequestBody List<Integer> blockQuestionIdList) {
		return examService.getQuestionByBlockQuestionIdList(blockQuestionIdList);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getAnswerByQuestionIdList", method = RequestMethod.POST)
	public List<Answers> getAnswerByQuestionIdList(@RequestBody List<Integer> questionIdList) {
		return examService.getAnswerByQuestionIdList(questionIdList);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getExam/{classId}/{examsId}", method = RequestMethod.GET)
	public Student_Exam getExam(@PathVariable("classId") long classId, @PathVariable("examsId") long examsId, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		Student_Exam studentExam = new Student_Exam();
		
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
	    SqlSession sqlSession = sqlMapper.openSession(true);
	    ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
	    
	    Exams exams = examsMapper.selectByPrimaryKey(examsId);
	    if (exams.equals(null)) return studentExam;
		
		if(userlogin.getRole().equals("student")) {
			long times = examsService.getExamsCountOfStudentByClassId(classId, userlogin.getId(), examsId);
			if (times >= exams.getTimes()) return studentExam;
			studentExam = examService.getBlockQuestionByExamsId(userlogin.getId(), examsId, checkService.checkStudentExam(userlogin.getId(), examsId));
		}
		
		return studentExam;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/submitTest", method = RequestMethod.POST)
	public long submitTest(@RequestBody List<Test> testList, Principal principal) {
		return examService.submitTest(testList);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getBlockQuestionByKeyWord/{subjectId}/{chapterId}/{keyWord}", method = RequestMethod.GET)
	public List<BlockQuestion> getBlockQuestionByKeyWord(@PathVariable("subjectId") long subjectId, @PathVariable("chapterId") long chapterId, @PathVariable("keyWord") String keyWord) {
		return examService.getBlockQuestionByKeyWord(subjectId, chapterId, keyWord);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getBlockQuestionByKeyWord/{subjectId}/{chapterId}/", method = RequestMethod.GET)
	public List<BlockQuestion> getBlockQuestionByKeyWord(@PathVariable("subjectId") long subjectId, @PathVariable("chapterId") long chapterId) {
		return examService.getBlockQuestionByKeyWord(subjectId, chapterId, "");
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/updateExam", method = RequestMethod.POST)
	public int updateExam(@RequestBody UpdateExam updateExam) {
		return examService.updateExam(updateExam);
	}
}
