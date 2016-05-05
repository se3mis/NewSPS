package job.web;

import java.math.BigDecimal;



public class AllocateDetails {
	
	private String jobNo;
	private String consumerName;
	private Double jobAmount;
	private String jobAllocate;
	public String getJobNo() {
		return jobNo;
	}
	@Override
	public String toString() {
		return "AllocateDetails [jobNo=" + jobNo + ", consumerName="
				+ consumerName + ", jobAmount=" + jobAmount + ", jobAllocate="
				+ jobAllocate + "]";
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	
	public Double getJobAmount() {
		return jobAmount;
	}
	public void setJobAmount(Double jobAmount) {
		this.jobAmount = jobAmount;
	}
	public String getJobAllocate() {
		return jobAllocate;
	}
	public void setJobAllocate(String jobAllocate) {
		this.jobAllocate = jobAllocate;
	}
	
	public AllocateDetails(String jobNo, String consumerName,
			Double jobAmount, String jobAllocate ) {
		super();
		this.jobNo = jobNo;
		this.consumerName = consumerName;
		this.jobAmount = jobAmount;
		this.jobAllocate = jobAllocate;
		
	}
	
	public AllocateDetails(String jobNo, String consumerName
			) {
		super();
		this.jobNo = jobNo;
		this.consumerName = consumerName;
		
		
	}
	
	public AllocateDetails(String jobNo, String consumerName , Double jobAmount	) {
		super();
		this.jobNo = jobNo;
		this.consumerName = consumerName;
		this.jobAmount = jobAmount;


}

	
	

}
