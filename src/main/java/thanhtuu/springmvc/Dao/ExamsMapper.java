package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Exams;

public interface ExamsMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Exams record);
    
    int insertSelective(Exams record);
    
    Exams selectByPrimaryKey(Long id);
    
    Exams selectByID(long id);
    
    int updateByPrimaryKeySelective(Exams record);
    
    int updateByPrimaryKey(Exams record);
    
    List<Exams>getByIdInList(@Param("list") List<Integer> list);
    
    List<Exams>getByIdInListForStudent(@Param("list") List<Integer> list);
    
    List<Exams>getByTeacherId(@Param("teacherId") long teacherId);
    
    List<Exams>getByStudentId(@Param("studentId") long studentId);
    
    List<Exams>getBySubjectId(@Param("subjectId") long subjectId);
    
    Exams getIDExamCurrent();
    
    Exams getRootExamId(@Param("id") long rootExams);
    
    List<Exams> getAllSubjectOfTeacher(@Param("teacherId") long teacherId, @Param("examType") String examType);
   
    List<Exams> getAllExamOfSubjectOfTeacher(@Param("teacherId") long teacherId,@Param("subjectId") long subjectId, @Param("examType") String examType);
    
    
    
}