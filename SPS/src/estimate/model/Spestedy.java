package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SPESTEDY database table.
 * 
 */
@Entity
public class Spestedy implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestedyPK id;

	@Column(name="ALLOCATED_BY")
	private String allocatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ALLOCATED_DATE")
	private Date allocatedDate;

	@Column(name="ALLOCATED_TIME")
	private String allocatedTime;

	@Column(name="ALLOCATED_TO")
	private String allocatedTo;

	@Column(name="APPOINMENT_TYPE")
	private String appoinmentType;

    @Temporal( TemporalType.DATE)
	@Column(name="APPOINTMENT_DATE")
	private Date appointmentDate;

	private String description;

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	private String status;

	private String suburb;

	@Column(name="TIME_SESSION")
	private String timeSession;

    public Spestedy() {
    }

	public SpestedyPK getId() {
		return this.id;
	}

	public void setId(SpestedyPK id) {
		this.id = id;
	}
	
	public String getAllocatedBy() {
		return this.allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public Date getAllocatedDate() {
		return this.allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public String getAllocatedTime() {
		return this.allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getAllocatedTo() {
		return this.allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	public String getAppoinmentType() {
		return this.appoinmentType;
	}

	public void setAppoinmentType(String appoinmentType) {
		this.appoinmentType = appoinmentType;
	}

	public Date getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuburb() {
		return this.suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getTimeSession() {
		return this.timeSession;
	}

	public void setTimeSession(String timeSession) {
		this.timeSession = timeSession;
	}

	@Override
	public String toString() {
		return "Spestedy [id=" + id + ", allocatedBy=" + allocatedBy
				+ ", allocatedDate=" + allocatedDate + ", allocatedTime="
				+ allocatedTime + ", allocatedTo=" + allocatedTo
				+ ", appoinmentType=" + appoinmentType + ", appointmentDate="
				+ appointmentDate + ", description=" + description
				+ ", referenceNo=" + referenceNo + ", status=" + status
				+ ", suburb=" + suburb + ", timeSession=" + timeSession + "]";
	}

}