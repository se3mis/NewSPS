package estimate.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPUGCBLM database table.
 * 
 */
@Entity 
public class Spugcblm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpugcblmPK id;

	@Column(name="FIXED_COST")
	private BigDecimal fixedCost;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="WIRE_METER_PRICE")
	private BigDecimal wireMeterPrice;

    public Spugcblm() {
    }

	public SpugcblmPK getId() {
		return this.id;
	}

	public void setId(SpugcblmPK id) {
		this.id = id;
	}
	
	public BigDecimal getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
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