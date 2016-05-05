package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPLBPOLT database table.
 * 
 */
@Entity
public class Splbpolt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SplbpoltPK id;

	@Column(name="ACTIVITY_CODE")
	private String activityCode;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="LABOUR_HOURS")
	private BigDecimal labourHours;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Splbpolt() {
    }

	public SplbpoltPK getId() {
		return this.id;
	}

	public void setId(SplbpoltPK id) {
		this.id = id;
	}
	
	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public BigDecimal getLabourHours() {
		return this.labourHours;
	}

	public void setLabourHours(BigDecimal labourHours) {
		this.labourHours = labourHours;
	}

	public String getMatCd() {
		return this.matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Splbpolt [id=" + id + ", activityCode=" + activityCode
				+ ", applicationType=" + applicationType + ", labourHours="
				+ labourHours + ", matCd=" + matCd + ", unitPrice=" + unitPrice
				+ "]";
	}
	
	

}