package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MTREQHMT database table.
 * 
 */
@Entity
public class Mtreqhmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MtreqhmtPK id;

	private String alert;

    @Temporal( TemporalType.DATE)
	@Column(name="APR_DT_1")
	private Date aprDt1;

    @Temporal( TemporalType.DATE)
	@Column(name="APR_DT_2")
	private Date aprDt2;

	@Column(name="APR_UID_1")
	private String aprUid1;

	@Column(name="APR_UID_2")
	private String aprUid2;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="EXP_CD")
	private String expCd;

	@Column(name="GL_CD")
	private String glCd;

	@Column(name="ISSUE_DEPT_ID")
	private String issueDeptId;

	@Column(name="ISSUE_DOC_NO")
	private String issueDocNo;

	@Column(name="ISSUE_DOC_PF")
	private String issueDocPf;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

    @Temporal( TemporalType.DATE)
	@Column(name="QTY_APR_DT")
	private Date qtyAprDt;

	@Column(name="QTY_APR_UID")
	private String qtyAprUid;

	@Column(name="REF_1")
	private String ref1;

	@Column(name="REF_2")
	private String ref2;

	@Column(name="REF_3")
	private String ref3;

	@Column(name="REF_4")
	private String ref4;

    @Temporal( TemporalType.DATE)
	@Column(name="REJ_DT")
	private Date rejDt;

	@Column(name="REJ_UID")
	private String rejUid;

	private String remarks;

	@Column(name="REQ_COST")
	private BigDecimal reqCost;

    @Temporal( TemporalType.DATE)
	@Column(name="REQ_DT")
	private Date reqDt;

	@Column(name="REQ_FROM")
	private BigDecimal reqFrom;

	@Column(name="REQ_SOURCE")
	private String reqSource;

	private BigDecimal status;

	@Column(name="SUB_AC")
	private String subAc;

	@Column(name="TRX_TYPE")
	private BigDecimal trxType;

	@Column(name="WRH_CD")
	private String wrhCd;

	@Column(name="WRH_DEPT_ID")
	private String wrhDeptId;

    public Mtreqhmt() {
    }

	public MtreqhmtPK getId() {
		return this.id;
	}

	public void setId(MtreqhmtPK id) {
		this.id = id;
	}
	
	public String getAlert() {
		return this.alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public Date getAprDt1() {
		return this.aprDt1;
	}

	public void setAprDt1(Date aprDt1) {
		this.aprDt1 = aprDt1;
	}

	public Date getAprDt2() {
		return this.aprDt2;
	}

	public void setAprDt2(Date aprDt2) {
		this.aprDt2 = aprDt2;
	}

	public String getAprUid1() {
		return this.aprUid1;
	}

	public void setAprUid1(String aprUid1) {
		this.aprUid1 = aprUid1;
	}

	public String getAprUid2() {
		return this.aprUid2;
	}

	public void setAprUid2(String aprUid2) {
		this.aprUid2 = aprUid2;
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

	public String getExpCd() {
		return this.expCd;
	}

	public void setExpCd(String expCd) {
		this.expCd = expCd;
	}

	public String getGlCd() {
		return this.glCd;
	}

	public void setGlCd(String glCd) {
		this.glCd = glCd;
	}

	public String getIssueDeptId() {
		return this.issueDeptId;
	}

	public void setIssueDeptId(String issueDeptId) {
		this.issueDeptId = issueDeptId;
	}

	public String getIssueDocNo() {
		return this.issueDocNo;
	}

	public void setIssueDocNo(String issueDocNo) {
		this.issueDocNo = issueDocNo;
	}

	public String getIssueDocPf() {
		return this.issueDocPf;
	}

	public void setIssueDocPf(String issueDocPf) {
		this.issueDocPf = issueDocPf;
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

	public Date getQtyAprDt() {
		return this.qtyAprDt;
	}

	public void setQtyAprDt(Date qtyAprDt) {
		this.qtyAprDt = qtyAprDt;
	}

	public String getQtyAprUid() {
		return this.qtyAprUid;
	}

	public void setQtyAprUid(String qtyAprUid) {
		this.qtyAprUid = qtyAprUid;
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

	public Date getRejDt() {
		return this.rejDt;
	}

	public void setRejDt(Date rejDt) {
		this.rejDt = rejDt;
	}

	public String getRejUid() {
		return this.rejUid;
	}

	public void setRejUid(String rejUid) {
		this.rejUid = rejUid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getReqCost() {
		return this.reqCost;
	}

	public void setReqCost(BigDecimal reqCost) {
		this.reqCost = reqCost;
	}

	public Date getReqDt() {
		return this.reqDt;
	}

	public void setReqDt(Date reqDt) {
		this.reqDt = reqDt;
	}

	public BigDecimal getReqFrom() {
		return this.reqFrom;
	}

	public void setReqFrom(BigDecimal reqFrom) {
		this.reqFrom = reqFrom;
	}

	public String getReqSource() {
		return this.reqSource;
	}

	public void setReqSource(String reqSource) {
		this.reqSource = reqSource;
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

	public BigDecimal getTrxType() {
		return this.trxType;
	}

	public void setTrxType(BigDecimal trxType) {
		this.trxType = trxType;
	}

	public String getWrhCd() {
		return this.wrhCd;
	}

	public void setWrhCd(String wrhCd) {
		this.wrhCd = wrhCd;
	}

	public String getWrhDeptId() {
		return this.wrhDeptId;
	}

	public void setWrhDeptId(String wrhDeptId) {
		this.wrhDeptId = wrhDeptId;
	}

}