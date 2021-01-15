package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Exams {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.id
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.teacherId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer teacherid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.subjectId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer subjectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.examCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer examcount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.questionCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer questioncount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.answerCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer answercount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.timeType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private String timetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.name
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.time
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.times
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer times;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.approverId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Integer approverid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.status
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.startTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Date starttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.endTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Date endtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.modifiedAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Date modifiedat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.createdAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private Date createdat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exams.examType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    private String examtype;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.id
     *
     * @return the value of exams.id
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.id
     *
     * @param id the value for exams.id
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.teacherId
     *
     * @return the value of exams.teacherId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.teacherId
     *
     * @param teacherid the value for exams.teacherId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.subjectId
     *
     * @return the value of exams.subjectId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.subjectId
     *
     * @param subjectid the value for exams.subjectId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.examCount
     *
     * @return the value of exams.examCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getExamcount() {
        return examcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.examCount
     *
     * @param examcount the value for exams.examCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setExamcount(Integer examcount) {
        this.examcount = examcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.questionCount
     *
     * @return the value of exams.questionCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getQuestioncount() {
        return questioncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.questionCount
     *
     * @param questioncount the value for exams.questionCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setQuestioncount(Integer questioncount) {
        this.questioncount = questioncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.answerCount
     *
     * @return the value of exams.answerCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getAnswercount() {
        return answercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.answerCount
     *
     * @param answercount the value for exams.answerCount
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setAnswercount(Integer answercount) {
        this.answercount = answercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.timeType
     *
     * @return the value of exams.timeType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public String getTimetype() {
        return timetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.timeType
     *
     * @param timetype the value for exams.timeType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setTimetype(String timetype) {
        this.timetype = timetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.name
     *
     * @return the value of exams.name
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.name
     *
     * @param name the value for exams.name
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.time
     *
     * @return the value of exams.time
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.time
     *
     * @param time the value for exams.time
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.times
     *
     * @return the value of exams.times
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.times
     *
     * @param times the value for exams.times
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.approverId
     *
     * @return the value of exams.approverId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Integer getApproverid() {
        return approverid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.approverId
     *
     * @param approverid the value for exams.approverId
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setApproverid(Integer approverid) {
        this.approverid = approverid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.status
     *
     * @return the value of exams.status
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.status
     *
     * @param status the value for exams.status
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.startTime
     *
     * @return the value of exams.startTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.startTime
     *
     * @param starttime the value for exams.startTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.endTime
     *
     * @return the value of exams.endTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.endTime
     *
     * @param endtime the value for exams.endTime
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.modifiedAt
     *
     * @return the value of exams.modifiedAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Date getModifiedat() {
        return modifiedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.modifiedAt
     *
     * @param modifiedat the value for exams.modifiedAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setModifiedat(Date modifiedat) {
        this.modifiedat = modifiedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.createdAt
     *
     * @return the value of exams.createdAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public Date getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.createdAt
     *
     * @param createdat the value for exams.createdAt
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exams.examType
     *
     * @return the value of exams.examType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public String getExamtype() {
        return examtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exams.examType
     *
     * @param examtype the value for exams.examType
     *
     * @mbggenerated Fri Feb 24 11:13:22 ICT 2017
     */
    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }
}