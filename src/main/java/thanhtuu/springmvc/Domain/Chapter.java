package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Chapter extends ChapterKey {
    private String name;

    private Integer teacherid;

    private Boolean isactive;
    
    private Date modifiedat;

    private Date createdat;
    
    public Boolean getIsactive() {
        return isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
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