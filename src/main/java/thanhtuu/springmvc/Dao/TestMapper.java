package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import thanhtuu.springmvc.Domain.Test;
import thanhtuu.springmvc.Domain.TestKey;

public interface TestMapper {
    int deleteByPrimaryKey(TestKey key);
    
    int insert(Test record);
    
    int insertSelective(Test record);
    
    Test selectByPrimaryKey(TestKey key);
    
    int updateByPrimaryKeySelective(Test record);
    
    int updateByPrimaryKey(Test record);
    
    int insertList(@Param("list")List<Test> studentExamList);
}