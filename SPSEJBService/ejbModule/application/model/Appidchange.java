package application.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the APPIDCHANGE database table.
 * 
 */
@Entity
public class Appidchange implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppidchangePK id;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

    public Appidchange() {
    }

	public AppidchangePK getId() {
		return this.id;
	}

	public void setId(AppidchangePK id) {
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

}