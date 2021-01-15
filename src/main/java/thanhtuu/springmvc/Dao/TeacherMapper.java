package thanhtuu.springmvc.Dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Teacher record);
    
    int insertSelective(Teacher record);
    
    Teacher selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Teacher record);
    
    int updateByPrimaryKey(Teacher record);
    
    List<Teacher> getAllTeacher();
    
    List<Teacher> getInList(@Param("list")List<Integer> list);
    
    Teacher getTeacherById(@Param("Id")long id);
}