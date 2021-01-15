package thanhtuu.springmvc.Domain;

public class Target extends TargetKey {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    private Boolean isactive;
    
    public Boolean getIsactive() {
        return isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}