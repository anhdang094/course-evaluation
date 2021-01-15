package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Super_Teacher_Subject extends Super_Teacher_SubjectKey {
    private Integer adminid;

    private Date modifiedat;

    private Date createdat;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

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