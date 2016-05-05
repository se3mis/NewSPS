package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPLABRAT database table.
 * 
 */
@Entity
public class Splabrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SplabratPK id;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	private String description;

	@Column(name="LABOUR_HOURS")
	private BigDecimal labourHours;

	@Column(name="LABOUR_NAME")
	private String labourName;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Splabrat() {
    }

	public SplabratPK getId() {
		return this.id;
	}

	public void setId(SplabratPK id) {
		this.id = id;
	}
	
	public String getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLabourHours() {
		return this.labourHours;
	}

	public void setLabourHours(BigDecimal labourHours) {
		this.labourHours = labourHours;
	}

	public String getLabourName() {
		return this.labourName;
	}

	public void setLabourName(String labourName) {
		this.labourName = labourName;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}