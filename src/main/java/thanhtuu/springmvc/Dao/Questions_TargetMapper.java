package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Questions_TargetKey;

public interface Questions_TargetMapper {
    int deleteByPrimaryKey(Questions_TargetKey key);
    
    int insert(Questions_TargetKey record);
    
    int insertSelective(Questions_TargetKey record);
    
    List<Questions_TargetKey> getBySubjectIdChapterIdTargetId(@Param("subjectId") long subjectId, @Param("chapterId") long chapterId, @Param("targetId") long targetId);

    List<Questions_TargetKey> getByQuestionIdList(@Param("list") List<Integer> list);
}