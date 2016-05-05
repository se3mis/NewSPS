package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SPEXPJOB database table.
 * 
 */
@Entity
public class Spexpjob implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpexpjobPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ACC_CREATED_DATE")
	private Date accCreatedDate;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

    @Temporal( TemporalType.DATE)
	@Column(name="EXPORTED_DATE")
	private Date exportedDate;

	@Column(name="EXPORTED_TIME")
	private String exportedTime;

	@Column(name="IS_TRANSFERRED")
	private String isTransferred;

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	@Column(name="SMC_TYPE")
	private String smcType;

    public Spexpjob() {
    }

	public SpexpjobPK getId() {
		return this.id;
	}

	public void setId(SpexpjobPK id) {
		this.id = id;
	}
	
	public Date getAccCreatedDate() {
		return this.accCreatedDate;
	}

	public void setAccCreatedDate(Date accCreatedDate) {
		this.accCreatedDate = accCreatedDate;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getExportedDate() {
		return this.exportedDate;
	}

	public void setExportedDate(Date exportedDate) {
		this.exportedDate = exportedDate;
	}

	public String getExportedTime() {
		return this.exportedTime;
	}

	public void setExportedTime(String exportedTime) {
		this.exportedTime = exportedTime;
	}

	public String getIsTransferred() {
		return this.isTransferred;
	}

	public void setIsTransferred(String isTransferred) {
		this.isTransferred = isTransferred;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getSmcType() {
		return this.smcType;
	}

	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}

}