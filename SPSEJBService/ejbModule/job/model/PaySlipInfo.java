package job.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class PaySlipInfo implements Serializable{
	private String docNo;
	private List<String> jobNos;
	private BigDecimal paySlipAmount;
	private Date chequeDate;
	
	
	
	
	public PaySlipInfo() {
		super();
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public List<String> getJobNos() {
		return jobNos;
	}
	public void setJobNos(List<String> jobNos) {
		this.jobNos = jobNos;
	}
	public BigDecimal getPaySlipAmount() {
		return paySlipAmount;
	}
	public void setPaySlipAmount(BigDecimal paySlipAmount) {
		this.paySlipAmount = paySlipAmount;
	}
	public Date getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	@Override
	public String toString() {
		return "PaySlipInfo [docNo=" + docNo + ", jobNos=" + jobNos
				+ ", paySlipAmount=" + paySlipAmount + ", chequeDate="
				+ chequeDate + "]";
	}
	
	
	

}
