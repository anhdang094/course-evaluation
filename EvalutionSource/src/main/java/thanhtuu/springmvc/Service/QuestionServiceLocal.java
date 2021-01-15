package thanhtuu.springmvc.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Question;
import thanhtuu.springmvc.Domain.QuestionWithBLOBs;

public interface QuestionServiceLocal {
	
 Question getByQuestionId(long id);
 
 void deleteQuestionId(long questionid);
 
 void updateByPrimaryKeyWithBLOBs(QuestionWithBLOBs questionid);
 
 List<Question> getQuestionList();
 
 List<QuestionWithBLOBs> getQuestionWithBLOBsList();
 
 List<Question> getqueryExamQuestion (int ExamID, int chapterSubjects);
 
 List<Question> getquerySubjectQuestion(String subjects);
 
 List<Question> getqueryLevelQuestion (String LevelQ);

 void insert(QuestionWithBLOBs questionW);
 
 List<Question> getSubjectsList();
 
 
}
