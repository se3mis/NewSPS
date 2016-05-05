package calendarBS.web;

import java.io.Serializable;

public class Schedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scheduleNo;
	private String description;
	private String time;
	private String constructionRef;
	private String allocatedBy;
	private String allocatedTo;
	private String applicationNo;
	private String appointmentType;
	private String selectedcostCentre;
	private String appointmentStatus;
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getSelectedcostCentre() {
		return selectedcostCentre;
	}
	public void setSelectedcostCentre(String selectedcostCentre) {
		this.selectedcostCentre = selectedcostCentre;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	public String getAllocatedBy() {
		return allocatedBy;
	}
	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}
	public String getAllocatedTo() {
		return allocatedTo;
	}
	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getConstructionRef() {
		return constructionRef;
	}
	public void setConstructionRef(String constructionRef) {
		this.constructionRef = constructionRef;
	}
	public Schedule(String scheduleNo, String description,
			String time,String appointUserName,String allocatedTo,String applicationNo,String constrictionRef,String appointmentType ,String selectedcostCentre, String appointmentStatus) {
		super();
		this.scheduleNo = scheduleNo;
		this.description = description;
		this.allocatedBy = appointUserName;
		this.allocatedTo = allocatedTo;
		this.constructionRef = constrictionRef;
		this.time = time;
		this.applicationNo = applicationNo;
		this.appointmentType = appointmentType;
		this.selectedcostCentre = selectedcostCentre;
		this.appointmentStatus =appointmentStatus;
		
	}
	
	
}
