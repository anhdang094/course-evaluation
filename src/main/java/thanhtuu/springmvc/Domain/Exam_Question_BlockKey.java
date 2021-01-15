package thanhtuu.springmvc.Domain;

public class Exam_Question_BlockKey {
    private Long examid;

    private Integer examsid;

    private Integer questionblockid;

    public Long getExamid() {
        return examid;
    }

    public void setExamid(Long examid) {
        this.examid = examid;
    }

    public Integer getExamsid() {
        return examsid;
    }

    public void setExamsid(Integer examsid) {
        this.examsid = examsid;
    }

    public Integer getQuestionblockid() {
        return questionblockid;
    }

    public void setQuestionblockid(Integer questionblockid) {
        this.questionblockid = questionblockid;
    }
}