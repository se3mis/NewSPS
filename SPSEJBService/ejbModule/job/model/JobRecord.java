package job.model;

import java.io.Serializable;
import java.util.Date;



@SuppressWarnings("serial")
public class JobRecord implements Serializable {
	
	private String estimateNo;
	private Date estimateDate;
	private String jobDescription;
	
	public JobRecord() {	
		
	}
	
	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public JobRecord(String estimateNo, Date estimateDate,
			String jobDescription ) {
		super();
		this.estimateNo = estimateNo;
		this.estimateDate = estimateDate;
		this.jobDescription = jobDescription;
		
	}
	
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

		
	@Override
	public String toString() {
		return "JobRecord [estimateNo=" + estimateNo
				+ ", estimateDate=" + estimateDate 
				+ ", jobDescription=" + jobDescription
				+ "]\n";
	}
}
