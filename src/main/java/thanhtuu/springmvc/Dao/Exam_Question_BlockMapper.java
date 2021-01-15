package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;

public interface Exam_Question_BlockMapper {
    int deleteByPrigetBlockQuestionByExamIdmaryKey(Exam_Question_BlockKey key);
    
    int insert(Exam_Question_BlockKey record);
    
    int insertSelective(Exam_Question_BlockKey record);
    
    List<Exam_Question_BlockKey> getBlockQuestionByExamId(Exam_Question_BlockKey record);
    
    List<Exam_Question_BlockKey> getIdBlockQuestionOfRootExamId(@Param("examsid")long examsId);
    
    Exam_Question_BlockKey getIdExamOfRootExamsId(@Param("examsid")long examsId, @Param("questionblockid")long questionblockid);
    
    Exam_Question_BlockKey findExamBlockQuestionExist(@Param("examid") long examId, @Param("examsid")long examsId, @Param("questionblockid")long questionblockid);
    

}