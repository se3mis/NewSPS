package rebate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPREBATEH database table.
 * 
 */
@Entity
public class Sprebateh implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SprebatehPK id;

	@Column(name="OFFCHARGE_QTY")
	private BigDecimal offchargeQty;

	@Column(name="REBATE_COST")
	private BigDecimal rebateCost;

	@Column(name="REBATE_QTY")
	private BigDecimal rebateQty;

	@Column(name="REUSABLE_QTY")
	private BigDecimal reusableQty;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	public Sprebateh() {
	}

	public SprebatehPK getId() {
		return this.id;
	}

	public void setId(SprebatehPK id) {
		this.id = id;
	}

	public BigDecimal getOffchargeQty() {
		return this.offchargeQty;
	}

	public void setOffchargeQty(BigDecimal offchargeQty) {
		this.offchargeQty = offchargeQty;
	}

	public BigDecimal getRebateCost() {
		return this.rebateCost;
	}

	public void setRebateCost(BigDecimal rebateCost) {
		this.rebateCost = rebateCost;
	}

	public BigDecimal getRebateQty() {
		return this.rebateQty;
	}

	public void setRebateQty(BigDecimal rebateQty) {
		this.rebateQty = rebateQty;
	}

	public BigDecimal getReusableQty() {
		return this.reusableQty;
	}

	public void setReusableQty(BigDecimal reusableQty) {
		this.reusableQty = reusableQty;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}