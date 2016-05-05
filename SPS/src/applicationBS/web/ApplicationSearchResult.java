package applicationBS.web;

import java.util.Date;

public class ApplicationSearchResult {

	private String nic;
	private String name;
	private String address;
	private String applicationID;
	private String applicationNumber;
	private String applicationType;
	private Date submitedDate;
	private String applicationStatus;
	 private String estimateStatus;
	 
	 
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public Date getSubmitedDate() {
		return submitedDate;
	}
	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getEstimateStatus() {
		return estimateStatus;
	}
	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}
	 
	 
}
