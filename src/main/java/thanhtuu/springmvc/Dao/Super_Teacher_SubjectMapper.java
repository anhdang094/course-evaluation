package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Super_Teacher_Subject;
import thanhtuu.springmvc.Domain.Super_Teacher_SubjectKey;

public interface Super_Teacher_SubjectMapper {
    int deleteByPrimaryKey(Super_Teacher_SubjectKey key);
    
    int insert(Super_Teacher_Subject record);
    
    int insertSelective(Super_Teacher_Subject record);
    
    Super_Teacher_Subject selectByPrimaryKey(Super_Teacher_SubjectKey key);
    
    int updateByPrimaryKeySelective(Super_Teacher_Subject record);
    
    int updateByPrimaryKey(Super_Teacher_Subject record);
    
    List<Super_Teacher_Subject> getByTeacherId(@Param("teacherId") long teacherId);
    
    List<Super_Teacher_Subject> getBySubjectId(@Param("subjectId") long subjectId);
    
    List<Super_Teacher_Subject> isSuperTeacherOfSubject(@Param("teacherId") long teacherId, @Param("subjectId") long subjectId);
}