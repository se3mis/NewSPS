package export.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MIS_SMC_MASTER database table.
 * 
 */
@Entity
@Table(name="MIS_SMC_MASTER")
public class MisSmcMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MisSmcMasterPK id;

	@Column(name="ACCT_NUMBER")
	private String acctNumber;

    @Temporal( TemporalType.DATE)
	private Date adddate;

	@Column(name="ADDRESS_1")
	private String address1;

	@Column(name="ADDRESS_2")
	private String address2;

	@Column(name="ADDRESS_3")
	private String address3;

	@Column(name="AVG_CONSMN")
	private BigDecimal avgConsmn;

	@Column(name="BILL_TYPE")
	private String billType;

	@Column(name="CAPT_AMT")
	private BigDecimal captAmt;

	@Column(name="CAPT_AMT2")
	private BigDecimal captAmt2;

	@Column(name="CONECT_TYPE")
	private BigDecimal conectType;
	
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="CUST_CODE")
	private String custCode;

	@Column(name="CUST_FNAME")
	private String custFname;

	@Column(name="CUST_LNAME")
	private String custLname;

	@Column(name="CUST_TYPE")
	private String custType;
	
	@Column(name="CUSTOMER_CATEGORY")
	private String customerCategory;
	
	@Column(name="CUSTOMER_TYPE")
	private String customerType;

	@Column(name="DLY_PACK_NO")
	private String dlyPackNo;

	private String email;

	@Column(name="ERR_MSG")
	private String errMsg;

    @Temporal( TemporalType.DATE)
	@Column(name="INIT_RD_DT")
	private Date initRdDt;

	@Column(name="INIT_RDNG1")
	private BigDecimal initRdng1;

	@Column(name="INIT_RDNG2")
	private BigDecimal initRdng2;

	@Column(name="INIT_RDNG3")
	private BigDecimal initRdng3;

	@Column(name="INSTMNT_AMT")
	private BigDecimal instmntAmt;

	@Column(name="INSTMNT_AMT2")
	private BigDecimal instmntAmt2;

	@Column(name="INSTS_TOPAY")
	private BigDecimal instsTopay;

	@Column(name="INSTS_TOPAY2")
	private BigDecimal instsTopay2;

	private BigDecimal intrest;

	private BigDecimal intrest2;

	@Column(name="INTRST_AMT")
	private BigDecimal intrstAmt;

	@Column(name="INTRST_AMT2")
	private BigDecimal intrstAmt2;

	@Column(name="KVA_RATING")
	private BigDecimal kvaRating;

	@Column(name="LOAN_AMT")
	private BigDecimal loanAmt;

	@Column(name="LOAN_AMT2")
	private BigDecimal loanAmt2;

	@Column(name="LOAN_CODE")
	private String loanCode;

	@Column(name="LOAN_CODE2")
	private String loanCode2;

    @Temporal( TemporalType.DATE)
	@Column(name="LOAN_DATE")
	private Date loanDate;

    @Temporal( TemporalType.DATE)
	@Column(name="LOAN_DATE2")
	private Date loanDate2;

	@Column(name="MET_NO1")
	private String metNo1;

	@Column(name="MET_NO2")
	private String metNo2;

	@Column(name="MET_NO3")
	private String metNo3;

	@Column(name="MET_TYPE1")
	private String metType1;

	@Column(name="MET_TYPE2")
	private String metType2;

	@Column(name="MET_TYPE3")
	private String metType3;

	@Column(name="NBR_ACCT")
	private String nbrAcct;
	
	@Column(name="NIC_NO")
	private String nicNo;

	@Column(name="NO_OF_DIG1")
	private BigDecimal noOfDig1;

	@Column(name="NO_OF_DIG2")
	private BigDecimal noOfDig2;

	@Column(name="NO_OF_DIG3")
	private BigDecimal noOfDig3;

	@Column(name="NO_OF_METRS")
	private BigDecimal noOfMetrs;

	@Column(name="NO_OF_PHASE")
	private BigDecimal noOfPhase;

	@Column(name="PIV_NO")
	private String pivNo;
	
	@Column(name="PIV_NO_COMGEN")
	private String pivNoComgen;
	
	@Column(name="PROJECT_NO")
	private String projectNo;

    @Temporal( TemporalType.DATE)
	@Column(name="PMNT_DATE")
	private Date pmntDate;

	@Column(name="PMNT_MODE")
	private String pmntMode;

    @Temporal( TemporalType.DATE)
	@Column(name="PROC_DATE")
	private Date procDate;

	@Column(name="READER_CODE")
	private String readerCode;

	@Column(name="SEC_DEPOSIT")
	private BigDecimal secDeposit;

	@Column(name="SM_CHARGE")
	private BigDecimal smCharge;

	@Column(name="SUBSTN_CODE")
	private String substnCode;

	@Column(name="TARIFF_CODE")
	private BigDecimal tariffCode;

	@Column(name="TBL_SERL")
	private BigDecimal tblSerl;

	private String telphn1;

	private String telphn2;

	@Column(name="UPDT_FLAG")
	private String updtFlag;

	@Column(name="WALK_SEQ")
	private String walkSeq;

    public MisSmcMaster() {
    }

	public MisSmcMasterPK getId() {
		return this.id;
	}

	public void setId(MisSmcMasterPK id) {
		this.id = id;
	}
	
	public String getAcctNumber() {
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public Date getAdddate() {
		return this.adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public BigDecimal getAvgConsmn() {
		return this.avgConsmn;
	}

	public void setAvgConsmn(BigDecimal avgConsmn) {
		this.avgConsmn = avgConsmn;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public BigDecimal getCaptAmt() {
		return this.captAmt;
	}

	public void setCaptAmt(BigDecimal captAmt) {
		this.captAmt = captAmt;
	}

	public BigDecimal getCaptAmt2() {
		return this.captAmt2;
	}

	public void setCaptAmt2(BigDecimal captAmt2) {
		this.captAmt2 = captAmt2;
	}

	public BigDecimal getConectType() {
		return this.conectType;
	}

	public void setConectType(BigDecimal conectType) {
		this.conectType = conectType;
	}

	public String getCustCode() {
		return this.custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustFname() {
		return this.custFname;
	}

	public void setCustFname(String custFname) {
		this.custFname = custFname;
	}

	public String getCustLname() {
		return this.custLname;
	}

	public void setCustLname(String custLname) {
		this.custLname = custLname;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getDlyPackNo() {
		return this.dlyPackNo;
	}

	public void setDlyPackNo(String dlyPackNo) {
		this.dlyPackNo = dlyPackNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Date getInitRdDt() {
		return this.initRdDt;
	}

	public void setInitRdDt(Date initRdDt) {
		this.initRdDt = initRdDt;
	}

	public BigDecimal getInitRdng1() {
		return this.initRdng1;
	}

	public void setInitRdng1(BigDecimal initRdng1) {
		this.initRdng1 = initRdng1;
	}

	public BigDecimal getInitRdng2() {
		return this.initRdng2;
	}

	public void setInitRdng2(BigDecimal initRdng2) {
		this.initRdng2 = initRdng2;
	}

	public BigDecimal getInitRdng3() {
		return this.initRdng3;
	}

	public void setInitRdng3(BigDecimal initRdng3) {
		this.initRdng3 = initRdng3;
	}

	public BigDecimal getInstmntAmt() {
		return this.instmntAmt;
	}

	public void setInstmntAmt(BigDecimal instmntAmt) {
		this.instmntAmt = instmntAmt;
	}

	public BigDecimal getInstmntAmt2() {
		return this.instmntAmt2;
	}

	public void setInstmntAmt2(BigDecimal instmntAmt2) {
		this.instmntAmt2 = instmntAmt2;
	}

	public BigDecimal getInstsTopay() {
		return this.instsTopay;
	}

	public void setInstsTopay(BigDecimal instsTopay) {
		this.instsTopay = instsTopay;
	}

	public BigDecimal getInstsTopay2() {
		return this.instsTopay2;
	}

	public void setInstsTopay2(BigDecimal instsTopay2) {
		this.instsTopay2 = instsTopay2;
	}

	public BigDecimal getIntrest() {
		return this.intrest;
	}

	public void setIntrest(BigDecimal intrest) {
		this.intrest = intrest;
	}

	public BigDecimal getIntrest2() {
		return this.intrest2;
	}

	public void setIntrest2(BigDecimal intrest2) {
		this.intrest2 = intrest2;
	}

	public BigDecimal getIntrstAmt() {
		return this.intrstAmt;
	}

	public void setIntrstAmt(BigDecimal intrstAmt) {
		this.intrstAmt = intrstAmt;
	}

	public BigDecimal getIntrstAmt2() {
		return this.intrstAmt2;
	}

	public void setIntrstAmt2(BigDecimal intrstAmt2) {
		this.intrstAmt2 = intrstAmt2;
	}

	public BigDecimal getKvaRating() {
		return this.kvaRating;
	}

	public void setKvaRating(BigDecimal kvaRating) {
		this.kvaRating = kvaRating;
	}

	public BigDecimal getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	public BigDecimal getLoanAmt2() {
		return this.loanAmt2;
	}

	public void setLoanAmt2(BigDecimal loanAmt2) {
		this.loanAmt2 = loanAmt2;
	}

	public String getLoanCode() {
		return this.loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getLoanCode2() {
		return this.loanCode2;
	}

	public void setLoanCode2(String loanCode2) {
		this.loanCode2 = loanCode2;
	}

	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getLoanDate2() {
		return this.loanDate2;
	}

	public void setLoanDate2(Date loanDate2) {
		this.loanDate2 = loanDate2;
	}

	public String getMetNo1() {
		return this.metNo1;
	}

	public void setMetNo1(String metNo1) {
		this.metNo1 = metNo1;
	}

	public String getMetNo2() {
		return this.metNo2;
	}

	public void setMetNo2(String metNo2) {
		this.metNo2 = metNo2;
	}

	public String getMetNo3() {
		return this.metNo3;
	}

	public void setMetNo3(String metNo3) {
		this.metNo3 = metNo3;
	}

	public String getMetType1() {
		return this.metType1;
	}

	public void setMetType1(String metType1) {
		this.metType1 = metType1;
	}

	public String getMetType2() {
		return this.metType2;
	}

	public void setMetType2(String metType2) {
		this.metType2 = metType2;
	}

	public String getMetType3() {
		return this.metType3;
	}

	public void setMetType3(String metType3) {
		this.metType3 = metType3;
	}

	public String getNicNo() {
		return this.nicNo;
	}

	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

	public BigDecimal getNoOfDig1() {
		return this.noOfDig1;
	}

	public void setNoOfDig1(BigDecimal noOfDig1) {
		this.noOfDig1 = noOfDig1;
	}

	public BigDecimal getNoOfDig2() {
		return this.noOfDig2;
	}

	public void setNoOfDig2(BigDecimal noOfDig2) {
		this.noOfDig2 = noOfDig2;
	}

	public BigDecimal getNoOfDig3() {
		return this.noOfDig3;
	}

	public void setNoOfDig3(BigDecimal noOfDig3) {
		this.noOfDig3 = noOfDig3;
	}

	public BigDecimal getNoOfMetrs() {
		return this.noOfMetrs;
	}

	public void setNoOfMetrs(BigDecimal noOfMetrs) {
		this.noOfMetrs = noOfMetrs;
	}

	public BigDecimal getNoOfPhase() {
		return this.noOfPhase;
	}

	public void setNoOfPhase(BigDecimal noOfPhase) {
		this.noOfPhase = noOfPhase;
	}

	public String getPivNo() {
		return this.pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public Date getPmntDate() {
		return this.pmntDate;
	}

	public void setPmntDate(Date pmntDate) {
		this.pmntDate = pmntDate;
	}

	public String getPmntMode() {
		return this.pmntMode;
	}

	public void setPmntMode(String pmntMode) {
		this.pmntMode = pmntMode;
	}

	public Date getProcDate() {
		return this.procDate;
	}

	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}

	public String getReaderCode() {
		return this.readerCode;
	}

	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}

	public BigDecimal getSecDeposit() {
		return this.secDeposit;
	}

	public void setSecDeposit(BigDecimal secDeposit) {
		this.secDeposit = secDeposit;
	}

	public BigDecimal getSmCharge() {
		return this.smCharge;
	}

	public void setSmCharge(BigDecimal smCharge) {
		this.smCharge = smCharge;
	}

	public String getSubstnCode() {
		return this.substnCode;
	}

	public void setSubstnCode(String substnCode) {
		this.substnCode = substnCode;
	}

	public BigDecimal getTariffCode() {
		return this.tariffCode;
	}

	public void setTariffCode(BigDecimal tariffCode) {
		this.tariffCode = tariffCode;
	}

	public BigDecimal getTblSerl() {
		return this.tblSerl;
	}

	public void setTblSerl(BigDecimal tblSerl) {
		this.tblSerl = tblSerl;
	}

	public String getTelphn1() {
		return this.telphn1;
	}

	public void setTelphn1(String telphn1) {
		this.telphn1 = telphn1;
	}

	public String getTelphn2() {
		return this.telphn2;
	}

	public void setTelphn2(String telphn2) {
		this.telphn2 = telphn2;
	}

	public String getUpdtFlag() {
		return this.updtFlag;
	}

	public void setUpdtFlag(String updtFlag) {
		this.updtFlag = updtFlag;
	}

	public String getWalkSeq() {
		return this.walkSeq;
	}

	public void setWalkSeq(String walkSeq) {
		this.walkSeq = walkSeq;
	}
	
	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getNbrAcct() {
		return nbrAcct;
	}

	public void setNbrAcct(String nbrAcct) {
		this.nbrAcct = nbrAcct;
	}

	public String getPivNoComgen() {
		return pivNoComgen;
	}

	public void setPivNoComgen(String pivNoComgen) {
		this.pivNoComgen = pivNoComgen;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Override
	public String toString() {
		return "MisSmcMaster [id=" + id + ", acctNumber=" + acctNumber
				+ ", adddate=" + adddate + ", address1=" + address1
				+ ", address2=" + address2 + ", address3=" + address3
				+ ", avgConsmn=" + avgConsmn + ", billType=" + billType
				+ ", captAmt=" + captAmt + ", captAmt2=" + captAmt2
				+ ", conectType=" + conectType + ", deptId=" + deptId
				+ ", custCode=" + custCode + ", custFname=" + custFname
				+ ", custLname=" + custLname + ", custType=" + custType
				+ ", customerCategory=" + customerCategory + ", customerType="
				+ customerType + ", dlyPackNo=" + dlyPackNo + ", email="
				+ email + ", errMsg=" + errMsg + ", initRdDt=" + initRdDt
				+ ", initRdng1=" + initRdng1 + ", initRdng2=" + initRdng2
				+ ", initRdng3=" + initRdng3 + ", instmntAmt=" + instmntAmt
				+ ", instmntAmt2=" + instmntAmt2 + ", instsTopay=" + instsTopay
				+ ", instsTopay2=" + instsTopay2 + ", intrest=" + intrest
				+ ", intrest2=" + intrest2 + ", intrstAmt=" + intrstAmt
				+ ", intrstAmt2=" + intrstAmt2 + ", kvaRating=" + kvaRating
				+ ", loanAmt=" + loanAmt + ", loanAmt2=" + loanAmt2
				+ ", loanCode=" + loanCode + ", loanCode2=" + loanCode2
				+ ", loanDate=" + loanDate + ", loanDate2=" + loanDate2
				+ ", metNo1=" + metNo1 + ", metNo2=" + metNo2 + ", metNo3="
				+ metNo3 + ", metType1=" + metType1 + ", metType2=" + metType2
				+ ", metType3=" + metType3 + ", nbrAcct=" + nbrAcct
				+ ", nicNo=" + nicNo + ", noOfDig1=" + noOfDig1 + ", noOfDig2="
				+ noOfDig2 + ", noOfDig3=" + noOfDig3 + ", noOfMetrs="
				+ noOfMetrs + ", noOfPhase=" + noOfPhase + ", pivNo=" + pivNo
				+ ", pivNoComgen=" + pivNoComgen + ", projectNo=" + projectNo
				+ ", pmntDate=" + pmntDate + ", pmntMode=" + pmntMode
				+ ", procDate=" + procDate + ", readerCode=" + readerCode
				+ ", secDeposit=" + secDeposit + ", smCharge=" + smCharge
				+ ", substnCode=" + substnCode + ", tariffCode=" + tariffCode
				+ ", tblSerl=" + tblSerl + ", telphn1=" + telphn1
				+ ", telphn2=" + telphn2 + ", updtFlag=" + updtFlag
				+ ", walkSeq=" + walkSeq + "]";
	}

}