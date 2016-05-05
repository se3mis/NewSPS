package application.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICATION_REFERENCE database table.
 * 
 */
@Entity
@Table(name="APPLICATION_REFERENCE")
public class ApplicationReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationReferencePK id;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="ID_NO")
	private String idNo;

	private String projectno;

	private String status;

    public ApplicationReference() {
    }

	public ApplicationReferencePK getId() {
		return this.id;
	}

	public void setId(ApplicationReferencePK id) {
		this.id = id;
	}
	
	public String getApplicationNo() {
		return this.applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApplicationReference [id=" + id + ", applicationNo="
				+ applicationNo + ", idNo=" + idNo + ", projectno=" + projectno
				+ ", status=" + status + "]";
	}
	
	

}