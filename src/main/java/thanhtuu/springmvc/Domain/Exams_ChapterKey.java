package thanhtuu.springmvc.Domain;

public class Exams_ChapterKey {
    private Integer chapterid;

    private Long examsid;

    private Integer subjectid;

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public Long getExamsid() {
        return examsid;
    }

    public void setExamsid(Long examsid) {
        this.examsid = examsid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }
}