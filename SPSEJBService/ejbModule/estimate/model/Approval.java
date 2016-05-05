package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the APPROVAL database table.
 * 
 */
@Entity
public class Approval implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="APPROVAL_ID")
	private String approvalId;

	@Column(name="APPROVAL_TYPE")
	private String approvalType;

	@Column(name="APPROVED_BY")
	private String approvedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="APPROVED_DATE")
	private Date approvedDate;

	@Column(name="APPROVED_LEVEL")
	private String approvedLevel;

	@Column(name="APPROVED_TIME")
	private String approvedTime;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="DETAILED_COST")
	private BigDecimal detailedCost;

	@Column(name="FROM_STATUS")
	private String fromStatus;

	private String reason;

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	@Column(name="STANDARD_COST")
	private BigDecimal standardCost;

	@Column(name="TO_STATUS")
	private String toStatus;

    public Approval() {
    }

	public String getApprovalId() {
		return this.approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalType() {
		return this.approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedLevel() {
		return this.approvedLevel;
	}

	public void setApprovedLevel(String approvedLevel) {
		this.approvedLevel = approvedLevel;
	}

	public String getApprovedTime() {
		return this.approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public BigDecimal getDetailedCost() {
		return this.detailedCost;
	}

	public void setDetailedCost(BigDecimal detailedCost) {
		this.detailedCost = detailedCost;
	}

	public String getFromStatus() {
		return this.fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public BigDecimal getStandardCost() {
		return this.standardCost;
	}

	public void setStandardCost(BigDecimal standardCost) {
		this.standardCost = standardCost;
	}

	public String getToStatus() {
		return this.toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

}