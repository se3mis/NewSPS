package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCTRXHMT database table.
 * 
 */
@Entity
public class Pctrxhmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PctrxhmtPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ACCT_DT")
	private Date acctDt;

	@Column(name="BATCH_ID")
	private String batchId;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_DT")
	private Date docDt;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="LOG_MTH")
	private BigDecimal logMth;

	@Column(name="LOG_YR")
	private BigDecimal logYr;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private String remarks;

	private BigDecimal status;

	@Column(name="TRX_TYPE")
	private BigDecimal trxType;

	@Column(name="TRX_VAL")
	private BigDecimal trxVal;

    public Pctrxhmt() {
    }

	public PctrxhmtPK getId() {
		return this.id;
	}

	public void setId(PctrxhmtPK id) {
		this.id = id;
	}
	
	public Date getAcctDt() {
		return this.acctDt;
	}

	public void setAcctDt(Date acctDt) {
		this.acctDt = acctDt;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getDocDt() {
		return this.docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
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

	public BigDecimal getLogMth() {
		return this.logMth;
	}

	public void setLogMth(BigDecimal logMth) {
		this.logMth = logMth;
	}

	public BigDecimal getLogYr() {
		return this.logYr;
	}

	public void setLogYr(BigDecimal logYr) {
		this.logYr = logYr;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTrxType() {
		return this.trxType;
	}

	public void setTrxType(BigDecimal trxType) {
		this.trxType = trxType;
	}

	public BigDecimal getTrxVal() {
		return this.trxVal;
	}

	public void setTrxVal(BigDecimal trxVal) {
		this.trxVal = trxVal;
	}

}