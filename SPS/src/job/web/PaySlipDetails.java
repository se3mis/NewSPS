package job.web;

import java.util.Date;

public class PaySlipDetails {

	private String jobNo;
	private String StringdocNo;
	private String StringpaySlipAmount;
	
	public PaySlipDetails(String jobNo, String stringdocNo,
			String stringpaySlipAmount, Date chequeDate) {
		super();
		this.jobNo = jobNo;
		StringdocNo = stringdocNo;
		StringpaySlipAmount = stringpaySlipAmount;
		this.chequeDate = chequeDate;
	}
	public PaySlipDetails() {
		super();
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getStringdocNo() {
		return StringdocNo;
	}
	public void setStringdocNo(String stringdocNo) {
		StringdocNo = stringdocNo;
	}
	public String getStringpaySlipAmount() {
		return StringpaySlipAmount;
	}
	public void setStringpaySlipAmount(String stringpaySlipAmount) {
		StringpaySlipAmount = stringpaySlipAmount;
	}
	public Date getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	private Date chequeDate;
}
