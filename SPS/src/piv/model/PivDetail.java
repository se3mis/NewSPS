package piv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PIV_DETAIL database table.
 * 
 */
@Entity
@Table(name="PIV_DETAIL")
public class PivDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivDetailPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="APP_REFERENCE_NO")
	private String appReferenceNo;

	@Column(name="APPROVED_BY")
	private String approvedBy;

	@Column(name="BANK_CODE")
	private String bankCode;

    @Temporal( TemporalType.DATE)
	@Column(name="BANK_PAID_DATE")
	private Date bankPaidDate;

	@Column(name="BRANCH_CODE")
	private String branchCode;

    @Temporal( TemporalType.DATE)
	@Column(name="CANCEL_DATE")
	private Date cancelDate;

	@Column(name="CANCEL_TIME")
	private String cancelTime;

	@Column(name="CANCEL_USER")
	private String cancelUser;

	@Column(name="CASH_IN_TRANSIT")
	private BigDecimal cashInTransit;

	@Column(name="CEB_BRANCH_NAME")
	private String cebBranchName;

	@Column(name="CHEQUE_BANK_CODE")
	private String chequeBankCode;

	@Column(name="CHEQUE_BRANCH_CODE")
	private String chequeBranchCode;

    @Temporal( TemporalType.DATE)
	@Column(name="CHEQUE_DATE")
	private Date chequeDate;

	@Column(name="CHEQUE_NO")
	private BigDecimal chequeNo;

	@Column(name="CONFIRMED_BY")
	private String confirmedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CONFIRMED_DATE")
	private Date confirmedDate;

	@Column(name="CONFIRMED_TIME")
	private String confirmedTime;

	private String description;

	@Column(name="ELECTRICITY_DEBTORS")
	private BigDecimal electricityDebtors;

	@Column(name="EST_REFERENCE_NO")
	private String estReferenceNo;

	@Column(name="FOR_DISHONORED_CHEQUE")
	private BigDecimal forDishonoredCheque;

	@Column(name="GRAND_TOTAL")
	private BigDecimal grandTotal;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_REFERENCE")
	private String loanReference;

	@Column(name="MISCELLANEOUS_DEPOSIT")
	private BigDecimal miscellaneousDeposit;

	@Column(name="MISCELLANEOUS_INCOME")
	private BigDecimal miscellaneousIncome;

    @Temporal( TemporalType.DATE)
	@Column(name="PAID_DATE")
	private Date paidDate;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	@Column(name="PIV_AMOUNT")
	private BigDecimal pivAmount;

    @Temporal( TemporalType.DATE)
	@Column(name="PIV_DATE")
	private Date pivDate;

	@Column(name="PIV_RECEIPT_NO")
	private String pivReceiptNo;

	@Column(name="PIV_SEQ_NO")
	private BigDecimal pivSeqNo;

	@Column(name="POS_A")
	private String posA;

	@Column(name="POS_CENTER")
	private String posCenter;

	@Column(name="PREPARED_BY")
	private String preparedBy;

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	@Column(name="REFERENCE_TYPE")
	private String referenceType;

	@Column(name="REVISE_REFERENCE_NO")
	private String reviseReferenceNo;

	@Column(name="SECURITY_DEPOSIT")
	private BigDecimal securityDeposit;

	@Column(name="SER_CONN_OR_ELEC_SCH")
	private BigDecimal serConnOrElecSch;

	private String status;

	@Column(name="SUB_TOTAL")
	private BigDecimal subTotal;

	@Column(name="TENDER_DEPOSIT")
	private BigDecimal tenderDeposit;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	
	@Column(name="VAT")
	private BigDecimal vat;
	
	@Column(name="NBT_AMOUNT")
	private BigDecimal nbt;

    public PivDetail() {
    }

	public PivDetailPK getId() {
		return this.id;
	}

	public void setId(PivDetailPK id) {
		this.id = id;
	}
	
	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAppReferenceNo() {
		return this.appReferenceNo;
	}

	public void setAppReferenceNo(String appReferenceNo) {
		this.appReferenceNo = appReferenceNo;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getBankPaidDate() {
		return this.bankPaidDate;
	}

	public void setBankPaidDate(Date bankPaidDate) {
		this.bankPaidDate = bankPaidDate;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelUser() {
		return this.cancelUser;
	}

	public void setCancelUser(String cancelUser) {
		this.cancelUser = cancelUser;
	}

	public BigDecimal getCashInTransit() {
		return this.cashInTransit;
	}

	public void setCashInTransit(BigDecimal cashInTransit) {
		this.cashInTransit = cashInTransit;
	}

	public String getCebBranchName() {
		return this.cebBranchName;
	}

	public void setCebBranchName(String cebBranchName) {
		this.cebBranchName = cebBranchName;
	}

	public String getChequeBankCode() {
		return this.chequeBankCode;
	}

	public void setChequeBankCode(String chequeBankCode) {
		this.chequeBankCode = chequeBankCode;
	}

	public String getChequeBranchCode() {
		return this.chequeBranchCode;
	}

	public void setChequeBranchCode(String chequeBranchCode) {
		this.chequeBranchCode = chequeBranchCode;
	}

	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public BigDecimal getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(BigDecimal chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getConfirmedBy() {
		return this.confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public Date getConfirmedDate() {
		return this.confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getConfirmedTime() {
		return this.confirmedTime;
	}

	public void setConfirmedTime(String confirmedTime) {
		this.confirmedTime = confirmedTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getElectricityDebtors() {
		return this.electricityDebtors;
	}

	public void setElectricityDebtors(BigDecimal electricityDebtors) {
		this.electricityDebtors = electricityDebtors;
	}

	public String getEstReferenceNo() {
		return this.estReferenceNo;
	}

	public void setEstReferenceNo(String estReferenceNo) {
		this.estReferenceNo = estReferenceNo;
	}

	public BigDecimal getForDishonoredCheque() {
		return this.forDishonoredCheque;
	}

	public void setForDishonoredCheque(BigDecimal forDishonoredCheque) {
		this.forDishonoredCheque = forDishonoredCheque;
	}

	public BigDecimal getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanReference() {
		return this.loanReference;
	}

	public void setLoanReference(String loanReference) {
		this.loanReference = loanReference;
	}

	public BigDecimal getMiscellaneousDeposit() {
		return this.miscellaneousDeposit;
	}

	public void setMiscellaneousDeposit(BigDecimal miscellaneousDeposit) {
		this.miscellaneousDeposit = miscellaneousDeposit;
	}

	public BigDecimal getMiscellaneousIncome() {
		return this.miscellaneousIncome;
	}

	public void setMiscellaneousIncome(BigDecimal miscellaneousIncome) {
		this.miscellaneousIncome = miscellaneousIncome;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getPivAmount() {
		return this.pivAmount;
	}

	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}

	public Date getPivDate() {
		return this.pivDate;
	}

	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}

	public String getPivReceiptNo() {
		return this.pivReceiptNo;
	}

	public void setPivReceiptNo(String pivReceiptNo) {
		this.pivReceiptNo = pivReceiptNo;
	}

	public BigDecimal getPivSeqNo() {
		return this.pivSeqNo;
	}

	public void setPivSeqNo(BigDecimal pivSeqNo) {
		this.pivSeqNo = pivSeqNo;
	}

	public String getPosA() {
		return this.posA;
	}

	public void setPosA(String posA) {
		this.posA = posA;
	}

	public String getPosCenter() {
		return this.posCenter;
	}

	public void setPosCenter(String posCenter) {
		this.posCenter = posCenter;
	}

	public String getPreparedBy() {
		return this.preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReviseReferenceNo() {
		return this.reviseReferenceNo;
	}

	public void setReviseReferenceNo(String reviseReferenceNo) {
		this.reviseReferenceNo = reviseReferenceNo;
	}

	public BigDecimal getSecurityDeposit() {
		return this.securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public BigDecimal getSerConnOrElecSch() {
		return this.serConnOrElecSch;
	}

	public void setSerConnOrElecSch(BigDecimal serConnOrElecSch) {
		this.serConnOrElecSch = serConnOrElecSch;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTenderDeposit() {
		return this.tenderDeposit;
	}

	public void setTenderDeposit(BigDecimal tenderDeposit) {
		this.tenderDeposit = tenderDeposit;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	
	public BigDecimal getNbt() {
		return this.nbt;
	}

	public void setNbt(BigDecimal nbt) {
		this.nbt = nbt;
	}

	@Override
	public String toString() {
		return "PivDetail [id=" + id + ", addDate=" + addDate + ", addTime="
				+ addTime + ", addUser=" + addUser + ", appReferenceNo="
				+ appReferenceNo + ", approvedBy=" + approvedBy + ", bankCode="
				+ bankCode + ", bankPaidDate=" + bankPaidDate + ", branchCode="
				+ branchCode + ", cancelDate=" + cancelDate + ", cancelTime="
				+ cancelTime + ", cancelUser=" + cancelUser
				+ ", cashInTransit=" + cashInTransit + ", cebBranchName="
				+ cebBranchName + ", chequeBankCode=" + chequeBankCode
				+ ", chequeBranchCode=" + chequeBranchCode + ", chequeDate="
				+ chequeDate + ", chequeNo=" + chequeNo + ", confirmedBy="
				+ confirmedBy + ", confirmedDate=" + confirmedDate
				+ ", confirmedTime=" + confirmedTime + ", description="
				+ description + ", electricityDebtors=" + electricityDebtors
				+ ", estReferenceNo=" + estReferenceNo
				+ ", forDishonoredCheque=" + forDishonoredCheque
				+ ", grandTotal=" + grandTotal + ", idNo=" + idNo
				+ ", loanAmount=" + loanAmount + ", loanReference="
				+ loanReference + ", miscellaneousDeposit="
				+ miscellaneousDeposit + ", miscellaneousIncome="
				+ miscellaneousIncome + ", paidDate=" + paidDate
				+ ", paymentMode=" + paymentMode + ", pivAmount=" + pivAmount
				+ ", pivDate=" + pivDate + ", pivReceiptNo=" + pivReceiptNo
				+ ", pivSeqNo=" + pivSeqNo + ", posA=" + posA + ", posCenter="
				+ posCenter + ", preparedBy=" + preparedBy + ", referenceNo="
				+ referenceNo + ", referenceType=" + referenceType
				+ ", reviseReferenceNo=" + reviseReferenceNo
				+ ", securityDeposit=" + securityDeposit
				+ ", serConnOrElecSch=" + serConnOrElecSch + ", status="
				+ status + ", subTotal=" + subTotal + ", tenderDeposit="
				+ tenderDeposit + ", updDate=" + updDate + ", updTime="
				+ updTime + ", updUser=" + updUser + ", vat=" + vat + ", nbt=" + nbt + " ]";
	}

}