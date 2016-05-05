package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPLBSRVC database table.
 * 
 */
@Entity
public class Splbsrvc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SplbsrvcPK id;

	@Column(name="ACTIVITY_CODE")
	private String activityCode;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="LABOUR_HOURS")
	private BigDecimal labourHours;

	private BigDecimal phase;

	@Column(name="SPAN_TYPE")
	private String spanType;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Splbsrvc() {
    }

	public SplbsrvcPK getId() {
		return this.id;
	}

	public void setId(SplbsrvcPK id) {
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

	public BigDecimal getPhase() {
		return this.phase;
	}

	public void setPhase(BigDecimal phase) {
		this.phase = phase;
	}

	public String getSpanType() {
		return this.spanType;
	}

	public void setSpanType(String spanType) {
		this.spanType = spanType;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}