package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPCNDCTM database table.
 * 
 */
@Entity
public class Spcndctm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpcndctmPK id;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="WIRE_METER_PRICE")
	private BigDecimal wireMeterPrice;

    public Spcndctm() {
    }

	public SpcndctmPK getId() {
		return this.id;
	}

	public void setId(SpcndctmPK id) {
		this.id = id;
	}
	
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getWireMeterPrice() {
		return this.wireMeterPrice;
	}

	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

}