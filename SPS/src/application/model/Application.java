package application.model;

import java.io.Serializable;
import javax.persistence.*;

import util.common.ApplicationType;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the APPLICATIONS database table.
 * 
 */
@Entity
@Table(name="APPLICATIONS")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="ALLOCATED_BY")
	private String allocatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ALLOCATED_DATE")
	private Date allocatedDate;

	@Column(name="ALLOCATED_TIME")
	private String allocatedTime;

	@Column(name="ALLOCATED_TO")
	private String allocatedTo;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="APPLICATION_SUB_TYPE")
	private String applicationSubType;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="CONFIRMED_BY")
	private String confirmedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CONFIRMED_DATE")
	private Date confirmedDate;

	@Column(name="CONFIRMED_TIME")
	private String confirmedTime;

	private String description;

	@Column(name="DISCONNECTED_WITHIN")
	private BigDecimal disconnectedWithin;

	private BigDecimal duration;

	@Column(name="DURATION_IN_DAYS")
	private BigDecimal durationInDays;

	@Column(name="DURATION_TYPE")
	private String durationType;

	@Column(name="FINALIZED_WITHIN")
	private BigDecimal finalizedWithin;

    @Temporal( TemporalType.DATE)
	@Column(name="FROM_DATE")
	private Date fromDate;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="IS_LOAN_APP")
	private String isLoanApp;

	@Column(name="IS_VISITNG_NEEDED")
	private String isVisitngNeeded;

	@Column(name="PREPARED_BY")
	private String preparedBy;

	private String status;

    @Temporal( TemporalType.DATE)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

    @Temporal( TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date toDate;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	//private String applicationTypeDes;
	
    public Application() {
    }

	public ApplicationPK getId() {
		return this.id;
	}

	public void setId(ApplicationPK id) {
		this.id = id;
	}
	
	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	/*public String getApplicationTypeDes() {
		return applicationTypeDes;
	}

	public void setApplicationTypeDes(String applicationTypeDes) {
		this.applicationTypeDes = applicationTypeDes;
	}*/

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
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

	public String getApplicationNo() {
		return this.applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApplicationSubType() {
		return this.applicationSubType;
	}

	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
	}

	public String getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
		
		/*if(applicationType.equalsIgnoreCase("BS")){
			setApplicationTypeDes(ApplicationType.BULK_SUPPLY_DESC);
		}else if(applicationType.equalsIgnoreCase("LN")){
			setApplicationTypeDes(ApplicationType.LAND_DESC);
		}else if(applicationType.equalsIgnoreCase("PL")){
			setApplicationTypeDes(ApplicationType.PLANNING_DESC);
		}else if(applicationType.equalsIgnoreCase("RE")){
			setApplicationTypeDes(ApplicationType.RURAL_ELECTRIFICATION_DESC);
		}else if(applicationType.equalsIgnoreCase("RI")){
			setApplicationTypeDes(ApplicationType.RURAL_ELECTRIFICATION_UVA_UDANAYA_DESC);
		} */
	}

	public String getConfirmedBy() {
		return this.confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public Date getConfirmedDate() {
		return this.confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getConfirmedTime() {
		return this.confirmedTime;
	}

	public void setConfirmedTime(String confirmedTime) {
		this.confirmedTime = confirmedTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDisconnectedWithin() {
		return this.disconnectedWithin;
	}

	public void setDisconnectedWithin(BigDecimal disconnectedWithin) {
		this.disconnectedWithin = disconnectedWithin;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getDurationInDays() {
		return this.durationInDays;
	}

	public void setDurationInDays(BigDecimal durationInDays) {
		this.durationInDays = durationInDays;
	}

	public String getDurationType() {
		return this.durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public BigDecimal getFinalizedWithin() {
		return this.finalizedWithin;
	}

	public void setFinalizedWithin(BigDecimal finalizedWithin) {
		this.finalizedWithin = finalizedWithin;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIsLoanApp() {
		return this.isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
	}

	public String getIsVisitngNeeded() {
		return this.isVisitngNeeded;
	}

	public void setIsVisitngNeeded(String isVisitngNeeded) {
		this.isVisitngNeeded = isVisitngNeeded;
	}

	public String getPreparedBy() {
		return this.preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", addDate=" + addDate + ", addTime="
				+ addTime + ", addUser=" + addUser + ", allocatedBy="
				+ allocatedBy + ", allocatedDate=" + allocatedDate
				+ ", allocatedTime=" + allocatedTime + ", allocatedTo="
				+ allocatedTo + ", applicationNo=" + applicationNo
				+ ", applicationSubType=" + applicationSubType
				+ ", applicationType=" + applicationType + ", confirmedBy="
				+ confirmedBy + ", confirmedDate=" + confirmedDate
				+ ", confirmedTime=" + confirmedTime + ", description="
				+ description + ", disconnectedWithin=" + disconnectedWithin
				+ ", duration=" + duration + ", durationInDays="
				+ durationInDays + ", durationType=" + durationType
				+ ", finalizedWithin=" + finalizedWithin + ", fromDate="
				+ fromDate + ", idNo=" + idNo + ", isLoanApp=" + isLoanApp
				+ ", isVisitngNeeded=" + isVisitngNeeded + ", preparedBy="
				+ preparedBy + ", status=" + status + ", submitDate="
				+ submitDate + ", toDate=" + toDate + ", updDate=" + updDate
				+ ", updTime=" + updTime + ", updUser=" + updUser + "]";
	}

}