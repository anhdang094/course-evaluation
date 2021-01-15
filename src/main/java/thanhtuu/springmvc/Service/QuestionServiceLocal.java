package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.LatexQuestion;

public interface QuestionServiceLocal {

    List<Integer> saveBlockQuestionList(List<BlockQuestion> blockQuestionList, int teacherId);
    
    List<Integer> getQuestionFromLatex(LatexQuestion latextQuestion, int teacherId);
    
    List<Question_BlocksWithBLOBs> getQuestionBlockInList(List<Integer> list);
    
    List<Integer> getQuestionL1CountByChapterIdList(List<Integer> list);

    List<Integer> getQuestionL2CountByChapterIdList(List<Integer> list);
    
    List<Integer> getQuestionL3CountByChapterIdList(List<Integer> list);
    
    List<Integer> getQuestionL4CountByChapterIdList(List<Integer> list);
    
    List<Integer> getQuestionL5CountByChapterIdList(List<Integer> list);
    
    List<BlockQuestion> getBLockQuestionOfRootExamId(long ExamId);
    
    List<Questions> getAllQuestionOfBlockQuestion(List<Exam_Question_BlockKey> blockQuestionId);
}
