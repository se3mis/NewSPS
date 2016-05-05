package calendar.web;

import java.util.Date;

public class ApplicationRecord {

	private String applicationNo;
	private Date applicationDate;
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public ApplicationRecord(String applicationNo, Date applicationDate) {
		super();
		this.applicationNo = applicationNo;
		this.applicationDate = applicationDate;
	}
	
}
