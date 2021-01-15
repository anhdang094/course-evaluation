package thanhtuu.springmvc.Domain;

public class Teacher_SubjectKey {
    private Long subjectid;

    private Integer teacherid;

    public Long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }
}