package thanhtuu.springmvc.Domain;

public class Questions_TargetKey {
    private Long questionblockid;

    private Long questionid;

    private Long subjectid;
    
    private Long chapterid;

    private Long targetid;
    
    public Long getChapterid() {
        return chapterid;
    }

    public void setChapterid(Long chapterid) {
        this.chapterid = chapterid;
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

    public Long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    public Long getTargetid() {
        return targetid;
    }

    public void setTargetid(Long targetid) {
        this.targetid = targetid;
    }
}