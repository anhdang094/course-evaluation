package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Subject record);
    
    int insertSelective(Subject record);
    
    Subject selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Subject record);
    
    int updateByPrimaryKey(Subject record);
    
    List<Subject> getAllSubject();
    
    List<Subject> getSubjectInList(@Param("list")List<Integer> list);
}