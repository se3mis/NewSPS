package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTEDY database table.
 * 
 */
@Embeddable
public class SpestedyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="APPOINTMENT_ID")
	private String appointmentId;

    public SpestedyPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getAppointmentId() {
		return this.appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestedyPK)) {
			return false;
		}
		SpestedyPK castOther = (SpestedyPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.appointmentId.equals(castOther.appointmentId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.appointmentId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpestedyPK [deptId=" + deptId + ", appointmentId="
				+ appointmentId + "]";
	}
	
	
}