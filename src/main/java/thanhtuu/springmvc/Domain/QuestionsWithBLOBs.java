package thanhtuu.springmvc.Domain;

public class QuestionsWithBLOBs extends Questions {
    private String content;

    private String keyword;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}