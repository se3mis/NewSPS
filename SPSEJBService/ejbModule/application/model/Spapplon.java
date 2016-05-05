package application.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPAPPLON database table.
 * 
 */
@Entity
public class Spapplon implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpapplonPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="APPROVAL_DS")
	private String approvalDs;

	@Column(name="APPROVAL_SBM")
	private String approvalSbm;

	@Column(name="APPROVAL_SDO")
	private String approvalSdo;

	private BigDecimal installment;

	@Column(name="INTEREST_RATE")
	private BigDecimal interestRate;

	@Column(name="IS_LOAN_4_SHARE")
	private String isLoan4Share;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_REFERENCE")
	private String loanReference;

	@Column(name="MEMBER_OF_SAMURDHI")
	private String memberOfSamurdhi;

	@Column(name="NO_OF_SHARES")
	private BigDecimal noOfShares;

	@Column(name="SAMURDHI_ID")
	private String samurdhiId;

	@Column(name="SAMURDHI_SHARE_PRICE")
	private BigDecimal samurdhiSharePrice;

	@Column(name="TOTAL_INTEREST")
	private BigDecimal totalInterest;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	private BigDecimal years;

    public Spapplon() {
    }

	public SpapplonPK getId() {
		return this.id;
	}

	public void setId(SpapplonPK id) {
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

	public String getApprovalDs() {
		return this.approvalDs;
	}

	public void setApprovalDs(String approvalDs) {
		this.approvalDs = approvalDs;
	}

	public String getApprovalSbm() {
		return this.approvalSbm;
	}

	public void setApprovalSbm(String approvalSbm) {
		this.approvalSbm = approvalSbm;
	}

	public String getApprovalSdo() {
		return this.approvalSdo;
	}

	public void setApprovalSdo(String approvalSdo) {
		this.approvalSdo = approvalSdo;
	}

	public BigDecimal getInstallment() {
		return this.installment;
	}

	public void setInstallment(BigDecimal installment) {
		this.installment = installment;
	}

	public BigDecimal getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getIsLoan4Share() {
		return this.isLoan4Share;
	}

	public void setIsLoan4Share(String isLoan4Share) {
		this.isLoan4Share = isLoan4Share;
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

	public String getMemberOfSamurdhi() {
		return this.memberOfSamurdhi;
	}

	public void setMemberOfSamurdhi(String memberOfSamurdhi) {
		this.memberOfSamurdhi = memberOfSamurdhi;
	}

	public BigDecimal getNoOfShares() {
		return this.noOfShares;
	}

	public void setNoOfShares(BigDecimal noOfShares) {
		this.noOfShares = noOfShares;
	}

	public String getSamurdhiId() {
		return this.samurdhiId;
	}

	public void setSamurdhiId(String samurdhiId) {
		this.samurdhiId = samurdhiId;
	}

	public BigDecimal getSamurdhiSharePrice() {
		return this.samurdhiSharePrice;
	}

	public void setSamurdhiSharePrice(BigDecimal samurdhiSharePrice) {
		this.samurdhiSharePrice = samurdhiSharePrice;
	}

	public BigDecimal getTotalInterest() {
		return this.totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
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

	public BigDecimal getYears() {
		return this.years;
	}

	public void setYears(BigDecimal years) {
		this.years = years;
	}

	@Override
	public String toString() {
		return "Spapplon [id=" + id + ", addDate=" + addDate + ", addTime="
				+ addTime + ", addUser=" + addUser + ", approvalDs="
				+ approvalDs + ", approvalSbm=" + approvalSbm
				+ ", approvalSdo=" + approvalSdo + ", installment="
				+ installment + ", interestRate=" + interestRate
				+ ", isLoan4Share=" + isLoan4Share + ", loanAmount="
				+ loanAmount + ", loanReference=" + loanReference
				+ ", memberOfSamurdhi=" + memberOfSamurdhi + ", noOfShares="
				+ noOfShares + ", samurdhiId=" + samurdhiId
				+ ", samurdhiSharePrice=" + samurdhiSharePrice
				+ ", totalInterest=" + totalInterest + ", updDate=" + updDate
				+ ", updTime=" + updTime + ", updUser=" + updUser + ", years="
				+ years + "]";
	}

}