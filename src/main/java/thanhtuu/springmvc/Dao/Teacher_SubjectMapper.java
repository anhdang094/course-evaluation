package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import thanhtuu.springmvc.Domain.Teacher_Subject;
import thanhtuu.springmvc.Domain.Teacher_SubjectKey;

public interface Teacher_SubjectMapper {
    int deleteByPrimaryKey(Teacher_SubjectKey key);
    
    int insert(Teacher_Subject record);
    
    int insertSelective(Teacher_Subject record);
    
    Teacher_Subject selectByPrimaryKey(Teacher_SubjectKey key);
    
    int updateByPrimaryKeySelective(Teacher_Subject record);
    
    int updateByPrimaryKey(Teacher_Subject record);
    
    List<Teacher_Subject> getAllSubjectByTeacherId(@Param("teacherId") long teacherId);
    
    List<Teacher_Subject> getBySubjectId(@Param("subjectId") long subjectId);
}