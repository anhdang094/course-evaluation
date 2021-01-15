package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Class_Member extends Class_MemberKey {
	private Boolean isactive;
	
    private Date modifiedat;

    private Date createdat;
    
    public Boolean getIsactive() {
        return isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
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
}