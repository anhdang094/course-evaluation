package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Exam;
import thanhtuu.springmvc.Domain.ExamKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Test;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.UpdateExam;
import thanhtuu.springmvc.Temporary.Exam.ListExam;
import thanhtuu.springmvc.Temporary.Exam.RootBlocksQuestion;
import thanhtuu.springmvc.Temporary.Exam.UpdateRootExam;

public interface ExamServiceLocal {
	List<Exam> getExamByExamsId(int examsId);
	
	List<Question_BlocksWithBLOBs> getBlockQuestionByExamId(long examId);
	
	List<BlockQuestion> getBlockQuestionByExamId1(long examId);
	
	List<RootBlocksQuestion> getRootBlockQuestion(long examId);
	
	List<Questions> getQuestionByBlockQuestionIdList(List<Integer> blockQuestionIdList);
	
	List<Answers> getAnswerByQuestionIdList(List<Integer> questionIdList);
	
	List<BlockQuestion> getBlockQuestionByKeyWord(long subjectId, long chapterId, String keyWord);
	
	Student_Exam getBlockQuestionByExamsId(long studentId, long examsId, Class_Member classMember);
	
	long checkExam(List<Test> blockQuestionIdList);
	
	long submitTest(List<Test> blockQuestionIdList);
	
	Exams fetchExamsByExamsId(long examsId);
	
	int updateExam (UpdateExam updateExam);
	
	int updateRootExam (UpdateRootExam updateRootExam);
	
	List<RootBlocksQuestion> getRootBlockQuestionBySearchText(long subjectId,long chapterId);

	ListExam getRootExamPDF(long examsid, long examid);
	
	List<ListExam> getAllExam(long examsid) throws InterruptedException;
	
	
	
}
