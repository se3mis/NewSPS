package calendar.web;

import java.io.Serializable;

public class Schedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scheduleNo;
	private String description;
	private String time;
	private String appointUserName;
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
	public String getAppointUserName() {
		return appointUserName;
	}
	public void setAppointUserName(String appointUserName) {
		this.appointUserName = appointUserName;
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
	
	public Schedule(String scheduleNo, String description,
			String time,String appointUserName,String applicationNo,String appointmentType ,String selectedcostCentre, String appointmentStatus) {
		super();
		this.scheduleNo = scheduleNo;
		this.description = description;
		this.appointUserName = appointUserName;
		this.time = time;
		this.applicationNo = applicationNo;
		this.appointmentType = appointmentType;
		this.selectedcostCentre = selectedcostCentre;
		this.appointmentStatus =appointmentStatus;
		
	}
	
	
}
