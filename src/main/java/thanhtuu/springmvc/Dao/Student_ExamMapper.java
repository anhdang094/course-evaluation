package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Student_Exam;

public interface Student_ExamMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Student_Exam record);
    
    int insertSelective(Student_Exam record);
    
    Student_Exam selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Student_Exam record);
    
    int updateByPrimaryKey(Student_Exam record);
    
    Long getStudentExamsCountBySubjectId(@Param("subjectId") long subjectId, @Param("examsId") long examsId);
    
    Long getStudentExamsCountByClassId(@Param("classId") long classId, @Param("examsId") long examsId);
    
    Long getExamsCountOfStudentByClassId(@Param("classId") long classId, @Param("studentId") long studentId, @Param("examsId") long examsId);

    Long getExamsCountOfStudentBySubjectId(@Param("subjectId") long subjectId, @Param("studentId") long studentId, @Param("examsId") long examsId);

    List<Student_Exam> getResultExam(@Param("classId") long classId, @Param("studentId") long studentId, @Param("examsId") long examsId);
    
    List<Student_Exam> getBySubjectIdExamsId(@Param("subjectId") long subjectId, @Param("examsId") long examsId);
    
    List<Student_Exam> getBySubjectIdClassIdExamsId(@Param("classId") long classId, @Param("examsId") long examsId);
}