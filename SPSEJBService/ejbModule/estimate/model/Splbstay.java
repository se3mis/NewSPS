package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPLBSTAY database table.
 * 
 */
@Entity
public class Splbstay implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SplbstayPK id;

	@Column(name="ACTIVITY_CODE")
	private String activityCode;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="LABOUR_HOURS")
	private BigDecimal labourHours;

	@Column(name="STAY_TYPE")
	private String stayType;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Splbstay() {
    }

	public SplbstayPK getId() {
		return this.id;
	}

	public void setId(SplbstayPK id) {
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

	public String getStayType() {
		return this.stayType;
	}

	public void setStayType(String stayType) {
		this.stayType = stayType;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Splbstay [id=" + id + ", activityCode=" + activityCode
				+ ", applicationType=" + applicationType + ", labourHours="
				+ labourHours + ", stayType=" + stayType + ", unitPrice="
				+ unitPrice + "]";
	}
	
	

}