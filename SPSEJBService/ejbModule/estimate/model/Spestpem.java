package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SPESTPEM database table.
 * 
 */
@Entity
public class Spestpem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestpemPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="CLEAR_DATE")
	private Date clearDate;

    @Temporal( TemporalType.DATE)
	@Column(name="ISSUES_DATE")
	private Date issuesDate;

    public Spestpem() {
    }

	public SpestpemPK getId() {
		return this.id;
	}

	public void setId(SpestpemPK id) {
		this.id = id;
	}
	
	public Date getClearDate() {
		return this.clearDate;
	}

	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
	}

	public Date getIssuesDate() {
		return this.issuesDate;
	}

	public void setIssuesDate(Date issuesDate) {
		this.issuesDate = issuesDate;
	}

	@Override
	public String toString() {
		return "Spestpem [id=" + id + ", clearDate=" + clearDate
				+ ", issuesDate=" + issuesDate + "]";
	}
	
	

}