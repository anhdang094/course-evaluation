package thanhtuu.springmvc.Domain;

import java.util.Date;

public class Subject {
    private Long id;

    private String name;

    private Integer adminid;

    private Date modifiedat;

    private Date createdat;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	private Boolean isactive;

	
 
    
}