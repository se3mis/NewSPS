package inventory.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the INWRHMTM database table.
 * 
 */
@Entity
public class Inwrhmtm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InwrhmtmPK id;

	@Column(name="AVG_CONSMP")
	private BigDecimal avgConsmp;

	@Column(name="AVG_COST")
	private BigDecimal avgCost;

	@Column(name="CLS_CD")
	private String clsCd;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MAT_COST")
	private BigDecimal matCost;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="QTY_ALOCATD")
	private BigDecimal qtyAlocatd;

	@Column(name="QTY_ON_HAND")
	private BigDecimal qtyOnHand;

	@Column(name="QTY_ON_ORDR")
	private BigDecimal qtyOnOrdr;

	@Column(name="QTY_ON_RQST")
	private BigDecimal qtyOnRqst;

	private BigDecimal status;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name="UOM_CD")
	private String uomCd;

    public Inwrhmtm() {
    }

	public InwrhmtmPK getId() {
		return this.id;
	}

	public void setId(InwrhmtmPK id) {
		this.id = id;
	}
	
	public BigDecimal getAvgConsmp() {
		return this.avgConsmp;
	}

	public void setAvgConsmp(BigDecimal avgConsmp) {
		this.avgConsmp = avgConsmp;
	}

	public BigDecimal getAvgCost() {
		return this.avgCost;
	}

	public void setAvgCost(BigDecimal avgCost) {
		this.avgCost = avgCost;
	}

	public String getClsCd() {
		return this.clsCd;
	}

	public void setClsCd(String clsCd) {
		this.clsCd = clsCd;
	}

	public String getEntBy() {
		return this.entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public Date getEntDt() {
		return this.entDt;
	}

	public void setEntDt(Date entDt) {
		this.entDt = entDt;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public BigDecimal getMatCost() {
		return this.matCost;
	}

	public void setMatCost(BigDecimal matCost) {
		this.matCost = matCost;
	}

	public String getModiBy() {
		return this.modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDt() {
		return this.modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	public BigDecimal getQtyAlocatd() {
		return this.qtyAlocatd;
	}

	public void setQtyAlocatd(BigDecimal qtyAlocatd) {
		this.qtyAlocatd = qtyAlocatd;
	}

	public BigDecimal getQtyOnHand() {
		return this.qtyOnHand;
	}

	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	public BigDecimal getQtyOnOrdr() {
		return this.qtyOnOrdr;
	}

	public void setQtyOnOrdr(BigDecimal qtyOnOrdr) {
		this.qtyOnOrdr = qtyOnOrdr;
	}

	public BigDecimal getQtyOnRqst() {
		return this.qtyOnRqst;
	}

	public void setQtyOnRqst(BigDecimal qtyOnRqst) {
		this.qtyOnRqst = qtyOnRqst;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUomCd() {
		return this.uomCd;
	}

	public void setUomCd(String uomCd) {
		this.uomCd = uomCd;
	}

	@Override
	public String toString() {
		return "Inwrhmtm [id=" + id + ", avgConsmp=" + avgConsmp + ", avgCost="
				+ avgCost + ", clsCd=" + clsCd + ", entBy=" + entBy
				+ ", entDt=" + entDt + ", logId=" + logId + ", matCost="
				+ matCost + ", modiBy=" + modiBy + ", modiDt=" + modiDt
				+ ", qtyAlocatd=" + qtyAlocatd + ", qtyOnHand=" + qtyOnHand
				+ ", qtyOnOrdr=" + qtyOnOrdr + ", qtyOnRqst=" + qtyOnRqst
				+ ", status=" + status + ", unitPrice=" + unitPrice
				+ ", uomCd=" + uomCd + "]";
	}
	
	

}