package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Classes;

public interface ClassesMapper {
    int deleteByPrimaryKey(Long id);
   
    int insert(Classes record);
  
    int insertSelective(Classes record);
   
    Classes selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Classes record);
    
    int updateByPrimaryKey(Classes record);
    
    List<Classes> getClassInList(@Param("list")List<Integer> list);
    
    List<Classes> getClassOfTeacher(@Param("userId")long userId);
    
    List<Classes> getBySubjectId(@Param("teacherId")long teacherId, @Param("subjectId")long subjectId);
}