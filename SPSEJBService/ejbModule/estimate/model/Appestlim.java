package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the APPESTLIM database table.
 * 
 */
@Entity
public class Appestlim implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppestlimPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

    public Appestlim() {
    }

	public AppestlimPK getId() {
		return this.id;
	}

	public void setId(AppestlimPK id) {
		this.id = id;
	}
	
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	@Override
	public String toString() {
		return "Appestlim [id=" + id + ", updDate=" + updDate + ", updTime="
				+ updTime + ", updUser=" + updUser + "]";
	}
	
	

}