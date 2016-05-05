package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCESTDTT database table.
 * 
 */
@Entity
public class Pcestdtt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcestdttPK id;

	@Column(name="APPROVED_COST")
	private BigDecimal approvedCost;

	@Column(name="APPROVED_QTY")
	private BigDecimal approvedQty;

	@Column(name="COMMITED_COST")
	private BigDecimal commitedCost;

	@Column(name="COMMITED_QTY")
	private BigDecimal commitedQty;

	@Column(name="CUSTOMER_QTY")
	private BigDecimal customerQty;

	@Column(name="DAMAGE_QTY")
	private BigDecimal damageQty;

	@Column(name="ESTIMATE_COST")
	private BigDecimal estimateCost;

	@Column(name="ESTIMATE_QTY")
	private BigDecimal estimateQty;

	@Column(name="GEN_RES")
	private String genRes;

	@Column(name="ISSUED_COST")
	private BigDecimal issuedCost;

	@Column(name="ISSUED_QTY")
	private BigDecimal issuedQty;

	@Column(name="MNT_QTY")
	private BigDecimal mntQty;

	@Column(name="NORM_DEFAULT")
	private String normDefault;

	@Column(name="REQUESTED_COST")
	private BigDecimal requestedCost;

	@Column(name="REQUESTED_QTY")
	private BigDecimal requestedQty;

	@Column(name="RES_CAT")
	private BigDecimal resCat;

	@Column(name="RES_TYPE")
	private String resType;

	@Column(name="RETURNED_COST")
	private BigDecimal returnedCost;

	@Column(name="RETURNED_QTY")
	private BigDecimal returnedQty;

	private BigDecimal tolerance;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	private String uom;

    public Pcestdtt() {
    }

	public PcestdttPK getId() {
		return this.id;
	}

	public void setId(PcestdttPK id) {
		this.id = id;
	}
	
	public BigDecimal getApprovedCost() {
		return this.approvedCost;
	}

	public void setApprovedCost(BigDecimal approvedCost) {
		this.approvedCost = approvedCost;
	}

	public BigDecimal getApprovedQty() {
		return this.approvedQty;
	}

	public void setApprovedQty(BigDecimal approvedQty) {
		this.approvedQty = approvedQty;
	}

	public BigDecimal getCommitedCost() {
		return this.commitedCost;
	}

	public void setCommitedCost(BigDecimal commitedCost) {
		this.commitedCost = commitedCost;
	}

	public BigDecimal getCommitedQty() {
		return this.commitedQty;
	}

	public void setCommitedQty(BigDecimal commitedQty) {
		this.commitedQty = commitedQty;
	}

	public BigDecimal getCustomerQty() {
		return this.customerQty;
	}

	public void setCustomerQty(BigDecimal customerQty) {
		this.customerQty = customerQty;
	}

	public BigDecimal getDamageQty() {
		return this.damageQty;
	}

	public void setDamageQty(BigDecimal damageQty) {
		this.damageQty = damageQty;
	}

	public BigDecimal getEstimateCost() {
		return this.estimateCost;
	}

	public void setEstimateCost(BigDecimal estimateCost) {
		//this.estimateCost = estimateCost;
		this.estimateCost = estimateCost.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getEstimateQty() {
		return this.estimateQty;
	}

	public void setEstimateQty(BigDecimal estimateQty) {
		//this.estimateQty = estimateQty;
		this.estimateQty = estimateQty.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getGenRes() {
		return this.genRes;
	}

	public void setGenRes(String genRes) {
		this.genRes = genRes;
	}

	public BigDecimal getIssuedCost() {
		return this.issuedCost;
	}

	public void setIssuedCost(BigDecimal issuedCost) {
		this.issuedCost = issuedCost;
	}

	public BigDecimal getIssuedQty() {
		return this.issuedQty;
	}

	public void setIssuedQty(BigDecimal issuedQty) {
		this.issuedQty = issuedQty;
	}

	public BigDecimal getMntQty() {
		return this.mntQty;
	}

	public void setMntQty(BigDecimal mntQty) {
		this.mntQty = mntQty;
	}

	public String getNormDefault() {
		return this.normDefault;
	}

	public void setNormDefault(String normDefault) {
		this.normDefault = normDefault;
	}

	public BigDecimal getRequestedCost() {
		return this.requestedCost;
	}

	public void setRequestedCost(BigDecimal requestedCost) {
		this.requestedCost = requestedCost;
	}

	public BigDecimal getRequestedQty() {
		return this.requestedQty;
	}

	public void setRequestedQty(BigDecimal requestedQty) {
		this.requestedQty = requestedQty;
	}

	public BigDecimal getResCat() {
		return this.resCat;
	}

	public void setResCat(BigDecimal resCat) {
		this.resCat = resCat;
	}

	public String getResType() {
		return this.resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public BigDecimal getReturnedCost() {
		return this.returnedCost;
	}

	public void setReturnedCost(BigDecimal returnedCost) {
		this.returnedCost = returnedCost;
	}

	public BigDecimal getReturnedQty() {
		return this.returnedQty;
	}

	public void setReturnedQty(BigDecimal returnedQty) {
		this.returnedQty = returnedQty;
	}

	public BigDecimal getTolerance() {
		return this.tolerance;
	}

	public void setTolerance(BigDecimal tolerance) {
		this.tolerance = tolerance;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	@Override
	public String toString() {
		return "Pcestdtt [id=" + id + ", approvedCost=" + approvedCost
				+ ", approvedQty=" + approvedQty + ", commitedCost="
				+ commitedCost + ", commitedQty=" + commitedQty
				+ ", customerQty=" + customerQty + ", damageQty=" + damageQty
				+ ", estimateCost=" + estimateCost + ", estimateQty="
				+ estimateQty + ", genRes=" + genRes + ", issuedCost="
				+ issuedCost + ", issuedQty=" + issuedQty + ", mntQty="
				+ mntQty + ", normDefault=" + normDefault + ", requestedCost="
				+ requestedCost + ", requestedQty=" + requestedQty
				+ ", resCat=" + resCat + ", resType=" + resType
				+ ", returnedCost=" + returnedCost + ", returnedQty="
				+ returnedQty + ", tolerance=" + tolerance + ", unitPrice="
				+ unitPrice + ", uom=" + uom + "]";
	}

}