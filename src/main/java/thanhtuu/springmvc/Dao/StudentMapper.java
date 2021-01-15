package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Student record);
    
    int insertSelective(Student record);
    
    Student selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Student record);
    
    int updateByPrimaryKey(Student record);
    
    List<Student> getAll();
    
    List<Student> getByIdInList(@Param("list")List<Integer> list);
    
    long checkStudent(@Param("id") long id);
}