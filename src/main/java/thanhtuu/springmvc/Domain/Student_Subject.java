package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Student_Subject extends Student_SubjectKey {
    private Date modifiedat;

    private Date createdat;

    public Date getModifiedat() {
        return modifiedat;
    }

    public void setModifiedat(Date modifiedat) {
        this.modifiedat = modifiedat;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
    
    private Boolean isactive;
    
    public Boolean getIsactive() {
        return isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}