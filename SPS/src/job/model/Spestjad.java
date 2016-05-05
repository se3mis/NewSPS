package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPESTJAD database table.
 * 
 */
@Entity
public class Spestjad implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestjadPK id;

	@Column(name="JOB_CATEGORY")
	private String jobCategory;

	@Column(name="JOB_CATEGORY_ID")
	private String jobCategoryId;

	@Column(name="JOB_DESCRIPTION")
	private String jobDescription;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Spestjad() {
    }

	public SpestjadPK getId() {
		return this.id;
	}

	public void setId(SpestjadPK id) {
		this.id = id;
	}
	
	public String getJobCategory() {
		return this.jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJobCategoryId() {
		return this.jobCategoryId;
	}

	public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Spestjad [id=" + id + ", jobCategory=" + jobCategory
				+ ", jobCategoryId=" + jobCategoryId + ", jobDescription="
				+ jobDescription + ", unitPrice=" + unitPrice + "]";
	}
	
	

}