package thanhtuu.springmvc.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.QuestionsKey;

public interface QuestionsMapper {
    int deleteByPrimaryKey(QuestionsKey key);
   
    int insert(Questions record);
    
    int insertSelective(Questions record);
    
    Questions selectByPrimaryKey(QuestionsKey key);
    Questions getQuestionID(@Param("id")long id);
    
    int updateByPrimaryKeySelective(Questions record);
    
    int updateByPrimaryKeyWithBLOBs(Questions record);
    
    int updateByPrimaryKey(Questions record);
    
    Long checkMaxIdQuestion();
    
    List<Questions> getQuestionByBlockQuestionIdList(@Param("list") List<Integer> list);
    
    List<Questions> getQuestionOfBlockQuestionByRootExam(@Param("list") List<Exam_Question_BlockKey> blockQuestionId);
    
    Long getQuestionL1CountByChapterId(@Param("chapterId") long chapterId);
    
    Long getQuestionL2CountByChapterId(@Param("chapterId") long chapterId);
    
    Long getQuestionL3CountByChapterId(@Param("chapterId") long chapterId);
    
    Long getQuestionL4CountByChapterId(@Param("chapterId") long chapterId);
    
    Long getQuestionL5CountByChapterId(@Param("chapterId") long chapterId);
    
    List <Questions> getQuestionIDByBlockQuestion(@Param("questionBlockId") long questionBlockId);
    
    List<Questions> getBySubjectIdChapterIdLevel(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId, @Param("level") long level);
    
    List<Questions> getL1BySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
    
    List<Questions> getL2BySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
    
    List<Questions> getL3BySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
    
    List<Questions> getL4BySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
    
    List<Questions> getL5BySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
    
    //Questions getListQuestionId(@Param("list") List<Integer> listId);
    
    
    
}