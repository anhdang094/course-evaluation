package thanhtuu.springmvc.Dao;

import thanhtuu.springmvc.Domain.Questions_ChaptersKey;

public interface Questions_ChaptersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions_chapters
     *
     * @mbggenerated Thu Sep 01 21:23:37 ICT 2016
     */
    int deleteByPrimaryKey(Questions_ChaptersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions_chapters
     *
     * @mbggenerated Thu Sep 01 21:23:37 ICT 2016
     */
    int insert(Questions_ChaptersKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions_chapters
     *
     * @mbggenerated Thu Sep 01 21:23:37 ICT 2016
     */
    int insertSelective(Questions_ChaptersKey record);
}