package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPESTBIP database table.
 * 
 */
@Entity
public class Spestbip implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestbipPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="BILL_REFERENCE_NO")
	private String billReferenceNo;

	@Column(name="PAY_AMT")
	private BigDecimal payAmt;

	@Column(name="RETENTION_AMT")
	private BigDecimal retentionAmt;

	@Column(name="TOTAL_BILL_AMT")
	private BigDecimal totalBillAmt;

	@Column(name="WITHHOLD_AMT")
	private BigDecimal withholdAmt;

    public Spestbip() {
    }

	public SpestbipPK getId() {
		return this.id;
	}

	public void setId(SpestbipPK id) {
		this.id = id;
	}
	
	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillReferenceNo() {
		return this.billReferenceNo;
	}

	public void setBillReferenceNo(String billReferenceNo) {
		this.billReferenceNo = billReferenceNo;
	}

	public BigDecimal getPayAmt() {
		return this.payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	public BigDecimal getRetentionAmt() {
		return this.retentionAmt;
	}

	public void setRetentionAmt(BigDecimal retentionAmt) {
		this.retentionAmt = retentionAmt;
	}

	public BigDecimal getTotalBillAmt() {
		return this.totalBillAmt;
	}

	public void setTotalBillAmt(BigDecimal totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}

	public BigDecimal getWithholdAmt() {
		return this.withholdAmt;
	}

	public void setWithholdAmt(BigDecimal withholdAmt) {
		this.withholdAmt = withholdAmt;
	}

}