package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPLABORM database table.
 * 
 */
@Entity
public class Splaborm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTIVITY_CODE")
	private String activityCode;

	@Column(name="ACTIVITY_NAME")
	private String activityName;

	private String description;

    public Splaborm() {
    }

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}