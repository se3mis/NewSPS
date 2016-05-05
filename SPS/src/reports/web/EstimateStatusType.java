package reports.web;

public class EstimateStatusType {
	private String estimateStatus;
	private String statusID;
	
	
	public EstimateStatusType(String estimateStatus,String statusID)
	{
		this.estimateStatus = estimateStatus;
		this.statusID = statusID;
	}


	public String getEstimateStatus() {
		return estimateStatus;
	}


	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}


	public String getStatusID() {
		return statusID;
	}


	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}


	
	 
}
