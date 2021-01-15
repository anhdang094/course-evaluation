package thanhtuu.springmvc.Domain;

public class Answers extends AnswersKey {
    private Boolean issolution;

    private String content;

    public Boolean getIssolution() {
        return issolution;
    }

    public void setIssolution(Boolean issolution) {
        this.issolution = issolution;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}