package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPESTCND database table.
 * 
 */
@Entity
public class Spestcnd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestcndPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ALLOCATED_DATE")
	private Date allocatedDate;

	@Column(name="ALLOCATED_TIME")
	private String allocatedTime;

	@Column(name="ALLOCATED_USER")
	private String allocatedUser;

	private BigDecimal amount;

	@Column(name="CONSUMER_NAME")
	private String consumerName;

	@Column(name="ERROR_MSG")
	private String errorMsg;

    @Temporal( TemporalType.DATE)
	@Column(name="FINISHED_DATE")
	private Date finishedDate;

	@Column(name="FINISHED_TIME")
	private String finishedTime;

	@Column(name="FINISHED_USER")
	private String finishedUser;

	@Column(name="IS_EXPORTED")
	private String isExported;

	@Column(name="SEAL_NO")
	private String sealNo;

	private String status;

    public Spestcnd() {
    }

	public SpestcndPK getId() {
		return this.id;
	}

	public void setId(SpestcndPK id) {
		this.id = id;
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

	public String getAllocatedUser() {
		return this.allocatedUser;
	}

	public void setAllocatedUser(String allocatedUser) {
		this.allocatedUser = allocatedUser;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getConsumerName() {
		return this.consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getFinishedDate() {
		return this.finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getFinishedTime() {
		return this.finishedTime;
	}

	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getFinishedUser() {
		return this.finishedUser;
	}

	public void setFinishedUser(String finishedUser) {
		this.finishedUser = finishedUser;
	}

	public String getIsExported() {
		return this.isExported;
	}

	public void setIsExported(String isExported) {
		this.isExported = isExported;
	}

	public String getSealNo() {
		return this.sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Spestcnd [id=" + id + ", allocatedDate=" + allocatedDate
				+ ", allocatedTime=" + allocatedTime + ", allocatedUser="
				+ allocatedUser + ", amount=" + amount + ", consumerName="
				+ consumerName + ", errorMsg=" + errorMsg + ", finishedDate="
				+ finishedDate + ", finishedTime=" + finishedTime
				+ ", finishedUser=" + finishedUser + ", isExported="
				+ isExported + ", sealNo=" + sealNo + ", status=" + status
				+ "]";
	}

}