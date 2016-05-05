package rebate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPREBATE database table.
 * 
 */
@Entity
public class Sprebate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SprebatePK id;

	@Column(name="OFFCHARGE_QTY")
	private BigDecimal offchargeQty;

	@Column(name="REBATE_COST")
	private BigDecimal rebateCost;

	@Column(name="REBATE_QTY")
	private BigDecimal rebateQty;

	@Column(name="REUSABLE_QTY")
	private BigDecimal reusableQty;

	@Column(name="REV_NO")
	private BigDecimal revNo;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;
	
	@Column(name="DOC_NO")
	private BigDecimal doc_no;

	public BigDecimal getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(BigDecimal doc_no) {
		this.doc_no = doc_no;
	}

	public Sprebate() {
	}

	public SprebatePK getId() {
		return this.id;
	}

	public void setId(SprebatePK id) {
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

	public BigDecimal getRevNo() {
		return this.revNo;
	}

	public void setRevNo(BigDecimal revNo) {
		this.revNo = revNo;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Sprebate [id=" + id + ", offchargeQty=" + offchargeQty
				+ ", rebateCost=" + rebateCost + ", rebateQty=" + rebateQty
				+ ", reusableQty=" + reusableQty + ", revNo=" + revNo
				+ ", unitPrice=" + unitPrice + ", doc_no=" + doc_no + "]";
	}

}