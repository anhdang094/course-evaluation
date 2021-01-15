package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Student_Subject;
import thanhtuu.springmvc.Domain.Student_SubjectKey;

public interface Student_SubjectMapper {
    int deleteByPrimaryKey(Student_SubjectKey key);
    
    int insert(Student_Subject record);
    
    int insertSelective(Student_Subject record);
    
    Student_Subject selectByPrimaryKey(Student_SubjectKey key);
    
    int updateByPrimaryKeySelective(Student_Subject record);
    
    int updateByPrimaryKey(Student_Subject record);
    
    List<Student_Subject> getByStudentId(@Param("studentId") long studentId);
    
    List<Student_Subject> getBySubjectId(@Param("subjectId") long subjectId);
}