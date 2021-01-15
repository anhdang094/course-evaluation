package thanhtuu.springmvc.Domain;
public class AnswersKey {
    private Long id;

    private Long questionblockid;

    private Long questionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionblockid() {
        return questionblockid;
    }

    public void setQuestionblockid(Long questionblockid) {
        this.questionblockid = questionblockid;
    }

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }
}