package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.ChapterKey;

public interface ChapterMapper {
    int deleteByPrimaryKey(ChapterKey key);
    
    int insert(Chapter record);
    
    int insertSelective(Chapter record);
    
    Chapter selectByPrimaryKey(ChapterKey key);
    
    int updateByPrimaryKeySelective(Chapter record);
    
    int updateByPrimaryKey(Chapter record);
    
    List<Chapter> getAllChapterBySubjectId(@Param("subjectId") int subjectId);
}