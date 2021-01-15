package thanhtuu.springmvc.Dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import thanhtuu.springmvc.Domain.Classes_Exams;
import thanhtuu.springmvc.Domain.Classes_ExamsKey;

public interface Classes_ExamsMapper {
    int deleteByPrimaryKey(Classes_ExamsKey key);
    
    int insert(Classes_Exams record);
    
    int insertSelective(Classes_Exams record);
    
    Classes_Exams selectByPrimaryKey(Classes_ExamsKey key);
    
    int updateByPrimaryKeySelective(Classes_Exams record);
    
    int updateByPrimaryKey(Classes_Exams record);
    
    List<Classes_Exams> getClassExamsByExamsId(@Param("examsId") long examsId);
    
    List<Classes_Exams> getByClassId(@Param("classId") long classId);
    
    Long getExamsCountByClassId(@Param("classId") long classId);
}