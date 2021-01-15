package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Classes_Exams extends Classes_ExamsKey {
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
}