package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APPESTLIM database table.
 * 
 */
@Embeddable
public class AppestlimPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="APPROVAL_TYPE")
	private String approvalType;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="USER_LEVEL")
	private String userLevel;

	@Column(name="LIMIT_FROM")
	private long limitFrom;

	@Column(name="LIMIT_TO")
	private long limitTo;

	@Column(name="STATUS_FROM")
	private long statusFrom;

	@Column(name="STATUS_TO")
	private long statusTo;

    public AppestlimPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getApprovalType() {
		return this.approvalType;
	}
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	public String getApplicationType() {
		return this.applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getUserLevel() {
		return this.userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public long getLimitFrom() {
		return this.limitFrom;
	}
	public void setLimitFrom(long limitFrom) {
		this.limitFrom = limitFrom;
	}
	public long getLimitTo() {
		return this.limitTo;
	}
	public void setLimitTo(long limitTo) {
		this.limitTo = limitTo;
	}
	public long getStatusFrom() {
		return this.statusFrom;
	}
	public void setStatusFrom(long statusFrom) {
		this.statusFrom = statusFrom;
	}
	public long getStatusTo() {
		return this.statusTo;
	}
	public void setStatusTo(long statusTo) {
		this.statusTo = statusTo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AppestlimPK)) {
			return false;
		}
		AppestlimPK castOther = (AppestlimPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.approvalType.equals(castOther.approvalType)
			&& this.applicationType.equals(castOther.applicationType)
			&& this.userLevel.equals(castOther.userLevel)
			&& (this.limitFrom == castOther.limitFrom)
			&& (this.limitTo == castOther.limitTo)
			&& (this.statusFrom == castOther.statusFrom)
			&& (this.statusTo == castOther.statusTo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.approvalType.hashCode();
		hash = hash * prime + this.applicationType.hashCode();
		hash = hash * prime + this.userLevel.hashCode();
		hash = hash * prime + ((int) (this.limitFrom ^ (this.limitFrom >>> 32)));
		hash = hash * prime + ((int) (this.limitTo ^ (this.limitTo >>> 32)));
		hash = hash * prime + ((int) (this.statusFrom ^ (this.statusFrom >>> 32)));
		hash = hash * prime + ((int) (this.statusTo ^ (this.statusTo >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "AppestlimPK [deptId=" + deptId + ", approvalType="
				+ approvalType + ", applicationType=" + applicationType
				+ ", userLevel=" + userLevel + ", limitFrom=" + limitFrom
				+ ", limitTo=" + limitTo + ", statusFrom=" + statusFrom
				+ ", statusTo=" + statusTo + "]";
	}
	
	
}