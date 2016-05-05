package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPEXPHST database table.
 * 
 */
@Entity
public class Spexphst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFERENCE_NO")
	private String referenceNo;

	private String acknowledge;

	@Column(name="DEPT_ID")
	private String deptId;

    @Temporal( TemporalType.DATE)
	@Column(name="EXPORTED_DATE")
	private Date exportedDate;

	@Column(name="EXPORTED_TIME")
	private String exportedTime;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="FILE_SIZE")
	private BigDecimal fileSize;

	@Column(name="NO_OF_JOBS")
	private BigDecimal noOfJobs;

    public Spexphst() {
    }

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getAcknowledge() {
		return this.acknowledge;
	}

	public void setAcknowledge(String acknowledge) {
		this.acknowledge = acknowledge;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BigDecimal getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public BigDecimal getNoOfJobs() {
		return this.noOfJobs;
	}

	public void setNoOfJobs(BigDecimal noOfJobs) {
		this.noOfJobs = noOfJobs;
	}

}