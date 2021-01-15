package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Exams_Target;
import thanhtuu.springmvc.Domain.Exams_TargetKey;

public interface Exams_TargetMapper {
	int deleteByPrimaryKey(Exams_TargetKey key);
	   
    int insert(Exams_Target record);
    
    int insertSelective(Exams_Target record);
    
    Exams_Target selectByPrimaryKey(Exams_TargetKey key);
    
    int updateByPrimaryKeySelective(Exams_Target record);
    
    int updateByPrimaryKey(Exams_Target record);
    
    List<Exams_Target> getByExamsId(@Param("examsId")long examsId);
}
