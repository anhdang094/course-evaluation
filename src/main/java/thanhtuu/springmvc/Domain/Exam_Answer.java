package thanhtuu.springmvc.Domain;

public class Exam_Answer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.Id
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.questionId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Integer questionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.answerId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Integer answerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.checkSolution
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Boolean checksolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.questionBlockId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Integer questionblockid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_answer.examId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    private Integer examid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.Id
     *
     * @return the value of exam_answer.Id
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.Id
     *
     * @param id the value for exam_answer.Id
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.questionId
     *
     * @return the value of exam_answer.questionId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.questionId
     *
     * @param questionid the value for exam_answer.questionId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.answerId
     *
     * @return the value of exam_answer.answerId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Integer getAnswerid() {
        return answerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.answerId
     *
     * @param answerid the value for exam_answer.answerId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setAnswerid(Integer answerid) {
        this.answerid = answerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.checkSolution
     *
     * @return the value of exam_answer.checkSolution
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Boolean getChecksolution() {
        return checksolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.checkSolution
     *
     * @param checksolution the value for exam_answer.checkSolution
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setChecksolution(Boolean checksolution) {
        this.checksolution = checksolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.questionBlockId
     *
     * @return the value of exam_answer.questionBlockId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Integer getQuestionblockid() {
        return questionblockid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.questionBlockId
     *
     * @param questionblockid the value for exam_answer.questionBlockId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setQuestionblockid(Integer questionblockid) {
        this.questionblockid = questionblockid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_answer.examId
     *
     * @return the value of exam_answer.examId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public Integer getExamid() {
        return examid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_answer.examId
     *
     * @param examid the value for exam_answer.examId
     *
     * @mbggenerated Wed Mar 22 13:51:03 ICT 2017
     */
    public void setExamid(Integer examid) {
        this.examid = examid;
    }
}