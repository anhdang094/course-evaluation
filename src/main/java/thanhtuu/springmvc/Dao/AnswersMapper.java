package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.AnswersKey;

public interface AnswersMapper {
    int deleteByPrimaryKey(AnswersKey key);
    
    int insert(Answers record);
    
    int insertSelective(Answers record);
    
    Answers selectByPrimaryKey(AnswersKey key);
    
    int updateByPrimaryKeySelective(Answers record);
    
    int updateByPrimaryKeyWithBLOBs(Answers record);
    
    int updateByPrimaryKey(Answers record);
    
    List<Answers> getAnswerByQuestionIdList(@Param("list") List<Integer> list);
    
    Long getSoulutionAnswerCountByIdInList(@Param("list") List<Integer> list);
    
    Answers getAnswerByIDAnswer(@Param("id") long id); 
    
    Answers getMaxIdAnswer();
    
    List<Answers> answerListByQuestionId(@Param("questionId") long questionid);
    
}