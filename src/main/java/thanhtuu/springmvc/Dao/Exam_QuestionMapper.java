package thanhtuu.springmvc.Dao;

import java.awt.List;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Exam_Question;

public interface Exam_QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    int insert(Exam_Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    int insertSelective(Exam_Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    Exam_Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    int updateByPrimaryKeySelective(Exam_Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_question
     *
     * @mbggenerated Sun Mar 19 22:01:59 ICT 2017
     */
    int updateByPrimaryKey(Exam_Question record);
    
    ArrayList<Exam_Question> getAllQuestionByIdExamandIdExams(@Param("examId") long examId, @Param("examsId")long examsId);
    
    ArrayList <Exam_Question> getAllQuestionIDByExamQuestionTable(@Param("questionblockId") long questionblockId, @Param("examsId") long examsId, @Param("examId") long examId);
    
    Exam_Question findQuestionIDByExamQuestionTable(
    		@Param("questionblockId") long questionblockId,
    		@Param("examsId") long examsId,
    		@Param("examId") long examId,
    		@Param("questionId") long questionId);

    
    ArrayList <Exam_Question> getAllQuestionIDByExamAndIDExamsAndIdBlock(@Param("examId") long examId, @Param("examsId")long examsId, @Param("questionblockId") long questionblockId);


}