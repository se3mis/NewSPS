package inventory.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the INTRHMT database table.
 * 
 */
@Entity
public class Intrhmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IntrhmtPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ACCT_DT")
	private Date acctDt;

    @Temporal( TemporalType.DATE)
	@Column(name="APPR_DT1")
	private Date apprDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="APPR_DT2")
	private Date apprDt2;

	@Column(name="APPRV_UID1")
	private String apprvUid1;

	@Column(name="APPRV_UID2")
	private String apprvUid2;

	private String cancel;

	@Column(name="DES_DEPT_ID")
	private String desDeptId;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="ENTRY_TYPE")
	private String entryType;

	@Column(name="EXP_CD")
	private String expCd;

	@Column(name="IS_REF")
	private String isRef;

	@Column(name="ISSUE_RECPT")
	private String issueRecpt;

	@Column(name="ISSUE_TO")
	private BigDecimal issueTo;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="POST_BY")
	private String postBy;

    @Temporal( TemporalType.DATE)
	@Column(name="POST_DT")
	private Date postDt;

	private BigDecimal printed;

	@Column(name="PYMT_STATUS")
	private BigDecimal pymtStatus;

	@Column(name="RC_FROM")
	private BigDecimal rcFrom;

	@Column(name="RC_REF")
	private String rcRef;

	@Column(name="RC_VAL")
	private BigDecimal rcVal;

	@Column(name="REF_1")
	private String ref1;

	@Column(name="REF_2")
	private String ref2;

	@Column(name="REF_3")
	private String ref3;

	@Column(name="REF_4")
	private String ref4;

	@Column(name="REJCT_BY1")
	private String rejctBy1;

	@Column(name="REJCT_BY2")
	private String rejctBy2;

    @Temporal( TemporalType.DATE)
	@Column(name="REJCT_DT1")
	private Date rejctDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="REJCT_DT2")
	private Date rejctDt2;

	private String remarks;

    @Temporal( TemporalType.DATE)
	@Column(name="SENT_DT1")
	private Date sentDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="SENT_DT2")
	private Date sentDt2;

	@Column(name="SRC_DEPT_ID")
	private String srcDeptId;

	@Column(name="SRC_DOC_NO")
	private String srcDocNo;

	@Column(name="SRC_DOC_PF")
	private String srcDocPf;

	private BigDecimal status;

	@Column(name="SUB_AC")
	private String subAc;

    @Temporal( TemporalType.DATE)
	@Column(name="TRX_DT")
	private Date trxDt;

	@Column(name="TRX_TYPE")
	private BigDecimal trxType;

	@Column(name="TRXN_VAL")
	private BigDecimal trxnVal;

	private BigDecimal weight;

	@Column(name="WRH_CD")
	private String wrhCd;

    public Intrhmt() {
    }

	public IntrhmtPK getId() {
		return this.id;
	}

	public void setId(IntrhmtPK id) {
		this.id = id;
	}
	
	public Date getAcctDt() {
		return this.acctDt;
	}

	public void setAcctDt(Date acctDt) {
		this.acctDt = acctDt;
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

	public String getCancel() {
		return this.cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getDesDeptId() {
		return this.desDeptId;
	}

	public void setDesDeptId(String desDeptId) {
		this.desDeptId = desDeptId;
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

	public String getEntryType() {
		return this.entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getExpCd() {
		return this.expCd;
	}

	public void setExpCd(String expCd) {
		this.expCd = expCd;
	}

	public String getIsRef() {
		return this.isRef;
	}

	public void setIsRef(String isRef) {
		this.isRef = isRef;
	}

	public String getIssueRecpt() {
		return this.issueRecpt;
	}

	public void setIssueRecpt(String issueRecpt) {
		this.issueRecpt = issueRecpt;
	}

	public BigDecimal getIssueTo() {
		return this.issueTo;
	}

	public void setIssueTo(BigDecimal issueTo) {
		this.issueTo = issueTo;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
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

	public BigDecimal getPrinted() {
		return this.printed;
	}

	public void setPrinted(BigDecimal printed) {
		this.printed = printed;
	}

	public BigDecimal getPymtStatus() {
		return this.pymtStatus;
	}

	public void setPymtStatus(BigDecimal pymtStatus) {
		this.pymtStatus = pymtStatus;
	}

	public BigDecimal getRcFrom() {
		return this.rcFrom;
	}

	public void setRcFrom(BigDecimal rcFrom) {
		this.rcFrom = rcFrom;
	}

	public String getRcRef() {
		return this.rcRef;
	}

	public void setRcRef(String rcRef) {
		this.rcRef = rcRef;
	}

	public BigDecimal getRcVal() {
		return this.rcVal;
	}

	public void setRcVal(BigDecimal rcVal) {
		this.rcVal = rcVal;
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

	public String getRejctBy1() {
		return this.rejctBy1;
	}

	public void setRejctBy1(String rejctBy1) {
		this.rejctBy1 = rejctBy1;
	}

	public String getRejctBy2() {
		return this.rejctBy2;
	}

	public void setRejctBy2(String rejctBy2) {
		this.rejctBy2 = rejctBy2;
	}

	public Date getRejctDt1() {
		return this.rejctDt1;
	}

	public void setRejctDt1(Date rejctDt1) {
		this.rejctDt1 = rejctDt1;
	}

	public Date getRejctDt2() {
		return this.rejctDt2;
	}

	public void setRejctDt2(Date rejctDt2) {
		this.rejctDt2 = rejctDt2;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getSrcDeptId() {
		return this.srcDeptId;
	}

	public void setSrcDeptId(String srcDeptId) {
		this.srcDeptId = srcDeptId;
	}

	public String getSrcDocNo() {
		return this.srcDocNo;
	}

	public void setSrcDocNo(String srcDocNo) {
		this.srcDocNo = srcDocNo;
	}

	public String getSrcDocPf() {
		return this.srcDocPf;
	}

	public void setSrcDocPf(String srcDocPf) {
		this.srcDocPf = srcDocPf;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubAc() {
		return this.subAc;
	}

	public void setSubAc(String subAc) {
		this.subAc = subAc;
	}

	public Date getTrxDt() {
		return this.trxDt;
	}

	public void setTrxDt(Date trxDt) {
		this.trxDt = trxDt;
	}

	public BigDecimal getTrxType() {
		return this.trxType;
	}

	public void setTrxType(BigDecimal trxType) {
		this.trxType = trxType;
	}

	public BigDecimal getTrxnVal() {
		return this.trxnVal;
	}

	public void setTrxnVal(BigDecimal trxnVal) {
		this.trxnVal = trxnVal;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getWrhCd() {
		return this.wrhCd;
	}

	public void setWrhCd(String wrhCd) {
		this.wrhCd = wrhCd;
	}

	@Override
	public String toString() {
		return "Intrhmt [id=" + id + ", acctDt=" + acctDt + ", apprDt1="
				+ apprDt1 + ", apprDt2=" + apprDt2 + ", apprvUid1=" + apprvUid1
				+ ", apprvUid2=" + apprvUid2 + ", cancel=" + cancel
				+ ", desDeptId=" + desDeptId + ", entBy=" + entBy + ", entDt="
				+ entDt + ", entryType=" + entryType + ", expCd=" + expCd
				+ ", isRef=" + isRef + ", issueRecpt=" + issueRecpt
				+ ", issueTo=" + issueTo + ", logId=" + logId + ", modiBy="
				+ modiBy + ", modiDt=" + modiDt + ", postBy=" + postBy
				+ ", postDt=" + postDt + ", printed=" + printed
				+ ", pymtStatus=" + pymtStatus + ", rcFrom=" + rcFrom
				+ ", rcRef=" + rcRef + ", rcVal=" + rcVal + ", ref1=" + ref1
				+ ", ref2=" + ref2 + ", ref3=" + ref3 + ", ref4=" + ref4
				+ ", rejctBy1=" + rejctBy1 + ", rejctBy2=" + rejctBy2
				+ ", rejctDt1=" + rejctDt1 + ", rejctDt2=" + rejctDt2
				+ ", remarks=" + remarks + ", sentDt1=" + sentDt1
				+ ", sentDt2=" + sentDt2 + ", srcDeptId=" + srcDeptId
				+ ", srcDocNo=" + srcDocNo + ", srcDocPf=" + srcDocPf
				+ ", status=" + status + ", subAc=" + subAc + ", trxDt="
				+ trxDt + ", trxType=" + trxType + ", trxnVal=" + trxnVal
				+ ", weight=" + weight + ", wrhCd=" + wrhCd + "]";
	}
	
	

}