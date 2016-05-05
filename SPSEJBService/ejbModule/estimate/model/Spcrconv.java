package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPCRCONV database table.
 * 
 */
@Entity
public class Spcrconv implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpcrconvPK id;

	@Column(name="FIXED_COST")
	private BigDecimal fixedCost;

	@Column(name="WIRE_METER_PRICE")
	private BigDecimal wireMeterPrice;

    public Spcrconv() {
    }

	public SpcrconvPK getId() {
		return this.id;
	}

	public void setId(SpcrconvPK id) {
		this.id = id;
	}
	
	public BigDecimal getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
	}

	public BigDecimal getWireMeterPrice() {
		return this.wireMeterPrice;
	}

	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

	@Override
	public String toString() {
		return "Spcrconv [id=" + id + ", fixedCost=" + fixedCost
				+ ", wireMeterPrice=" + wireMeterPrice + "]";
	}

}