package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CBPMTHMT database table.
 * 
 */
@Entity
public class Cbpmthmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbpmthmtPK id;

	private String address;

	@Column(name="APPR_BY")
	private String apprBy;

	@Column(name="APPR_BY1")
	private String apprBy1;

	@Column(name="APPR_BY2")
	private String apprBy2;

    @Temporal( TemporalType.DATE)
	@Column(name="APPR_DT")
	private Date apprDt;

    @Temporal( TemporalType.DATE)
	@Column(name="APPR_DT1")
	private Date apprDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="APPR_DT2")
	private Date apprDt2;

	@Column(name="APPRV_UID")
	private String apprvUid;

	@Column(name="APPRV_UID1")
	private String apprvUid1;

	@Column(name="APPRV_UID2")
	private String apprvUid2;

	@Column(name="BANK_AC")
	private BigDecimal bankAc;

	@Column(name="BANK_CD")
	private String bankCd;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="BNK_DEPT")
	private String bnkDept;

	@Column(name="CHQ_NO")
	private BigDecimal chqNo;

	@Column(name="CHQ_RUN")
	private String chqRun;

	@Column(name="CNTRY_CD")
	private String cntryCd;

	@Column(name="CONF_BY")
	private String confBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="CUR_CD")
	private String curCd;

    @Temporal( TemporalType.DATE)
	@Column(name="DOC_DT")
	private Date docDt;

    @Temporal( TemporalType.DATE)
	@Column(name="DUE_DT")
	private Date dueDt;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="ENTRY_TYPE")
	private BigDecimal entryType;

	@Column(name="EX_RT")
	private BigDecimal exRt;

	@Column(name="LCY_AMT")
	private BigDecimal lcyAmt;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="NON_TAXABL")
	private BigDecimal nonTaxabl;

	private String payee;

	@Column(name="PIN_DEPT")
	private String pinDept;

	@Column(name="PIN_DOCNO")
	private String pinDocno;

	@Column(name="PIN_DOCPF")
	private String pinDocpf;

	@Column(name="POST_BY")
	private String postBy;

    @Temporal( TemporalType.DATE)
	@Column(name="POST_DT")
	private Date postDt;

	@Column(name="PYMT_AMT")
	private BigDecimal pymtAmt;

	@Column(name="REF_1")
	private String ref1;

	@Column(name="REF_2")
	private String ref2;

	@Column(name="REF_3")
	private String ref3;

	@Column(name="REF_4")
	private String ref4;

	@Column(name="REJC_BY")
	private String rejcBy;

    @Temporal( TemporalType.DATE)
	@Column(name="REJC_DT")
	private Date rejcDt;

	private String remarks;

    @Temporal( TemporalType.DATE)
	@Column(name="SENT_DT")
	private Date sentDt;

    @Temporal( TemporalType.DATE)
	@Column(name="SENT_DT1")
	private Date sentDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="SENT_DT2")
	private Date sentDt2;

	private BigDecimal status;

	@Column(name="SUP_CD")
	private String supCd;

	@Column(name="TAX_NPBL_SUP")
	private BigDecimal taxNpblSup;

	@Column(name="TAX_PBL_SUP")
	private BigDecimal taxPblSup;

	private BigDecimal taxabl;

	@Column(name="TOT_PBL_SUP")
	private BigDecimal totPblSup;

	@Column(name="TOTAL_TAX")
	private BigDecimal totalTax;

    public Cbpmthmt() {
    }

	public CbpmthmtPK getId() {
		return this.id;
	}

	public void setId(CbpmthmtPK id) {
		this.id = id;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApprBy() {
		return this.apprBy;
	}

	public void setApprBy(String apprBy) {
		this.apprBy = apprBy;
	}

	public String getApprBy1() {
		return this.apprBy1;
	}

	public void setApprBy1(String apprBy1) {
		this.apprBy1 = apprBy1;
	}

	public String getApprBy2() {
		return this.apprBy2;
	}

	public void setApprBy2(String apprBy2) {
		this.apprBy2 = apprBy2;
	}

	public Date getApprDt() {
		return this.apprDt;
	}

	public void setApprDt(Date apprDt) {
		this.apprDt = apprDt;
	}

	public Date getApprDt1() {
		return this.apprDt1;
	}

	public void setApprDt1(Date apprDt1) {
		this.apprDt1 = apprDt1;
	}

	public Date getApprDt2() {
		return this.apprDt2;
	}

	public void setApprDt2(Date apprDt2) {
		this.apprDt2 = apprDt2;
	}

	public String getApprvUid() {
		return this.apprvUid;
	}

	public void setApprvUid(String apprvUid) {
		this.apprvUid = apprvUid;
	}

	public String getApprvUid1() {
		return this.apprvUid1;
	}

	public void setApprvUid1(String apprvUid1) {
		this.apprvUid1 = apprvUid1;
	}

	public String getApprvUid2() {
		return this.apprvUid2;
	}

	public void setApprvUid2(String apprvUid2) {
		this.apprvUid2 = apprvUid2;
	}

	public BigDecimal getBankAc() {
		return this.bankAc;
	}

	public void setBankAc(BigDecimal bankAc) {
		this.bankAc = bankAc;
	}

	public String getBankCd() {
		return this.bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBnkDept() {
		return this.bnkDept;
	}

	public void setBnkDept(String bnkDept) {
		this.bnkDept = bnkDept;
	}

	public BigDecimal getChqNo() {
		return this.chqNo;
	}

	public void setChqNo(BigDecimal chqNo) {
		this.chqNo = chqNo;
	}

	public String getChqRun() {
		return this.chqRun;
	}

	public void setChqRun(String chqRun) {
		this.chqRun = chqRun;
	}

	public String getCntryCd() {
		return this.cntryCd;
	}

	public void setCntryCd(String cntryCd) {
		this.cntryCd = cntryCd;
	}

	public String getConfBy() {
		return this.confBy;
	}

	public void setConfBy(String confBy) {
		this.confBy = confBy;
	}

	public Date getConfDt() {
		return this.confDt;
	}

	public void setConfDt(Date confDt) {
		this.confDt = confDt;
	}

	public String getCurCd() {
		return this.curCd;
	}

	public void setCurCd(String curCd) {
		this.curCd = curCd;
	}

	public Date getDocDt() {
		return this.docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
	}

	public Date getDueDt() {
		return this.dueDt;
	}

	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
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

	public BigDecimal getEntryType() {
		return this.entryType;
	}

	public void setEntryType(BigDecimal entryType) {
		this.entryType = entryType;
	}

	public BigDecimal getExRt() {
		return this.exRt;
	}

	public void setExRt(BigDecimal exRt) {
		this.exRt = exRt;
	}

	public BigDecimal getLcyAmt() {
		return this.lcyAmt;
	}

	public void setLcyAmt(BigDecimal lcyAmt) {
		this.lcyAmt = lcyAmt;
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

	public BigDecimal getNonTaxabl() {
		return this.nonTaxabl;
	}

	public void setNonTaxabl(BigDecimal nonTaxabl) {
		this.nonTaxabl = nonTaxabl;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPinDept() {
		return this.pinDept;
	}

	public void setPinDept(String pinDept) {
		this.pinDept = pinDept;
	}

	public String getPinDocno() {
		return this.pinDocno;
	}

	public void setPinDocno(String pinDocno) {
		this.pinDocno = pinDocno;
	}

	public String getPinDocpf() {
		return this.pinDocpf;
	}

	public void setPinDocpf(String pinDocpf) {
		this.pinDocpf = pinDocpf;
	}

	public String getPostBy() {
		return this.postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public Date getPostDt() {
		return this.postDt;
	}

	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}

	public BigDecimal getPymtAmt() {
		return this.pymtAmt;
	}

	public void setPymtAmt(BigDecimal pymtAmt) {
		this.pymtAmt = pymtAmt;
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

	public String getRef3() {
		return this.ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getRef4() {
		return this.ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}

	public String getRejcBy() {
		return this.rejcBy;
	}

	public void setRejcBy(String rejcBy) {
		this.rejcBy = rejcBy;
	}

	public Date getRejcDt() {
		return this.rejcDt;
	}

	public void setRejcDt(Date rejcDt) {
		this.rejcDt = rejcDt;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSentDt() {
		return this.sentDt;
	}

	public void setSentDt(Date sentDt) {
		this.sentDt = sentDt;
	}

	public Date getSentDt1() {
		return this.sentDt1;
	}

	public void setSentDt1(Date sentDt1) {
		this.sentDt1 = sentDt1;
	}

	public Date getSentDt2() {
		return this.sentDt2;
	}

	public void setSentDt2(Date sentDt2) {
		this.sentDt2 = sentDt2;
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

	public BigDecimal getTaxNpblSup() {
		return this.taxNpblSup;
	}

	public void setTaxNpblSup(BigDecimal taxNpblSup) {
		this.taxNpblSup = taxNpblSup;
	}

	public BigDecimal getTaxPblSup() {
		return this.taxPblSup;
	}

	public void setTaxPblSup(BigDecimal taxPblSup) {
		this.taxPblSup = taxPblSup;
	}

	public BigDecimal getTaxabl() {
		return this.taxabl;
	}

	public void setTaxabl(BigDecimal taxabl) {
		this.taxabl = taxabl;
	}

	public BigDecimal getTotPblSup() {
		return this.totPblSup;
	}

	public void setTotPblSup(BigDecimal totPblSup) {
		this.totPblSup = totPblSup;
	}

	public BigDecimal getTotalTax() {
		return this.totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	@Override
	public String toString() {
		return "Cbpmthmt [id=" + id + ", address=" + address + ", apprBy="
				+ apprBy + ", apprBy1=" + apprBy1 + ", apprBy2=" + apprBy2
				+ ", apprDt=" + apprDt + ", apprDt1=" + apprDt1 + ", apprDt2="
				+ apprDt2 + ", apprvUid=" + apprvUid + ", apprvUid1="
				+ apprvUid1 + ", apprvUid2=" + apprvUid2 + ", bankAc=" + bankAc
				+ ", bankCd=" + bankCd + ", batchId=" + batchId + ", bnkDept="
				+ bnkDept + ", chqNo=" + chqNo + ", chqRun=" + chqRun
				+ ", cntryCd=" + cntryCd + ", confBy=" + confBy + ", confDt="
				+ confDt + ", curCd=" + curCd + ", docDt=" + docDt + ", dueDt="
				+ dueDt + ", entBy=" + entBy + ", entDt=" + entDt
				+ ", entryType=" + entryType + ", exRt=" + exRt + ", lcyAmt="
				+ lcyAmt + ", modiBy=" + modiBy + ", modiDt=" + modiDt
				+ ", nonTaxabl=" + nonTaxabl + ", payee=" + payee
				+ ", pinDept=" + pinDept + ", pinDocno=" + pinDocno
				+ ", pinDocpf=" + pinDocpf + ", postBy=" + postBy + ", postDt="
				+ postDt + ", pymtAmt=" + pymtAmt + ", ref1=" + ref1
				+ ", ref2=" + ref2 + ", ref3=" + ref3 + ", ref4=" + ref4
				+ ", rejcBy=" + rejcBy + ", rejcDt=" + rejcDt + ", remarks="
				+ remarks + ", sentDt=" + sentDt + ", sentDt1=" + sentDt1
				+ ", sentDt2=" + sentDt2 + ", status=" + status + ", supCd="
				+ supCd + ", taxNpblSup=" + taxNpblSup + ", taxPblSup="
				+ taxPblSup + ", taxabl=" + taxabl + ", totPblSup=" + totPblSup
				+ ", totalTax=" + totalTax + "]";
	}
	
	

}