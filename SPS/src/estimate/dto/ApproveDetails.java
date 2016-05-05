package estimate.dto;

import java.io.Serializable;
import java.util.Date;


public class ApproveDetails implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String approvedBy;	
	private Date approvedDate;
	private Date rejectedDate;
	private String rejectedBy;
	private String planningByPE;
	private Date planningDatePE;
	private Date valiadteDateEE;
	private String valiadteByEE;
	private Date valiadteDateCE;
	private String valiadteByCE;
	private String rejectedReasonCE;
	private String rejectedReasonPE;
	private String rejectedReasonEE;
	
	public ApproveDetails() {
		super();
	}
	
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getRejectedBy() {
		return rejectedBy;
	}
	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}
	
	
	public String getPlanningByPE() {
		return planningByPE;
	}
	public void setPlanningByPE(String planningByPE) {
		this.planningByPE = planningByPE;
	}
	
	public String getValiadteByEE() {
		return valiadteByEE;
	}
	public void setValiadteByEE(String valiadteByEE) {
		this.valiadteByEE = valiadteByEE;
	}
	
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getRejectedDate() {
		return rejectedDate;
	}
	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	public Date getPlanningDatePE() {
		return planningDatePE;
	}
	public void setPlanningDatePE(Date planningDatePE) {
		this.planningDatePE = planningDatePE;
	}
	public Date getValiadteDateEE() {
		return valiadteDateEE;
	}
	public void setValiadteDateEE(Date valiadteDateEE) {
		this.valiadteDateEE = valiadteDateEE;
	}
	public Date getValiadteDateCE() {
		return valiadteDateCE;
	}
	public void setValiadteDateCE(Date valiadteDateCE) {
		this.valiadteDateCE = valiadteDateCE;
	}
	public String getValiadteByCE() {
		return valiadteByCE;
	}
	public void setValiadteByCE(String valiadteByCE) {
		this.valiadteByCE = valiadteByCE;
	}
	public String getRejectedReasonCE() {
		return rejectedReasonCE;
	}
	public void setRejectedReasonCE(String rejectedReasonCE) {
		this.rejectedReasonCE = rejectedReasonCE;
	}
	public String getRejectedReasonPE() {
		return rejectedReasonPE;
	}
	public void setRejectedReasonPE(String rejectedReasonPE) {
		this.rejectedReasonPE = rejectedReasonPE;
	}
	public String getRejectedReasonEE() {
		return rejectedReasonEE;
	}
	public void setRejectedReasonEE(String rejectedReasonEE) {
		this.rejectedReasonEE = rejectedReasonEE;
	}

	@Override
	public String toString() {
		return "ApproveDetails [approvedBy=" + approvedBy + ", rejectedBy="
				+ rejectedBy + ", rejectedReasonEE=" + rejectedReasonEE
				+ ", rejectedReasonPE=" + rejectedReasonPE + ", rejectedReasonCE="
				+ rejectedReasonCE + ", valiadteByCE=" + valiadteByCE + ", valiadteDateCE="
				+ valiadteDateCE + ", valiadteDateEE=" + valiadteDateEE + ", planningDatePE="
				+ planningDatePE + ", rejectedDate=" + rejectedDate + "]";
	}
}
