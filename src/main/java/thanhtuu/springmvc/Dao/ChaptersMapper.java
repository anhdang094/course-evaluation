package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Chapters;
import thanhtuu.springmvc.Domain.ChaptersKey;

public interface ChaptersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    int deleteByPrimaryKey(ChaptersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    int insert(Chapters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    int insertSelective(Chapters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    Chapters selectByPrimaryKey(ChaptersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    int updateByPrimaryKeySelective(Chapters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapters
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    int updateByPrimaryKey(Chapters record);
    
    List<Chapters> getAllChapterBySubjectId(@Param("subjectId") Long subjectId);

    Chapters getIdFromName(@Param("name") String name, @Param("subjectId") Long subjectId,@Param("teacherId") Long teacherId);
}