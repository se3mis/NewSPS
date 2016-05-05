package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCFUNDDM database table.
 * 
 */
@Entity
public class Pcfunddm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcfunddmPK id;

	@Column(name="ALLOCATED_AMT")
	private BigDecimal allocatedAmt;

	@Column(name="COMMITED_AMT")
	private BigDecimal commitedAmt;

	private String descr;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_DT")
	private Date docDt;

    @Temporal( TemporalType.DATE)
	@Column(name="EFECT_DT")
	private Date efectDt;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

    @Temporal( TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Column(name="FUNDED_AMT")
	private BigDecimal fundedAmt;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MOD_BY")
	private String modBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MOD_DT")
	private Date modDt;

	@Column(name="REF_1")
	private String ref1;

	@Column(name="REF_2")
	private String ref2;

	@Column(name="SRC_DOC_NO")
	private String srcDocNo;

	private BigDecimal status;

    public Pcfunddm() {
    }

	public PcfunddmPK getId() {
		return this.id;
	}

	public void setId(PcfunddmPK id) {
		this.id = id;
	}
	
	public BigDecimal getAllocatedAmt() {
		return this.allocatedAmt;
	}

	public void setAllocatedAmt(BigDecimal allocatedAmt) {
		this.allocatedAmt = allocatedAmt;
	}

	public BigDecimal getCommitedAmt() {
		return this.commitedAmt;
	}

	public void setCommitedAmt(BigDecimal commitedAmt) {
		this.commitedAmt = commitedAmt;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getDocDt() {
		return this.docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
	}

	public Date getEfectDt() {
		return this.efectDt;
	}

	public void setEfectDt(Date efectDt) {
		this.efectDt = efectDt;
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

	public Date getExpiryDt() {
		return this.expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public BigDecimal getFundedAmt() {
		return this.fundedAmt;
	}

	public void setFundedAmt(BigDecimal fundedAmt) {
		this.fundedAmt = fundedAmt;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getModBy() {
		return this.modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getModDt() {
		return this.modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getSrcDocNo() {
		return this.srcDocNo;
	}

	public void setSrcDocNo(String srcDocNo) {
		this.srcDocNo = srcDocNo;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pcfunddm [id=" + id + ", allocatedAmt=" + allocatedAmt
				+ ", commitedAmt=" + commitedAmt + ", descr=" + descr
				+ ", docDt=" + docDt + ", efectDt=" + efectDt + ", entBy="
				+ entBy + ", entDt=" + entDt + ", expiryDt=" + expiryDt
				+ ", fundedAmt=" + fundedAmt + ", logId=" + logId + ", modBy="
				+ modBy + ", modDt=" + modDt + ", ref1=" + ref1 + ", ref2="
				+ ref2 + ", srcDocNo=" + srcDocNo + ", status=" + status + "]";
	}

	
}