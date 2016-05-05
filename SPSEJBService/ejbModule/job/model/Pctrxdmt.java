package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCTRXDMT database table.
 * 
 */
@Entity
public class Pctrxdmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PctrxdmtPK id;

	@Column(name="ADD_DED")
	private String addDed;

	@Column(name="DUE_QTY")
	private BigDecimal dueQty;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="RES_CD")
	private String resCd;

	@Column(name="REV_NO")
	private BigDecimal revNo;

	@Column(name="TRX_AMT")
	private BigDecimal trxAmt;

	@Column(name="TRX_QTY")
	private BigDecimal trxQty;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name="UOM_CD")
	private String uomCd;

    public Pctrxdmt() {
    }

	public PctrxdmtPK getId() {
		return this.id;
	}

	public void setId(PctrxdmtPK id) {
		this.id = id;
	}
	
	public String getAddDed() {
		return this.addDed;
	}

	public void setAddDed(String addDed) {
		this.addDed = addDed;
	}

	public BigDecimal getDueQty() {
		return this.dueQty;
	}

	public void setDueQty(BigDecimal dueQty) {
		this.dueQty = dueQty;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getResCd() {
		return this.resCd;
	}

	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public BigDecimal getRevNo() {
		return this.revNo;
	}

	public void setRevNo(BigDecimal revNo) {
		this.revNo = revNo;
	}

	public BigDecimal getTrxAmt() {
		return this.trxAmt;
	}

	public void setTrxAmt(BigDecimal trxAmt) {
		this.trxAmt = trxAmt;
	}

	public BigDecimal getTrxQty() {
		return this.trxQty;
	}

	public void setTrxQty(BigDecimal trxQty) {
		this.trxQty = trxQty;
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
		return "Pctrxdmt [id=" + id + ", addDed=" + addDed + ", dueQty="
				+ dueQty + ", projectNo=" + projectNo + ", resCd=" + resCd
				+ ", revNo=" + revNo + ", trxAmt=" + trxAmt + ", trxQty="
				+ trxQty + ", unitPrice=" + unitPrice + ", uomCd=" + uomCd
				+ "]";
	}
	
	

}