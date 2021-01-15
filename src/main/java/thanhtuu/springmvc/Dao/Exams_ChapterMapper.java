package thanhtuu.springmvc.Dao;

import java.util.List;

import thanhtuu.springmvc.Domain.Exams_Chapter;
import thanhtuu.springmvc.Domain.Exams_ChapterKey;

public interface Exams_ChapterMapper {
    int deleteByPrimaryKey(Exams_ChapterKey key);
   
    int insert(Exams_Chapter record);
    
    int insertSelective(Exams_Chapter record);
    
    Exams_Chapter selectByPrimaryKey(Exams_ChapterKey key);
    
    int updateByPrimaryKeySelective(Exams_Chapter record);
    
    int updateByPrimaryKey(Exams_Chapter record);
    
    List<Exams_Chapter> getExamsChapterByExamsId(Exams_Chapter record);
}