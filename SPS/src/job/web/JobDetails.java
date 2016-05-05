package job.web;

import java.util.Date;

public class JobDetails {

	public JobDetails(String jobNo ) {
		super();
		this.jobNo = jobNo;
				
	}	

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	
	private String jobNo;
	
}
