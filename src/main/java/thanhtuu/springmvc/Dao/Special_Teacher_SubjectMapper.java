package thanhtuu.springmvc.Dao;

import thanhtuu.springmvc.Domain.Special_Teacher_Subject;

public interface Special_Teacher_SubjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    int deleteByPrimaryKey(Integer subjectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    int insert(Special_Teacher_Subject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    int insertSelective(Special_Teacher_Subject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    Special_Teacher_Subject selectByPrimaryKey(Integer subjectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    int updateByPrimaryKeySelective(Special_Teacher_Subject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table special_teacher_subject
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    int updateByPrimaryKey(Special_Teacher_Subject record);
}