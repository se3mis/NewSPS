package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPESTSMT database table.
 * 
 */
@Entity
public class Spsetpol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsetpolPK id;

	private String description;

	@Column(name="JOB_CATEGORY_ID")
	private String jobCategoryId;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Spsetpol() {
    }

	public SpsetpolPK getId() {
		return this.id;
	}

	public void setId(SpsetpolPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobCategoryId() {
		return this.jobCategoryId;
	}

	public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	public BigDecimal getMatQty() {
		return this.matQty;
	}

	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}

	@Override
	public String toString() {
		return "Spsetpol [id=" + id + ", description=" + description
				+ ", jobCategoryId=" + jobCategoryId + ", matQty=" + matQty
				+ "]";
	}

}