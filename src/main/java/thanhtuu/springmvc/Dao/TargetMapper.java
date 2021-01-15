package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Domain.TargetKey;

public interface TargetMapper {
    int deleteByPrimaryKey(TargetKey key);
    
    int insert(Target record);
    
    int insertSelective(Target record);
    
    Target selectByPrimaryKey(TargetKey key);
    
    int updateByPrimaryKeySelective(Target record);
    
    int updateByPrimaryKeyWithBLOBs(Target record);
    
    Target getIdTarget(@Param("Id") int Id);
    Target getIdFromName(@Param("name") String name);
    
    List<Target> getBySubjectId(@Param("subjectId") long subjectId);
    
    List<Target> getBySubjectIdChapterId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId);
}