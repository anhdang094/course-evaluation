package thanhtuu.springmvc.Domain;

public class Question_Blocks {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_blocks.id
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_blocks.subjectId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    private Integer subjectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_blocks.teacherId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    private Integer teacherid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_blocks.questionCount
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    private Integer questioncount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_blocks.id
     *
     * @return the value of question_blocks.id
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_blocks.id
     *
     * @param id the value for question_blocks.id
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_blocks.subjectId
     *
     * @return the value of question_blocks.subjectId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_blocks.subjectId
     *
     * @param subjectid the value for question_blocks.subjectId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_blocks.teacherId
     *
     * @return the value of question_blocks.teacherId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_blocks.teacherId
     *
     * @param teacherid the value for question_blocks.teacherId
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_blocks.questionCount
     *
     * @return the value of question_blocks.questionCount
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public Integer getQuestioncount() {
        return questioncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_blocks.questionCount
     *
     * @param questioncount the value for question_blocks.questionCount
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    public void setQuestioncount(Integer questioncount) {
        this.questioncount = questioncount;
    }
}