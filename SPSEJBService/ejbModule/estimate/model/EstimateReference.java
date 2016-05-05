package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICATION_REFERENCE database table.
 * 
 */
@Entity
@Table(name="ESTIMATE_REFERENCEBS")
public class EstimateReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstimateReferencePK id;
	
	
	@Column(name="ID_NO")
	private String idNo;

	private String projectno;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_BY")
	private String entryBy;
	
	@Column(name="FILE_REF")
	private String fileReference;

	public EstimateReference() {
    }

	public EstimateReferencePK getId() {
		return this.id;
	}

	public void setId(EstimateReferencePK id) {
		this.id = id;
	}
	
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getProjectno() {
		return this.projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EstimateReference [id=" + id + ", standardEstimateNo="
				+ id.getStandardEstimateNo() + ", projectno=" + projectno
				+ ", status=" + status + "]";
	}
	
	

}