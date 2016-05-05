package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CBCHQDMT database table.
 * 
 */
@Entity
public class Cbchqdmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbchqdmtPK id;

	@Column(name="AMT_WORDS")
	private String amtWords;

	@Column(name="BANK_CHRG")
	private BigDecimal bankChrg;

	@Column(name="CHQ_AMT")
	private BigDecimal chqAmt;

    @Temporal( TemporalType.DATE)
	@Column(name="CHQ_DT")
	private Date chqDt;

	@Column(name="CHQ_NO")
	private String chqNo;

	@Column(name="DOC_NO")
	private String docNo;

	@Column(name="DOC_PF")
	private String docPf;

	@Column(name="GRP_CRT")
	private String grpCrt;

	private String payee;

    @Temporal( TemporalType.DATE)
	@Column(name="PRINT_DT")
	private Date printDt;

    @Temporal( TemporalType.DATE)
	@Column(name="PRINT_TM")
	private Date printTm;

	@Column(name="PRINT_UID")
	private String printUid;

	private BigDecimal status;

	@Column(name="SUP_CD")
	private String supCd;

    public Cbchqdmt() {
    }

	public CbchqdmtPK getId() {
		return this.id;
	}

	public void setId(CbchqdmtPK id) {
		this.id = id;
	}
	
	public String getAmtWords() {
		return this.amtWords;
	}

	public void setAmtWords(String amtWords) {
		this.amtWords = amtWords;
	}

	public BigDecimal getBankChrg() {
		return this.bankChrg;
	}

	public void setBankChrg(BigDecimal bankChrg) {
		this.bankChrg = bankChrg;
	}

	public BigDecimal getChqAmt() {
		return this.chqAmt;
	}

	public void setChqAmt(BigDecimal chqAmt) {
		this.chqAmt = chqAmt;
	}

	public Date getChqDt() {
		return this.chqDt;
	}

	public void setChqDt(Date chqDt) {
		this.chqDt = chqDt;
	}

	public String getChqNo() {
		return this.chqNo;
	}

	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocPf() {
		return this.docPf;
	}

	public void setDocPf(String docPf) {
		this.docPf = docPf;
	}

	public String getGrpCrt() {
		return this.grpCrt;
	}

	public void setGrpCrt(String grpCrt) {
		this.grpCrt = grpCrt;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public Date getPrintDt() {
		return this.printDt;
	}

	public void setPrintDt(Date printDt) {
		this.printDt = printDt;
	}

	public Date getPrintTm() {
		return this.printTm;
	}

	public void setPrintTm(Date printTm) {
		this.printTm = printTm;
	}

	public String getPrintUid() {
		return this.printUid;
	}

	public void setPrintUid(String printUid) {
		this.printUid = printUid;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSupCd() {
		return this.supCd;
	}

	public void setSupCd(String supCd) {
		this.supCd = supCd;
	}

}