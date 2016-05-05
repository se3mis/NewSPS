package job.model;

import java.io.Serializable;
import java.util.Date;



@SuppressWarnings("serial")
public class ClosedJobInfo implements Serializable {
	private String projectNo;
	private String estimateNo;
	private String applicationType;
	private String firstName;
	private String lastName; 
	private String streetAddress;
	private String suburb;
	private String city;
	private String idNo;
	private Date submitDate;
	
	public ClosedJobInfo() {
		super();
	}

	public ClosedJobInfo(String projectNo, String estimateNo,
			String applicationType, String firstName, String lastName,
			String streetAddress, String suburb, String city,
			String idNo, Date submitDate) {
		super();
		this.projectNo = projectNo;
		this.estimateNo = estimateNo;
		this.applicationType = applicationType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.city = city;
		this.idNo = idNo;
		this.submitDate = submitDate;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	@Override
	public String toString() {
		return "ClosedJobInfo [projectNo=" + projectNo + ", estimateNo="
				+ estimateNo + ", applicationType=" + applicationType
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", streetAddress=" + streetAddress + ", suburb=" + suburb
				+ ", city=" + city + ", idNo="
				+ idNo + ", submitDate=" + submitDate + "]";
	}
	
	
	
	
	
	

}
