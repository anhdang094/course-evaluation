package thanhtuu.springmvc.Domain;

public class Special_Teacher_Subject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column special_teacher_subject.subjectId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    private Integer subjectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column special_teacher_subject.teacherId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    private Long teacherid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column special_teacher_subject.subjectId
     *
     * @return the value of special_teacher_subject.subjectId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column special_teacher_subject.subjectId
     *
     * @param subjectid the value for special_teacher_subject.subjectId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column special_teacher_subject.teacherId
     *
     * @return the value of special_teacher_subject.teacherId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    public Long getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column special_teacher_subject.teacherId
     *
     * @param teacherid the value for special_teacher_subject.teacherId
     *
     * @mbggenerated Thu Sep 01 21:24:12 ICT 2016
     */
    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }
}